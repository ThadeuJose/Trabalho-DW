/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    private boolean logou;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        
        if(checaUsuario(usuario, senha))
        {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            logou = true;
            request.setAttribute("logou", logou);            
            rs.forward(request, response);
        }
        else
        {
           out.println("Usuario ou senha incorretos.");
           RequestDispatcher rs = request.getRequestDispatcher("login.html");
           request.setAttribute("logou", logou);
           rs.include(request, response);
        }
        
    }
    
    public boolean checaUsuario(String usuario, String senha){
         boolean st =false;
         //creating connection with the database 
         Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb","root","root");
            PreparedStatement ps =con.prepareStatement("select * from usuarios where emailusu=? and senhausu=?");
         ps.setString(1, usuario);
         ps.setString(2, senha);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         return st;
    }

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
