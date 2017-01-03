/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ClassesHib.Passageiros;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.mapping.Collection;

/**
 *
 * @author Incubadora 04
 */
@WebServlet(name = "MudaAssentoServlet", urlPatterns = {"/MudaAssentoServlet"})
public class MudaAssentoServlet extends HttpServlet {    
    
    private boolean temRepetida;
    private ArrayList<Passageiros> listaPas;
    private ArrayList<String> assentosIda;
    private ArrayList<String> assentosVolta;
    private String vooIda;
    private String vooVolta;
    private int tam;
    private ArrayList<String> opcoesIda;
    private ArrayList<String> opcoesVolta;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{      
        temRepetida = false;               
        listaPas = (ArrayList<Passageiros>) request.getSession().getAttribute("listaPassageiros");
        assentosIda = (ArrayList<String>) request.getSession().getAttribute("assentosIda");
        assentosVolta = (ArrayList<String>) request.getSession().getAttribute("assentosVolta");
        vooIda = (String) request.getSession().getAttribute("vooIdaCheckin");
        vooVolta = (String) request.getSession().getAttribute("vooVoltaCheckin");
        tam = listaPas.size();
        
        opcoesIda = new ArrayList<String>();
        opcoesVolta = new ArrayList<String>();  
        ArrayList<String> aux = new ArrayList<String>();
        
        for (int i = 0; i < tam; i++) {
            opcoesIda.add(request.getParameter("modAssIda"+i));            
        }
        if(verificaDuplicidade(opcoesIda)){
            temRepetida = true;
        }
        
        for (int i = 0; i < tam; i++) {
            opcoesVolta.add(request.getParameter("modAssVolta"+i));            
        }
        if(verificaDuplicidade(opcoesVolta)){
            temRepetida = true;
        }        
        System.out.println(temRepetida);
        
        if(!temRepetida){           
            guardarNoDB(request);
            recuperaAssentos(request);
            RequestDispatcher rs = request.getRequestDispatcher("CartaoEmbarque.jsp");
            rs.forward(request, response);
        }else{
            RequestDispatcher rs = request.getRequestDispatcher("MudaAssento.jsp");
            rs.forward(request, response);
        }     
    }    
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean verificaDuplicidade(ArrayList<String> opcoes) {
        for (int i = 0; i < opcoes.size(); i++) {
            for (int j = i+1; j < opcoes.size(); j++) {
                if(opcoes.get(i).equals(opcoes.get(j))){
                    if(!opcoes.get(i).equals("NAOMUDOU")) return true;
                }                
            }            
        }
        return false;
    }

    private void guardarNoDB(HttpServletRequest request) {
        
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;      
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            
            //atualiza assentos do voo de ida
            String codCheckIn = (String) request.getSession().getAttribute("codCheckIn");
            
            for (int i = 0; i < listaPas.size(); i++) {
                //atualizando banco para voo ida
                String id = listaPas.get(i).getIdpas();
                if(!opcoesIda.get(i).equals("NAOMUDOU")){
                    ps = con.prepareStatement("update assentos set idpas = '' where idvoo = ? and idpas = ? ");
                    ps.setString(1,vooIda);
                    ps.setString(2,id);
                    ps.executeUpdate();
                
                    ps = con.prepareStatement("update assentos set idpas = ? where idvoo = ? and nmass = ? ");
                    ps.setString(1,id);
                    ps.setString(2,vooIda);
                    ps.setString(3,opcoesIda.get(i));
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("update assentoscomprados set idass = (select idass from assentos where nmass = ? and idvoo = ?) where idvoo = ? and idass = (select idass from assentos where nmass = ? and idvoo = ?)");
                    ps.setString(1,opcoesIda.get(i));
                    ps.setString(2,vooIda);
                    ps.setString(3,vooIda);
                    ps.setString(4,assentosIda.get(i));
                    ps.setString(5,vooIda); 
                    ps.executeUpdate();
                }
                
                if(!opcoesVolta.get(i).equals("NAOMUDOU")){
                    //atualizando banco para voo volta
                    ps = con.prepareStatement("update assentos set idpas = '' where idvoo = ? and idpas = ? ");
                    ps.setString(1,vooVolta);
                    ps.setString(2,id);
                    ps.executeUpdate();
                
                    ps = con.prepareStatement("update assentos set idpas = ? where idvoo = ? and nmass = ? ");
                    ps.setString(1,id);
                    ps.setString(2,vooVolta);
                    ps.setString(3,opcoesVolta.get(i));
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("update assentoscomprados set idass = (select idass from assentos where nmass = ? and idvoo = ?) where idvoo = ? and idass = (select idass from assentos where nmass = ? and idvoo = ?)");
                    ps.setString(1,opcoesVolta.get(i));
                    ps.setString(2,vooVolta);
                    ps.setString(3,vooVolta);
                    ps.setString(4,assentosVolta.get(i));
                    ps.setString(5,vooVolta); 
                    ps.executeUpdate();
                }               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recuperaAssentos(HttpServletRequest request){
        assentosIda.clear();
        assentosVolta.clear();
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
                                                            "and s.IDVOO = ? order by p.IDPAS");
                ps.setString(1, (String)request.getSession().getAttribute("codCheckIn"));
                ps.setString(2, (String)request.getSession().getAttribute("vooIdaCheckin"));
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
                                                            "and s.IDVOO = ? order by p.IDPAS");
                ps2.setString(1, (String)request.getSession().getAttribute("codCheckIn"));
                ps2.setString(2, (String)request.getSession().getAttribute("vooVoltaCheckin"));
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
        
}
    
    
