<%-- 
    Document   : viewServiceTicketDetail
    Created on : Mar 10, 2025, 3:13:53 AM
    Author     : legiang300304
--%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Service Ticket Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/view-service-ticket-detail.css"/> 

    </head>
    <body>
        <nav class="header__nav">
            <a href="CustomerServlet?action=home" class="header_link">Home</a>
            <a href="CustomerServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <h1>Details for Service Ticket: ${serviceTicketID}</h1>

        <c:if test="${empty serviceDetails}">
            <p>No details found for this ticket.</p>
        </c:if>

        <c:if test="${not empty serviceDetails}">
            <div class="container">
                <h1>Service Ticket Detail</h1>
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Service Name</th>
                                <th>Mechanic Name</th>
                                <th>Hours</th>
                                <th>Rate (per hour)</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="detail" items="${serviceDetails}">
                                <tr>
                                    <td>${detail.serviceName}</td>
                                    <td>${detail.mechanicName}</td>
                                    <td>${detail.hours}</td>
                                    <td>${detail.rate}</td>
                                    <td>${detail.hours * detail.rate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
    </body>
</html>


