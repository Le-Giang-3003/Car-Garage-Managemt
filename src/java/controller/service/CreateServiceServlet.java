/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.service;

import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Service;

/**
 *
 * @author legiang300304
 */
public class CreateServiceServlet extends HttpServlet {

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

            try {
                request.setCharacterEncoding("utf-8");
                String name = request.getParameter("txtname".trim());
                BigDecimal rate = new BigDecimal(request.getParameter("hourlyrate").trim());
                String status = request.getParameter("status");
                
                ServiceDAO d = new ServiceDAO();
                String generatedId = d.getNextId();
                if (name == null || name.trim().isEmpty()
                        || rate == null) {
                    request.setAttribute("error", "TRY AGAIN");
                    request.getRequestDispatcher("MechanicServlet?action=createForm").forward(request, response);
                    return;
                }

                boolean isDuplicate = d.checkDuplicate(name);
                if (isDuplicate) {
                    request.setAttribute("error", "Service name already exists!");
                    request.getRequestDispatcher("MechanicServlet?action=createForm").forward(request, response);
                    return;
                }

                // Nếu không trùng, tiếp tục tạo dịch vụ
                Service s = new Service(generatedId, name, rate, status);
                boolean isInserted = d.insertServices(s);
                if (isInserted) {
                    request.setAttribute("createService", "Service added successfully! ID: " + generatedId);
                } else {
                    request.setAttribute("error", "TRY AGAIN");
                    return;
                }

                List<Service> services = d.showAllService();
                request.setAttribute("LIST_SERVICE", services);

                response.sendRedirect("MechanicServlet?action=showService");

            } catch (Exception e) {
                e.printStackTrace();
            }
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
