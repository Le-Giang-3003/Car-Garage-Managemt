<%-- 
    Document   : SPUpdateCarPage
    Created on : Mar 14, 2025, 11:07:54 AM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Car Information</title>
        <link rel="stylesheet" href="css/update-car-and-part.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Update Car Information</h1>
            <c:if test="${not empty updateCar}">
                <c:set var="car" value="${updateCar}" />
                <form action="SPUpdateCarServlet" method="POST" accept-charset="utf-8">
                    <input type="hidden" name="carID" value="${car.carID}">
                    <label>Serial Number:</label>
                    <input type="text" name="serialNumber" placeholder="Serial Number" value="${car.serialNumber}" readonly><br>
                    <label>Model:</label>
                    <input type="text" name="model" placeholder="Model" value="${car.model}"><br>
                    <label>Colour:</label>
                    <input type="text" name="colour" placeholder="Colour" value="${car.colour}"><br>
                    <label>Year:</label>
                    <input type="number" name="year" placeholder="Year" min="1990" max="2025" value="${car.year}"><br>
                    <label>Price:</label>
                    <input type="text" name="price" placeholder="Price" value="${car.price}"><br>
                    <input type="submit" value="Update">
                </form>
            </c:if>
            <p>${msg}</p>
            <a class="btn" href="SalePersonServlet?action=showCar">Back</a>
        </div>
    </body>
</html>


