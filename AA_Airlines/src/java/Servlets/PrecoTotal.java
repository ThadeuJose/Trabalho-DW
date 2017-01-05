package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/PrecoTotal"})
public class PrecoTotal extends HttpServlet {

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
        
        String vooIda = request.getParameter("vooIda");
        String[] listVooIda = vooIda.split("/");
    
        this.getServletConfig().getServletContext().setAttribute("idVooIda",listVooIda[0]); // add to application context
            
        request.setAttribute("cidadeIdaOri",listVooIda[1]);
        request.setAttribute("cidadeIdaDest",listVooIda[4]);
        request.setAttribute("horaIda",listVooIda[2]+" - "+listVooIda[3]);
        request.setAttribute("precoIda",listVooIda[6]);
        
        String vooVolta = request.getParameter("vooVolta");
        String[] listVooVolta = vooVolta.split("/");
        
        this.getServletConfig().getServletContext().setAttribute("idVooVolta",listVooVolta[0]); // add to application context
        
        request.setAttribute("cidadeVoltaOri",listVooVolta[1]);
        request.setAttribute("cidadeVoltaDest",listVooVolta[4]);
        request.setAttribute("horaVolta",listVooVolta[2]+" - "+listVooVolta[3]);
        request.setAttribute("precoVolta",listVooVolta[6]);
        
        request.setAttribute("precoTotal",Float.parseFloat(listVooIda[6])+Float.parseFloat(listVooVolta[6]));
        
        this.getServletConfig().getServletContext().setAttribute("precoTotal",Float.parseFloat(listVooIda[6])+Float.parseFloat(listVooVolta[6])); // add to application context
        
        RequestDispatcher view = request.getRequestDispatcher("PrecoTotal.jsp");
        view.forward(request, response);
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
