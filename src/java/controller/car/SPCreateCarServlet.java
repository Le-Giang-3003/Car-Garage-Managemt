/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.car;

import dao.CarDAO;
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
public class SPCreateCarServlet extends HttpServlet {

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
        String serialNumber = request.getParameter("serialNumber").trim();
        String model = request.getParameter("model").trim();
        String colour = request.getParameter("colour").trim();
        String yearStr = request.getParameter("year").trim();
        String priceStr = request.getParameter("price").trim();
        if (serialNumber == null || serialNumber.isEmpty()
                || model == null || model.isEmpty() || colour == null || colour.isEmpty() || yearStr == null || yearStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            request.setAttribute("msg", "Vui lòng nhập đủ thông tin xe!");
            request.getRequestDispatcher("SalePersonServlet?action=createCar").forward(request, response);
            return;
        }
        try {
            CarDAO c = new CarDAO();
            if (c.checkCar(serialNumber)) {
                request.setAttribute("msg", "Xe đã tồn tại!!!");
                request.getRequestDispatcher("SalePersonServlet?action=createCar").forward(request, response);
                return;
            }
            int year = Integer.parseInt(yearStr);
            double price = Double.parseDouble(priceStr);
            if (price < 0) {
                request.setAttribute("msg", "Giá xe không được âm");
                request.getRequestDispatcher("SalePersonServlet?action=createCar").forward(request, response);
                return;
            }
            if (c.createCar(serialNumber, model, colour, year, price)) {
                request.setAttribute("msg", "đã thêm mới thành công");
            } else {
                request.setAttribute("msg", "Lỗi khi thêm xe, vui lòng thử lại!");
            }
            request.getRequestDispatcher("SalePersonServlet?action=createCar").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("msg", "Năm phải là số hợp lệ!");
            request.getRequestDispatcher("SalePersonServlet?action=createCar").forward(request, response);
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
