/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ClassesHib.Usuarios;
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
public class Cartao extends HttpServlet {

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
            rs.next();
            
            int idreserva = rs.getInt("idReservas");
            
            //insert into reservas values ('id da reserva','codigo do checkin (igual ao id da reserva)','id do usuario')
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("insert into reservas values (?,?,?)");

            Usuarios usu = (Usuarios) request.getSession().getAttribute("usuarioLogado");
            
            ps.setString(1,Integer.toString(idreserva));
            ps.setString(2,Integer.toString(idreserva));
            ps.setString(3,usu.getIdusu());

            ps.executeUpdate();
            
            
            int numAdultos = Integer.parseInt((String)this.getServletConfig().getServletContext().getAttribute("numAdultos"));
            int numCriancas = Integer.parseInt((String)this.getServletConfig().getServletContext().getAttribute("numCriancas"));
            String idVooIda  = (String)this.getServletConfig().getServletContext().getAttribute("idVooIda");        
            String idVooVolta  = (String)this.getServletConfig().getServletContext().getAttribute("idVooVolta");
            String valor = (String)this.getServletConfig().getServletContext().getAttribute("precoTotal");
            
            for (int i = 0; i < numAdultos+numCriancas; i++) {
                //insert into assentoscomprados values ('id da reserva (igual ao de cima)', 'id do assento comprado', 'id do voo', valor pago na passagem)
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
                ps = con.prepareStatement("insert into assentoscomprados values (?,?,?,?)");
              
                System.out.println(request.getParameter("assento"+i));

                ps.setString(1,Integer.toString(idreserva));
                ps.setString(2,request.getParameter("assento"+i)); 
                ps.setString(3,idVooIda); 
                ps.setString(4,valor); 

                ps.executeUpdate();

                //insert into assentoscomprados values ('id da reserva (igual ao de cima)', 'id do assento comprado', 'id do voo', valor pago na passagem)
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
                ps = con.prepareStatement("insert into assentoscomprados values (?,?,?,?)");

                ps.setString(1,Integer.toString(idreserva));
                ps.setString(2,request.getParameter("assento"+i)); 
                ps.setString(3,idVooVolta); 
                ps.setString(4,valor);  

                ps.executeUpdate();
                
            }
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select max(idres) as idReservas from reservas");
            rs =ps.executeQuery();
            rs.next();
            
            //Retornar o novo idreserva
            request.setAttribute("codRes",rs.getInt("idReservas") );
                        
            
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
