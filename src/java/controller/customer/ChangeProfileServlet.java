/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dto.Customer;

/**
 *
 * @author legiang300304
 */
public class ChangeProfileServlet extends HttpServlet {

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
            
            HttpSession session = request.getSession();
            Customer cust = (Customer) session.getAttribute("customer");

            if (cust == null) {
                request.setAttribute("loginError", "Đăng nhập đã người đẹp");
                request.getRequestDispatcher("MainServlet?action=loginfailed").forward(request, response);
            }else{
                String custID = cust.getCustID();
                String custName = request.getParameter("custName").trim();
                String phone = request.getParameter("phone").trim();
                String sex = request.getParameter("sex").trim();
                String custAddress = request.getParameter("custAddress").trim();
                CustomerDAO dao = new CustomerDAO();
                if(dao.checkPhone(phone, custID)) {
                    session.setAttribute("message", "Phone number already exists! Please enter another number.");
                    request.getRequestDispatcher("CustomerServlet?action=changeProfile").forward(request, response);
                    return;
                }
                Customer updatedCustomer = new Customer(custID, custName, phone, sex, custAddress, null);
                
                dao.updateCustomer(updatedCustomer);

                session.setAttribute("customer", updatedCustomer);
                session.setAttribute("message", "Profile updated successfully!");
                request.getRequestDispatcher("CustomerServlet?action=changeProfile").forward(request, response);
            
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
