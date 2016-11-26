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

        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select * from voos v, aeroportos a, cidades c1, aeroportos b, cidades c2,classesassento "
                    + "where v.idaerpar = a.IDAER and a.IDCID = c1.IDCID and c1.NMCID = ? and v.IDAERCHE = b.IDAER and b.IDCID = c2.IDCID "
                    + "and c2.NMCID = ? and v.DTPAR = ? and v.DTCHE = ? and idcla = ?");//Consulta de aeroporto A para aeroporto B
                             
            ps.setString(1,request.getParameter("De"));//cidade de saida
            ps.setString(2,request.getParameter("Para"));//cidade de destino
            ps.setString(3,request.getParameter("DataDe"));//data de saida
            ps.setString(4,request.getParameter("DataPara"));//data de chegada
            ps.setString(5,request.getParameter("Categoria"));//Classe
            
            rs =ps.executeQuery();
                        
            ArrayList<EscolhaVoo> listIda = new ArrayList<>();
            while(rs.next()){
                listIda.add(new EscolhaVoo(rs.getString("HRPAR"), rs.getString("HRCHE"), rs.getString("")));
            }          
            
            request.setAttribute("DataIda", request.getParameter("DataDe"));
            request.setAttribute("listIda", listIda);
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/aadb", "root", "root");
            ps = con.prepareStatement("select * from voos v, aeroportos a, cidades c1, aeroportos b, cidades c2,classesassento "
                    + "where v.idaerpar = a.IDAER and a.IDCID = c1.IDCID and c1.NMCID = ? and v.IDAERCHE = b.IDAER and b.IDCID = c2.IDCID "
                    + "and c2.NMCID = ? and v.DTPAR = ? and v.DTCHE = ? and idcla = ?");//Consulta de aeroporto A para aeroporto B
            
            ps.setString(1,request.getParameter("Para"));//cidade de destino
            ps.setString(2,request.getParameter("De"));//cidade de saida            
            ps.setString(3,request.getParameter("DataPara"));//data de chegada
            ps.setString(4,request.getParameter("DataDe"));//data de saida            
            ps.setString(5,request.getParameter("Categoria"));//Classe
            
            rs =ps.executeQuery();
                        
            ArrayList<EscolhaVoo> listVolta = new ArrayList<>();
            while(rs.next()){
                listVolta.add(new EscolhaVoo(rs.getString("HRPAR"), rs.getString("HRCHE"), rs.getString("")));
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
