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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thadeu Jose
 */
@WebServlet(name = "EscAssento", urlPatterns = {"/EscAssento"})
public class EscAssento extends HttpServlet {

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
        
        int numAdultos = Integer.parseInt((String)this.getServletConfig().getServletContext().getAttribute("numAdultos"));
        int numCriancas = Integer.parseInt((String)this.getServletConfig().getServletContext().getAttribute("numCriancas"));
        String classe = (String)this.getServletConfig().getServletContext().getAttribute("classe");
        
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select idass, nmass from assentos where idvoo = ? and idclass = ? and idpass is null;");
//            
//            ps.setString(1,request.getParameter("De"));//cidade de saida
//            ps.setString(2,classe);//cidade de destino
//            
//            rs =ps.executeQuery();
//                        
//            ArrayList<EscolhaVoo> listIda = new ArrayList<>();
//            while(rs.next()){
//                float preco = Float.parseFloat(rs.getString("preco"));
//                float precoAdultos = numAdultos * preco;
//                float precoCriancas = numCriancas * preco * 0.5f;
//                float pt = precoAdultos + precoCriancas;
//                String precoTotal = ""+pt;
//                //listIda.add(new EscolhaVoo(rs.getString("HRPAR"),rs.getString("HRCHE"), rs.getString("NMCLA"), precoTotal, rs.getString("aeroportoPartida"),rs.getString("aeroportoChegada")));
//            }          
//            
//            request.setAttribute("DataIda", request.getParameter("DataDe"));
//            request.setAttribute("listIda", listIda);
            
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
