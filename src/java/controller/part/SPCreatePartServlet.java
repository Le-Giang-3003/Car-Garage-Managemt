/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.part;

import dao.PartDAO;
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
public class SPCreatePartServlet extends HttpServlet {

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
            String partName = request.getParameter("txtname").trim();
            String purpriceStr = request.getParameter("purprice").trim();
            String retpriceStr = request.getParameter("retprice").trim();

            if (partName == null || partName.isEmpty()
                    || purpriceStr == null || purpriceStr.isEmpty()
                    || retpriceStr == null || retpriceStr.isEmpty()) {
                request.setAttribute("msg", "Vui lòng nhập đầy đủ thông tin!");
                request.getRequestDispatcher("SalePersonServlet?action=createPart").forward(request, response);
                return;
            }

            try {
                PartDAO d = new PartDAO();
                if (d.checkExistence(partName)) {
                    request.setAttribute("msg", "Phụ kiện đã tồn tại");
                    request.getRequestDispatcher("SalePersonServlet?action=createPart").forward(request, response);
                } else {
                    double purprice = Double.parseDouble(purpriceStr);
                    double retprice = Double.parseDouble(retpriceStr);
                    if (purprice < 0 || retprice < 0) {
                        request.setAttribute("msg", "Giá không thể âm!");
                        request.getRequestDispatcher("SalePersonServlet?action=createPart").forward(request, response);
                        return;
                    }
                    boolean kq = d.insertPart(partName, purprice, retprice);

                    if (kq) {
                        request.setAttribute("msg", "Đã thành công!");
                    } else {
                        request.setAttribute("msg", "Không thành công!");
                    }
                    request.getRequestDispatcher("SalePersonServlet?action=createPart").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("msg", "Giá phải là số hợp lệ!");
                request.getRequestDispatcher("SalePersonServlet?action=createPart").forward(request, response);
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
