/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.serviceticket;

import dao.CustomerDAO;
import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
public class SPCreateServiceTicketServlet extends HttpServlet {

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
        CustomerDAO d =new CustomerDAO();
        List<Customer> customers = d.showAllCustomer();
        request.setAttribute("customerList", customers);
        request.getRequestDispatcher("SalePersonServlet?action=editST").forward(request, response);
        
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
        String dateReceiveStr = request.getParameter("dateReceive");
        String dateReturnStr = request.getParameter("dateReturn");
        String custId = request.getParameter("custId");
        String carId = request.getParameter("carId");

        Date dateReceived = null;
        Date dateReturned = null;

        try {
            LocalDate receivedLocalDate = (dateReceiveStr == null || dateReceiveStr.isEmpty())
                    ? LocalDate.now()
                    : LocalDate.parse(dateReceiveStr.trim());

            LocalDate returnedLocalDate = (dateReturnStr == null || dateReturnStr.isEmpty())
                    ? receivedLocalDate.plusDays(7)
                    : LocalDate.parse(dateReturnStr.trim());

            if (!returnedLocalDate.isAfter(receivedLocalDate)) {
                request.setAttribute("msg", "Ngày trả phải sau ngày nhận.");
                request.getRequestDispatcher("SalePersonServlet?action=manageST").forward(request, response);
                return;
            }

            dateReceived = Date.valueOf(receivedLocalDate);
            dateReturned = Date.valueOf(returnedLocalDate);

            ServiceTicketDAO d = new ServiceTicketDAO();
            boolean success = d.createServiceTicket(dateReceived, dateReturned, custId, carId);

            if (!success) {
                request.setAttribute("msg", "Không thể tạo vé dịch vụ.");
                request.getRequestDispatcher("SalePersonServlet?action=manageST").forward(request, response);
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Có lỗi xảy ra: " + e.getMessage());
        }
        response.sendRedirect("SalePersonServlet?action=showST");

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
