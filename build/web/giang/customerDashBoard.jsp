<%-- 
    Document   : home
    Created on : Feb 18, 2025, 1:48:48 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Customer Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/dashboard.css">
    </head>

    <body>
        <nav class="header__nav">
            <a href="CustomerServlet?action=home" class="header_link">Home</a>
            <a href="CustomerServlet?action=logout" class="header_link">Log Out</a>
        </nav>



        <div class="container-fluid">
            <div class="row">
                <nav class="col-lg-3 sidebar p-4">
                    <ul class="list-unstyled">
                        <li><a href="CustomerServlet?action=showServiceTicket">View Service Ticket</a></li>
                        <li><a href="CustomerServlet?action=showInvoice">View Invoice</a></li>
                        <li><a href="CustomerServlet?action=changeProfile">Change profile</a></li>
                    </ul>
                </nav>

                <main class="col-lg-9 p-4">
                    <div class="dashboard-header">
                        <h1>Customer Dashboard</h1>
                    </div>

                    <div class="main-content mt-4">
                        <h2>Hello, ${sessionScope.customer.custName}</h2>
                        <p>Welcome to your dashboard</p>
                    </div>
                </main>
            </div>
        </div>
        <footer class="footer">
            <p>&copy; Lê Giang - Lê Trần Bảo Duy - Đỗ Hoàng Quốc Phong.</p>
        </footer> 
    </body>
</html>
