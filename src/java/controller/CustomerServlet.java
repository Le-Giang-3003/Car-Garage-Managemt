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
public class CustomerServlet extends HttpServlet {

    private static final String CUSTOMER_DASHBOARD = "giang/customerDashBoard.jsp";
    private static final String SHOW_SERVICE_TICKET_SERVLET = "ViewServiceTicketServlet";
    private static final String VIEW_SERVICE_TICKET_PAGE = "giang/viewServiceTicketPage.jsp";
    private static final String VIEW_SERVICE_TICKET_DETAIL_PAGE = "giang/viewServiceTicketDetail.jsp";
    private static final String VIEW_INVOICE_SERVLET = "ViewInvoiceServlet";
    private static final String CHANGE_PROFILE_PAGE = "phong/changeProfilePage.jsp";
    private static final String SERVICE_TICKET_DETAIL_SERVLET = "ServiceTicketDetailServlet";
    private static final String LOG_OUT_SERVLET = "LogoutServlet";

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            request.setAttribute("loginError", "Unauthorized access! Please log in.");
            request.getRequestDispatcher("giang/loginPage.jsp").forward(request, response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = "home";
            try {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "home";
                }
                switch (action) {
                    case "home":
                        url = CUSTOMER_DASHBOARD;
                        break;
                    case "showServiceTicket":
                        url = SHOW_SERVICE_TICKET_SERVLET;
                        break;
                    case "viewServiceTicket":
                        url = VIEW_SERVICE_TICKET_PAGE;
                        break;
                    case "viewServiceTicketDetail":
                        url = VIEW_SERVICE_TICKET_DETAIL_PAGE;
                        break;
                    case "showInvoice":
                        url = VIEW_INVOICE_SERVLET;
                        break;
                    case "changeProfile":
                        url = CHANGE_PROFILE_PAGE;
                        break;
                    case "serviceTicketDetail":
                        url = SERVICE_TICKET_DETAIL_SERVLET;
                        break;
                    case "logout":
                        url = LOG_OUT_SERVLET;
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
