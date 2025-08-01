/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author legiang300304
 */
public class SalePersonServlet extends HttpServlet {

    private static final String SALE_DASHBOARD = "giang/salePersonDashBoard.jsp";
    private static final String MANAGE_CUSTOMER_PAGE = "giang/manageCustomerPage.jsp";
    private static final String CREATE_CUSTOMER_PAGE = "giang/createCustomerPage.jsp";
    private static final String CREATE_CUSTOMER_SERVLET = "CreateCustomerServlet";
    private static final String LIST_CUSTOMER_SERVLET = "ListCustomerServlet";
    private static final String UPDATE_CUSTOMER_PAGE = "giang/updateCustomerPage.jsp";
    private static final String SEARCH_CUSTOMER_SERVLET = "SearchCustomerServlet";
    private static final String LOGOUT_SERVLET = "LogoutServlet";
    private static final String VIEW_PART_SERVLET = "SPViewPartServlet";
    private static final String MANAGE_PART_PAGE = "duy/SPManagePartPage.jsp";
    private static final String CREATE_PART_PAGE = "duy/SPCreatePartPage.jsp";
    private static final String UPDATE_PART_PAGE = "duy/SPUpdatePartPage.jsp";
    private static final String VIEW_CAR_SERVLET = "SPViewCarServlet";
    private static final String MANAGE_CAR_PAGE = "duy/SPManageCarPage.jsp";
    private static final String CREATE_CAR_PAGE = "duy/SPCreateCarPage.jsp";
    private static final String UPDATE_CAR_PAGE = "duy/SPUpdateCarPage.jsp";
    private static final String VIEW_SERVICE_TICKET_SERVLET = "SPViewServiceTicketServlet";
    private static final String MANAGE_SERVICE_TICKET_PAGE = "duy/SPManageServiceTicketPage.jsp";
    private static final String CREATE_SERVICE_TICKET_SERVLET = "SPCreateServiceTicketServlet";
    private static final String EDIT_SERVICE_TICKET_PAGE = "duy/SPCreateServiceTicketPage.jsp";
    private static final String CREATE_INVOICE_SERVLET = "CreateInvoiceServlet";
    private static final String VIEW_REPORT_PAGE = "phong/viewReport.jsp";

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

        /* TODO output your page here. You may use following sample code. */
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("saleperson") == null) {
            request.setAttribute("loginError", "Unauthorized access! Please log in.");
            request.getRequestDispatcher("giang/loginPage.jsp").forward(request, response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String url = "home";
            try {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "home";
                }
                switch (action) {
                    case "home":
                        url = SALE_DASHBOARD;
                        break;
                    case "manageCust":
                        url = MANAGE_CUSTOMER_PAGE;
                        break;
                    case "createForm":
                        url = CREATE_CUSTOMER_PAGE;
                        break;
                    case "createCust":
                        url = CREATE_CUSTOMER_SERVLET;
                        break;
                    case "showCust":
                        url = LIST_CUSTOMER_SERVLET;
                        break;
                    case "updateCust":
                        url = UPDATE_CUSTOMER_PAGE;
                        break;
                    case "searchCustomer":
                        url = SEARCH_CUSTOMER_SERVLET;
                        break;
                    case "logout":
                        url = LOGOUT_SERVLET;
                        break;
                    case "showPart":
                        url = VIEW_PART_SERVLET;
                        break;
                    case "managePart":
                        url = MANAGE_PART_PAGE;
                        break;
                    case "createPart":
                        url = CREATE_PART_PAGE;
                        break;
                    case "updatePart":
                        url = UPDATE_PART_PAGE;
                        break;
                    case "showCar":
                        url = VIEW_CAR_SERVLET;
                        break;
                    case "manageCar":
                        url = MANAGE_CAR_PAGE;
                        break;
                    case "createCar":
                        url = CREATE_CAR_PAGE;
                        break;
                    case "updateCar":
                        url = UPDATE_CAR_PAGE;
                        break;
                    case "showST":
                        url = VIEW_SERVICE_TICKET_SERVLET;
                        break;
                    case "manageST":
                        url = MANAGE_SERVICE_TICKET_PAGE;
                        break;
                    case "createST":
                        url = CREATE_SERVICE_TICKET_SERVLET;
                        break;
                    case "editST":
                        url = EDIT_SERVICE_TICKET_PAGE;
                        break;
                    case "createInvoice":
                        url = CREATE_INVOICE_SERVLET;
                        break;
                    case "viewReport":
                        url = VIEW_REPORT_PAGE;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
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
