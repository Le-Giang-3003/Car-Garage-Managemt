<%-- 
    Document   : viewInvoiceDetail
    Created on : Mar 14, 2025, 8:50:56 PM
    Author     : legiang300304
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/view-invoice-detail.css">
    </head>
    <body>
        <div class="header__nav">
            <div class="header__left">
                <a href="CustomerServlet?action=home" class="home-link">Home</a>
                <a href="CustomerServlet?action=showInvoice" class="back-invoice-link">My Invoices</a>
            </div>
            <h2>Invoice Details</h2>
            <a href="CustomerServlet?action=logout" class="header__right">Log Out</a>
        </div>

        <div class="container mt-5">
            <c:if test="${empty requestScope.invoiceDetail}">
                <p class="text-danger">Invoice not found.</p>
            </c:if>

            <c:if test="${not empty requestScope.invoiceDetail}">
                <table class="table table-bordered">
                    <tr><th>Invoice ID</th><td>${invoiceDetail.invoiceID}</td></tr>
                    <tr><th>Invoice Date</th><td>${invoiceDetail.invoiceDate}</td></tr>
                    <tr><th>Sales ID</th><td>${invoiceDetail.salesID}</td></tr>
                    <tr><th>Sales Name</th><td>${invoiceDetail.salesName}</td></tr>
                    <tr><th>Car ID</th><td>${invoiceDetail.carID}</td></tr>
                    <tr><th>Serial Number</th><td>${invoiceDetail.serialNumber}</td></tr>
                    <tr><th>Model</th><td>${invoiceDetail.model}</td></tr>
                    <tr><th>Color</th><td>${invoiceDetail.colour}</td></tr>
                    <tr><th>Year</th><td>${invoiceDetail.year}</td></tr>
                </table>
            </c:if>
        </div>
    </body>
</html>



