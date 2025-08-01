/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import dao.MechanicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dto.Mechanic;

/**
 *
 * @author legiang300304
 */
public class LoginMechanicServlet extends HttpServlet {

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
            
            String name = request.getParameter("txtname").trim();

            MechanicDAO md = new MechanicDAO();
            Mechanic m = md.checkLogin(name);
            
            if (m == null) {
                request.setAttribute("loginError", "Mechanic not found!");
                request.getRequestDispatcher("LoginServlet?action=loginfailed").forward(request, response);
                return;
            } else {
                HttpSession s = request.getSession(true);
                s.setAttribute("mechanic", m);
                request.getRequestDispatcher("giang/mechanicDashBoard.jsp").forward(request, response);

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
        //processRequest(request, response);
        HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
        if (session == null || session.getAttribute("mechanic") == null) {
            request.setAttribute("loginError", "Unauthorized access! Please log in.");
            request.getRequestDispatcher("giang/loginPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("giang/mechanicDashBoard.jsp");
        }

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
