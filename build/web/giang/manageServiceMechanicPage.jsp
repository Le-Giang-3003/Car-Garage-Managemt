<%-- 
    Document   : manageServiceMechanicPage
    Created on : Feb 24, 2025, 5:18:15 AM
    Author     : legiang300304
--%>

<%@page import="java.util.List"%>
<%@page import="dto.ServiceMechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Manage Service Mechanic</title>
        <link rel="stylesheet" href="css/manage-service-mechanic.css"> <!-- Kết nối file CSS -->
    </head>
    <body>
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Manage Service Mechanic</h1>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Service Ticket ID</th>
                            <th>Service ID</th>
                            <th>Mechanic ID</th>
                            <th>Hours</th>
                            <th>Comment</th>
                            <th>Rate</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty LIST_SERVICE_MECHANIC}">
                                <c:forEach var="sm" items="${LIST_SERVICE_MECHANIC}">
                                    <tr>
                                        <td>${sm.serviceTicketID}</td>
                                        <td>${sm.serviceID}</td>
                                        <td>${sm.mechanicID}</td>
                                        <td>${sm.hours}</td>
                                        <td>${sm.comment}</td>
                                        <td>${sm.rate}</td>
                                        <td class="actions">
                                            <a href="MechanicServlet?action=updateSerMec&id=${sm.serviceTicketID}" class="btn-action">Update</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="7" style="text-align:center; color:red;">No service mechanics found</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

