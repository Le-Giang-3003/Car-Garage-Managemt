<%-- 
    Document   : SPManageCarPage
    Created on : Mar 14, 2025, 11:06:32 AM
    Author     : legiang300304
--%>

<%@page import="dto.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Cars</title>
        <link rel="stylesheet" href="css/manage-car-and-part.css">
    </head>
    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <div class="container">
            <h1>Car List</h1>
            <div class="search-form">
                <form action="SPViewCarServlet" method="GET"class="form-inline">
                    <input type="text" name="serialNumber" placeholder="Enter serial number" value="${param.serialNumber}">
                    <input type="text" name="model" placeholder="Enter model" value="${param.model}">
                    <input type="number" name="year" min="1900" max="2025" placeholder="Enter year" value="${param.year}">
                    <button type="submit">Search</button>
                </form>
            </div>
            <c:if test="${not empty msg}">
                <p class="message">${msg}</p>
                <c:remove var="msg" scope="session" />
            </c:if>
            <c:if test="${not empty listcar}">
                <table>
                    <tr>
                        <th>Car ID</th>
                        <th>Serial number</th>
                        <th>Model</th>
                        <th>Colour</th>
                        <th>Year</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="c" items="${listcar}">
                        <tr>
                            <td>${c.carID}</td>
                            <td>${c.serialNumber}</td>
                            <td>${c.model}</td>
                            <td>${c.colour}</td>
                            <td>${c.year}</td>
                            <td>$${c.price} </td>
                            <td class="action-btn">
                                <a href="SPUpdateCarServlet?carID=${c.carID}" class="btn btn-update">Update</a>
                                <form action="SPDeleteCarServlet" method="POST">
                                    <input type="hidden" name="carID" value="${c.carID}">
                                    <button type="submit" class="btn btn-delete" onclick="return confirm('Bạn có chắc muốn xóa không?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <a class="create-link" href="SalePersonServlet?action=createCar">Add</a>
        </div>
    </body>
</html>
