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
public class MechanicServlet extends HttpServlet {

    private static final String MECHANIC_DASHBOARD = "giang/mechanicDashBoard.jsp";
    private static final String LIST_SERVICE_MECHANIC_SERVLET = "ListServiceMechanicServlet";
    private static final String LIST_SERVICE_SERVLET = "ListServiceServlet";
    private static final String MANAGE_SERVICE_PAGE = "giang/manageServicePage.jsp";
    private static final String UPDATE_SERVICE_SERVLET = "UpdateServiceServlet";
    private static final String UPDATE_SERVICE_PAGE = "giang/updateServicePage.jsp";
    private static final String CREATE_SERVICE_PAGE = "giang/createServicePage.jsp";
    private static final String CREATE_SERVICE_SERVLET = "CreateServiceServlet";
    private static final String LIST_SERVICE_TICKET_SERVLET = "ListServiceTicketServlet";
    private static final String MANAGE_SERVICE_TICKET_PAGE = "giang/manageServiceTicketPage.jsp";
    private static final String MANAGE_SERVICE_MECHANIC_PAGE = "giang/manageServiceMechanicPage.jsp";
    private static final String UPDATE_SERVICE_MECHANIC_SERVLET = "UpdateServiceMechanicServlet";
    private static final String UPDATE_SERVICE_MECHANIC_PAGE = "giang/updateServiceMechanic.jsp";
    private static final String SEARCH_SERVICE_TICKET_SERVLET = "SearchServiceTicketServlet";
    private static final String LOGOUT_SERVLET = "LogoutServlet";

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
        if (session == null || session.getAttribute("mechanic") == null) {
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
                        url = MECHANIC_DASHBOARD;
                        break;
                    case "showServiceMechanic":
                        url = LIST_SERVICE_MECHANIC_SERVLET;
                        break;
                    case "showService":
                        url = LIST_SERVICE_SERVLET;
                        break;
                    case "manageService":
                        url = MANAGE_SERVICE_PAGE;
                        break;
                    case "updateSer":
                        url = UPDATE_SERVICE_SERVLET;
                        break;
                    case "updateService":
                        url = UPDATE_SERVICE_PAGE;
                        break;
                    case "createForm":
                        url = CREATE_SERVICE_PAGE;
                        break;
                    case "createService":
                        url = CREATE_SERVICE_SERVLET;
                        break;
                    case "showServiceTicket":
                        url = LIST_SERVICE_TICKET_SERVLET;
                        break;
                    case "manageServiceTicket":
                        url = MANAGE_SERVICE_TICKET_PAGE;
                        break;
                    case "manageServiceMechanic":
                        url = MANAGE_SERVICE_MECHANIC_PAGE;
                        break;
                    case "updateSerMec":
                        url = UPDATE_SERVICE_MECHANIC_SERVLET;
                        break;
                    case "updateServiceMechanic":
                        url = UPDATE_SERVICE_MECHANIC_PAGE;
                        break;
                    case "searchServiceTicket":
                        url = SEARCH_SERVICE_TICKET_SERVLET;
                        break;
                    case "logout":
                        url = LOGOUT_SERVLET;
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
