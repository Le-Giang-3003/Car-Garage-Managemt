<%-- 
    Document   : viewInvoice
    Created on : Mar 14, 2025, 8:50:12 PM
    Author     : legiang300304
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoices</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/view-invoice.css">
    </head>
    <body>
        <div class="header__nav">
            <a href="CustomerServlet?action=home" class="home-link">Home</a>
            <h2>My Invoices</h2>
            <a href="CustomerServlet?action=logout" class="logout-link">Log Out</a>
        </div>
    <c:choose>
        <c:when test="${empty invoices}">
            <p>No invoices found.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Invoice ID</th>
                    <th>Date</th>
                    <th>Sales ID</th>
                    <th>Car ID</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="inv" items="${invoices}">
                    <tr>
                        <td>${inv.invoiceID}</td>
                        <td>${inv.invoiceDate}</td>
                        <td>${inv.salesID}</td>
                        <td>${inv.carID}</td>
                        <td><a href="ViewInvoiceDetailServlet?invoiceID=${inv.invoiceID}" class="action-link">View Details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>

