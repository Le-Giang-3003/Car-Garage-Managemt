<%-- 
    Document   : createServicePage
    Created on : Mar 6, 2025, 10:09:34 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create Service</title>
        <link rel="stylesheet" href="css/create-service.css"> <!-- Kết nối file CSS -->
    </head>
    <body>
        <!-- Navigation -->
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Create Service</h1>

            <!-- Hiển thị thông báo lỗi hoặc thành công -->
            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>

            <c:if test="${not empty createService}">
                <p class="success-message">${createService}</p>
            </c:if>

            <!-- Form tạo dịch vụ -->
            <form action="MechanicServlet" accept-charset="utf-8" class="create-form">
                <label>Service Name:</label>
                <input type="text" name="txtname" placeholder="Enter service name" required/>
                <label>Hourly Rate:</label>
                <input type="text" name="hourlyrate" placeholder="Enter hourly rate" required/>
                <p><input type="hidden" name="status" value="Active" ></p>
                <input type="hidden" name="action" value="createService">
                <input type="submit" value="Create">
            </form>
        </div>
    </body>
</html>

