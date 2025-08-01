<%-- 
    Document   : createInvoicePage
    Created on : Mar 14, 2025, 8:28:31 PM
    Author     : legiang300304
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/create-invoice.css">
    </head>
    <body>
        <div class="header__nav">
            <a href="SalePersonServlet?action=home" class="nav-left">Home</a>
            <h2 class="nav-center">Create New Invoice</h2>
            <a href="LogoutServlet" class="nav-right">Log Out</a>
        </div>

        <div class="container">
            <form action="CreateInvoiceServlet" method="post">
                <input type="hidden" name="invoiceID" value="${nextInvoiceID}" readonly>
                <input type="hidden" name="invoiceDate" value="${currentDate}" readonly>
                <input type="hidden" name="salesID" value="${salesID}" readonly>

                <label>Customer:</label>
                <select name="custID" required>
                    <option value="" disabled selected>Select a customer</option>
                    <c:forEach var="cust" items="${customers}">
                        <option value="${cust.custID}">${cust.custID} - ${cust.custName} - ${cust.phone}</option>
                    </c:forEach>
                </select>

                <label>Car:</label>
                <select name="carID" required>
                    <option value="" disabled selected>Select a car</option>
                    <c:forEach var="car" items="${cars}">
                        <option value="${car.carID}">${car.carID} - ${car.serialNumber} - ${car.model} - ${car.colour} - ${car.year} </option>
                    </c:forEach>
                </select>

                <button type="submit">Create Invoice</button>
            </form>

            <c:if test="${not empty message}">
                <div class="alert alert-info">${message}</div>
            </c:if>

            <c:if test="${not empty sessionScope.recentInvoices}">
                <h3>Recently Created Invoices</h3>
                <div class="table-container">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Invoice ID</th>
                                <th>Date</th>
                                <th>Sales ID</th>
                                <th>Customer</th>
                                <th>Car</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="invoice" items="${sessionScope.recentInvoices}">
                            <tr>
                                <td>${invoice.invoiceID}</td>
                                <td>${invoice.invoiceDate}</td>
                                <td>${invoice.salesID}</td>
                                <td>${invoice.custID}</td>
                                <td>${invoice.carID}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </body>
</html>





