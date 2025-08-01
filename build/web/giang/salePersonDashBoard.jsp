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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sale Person Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/dashboard.css">
    </head>

    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-lg-3 sidebar p-4">
                    <ul class="list-unstyled">
                        <li><a href="SalePersonServlet?action=showCust">Manage Customers</a></li>
                        <li><a href="SalePersonServlet?action=showCar">Manage Cars</a></li>
                        <li><a href="SalePersonServlet?action=showST">Manage Service Tickets</a></li>
                        <li><a href="SalePersonServlet?action=showPart">Manage Parts</a></li>
                        <li><a href="SalePersonServlet?action=createInvoice">Create Invoices</a></li>
                        <li><a href="SalePersonServlet?action=viewReport">View Reports</a></li>
                    </ul>
                </nav>

                <main class="col-lg-9 p-4">
                    <div class="dashboard-header">
                        <h1>Salesperson Dashboard</h1>
                    </div>

                    <div class="main-content mt-4">
                        <h2>Hello, ${sessionScope.saleperson.name}</h2>
                        <p>Welcome to your dashboard</p>
                    </div>
                </main>
            </div>
        </div>
        <footer class="footer">
            <p>&copy; Lê Giang - Lê Trần Bảo Duy - Đỗ Hoàng Quốc Phong.</p>
        </footer>        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
