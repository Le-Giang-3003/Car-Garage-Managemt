<%-- 
    Document   : updateServiceMechanic
    Created on : Feb 24, 2025, 7:39:32 PM
    Author     : legiang300304
--%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Service Mechanic</title>
        <link rel="stylesheet" href="css/update-service-mechanic.css"/>
    </head>
    <body>
        <nav class="header__nav">
            <a href="MechanicServlet?action=home" class="header_link">Home</a>
            <a href="MechanicServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <h1>Update Service Mechanic</h1>

            <c:choose>
                <c:when test="${not empty updateServiceMechanic}">
                    <form action="UpdateServiceMechanicServlet" method="post" accept-charset="utf-8" class="update-form">
                        <label>Service Ticket ID:</label>
                        <input type="text" name="txtserticid" value="${updateServiceMechanic.serviceTicketID}" readonly/>

                        <label>Service ID:</label>
                        <input type="text" name="txtserid" value="${updateServiceMechanic.serviceID}" readonly/>

                        <label>Mechanic ID:</label>
                        <input type="text" name="txtmecid" value="${updateServiceMechanic.mechanicID}" readonly/>

                        <label>Hours:</label>
                        <input type="text" name="txthour" value="${updateServiceMechanic.hours}"/>

                        <label>Comment:</label>
                        <input type="text" name="txtcom" value="${updateServiceMechanic.comment}"/>

                        <label>Rate:</label>
                        <input type="text" name="txtrate" value="${updateServiceMechanic.rate}"/>

                        <input type="submit" value="Update">
                    </form>
                </c:when>

                <c:otherwise>
                    <p style="color:red; text-align:center;">Service mechanic not found.</p>
                </c:otherwise>
            </c:choose>
        </div>
        
    </body>
</html>

