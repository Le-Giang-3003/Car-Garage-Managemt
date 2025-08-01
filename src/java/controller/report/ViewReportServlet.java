/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.report;

import dao.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dto.SalePerson;

/**
 *
 * @author legiang300304
 */
public class ViewReportServlet extends HttpServlet {

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
            String reportType = request.getParameter("reportType");
            ReportDAO dao = new ReportDAO();
            Map<String, Object> reportData = new HashMap<>();

            switch (reportType) {
                case "carsSold":
                    reportData.put("header", new String[]{"Year", "Cars Sold"});
                    reportData.put("data", dao.getCarsSoldByYear());
                    break;
                case "revenue":
                    reportData.put("header", new String[]{"Year", "Revenue"});
                    reportData.put("data", dao.getRevenueByYear());
                    break;
                case "bestCars":
                    reportData.put("header", new String[]{"Model", "Units Sold"});
                    reportData.put("data", dao.getBestSellingCars());
                    break;
                case "bestParts":
                    reportData.put("header", new String[]{"Part Name", "Usage Count"});
                    reportData.put("data", dao.getBestUsedParts());
                    break;
                case "topMechanics":
                    reportData.put("header", new String[]{"Mechanic Name", "Repairs Count"});
                    reportData.put("data", dao.getTopMechanics());
                    break;
                default:
                    request.setAttribute("error", "Loại báo cáo không hợp lệ.");
                    request.getRequestDispatcher("viewReport.jsp").forward(request, response);
                    return;
            }

            request.setAttribute("reportType", reportType);
            request.setAttribute("reportHeader", reportData.get("header"));
            request.setAttribute("reportData", reportData.get("data"));
            request.getRequestDispatcher("SalePersonServlet?action=viewReport").forward(request, response);
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
