/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateCustomerServlet extends HttpServlet {

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
            
            request.setCharacterEncoding("utf-8");
            
            String id = request.getParameter("id").trim();
            
            CustomerDAO d = new CustomerDAO();
            Customer c = d.getCustomerById(id);

            if (c != null) {
                request.setAttribute("updateCustomer", c);
                request.getRequestDispatcher("SalePersonServlet?action=updateCust").forward(request, response);
            } else {
                request.setAttribute("error", "CANNOT FOUND CUSTOMER TO UPDATE");
                request.getRequestDispatcher("SalePersonServlet?action=manageCust").forward(request, response);
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
        //processRequest(request, response);

        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("txtid").trim();
        String name = request.getParameter("txtname").trim();
        String phone = request.getParameter("txtphone").trim();
        String sex = request.getParameter("txtsex").trim();
        String address = request.getParameter("txtaddress").trim();
        String status = request.getParameter("txtstatus").trim();

        CustomerDAO d = new CustomerDAO();
        Customer existingCustomer = d.getCustomerById(id);

        if (!phone.equals(existingCustomer.getPhone()) && d.checkDuplicate(phone)) {
            request.setAttribute("error", "Phone number is already in use by another customer.");

            // Load lại danh sách khách hàng
            List<Customer> customerList = d.showAllCustomer();
            request.setAttribute("listCustomer", customerList);

            request.setAttribute("updateCustomer", existingCustomer);
            request.getRequestDispatcher("giang/manageCustomerPage.jsp").forward(request, response);
            return;
        }

        Customer c = new Customer(id, name, phone, sex, address, status);
        d.updateCustomer(c);

        response.sendRedirect("SalePersonServlet?action=showCust");
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
