<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
         <title>Create New Service Ticket</title>
        <link rel="stylesheet" href="css/create-st.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Create New Service Ticket</h1>
            ${msg}
            <form action="SPGetListCarServlet" method="get">
                <label>Select Customer:</label><br>
                <select name="custId" required>
                    <option value="">-- Select Customer --</option>
                    <c:forEach var="customer" items="${customerList}">
                        <option value="${customer.custID}" ${customer.custID == selectedCustId ? 'selected' : ''}>
                            ${customer.custID} - ${customer.custName}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="Get Car List">
            </form>

            <c:if test="${not empty carList}">
                <form action="SPCreateServiceTicketServlet" method="post">
                    <input type="hidden" name="custId" value="${selectedCustId}">

                    <label>Car:</label><br>
                    <select name="carId" required>
                        <option value="">-- Select Car --</option>
                        <c:forEach var="car" items="${carList}">
                            <option value="${car[0]}">${car[0]} - ${car[1]}</option>
                        </c:forEach>
                    </select><br>

                    <label>Date Received:</label><br>
                    <input type="date" name="dateReceive"><br>

                    <label>Date Returned:</label><br>
                    <input type="date" name="dateReturn"><br>

                    <input type="submit" value="Create">
                </form>
            </c:if>
            <a class="btn-home" href="SalePersonServlet?action=showST">Back</a>
        </div>
    </body>
</html>
