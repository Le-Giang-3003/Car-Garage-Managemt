<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Service Tickets</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/view-service-ticket.css"/>
    </head>
    <body>
        <c:if test="${empty serviceTickets}">
            <p>No service tickets found.</p>
        </c:if>
        <nav class="header__nav">
            <a href="CustomerServlet?action=home" class="header_link">Home</a>
            <a href="CustomerServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Your Service Tickets</h1>
            <c:if test="${not empty serviceTickets}">

                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Service Ticket ID</th>
                                <th>Date Received</th>
                                <th>Date Returned</th>
                                <th>Car ID</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ticket" items="${serviceTickets}">
                                <tr>
                                    <td>${ticket.serviceTicketID}</td>
                                    <td>${ticket.dateReceived}</td>
                                    <td>${ticket.dateReturned}</td>
                                    <td>${ticket.carID}</td>
                                    <td>
                                        <a href="ServiceTicketDetailServlet?id=${ticket.serviceTicketID}" class="btn btn-primary">View Details</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>

