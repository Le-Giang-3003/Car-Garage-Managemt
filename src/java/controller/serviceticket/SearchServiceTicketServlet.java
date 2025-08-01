/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.serviceticket;

import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.ServiceTicket;

/**
 *
 * @author legiang300304
 */
public class SearchServiceTicketServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            request.removeAttribute("LIST_SERVICE_TICKET");
            
            String custID = request.getParameter("custID".trim());
            String carID = request.getParameter("carID".trim());
            String dateReceivedStr = request.getParameter("dateReceived".trim());

            // Chuyển dateReceived từ String sang java.sql.Date
            Date dateReceived = null;
            if (dateReceivedStr != null && !dateReceivedStr.trim().isEmpty()) {
                try {
                    dateReceived = Date.valueOf(dateReceivedStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format: " + dateReceivedStr);
                }
            }

            // Gọi DAO để search
            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicket> result = dao.searchServiceTickets(custID, carID, dateReceived);

            // Gửi kết quả về JSP
            request.setAttribute("LIST_SERVICE_TICKET", result);
            request.getRequestDispatcher("giang/manageServiceTicketPage.jsp").forward(request, response);
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
