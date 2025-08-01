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
import dto.Car;

/**
 *
 * @author legiang300304
 */
public class SPUpdateCarServlet extends HttpServlet {

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

            String carID = request.getParameter("carID").trim();
            CarDAO d = new CarDAO();
            Car update = d.findCarByID(carID);
            if (update == null) {
                request.setAttribute("message", "Không tìm thấy sản phẩm nào!");
            } else {
                request.setAttribute("updateCar", update);
            }
            request.getRequestDispatcher("SalePersonServlet?action=updateCar").forward(request, response);
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
        String carID = request.getParameter("carID").trim();
        String serialNumber = request.getParameter("serialNumber").trim().toUpperCase();
        String model = request.getParameter("model").trim();
        String colour = request.getParameter("colour").trim();
        String yearStr = request.getParameter("year").trim();
        String priceStr = request.getParameter("price").trim();
        if (carID == null || serialNumber == null || model == null || colour == null || yearStr == null
                || carID.isEmpty() || serialNumber.isEmpty() || model.isEmpty() || colour.isEmpty() || yearStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            request.setAttribute("msg", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("SalePersonServlet?action=updateCar").forward(request, response);
            return;
        }

        try {
            CarDAO c = new CarDAO();
            int year = Integer.parseInt(yearStr);
            double price = Double.parseDouble(priceStr);
            if (price < 0) {
                request.setAttribute("msg", "Giá không thể âm");
                request.getRequestDispatcher("SalePersonServlet?action=updateCar").forward(request, response);
                return;
            }
            boolean updated = c.updateCar(new Car(carID, serialNumber, model, colour, year, price));
            if (updated) {
                request.setAttribute("msg", "Cập nhật thành công");
            } else {
                request.setAttribute("msg", "Cập nhật thất bại");
            }
            request.getRequestDispatcher("SalePersonServlet?action=updateCar").forward(request, response);
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
