/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.part;

import dao.PartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Part;

/**
 *
 * @author Admin
 */
public class SPUpdatePartServlet extends HttpServlet {

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
            String partID = request.getParameter("partID").trim();
            PartDAO dao = new PartDAO();
            ArrayList<Part> list = dao.findPart(partID, true);

            if (list.isEmpty()) {
                request.setAttribute("message", "Không tìm thấy sản phẩm nào!");
            } else {
                request.setAttribute("updateList", list);
            }
            request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
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
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("txtid").trim();
        String name = request.getParameter("txtname").trim();
        String purPriceStr = request.getParameter("purprice").trim();
        String retPriceStr = request.getParameter("retprice").trim();
        String deletedStr = request.getParameter("deleted").trim();
        if (id == null || name == null || purPriceStr == null || retPriceStr == null
                || id.isEmpty() || name.isEmpty() || purPriceStr.isEmpty() || retPriceStr.isEmpty()
                || deletedStr==null || deletedStr.isEmpty()) {
            request.setAttribute("message", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
            return;
        }

        try {
            PartDAO d = new PartDAO();
            ArrayList<Part> oldPart = d.findPart(id, true);
            if (oldPart.isEmpty()) {
                request.setAttribute("message", "Không tìm thấy phụ kiện!");
                request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
                return;
            }
            if (!oldPart.get(0).getPartName().equalsIgnoreCase(name) && d.checkExistence(name)) {
                request.setAttribute("message", "Tên phụ kiện đã tồn tại");
                request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
            } else {
                double purPrice = Double.parseDouble(purPriceStr);
                double retPrice = Double.parseDouble(retPriceStr);
                boolean deleted = Boolean.parseBoolean(deletedStr);
                if (purPrice < 0 || retPrice < 0) {
                    request.setAttribute("message", "Giá không thể âm!");
                    request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
                    return;
                }
                Part part = new Part(id, name, purPrice, retPrice, deleted);
                boolean updated = d.updatePart(part);
                if (updated) {
                    request.setAttribute("message", "Cập nhật thành công!");
                } else {
                    request.setAttribute("message", "Cập nhật thất bại!");
                }
                request.getRequestDispatcher("SalePersonServlet?action=updatePart").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
