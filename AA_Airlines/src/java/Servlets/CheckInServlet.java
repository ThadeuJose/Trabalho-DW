/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ClassesHib.Passageiros;
import ClassesHib.Reservas;
import ClassesHib.Usuarios;
import ClassesHib.Voos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauloRoberto
 */
@WebServlet(name = "CheckInServlet", urlPatterns = {"/CheckInServlet"})
public class CheckInServlet extends HttpServlet {
    
    private ArrayList<Passageiros> listaPas;
    private ArrayList<String> assentosIda;
    private ArrayList<String> assentosVolta;
    private String vooIdaCheckin;   
    private String vooVoltaCheckin;
    private String codCheckIn;
    private Double precoIda;
    private Double precoVolta;
    private Double taxaIda;
    private Double taxaVolta;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        listaPas = new ArrayList<Passageiros>();
        assentosIda = new ArrayList<String>();
        assentosVolta = new ArrayList<String>();
        
        vooIdaCheckin = "";
        vooVoltaCheckin = "";
        request.getSession().setAttribute("listaPassageiros", null);
        request.getSession().setAttribute("vooIdaCheckin", null);
        request.getSession().setAttribute("vooVoltaCheckin", null);
        request.getSession().setAttribute("aerosIdaCheck", null);
        request.getSession().setAttribute("dataIdaCheck", null);
        request.getSession().setAttribute("horaIdaCheck", null);
        request.getSession().setAttribute("aerosVoltaCheck", null);
        request.getSession().setAttribute("dataVoltaCheck", null);
        request.getSession().setAttribute("horaVoltaCheck", null);
        request.getSession().setAttribute("assentosIda", null);
        request.getSession().setAttribute("assentosVolta", null);
        request.getSession().setAttribute("precoIda", null);
        request.getSession().setAttribute("precoVolta", null);
        request.getSession().setAttribute("taxaIda", null);
        request.getSession().setAttribute("taxaVolta", null);
        
        
        codCheckIn = request.getParameter("bilhete");
        
        //testes
        System.out.println(codCheckIn);
        
        if(checaBilhete(codCheckIn)){
            System.out.println("achou o codigo");
            request.getSession().setAttribute("vooIdaCheckin", vooIdaCheckin);
            request.getSession().setAttribute("vooVoltaCheckin", vooVoltaCheckin);            
            recuperaInfosVoo(request);
            recuperaAssentos(request);
            recuperaPreco(request);
            request.getSession().setAttribute("listaPassageiros", listaPas);
            RequestDispatcher rs = request.getRequestDispatcher("MudaAssento.jsp");
            rs.forward(request, response); 
        }else{
            RequestDispatcher rs = request.getRequestDispatcher("checkin.html");
            rs.forward(request, response);  
        }      
        
             
    }
    
    public boolean checaBilhete(String bilhete){
         boolean st =false;
         //creating connection with the database 
         Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
                //primeiro vamos pegar os IDS dos voos de ida e volta
                PreparedStatement ps = con.prepareStatement("select distinct a.IDVOO\n" +
                                                            "from assentoscomprados a, assentos s, passageiros p, usuarios u, reservas r\n" +
                                                            "where a.IDRES = r.IDRES\n" +
                                                            "and a.IDASS = s.IDASS\n" +
                                                            "and p.IDPAS = s.IDPAS\n" +
                                                            "and r.CDCHK = ?");
                ps.setString(1, bilhete);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    vooIdaCheckin = rs.getString("IDVOO");
                }
                if(rs.next()){
                    vooVoltaCheckin = rs.getString("IDVOO");
                    st = true;
                }  
                //agora vamos pegas as infos dos passageiros
                PreparedStatement ps2 =con.prepareStatement("select distinct p.IDPAS, p.NMPAS, p.SNPAS\n" +
                                         "from assentoscomprados a, assentos s, passageiros p, usuarios u, reservas r\n" +
                                         "where a.IDRES = r.IDRES\n" +
                                         "and a.IDASS = s.IDASS\n" +
                                         "and p.IDPAS = s.IDPAS\n" +
                                         "and r.CDCHK = ?");
                ps2.setString(1, bilhete);
         
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){                    
                    Passageiros p = new Passageiros(rs2.getString("IDPAS"), rs2.getString("NMPAS"), rs2.getString("SNPAS"), "1", "", "", 18);
                    listaPas.add(p);
                }
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         return st;
    }   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void recuperaInfosVoo(HttpServletRequest request){
        Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
                //PEGANDO INFOS DA IDA
                PreparedStatement ps = con.prepareStatement("select distinct v.IDAERPAR, par.NMAER as AERPAR, v.IDAERCHE, che.NMAER as AERCHE, v.DTPAR, v.DTCHE, v.HRPAR, v.HRCHE\n" +
                                                            "from reservas r, assentoscomprados a, voos v, aeroportos par, aeroportos che\n" +
                                                            "where r.CDCHK = ?\n" +
                                                            "and r.IDRES = a.IDRES\n" +
                                                            "and a.IDVOO = v.IDVOO\n" +
                                                            "and v.IDAERPAR = par.IDAER\n" +
                                                            "and v.IDAERCHE = che.IDAER\n" +
                                                            "and a.IDVOO = ?");
                ps.setString(1, codCheckIn);
                ps.setString(2, vooIdaCheckin);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String aerosIdaCheck = rs.getString("AERPAR")+"  --  "+rs.getString("AERCHE");
                String dataIdaCheck = rs.getString("DTPAR")+"  --  "+rs.getString("DTCHE");
                String horaIdaCheck = rs.getString("HRPAR")+"  --  "+rs.getString("HRCHE");
                
                //PEGANDO INFOS DA VOLTA
                PreparedStatement ps2 = con.prepareStatement("select distinct v.IDAERPAR, par.NMAER as AERPAR, v.IDAERCHE, che.NMAER as AERCHE, v.DTPAR, v.DTCHE, v.HRPAR, v.HRCHE\n" +
                                                            "from reservas r, assentoscomprados a, voos v, aeroportos par, aeroportos che\n" +
                                                            "where r.CDCHK = ?\n" +
                                                            "and r.IDRES = a.IDRES\n" +
                                                            "and a.IDVOO = v.IDVOO\n" +
                                                            "and v.IDAERPAR = par.IDAER\n" +
                                                            "and v.IDAERCHE = che.IDAER\n" +
                                                            "and a.IDVOO = ?");
                ps2.setString(1, codCheckIn);
                ps2.setString(2, vooVoltaCheckin);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                String aerosVoltaCheck = rs2.getString("AERPAR")+"  --  "+rs2.getString("AERCHE");
                String dataVoltaCheck = rs2.getString("DTPAR")+"  --  "+rs2.getString("DTCHE");
                String horaVoltaCheck = rs2.getString("HRPAR")+"  --  "+rs2.getString("HRCHE");
                
                request.getSession().setAttribute("aerosIdaCheck", aerosIdaCheck);
                request.getSession().setAttribute("dataIdaCheck", dataIdaCheck);
                request.getSession().setAttribute("horaIdaCheck", horaIdaCheck);
                request.getSession().setAttribute("aerosVoltaCheck", aerosVoltaCheck);
                request.getSession().setAttribute("dataVoltaCheck", dataVoltaCheck);
                request.getSession().setAttribute("horaVoltaCheck", horaVoltaCheck);
                
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void recuperaAssentos(HttpServletRequest request){
        
        Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
                //PEGANDO INFOS DA IDA
                PreparedStatement ps = con.prepareStatement("select p.NMPAS, p.SNPAS, p.CPFPAS, p.PSTPAS, p.TELPAS, p.ENDPAS, s.NMASS, s.IDVOO\n" +
                                                            "from assentoscomprados a, assentos s, passageiros p, usuarios u, reservas r\n" +
                                                            "where a.IDRES = r.IDRES\n" +
                                                            "and a.IDASS = s.IDASS\n" +
                                                            "and p.IDPAS = s.IDPAS\n" +
                                                            "and r.CDCHK = ?\n" +
                                                            "and s.IDVOO = ?");
                ps.setString(1, codCheckIn);
                ps.setString(2, vooIdaCheckin);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                 assentosIda.add(rs.getString("NMASS"));
                }                
                //PEGANDO INFOS DA VOLTA
                PreparedStatement ps2 = con.prepareStatement("select p.NMPAS, p.SNPAS, p.CPFPAS, p.PSTPAS, p.TELPAS, p.ENDPAS, s.NMASS, s.IDVOO\n" +
                                                            "from assentoscomprados a, assentos s, passageiros p, usuarios u, reservas r\n" +
                                                            "where a.IDRES = r.IDRES\n" +
                                                            "and a.IDASS = s.IDASS\n" +
                                                            "and p.IDPAS = s.IDPAS\n" +
                                                            "and r.CDCHK = ?\n" +
                                                            "and s.IDVOO = ?");
                ps2.setString(1, codCheckIn);
                ps2.setString(2, vooVoltaCheckin);
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){
                 assentosVolta.add(rs2.getString("NMASS"));
                } 
                request.getSession().setAttribute("assentosIda", assentosIda);
                request.getSession().setAttribute("assentosVolta", assentosVolta);
                
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void recuperaPreco(HttpServletRequest request){
        
        Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
                //PEGANDO preco das pernas
                PreparedStatement ps = con.prepareStatement("select sum(a.VRPAG) as precoPerna\n" +
                                                            "from reservas r, assentoscomprados a, voos v, aeroportos par, aeroportos che\n" +
                                                            "where r.CDCHK = ?\n" +
                                                            "and r.IDRES = a.IDRES\n" +
                                                            "and v.IDVOO = a.IDVOO\n" +
                                                            "and v.IDAERPAR = par.IDAER\n" +
                                                            "and v.IDAERCHE = che.IDAER\n" +
                                                            "group by v.IDVOO");
                ps.setString(1, codCheckIn);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    precoIda = rs.getDouble("precoPerna");
                }
                if(rs.next()){
                    precoVolta = rs.getDouble("precoPerna");
                }
                //PEGANDO taxas
                PreparedStatement ps2 = con.prepareStatement("select distinct v.IDVOO, par.TXEMB + che.TXEMB as taxas\n" +
                                                             "from reservas r, assentoscomprados a, voos v, aeroportos par, aeroportos che\n" +
                                                             "where r.CDCHK = ?\n" +
                                                             "and r.IDRES = a.IDRES\n" +
                                                             "and v.IDVOO = a.IDVOO\n" +
                                                             "and v.IDAERPAR = par.IDAER\n" +
                                                             "and v.IDAERCHE = che.IDAER");
                ps2.setString(1, codCheckIn);
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    taxaIda = rs2.getDouble("taxas");
                }
                if(rs2.next()){
                    taxaVolta = rs2.getDouble("taxas");
                }
                request.getSession().setAttribute("precoIda", precoIda);
                request.getSession().setAttribute("precoVolta", precoVolta);
                request.getSession().setAttribute("taxaIda", taxaIda);
                request.getSession().setAttribute("taxaVolta", taxaVolta);
                
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    
}
