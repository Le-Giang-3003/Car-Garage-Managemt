/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servicemechanic;

import dao.ServiceMechanicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.ServiceMechanic;

/**
 *
 * @author legiang300304
 */
public class UpdateServiceMechanicServlet extends HttpServlet {

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
            String id = request.getParameter("id".trim());

            ServiceMechanicDAO d = new ServiceMechanicDAO();
            ServiceMechanic sm = d.getServiceMechanicByID(id);

            if (sm != null) {
                request.setAttribute("updateServiceMechanic", sm);
                request.getRequestDispatcher("MechanicServlet?action=updateServiceMechanic").forward(request, response);
            } else {
                request.setAttribute("updateFailed", "Service mechanic not found.");
                request.getRequestDispatcher("giang/manageServiceMechanic.jsp").forward(request, response);
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

        String serticid = request.getParameter("txtserticid").trim();
        String serid = request.getParameter("txtserid").trim();
        String mecid = request.getParameter("txtmecid").trim();
        int hour = Integer.parseInt(request.getParameter("txthour").trim());
        String comment = request.getParameter("txtcom").trim();
        BigDecimal rate = new BigDecimal(request.getParameter("txtrate").trim());

        ServiceMechanicDAO d = new ServiceMechanicDAO();
        ServiceMechanic sm = new ServiceMechanic(serticid, serid, mecid, hour, comment, rate);
        d.updateServiceMechanic(sm);

        request.getRequestDispatcher("MechanicServlet?action=showServiceMechanic").forward(request, response);
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
