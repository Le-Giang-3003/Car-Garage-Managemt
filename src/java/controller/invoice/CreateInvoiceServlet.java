/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.invoice;

import dao.CarDAO;
import dao.CustomerDAO;
import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dto.Car;
import dto.Customer;
import dto.Invoice;
import dto.SalePerson;

/**
 *
 * @author legiang300304
 */
public class CreateInvoiceServlet extends HttpServlet {

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

            HttpSession session = request.getSession();
            SalePerson sp = (SalePerson) session.getAttribute("saleperson");

            if (sp == null) {
                request.setAttribute("loginError", "Đăng nhập đã người đẹp");
                request.getRequestDispatcher("MainServlet?action=loginfailed").forward(request, response);
                return;
            }

            InvoiceDAO invoiceDAO = new InvoiceDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            CarDAO carDAO = new CarDAO();

            List<Customer> customers = customerDAO.showAllCustomer();
            List<Car> cars = carDAO.getAllCar();
            String nextInvoiceID = invoiceDAO.getNextInvoiceID();
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            request.setAttribute("salesID", sp.getId());
            request.setAttribute("customers", customers);
            request.setAttribute("cars", cars);
            request.setAttribute("nextInvoiceID", nextInvoiceID);
            request.setAttribute("currentDate", currentDate);

            String carID = request.getParameter("carID");
            String custID = request.getParameter("custID");

            if (carID == null || carID.isEmpty() || custID == null || custID.isEmpty()) {
                request.setAttribute("message", "Please select both a customer and a car.");
            } else {
                Invoice invoice = new Invoice(nextInvoiceID, currentDate, sp.getId(), carID, custID);
                boolean success = invoiceDAO.createInvoice(invoice);

                if (success) {
                    cars = carDAO.getAllCar();
                    request.setAttribute("cars", cars);

                    request.setAttribute("message", "Invoice created successfully!");
                    List<Invoice> recentInvoices = invoiceDAO.getRecentInvoicesBySalesIDAndDate(sp.getId(), currentDate);
                    session.setAttribute("recentInvoices", recentInvoices);
                } else {
                    request.setAttribute("message", "Error! Could not create invoice.");
                }
            }

            request.getRequestDispatcher("phong/createInvoicePage.jsp").forward(request, response);
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
