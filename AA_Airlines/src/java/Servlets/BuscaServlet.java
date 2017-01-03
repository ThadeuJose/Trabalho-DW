/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.EscolhaVoo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thadeu Jose
 */
@WebServlet(name = "BuscaServlet", urlPatterns = {"/BuscaServlet"})
public class BuscaServlet extends HttpServlet {

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
            throws ServletException, IOException {

        System.out.println("Entrou no busca");
        System.out.println(request.getParameter("De"));
        System.out.println(request.getParameter("DataDe"));
        System.out.println(request.getParameter("DataPara"));
        
        this.getServletConfig().getServletContext().setAttribute("numAdultos", request.getParameter("numAdultos")); // add to application context
        this.getServletConfig().getServletContext().setAttribute("numCriancas", request.getParameter("numCriancas")); // add to application context
        this.getServletConfig().getServletContext().setAttribute("classe", request.getParameter("classe")); // add to application context
        
        
        int numAdultos = 0;
        int numCriancas = 0; 
        
        if(!request.getParameter("numAdultos").equals("")){
            numAdultos = Integer.parseInt((String)request.getParameter("numAdultos"));
        }
        if(!request.getParameter("numCriancas").equals("")){
            numCriancas = Integer.parseInt((String)request.getParameter("numCriancas"));
        }
        
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select v.HRPAR, v.HRCHE, ca.NMCLA, a.TXEMB + b.TXEMB + ca.PCCLA as preco, a.NMAER as aeroportoPartida, b.NMAER as aeroportoChegada, v.IDVOO\n" +
                                      "from voos v, aeroportos a, cidades c1, aeroportos b, cidades c2, classesassento ca\n" +
                                      "where v.idaerpar = a.IDAER \n" +
                                      "and a.IDCID = c1.IDCID \n" +
                                      "and c1.NMCID = ?\n" +
                                      "and v.IDAERCHE = b.IDAER \n" +
                                      "and b.IDCID = c2.IDCID \n" +
                                      "and c2.NMCID = ? \n" +
                                      "and v.DTPAR = ? \n" +
                                      "and v.DTCHE = ? " +
                                      "and  ca.IDCLA= ? ");
            //Consulta de aeroporto A para aeroporto B
            //Retorna, nessa ordem: hora de partida, hora de chegada, nome da classe, preco da classe                 
            
            ps.setString(1,request.getParameter("De"));//cidade de saida
            ps.setString(2,request.getParameter("Para"));//cidade de destino
            ps.setString(3,request.getParameter("DataDe"));//data de saida
            ps.setString(4,request.getParameter("DataPara"));//data de chegada
            ps.setString(5,request.getParameter("classe"));//Classe
            
            rs =ps.executeQuery();
                        
            ArrayList<EscolhaVoo> listIda = new ArrayList<>();
            while(rs.next()){
                float preco = Float.parseFloat(rs.getString("preco"));
                float precoAdultos = numAdultos * preco;
                float precoCriancas = numCriancas * preco * 0.5f;
                float pt = precoAdultos + precoCriancas;
                String precoTotal = ""+pt;
                listIda.add(new EscolhaVoo(rs.getString("IDVOO"),rs.getString("HRPAR"),rs.getString("HRCHE"), rs.getString("NMCLA"), precoTotal, rs.getString("aeroportoPartida"),rs.getString("aeroportoChegada")));
            }          
            
            request.setAttribute("DataIda", request.getParameter("DataDe"));
            request.setAttribute("listIda", listIda);
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select v.HRPAR, v.HRCHE, ca.NMCLA, a.TXEMB + b.TXEMB + ca.PCCLA as preco, a.NMAER as aeroportoPartida, b.NMAER as aeroportoChegada, v.IDVOO\n" +
                                      "from voos v, aeroportos a, cidades c1, aeroportos b, cidades c2, classesassento ca\n" +
                                      "where v.idaerpar = a.IDAER \n" +
                                      "and a.IDCID = c1.IDCID \n" +
                                      "and c1.NMCID = ?\n" +
                                      "and v.IDAERCHE = b.IDAER \n" +
                                      "and b.IDCID = c2.IDCID \n" +
                                      "and c2.NMCID = ? \n" +
                                      "and v.DTPAR = ? \n" +
                                      "and v.DTCHE = ?"+
                                      "and  ca.IDCLA= ? ");
            //Consulta de aeroporto A para aeroporto B
            //Retorna, nessa ordem: hora de partida, hora de chegada, nome da classe, preco da classe                 
            
            ps.setString(1,request.getParameter("Para"));//cidade de saida
            ps.setString(2,request.getParameter("De"));//cidade de destino
            ps.setString(3,request.getParameter("DataPara"));//data de saida
            ps.setString(4,request.getParameter("DataDe"));//data de chegada
            ps.setString(5,request.getParameter("classe"));//Classe
            
            rs =ps.executeQuery();
                        
            ArrayList<EscolhaVoo> listVolta = new ArrayList<>();
            while(rs.next()){
                float preco = Float.parseFloat(rs.getString("preco"));
                float precoAdultos = numAdultos * preco;
                float precoCriancas = numCriancas * preco * 0.5f;
                float pt = precoAdultos + precoCriancas;
                String precoTotal = ""+pt;
                listVolta.add(new EscolhaVoo(rs.getString("IDVOO"),rs.getString("HRPAR"),rs.getString("HRCHE"), rs.getString("NMCLA"), precoTotal, rs.getString("aeroportoPartida"),rs.getString("aeroportoChegada")));
            }
            
            request.setAttribute("DataVolta", request.getParameter("DataDe"));
            request.setAttribute("listVolta", listVolta);
            
            RequestDispatcher view = request.getRequestDispatcher("Escolha de Voo.jsp");
            view.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
