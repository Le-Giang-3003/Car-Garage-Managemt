<%-- 
    Document   : manageServiceTicketPage
    Created on : Mar 13, 2025, 10:54:08 AM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Service Tickets</title>
        <link rel="stylesheet" href="css/manage-st.css"/>
    </head>
    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">

            <h1>Service Ticket List</h1>
            <c:if test="${not empty msg}">
                <p class="message">${msg}</p>
            </c:if>
            <c:if test="${not empty tickets}">
                <div class="table-wrapper">
                    <table>
                        <tr>
                            <th>Service Ticket ID</th>
                            <th>Date Received</th>
                            <th>Date Returned</th>
                            <th>Customer ID</th>
                            <th>Car ID</th>
                        </tr>
                        <c:forEach var="t" items="${tickets}">
                            <tr>
                                <td>${t.serviceTicketID} </td>
                                <td>${t.dateReceived} </td>
                                <td>${t.dateReturned} </td>
                                <td>${t.custID}</td>
                                <td>${t.carID}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <a class="btn" href="SalePersonServlet?action=createST">Add</a>
        </div>
    </body>
</html>


