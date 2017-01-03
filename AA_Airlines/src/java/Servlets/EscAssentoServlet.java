/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thadeu Jose
 */
@WebServlet(name = "EscAssentoServlet", urlPatterns = {"/EscAssentoServlet"})
public class EscAssentoServlet extends HttpServlet {

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
                
        String idvooIda = (String)this.getServletConfig().getServletContext().getAttribute("idvooIda");
        String idvooVolta = (String)this.getServletConfig().getServletContext().getAttribute("idvooVolta");
        String idclasse = (String)this.getServletConfig().getServletContext().getAttribute("classe");
        
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select idass, nmass from assentos where idvoo = ? and idcla = ? and idpas is null");

            ps.setString(1,idvooIda);//cidade de saida
            ps.setString(2,idclasse);//cidade de destino

            rs =ps.executeQuery();

            ArrayList<Integer> listAss = new ArrayList<>();
            while(rs.next()){
                listAss.add(Integer.parseInt(rs.getString("nmass")));
            }            
            request.setAttribute("listAss", listAss);
            
            request.setAttribute("totalAss",numAdultos+numCriancas);
            
            
            RequestDispatcher view = request.getRequestDispatcher("Escolha de Assento.jsp");
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
