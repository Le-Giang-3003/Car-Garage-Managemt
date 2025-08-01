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

/**
 *
 * @author legiang300304
 */
public class MainServlet extends HttpServlet {

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
            String url = "loginPage.jsp";
            try {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "home";
                }
                switch (action) {
                    case "home":
                        url = "giang/loginPage.jsp";
                        break;
                    case "loginMechanic":
                        url = "LoginMechanicServlet";
                        break;
                    case "loginSalePerson":
                        url = "LoginSalePersonServlet";
                        break;
                    case "loginCustomer":
                        url = "LoginCustomerServlet";
                        break;
                    case "saleperson":
                        url = "giang/salePersonDashBoard.jsp";
                        break;
                    case "mechanic":
                        url = "giang/mechanicDashBoard.jsp";
                        break;
                    case "customer":
                        url = "giang/customerDashBoard.jsp";
                        break;
                    case "loginfailed":
                        url = "giang/loginPage.jsp";
                        break;
                    case "Cancel":
                        url = "giang/loginPage.jsp";
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
