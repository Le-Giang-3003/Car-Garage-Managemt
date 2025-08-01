<%-- 
    Document   : mechanicDashBoard
    Created on : Feb 23, 2025, 10:08:15 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/dashboard.css">

    </head>
    <body>
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-lg-3 sidebar p-4">
                    <ul class="list-unstyled">
                        <li><a href="MechanicServlet?action=showServiceTicket"> View service ticket at here</a> </li>
                        <li><a href="MechanicServlet?action=showServiceMechanic"> Update the service ticket </a> </li>
                        <li><a href="MechanicServlet?action=showService"> Manage service at here </a></li>
                    </ul>
                </nav>

                <main class="col-lg-9 p-4">
                    <div class="mechanic-header">
                        <h1>Mechanic Dashboard</h1>
                    </div>

                    <div class="main-content mt-4">
                        <h2>Hello, ${sessionScope.mechanic.mechanicName} </h2>
                        <p>Welcome to your dashboard!</p>
                    </div>
                </main>
            </div>
        </div>
        <footer class="footer">
            <p>&copy; Lê Giang - Lê Trần Bảo Duy - Đỗ Hoàng Quốc Phong.</p>
        </footer> 
    </body>
</html>
