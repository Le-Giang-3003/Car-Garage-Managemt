<%-- 
    Document   : manageServiceTicketPage
    Created on : Mar 7, 2025, 12:00:57 AM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/manage-service-ticket.css" />
    </head>
    <body>
        <!-- Header -->
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <!-- Search form -->
        <div class="container">
            <h1>Search Service Ticket</h1>

            <!-- Search form -->
            <form action="MechanicServlet" class="search-form">
                <input type="text" id="custID" name="custID" placeholder="Enter customer ID">
                <input type="text" id="carID" name="carID" placeholder="Enter car ID">
                <input type="date" id="dateReceived" name="dateReceived">
                <input type="hidden" name="action" value="searchServiceTicket">
                <button type="submit">Search</button>
            </form>

            <!-- Table -->
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Service Ticket ID</th>
                            <th>Date Received</th>
                            <th>Date Returned</th>
                            <th>Customer ID</th>
                            <th>Car ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty LIST_SERVICE_TICKET}">
                                <c:forEach var="serviceticket" items="${LIST_SERVICE_TICKET}">
                                    <tr>
                                        <td>${serviceticket.serviceTicketID}</td>
                                        <td>${serviceticket.dateReceived}</td>
                                        <td>${serviceticket.dateReturned}</td>
                                        <td>${serviceticket.custID}</td>
                                        <td>${serviceticket.carID}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" style="text-align:center; color:red;">No service tickets found</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>


    </body>
</html>
