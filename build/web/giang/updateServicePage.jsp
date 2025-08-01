<%-- 
    Document   : updateServicePage
    Created on : Mar 6, 2025, 9:58:23 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UPDATE SERVICE PAGE</title>
        <link rel="stylesheet" href="css/update-service.css">
    </head>
    <body>
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Update Service</h1>

            <c:choose>
                <c:when test="${not empty requestScope.UPDATE_SERVICE_SUCCESSFULL}">
                    <form action="UpdateServiceServlet" method="post" class="update-form">
                        <div class="input-group">
                            <label class="input-label" for="txtid">Service ID</label>
                            <input type="text" id="txtid" name="txtid" value="${requestScope.UPDATE_SERVICE_SUCCESSFULL.serviceID}" readonly>
                        </div>

                        <div class="input-group">
                            <label class="input-label" for="txtname">Service Name</label>
                            <input type="text" id="txtname" name="txtname" value="${requestScope.UPDATE_SERVICE_SUCCESSFULL.serviceName}">
                        </div>

                        <div class="input-group">
                            <label class="input-label" for="hourlyrate">Hourly Rate</label>
                            <input type="text" id="hourlyrate" name="hourlyrate" value="${requestScope.UPDATE_SERVICE_SUCCESSFULL.hourlyRate}">
                        </div>

                        <div class="radio-group">
                            <label class="radio-label">Status:</label>
                            <label class="custom-radio">
                                <input type="radio" name="txtstatus" value="Active" ${requestScope.UPDATE_SERVICE_SUCCESSFULL.status.trim() == 'Active' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Active
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="txtstatus" value="Inactive" ${requestScope.UPDATE_SERVICE_SUCCESSFULL.status.trim() == 'Inactive' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Inactive
                            </label>
                        </div>

                        <input type="submit" value="Update">
                    </form>
                </c:when>
                <c:otherwise>
                    <p class="error-message">Service not found</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

