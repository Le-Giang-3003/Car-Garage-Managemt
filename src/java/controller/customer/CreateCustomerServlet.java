/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import dao.CustomerDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Customer;

/**
 *
 * @author legiang300304
 */
public class CreateCustomerServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        try {
            // Lấy thông tin từ request
            String name = request.getParameter("txtname").trim();
            String phone = request.getParameter("txtphone").trim();
            String sex = request.getParameter("txtsex").trim();
            String address = request.getParameter("txtaddress").trim();
            String status = request.getParameter("status");

            CustomerDAO d = new CustomerDAO();
            String generatedId = d.getNextId();

            if (name == null || name.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()
                    || sex == null || sex.trim().isEmpty()
                    || address == null || address.trim().isEmpty()) {

                request.setAttribute("ERROR", "Data is invalid");
                request.getRequestDispatcher("SalePersonServlet?action=createForm").forward(request, response);
                return;
            }

            boolean isDuplicate = d.checkDuplicate(phone);
            if (!isDuplicate) {
                Customer c = new Customer(generatedId, name, phone, sex, address, status);
                boolean isInserted = d.insertCustomer(c);
                if (isInserted) {
                    request.setAttribute("SUCCESS", "Customer added successfully! ID: " + generatedId);
                } else {
                    request.setAttribute("ERROR", "Failed to add customer.");
                }
            } else {
                request.setAttribute("ERROR", "Phone number already exists!");
                request.getRequestDispatcher("SalePersonServlet?action=createForm").forward(request, response);
                return;
            }

            List<Customer> customers = d.showAllCustomer();
            request.setAttribute("listCustomer", customers);

            response.sendRedirect("SalePersonServlet?action=showCust");

        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi
            request.setAttribute("ERROR", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("SalePersonServlet?action=createForm").forward(request, response);
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
