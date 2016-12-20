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
@WebServlet(name = "Pagamento", urlPatterns = {"/Pagamento"})
public class Pagamento extends HttpServlet {

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
        
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select max(idres) as idReservas from reservas");
            rs =ps.executeQuery();
            
            int idreserva = rs.getInt("idReservas");
            
            //insert into reservas values ('id da reserva','codigo do checkin (igual ao id da reserva)','id do usuario')
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("insert into reservas values (?,?,?)");

//            ps.setString(1,idreserva);
//            ps.setString(2,idreserva);
//            ps.setString(3,idusuario);

            rs =ps.executeQuery();
            
            //insert into assentoscomprados values ('id da reserva (igual ao de cima)', 'id do assento comprado', 'id do voo', valor pago na passagem)
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("insert into assentoscomprados values (?,?,?,?)");
            
//            ps.setString(1,idreserva);
//            ps.setString(2,idassento); 
//            ps.setString(3,idvoo); Tenho 
//            ps.setString(4,valor); Tenho 

            rs =ps.executeQuery();
            
//            request.setAttribute("listAss", listAss);
                        
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
