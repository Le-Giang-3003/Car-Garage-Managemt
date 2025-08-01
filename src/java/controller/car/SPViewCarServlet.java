/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.car;

import dao.CarDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Car;

/**
 *
 * @author Admin
 */
public class SPViewCarServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        String serialNumber = request.getParameter("serialNumber") != null ? request.getParameter("serialNumber").trim() : "";
        String model = request.getParameter("model") != null ? request.getParameter("model").trim() : "";
        String year = request.getParameter("year") != null ? request.getParameter("year").trim() : "";

        CarDAO d = new CarDAO();
        ArrayList<Car> listcar;

        // Kiểm tra xem có nhập giá trị tìm kiếm hay không
        if (!serialNumber.isEmpty() || !model.isEmpty() || !year.isEmpty()) {
            listcar = d.searchCar(serialNumber, model, year);
        } else {
            listcar = d.getAllCar();
        }

        request.setAttribute("listcar", (listcar != null) ? listcar : new ArrayList<>());
        request.getRequestDispatcher("SalePersonServlet?action=manageCar").forward(request, response);
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
