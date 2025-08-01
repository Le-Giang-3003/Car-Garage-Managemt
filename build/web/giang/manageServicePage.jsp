<%-- 
    Document   : manageServicePage
    Created on : Mar 6, 2025, 9:13:53 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="g" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Manage Service</title>
        <link rel="stylesheet" href="css/manage-service.css"> <!-- Kết nối file CSS -->
    </head>
    <body>
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Manage Services</h1>

            <!-- Create service button -->
            <a href="MechanicServlet?action=createForm" class="create-link">Create Service</a>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Hourly Rate</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:choose>
                            <g:when test="${not empty LIST_SERVICE}">
                                <g:forEach var="service" items="${LIST_SERVICE}">
                                    <tr>
                                        <td>${service.serviceID}</td>
                                        <td>${service.serviceName}</td>
                                        <td>${service.hourlyRate}</td>
                                        <td>${service.status}</td>
                                        <td class="actions">
                                            <a href="MechanicServlet?action=updateSer&id=${service.serviceID}" class="btn-action">Update</a>
                                            <a href="#" onclick="confirmDelete('${service.serviceID}')" class="btn-delete">Delete</a>
                                        </td> 
                                    </tr>
                                </g:forEach>
                            </g:when>
                            <g:otherwise>
                                <tr>
                                    <td colspan="4" style="text-align:center; color:red;">No service found</td>
                                </tr>
                            </g:otherwise>
                        </g:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            function confirmDelete(id) {
                if (confirm('CHẮC CHƯA BẠN ƠI?')) {
                    window.location.href = 'DeleteServiceServlet?id=' + encodeURIComponent(id);
                }
            }
        </script>
    </body>
</html>

