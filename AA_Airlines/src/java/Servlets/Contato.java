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
 * @author PauloRoberto
 */
@WebServlet(name = "Contato", urlPatterns = {"/Contato"})
public class Contato extends HttpServlet {
    
    private ArrayList<Passageiros> listaPas;

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
        response.setContentType("text/html;charset=UTF-8");
        listaPas = (ArrayList<Passageiros>) request.getSession().getAttribute("listaPassageiros");
        
        for (int i = 0; i < listaPas.size(); i++) {
            listaPas.get(i).setTelpas(request.getParameter("Telefone"+(i+1)));
            listaPas.get(i).setEndpas(request.getParameter("Endereco"+(i+1)));            
        }
 
        gravaNoBanco();
        request.getSession().setAttribute("listaPassageiros", listaPas);
        RequestDispatcher rs = request.getRequestDispatcher("CartaoEmbarque.jsp");
        rs.forward(request, response);        
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

    public void gravaNoBanco(){
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");            
            for (int i = 0; i < listaPas.size(); i++) {
                PreparedStatement ps =con.prepareStatement("update passageiros set telpas = ?, endpas = ? where idpas = ? ");
                ps.setString(1, listaPas.get(i).getTelpas());
                ps.setString(2, listaPas.get(i).getEndpas());
                ps.setString(3, listaPas.get(i).getIdpas());
                ps.executeUpdate();
            }            
        }catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
