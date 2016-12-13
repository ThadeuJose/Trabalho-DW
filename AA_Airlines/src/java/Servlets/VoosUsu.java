/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ClassesHib.Passageiros;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauloRoberto
 */
@WebServlet(name = "VoosUsu", urlPatterns = {"/VoosUsu"})
public class VoosUsu extends HttpServlet {

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
        Usuarios usu = (Usuarios) request.getSession().getAttribute("usuarioLogado");
        String idUsu = usu.getIdusu();      
        ArrayList<String> voos = new ArrayList<String>();
        String resp = "";
        request.getSession().setAttribute("listaVoosUsuario",null);
        Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
                PreparedStatement ps = con.prepareStatement("select distinct v.IDVOO, v.IDAERPAR, par.NMAER as AERPAR, v.IDAERCHE, che.NMAER as AERCHE, v.DTPAR, v.DTCHE, v.HRPAR, v.HRCHE, r.CDCHK\n" +
                                                            "from reservas r, assentoscomprados a, voos v, aeroportos par, aeroportos che\n" +
                                                            "where r.IDUSU = ?\n" +
                                                            "and r.IDRES = a.IDRES\n" +
                                                            "and a.IDVOO = v.IDVOO\n" +
                                                            "and v.IDAERPAR = par.IDAER\n" +
                                                            "and v.IDAERCHE = che.IDAER");
                
                ps.setString(1, idUsu);
                ResultSet rs = ps.executeQuery();
                String oldCd = "";
                while(rs.next()){
                    if(!oldCd.equals(rs.getString("CDCHK"))){
                        oldCd = rs.getString("CDCHK");
                        voos.add("Voo: "+rs.getString("IDVOO"));
                        voos.add("Codigo de check-in: "+rs.getString("CDCHK"));
                        voos.add(rs.getString("AERPAR")+"  --  "+rs.getString("AERCHE"));
                        voos.add(rs.getString("DTPAR")+"  --  "+rs.getString("DTCHE"));
                        voos.add(rs.getString("HRPAR")+"  --  "+rs.getString("HRCHE"));
                    }                    
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("listaVoosUsuario",voos);
        if(voos.size() > 0){            
            RequestDispatcher rs = request.getRequestDispatcher("voosUsuario.jsp");
            rs.forward(request, response); 
        }else{
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
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

}
