<%-- 
    Document   : SPManagePartPage
    Created on : Mar 14, 2025, 8:09:42 AM
    Author     : legiang300304
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Part"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Car Parts</title>
        <link rel="stylesheet" href="css/manage-car-and-part.css">
    </head>
    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <div class="container">
            <h1>Part List</h1>
            <div class="search-form">
                <form action="SPViewPartServlet" accept-charset="utf-8" class="form-inline">
                    <input type="text" name="search" placeholder="Enter part name..." value="${param.search}">
                    <button type="submit">Search</button>
                </form>
            </div>
            <c:if test="${not empty message}">
                <p class="message">${message}</p>
                <c:remove var="message" scope="session" />
            </c:if>

            <c:if test="${not empty list}">
                <table>
                    <tr>
                        <th>Part ID</th>
                        <th>Part Name</th>
                        <th>Purchase Price</th>
                        <th>Retail Price</th>
                        <th>Deleted</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="p" items="${list}">
                        <tr>
                            <td>${p.partID}</td>
                            <td>${p.partName}</td>
                            <td>${p.purPrice} VND</td>
                            <td>${p.retPrice} VND</td>
                            <td>${p.deleted}</td>
                            <td class="action-btn">
                                <a href="SPUpdatePartServlet?partID=${p.partID}" class="btn btn-update">Update</a>
                                <form action="SPDeletePartServlet" method="POST">
                                    <input type="hidden" name="partID" value="${p.partID}">
                                    <button type="submit" class="btn btn-delete" onclick="return confirm('Bạn có chắc muốn xóa không?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <a class="create-link" href="SalePersonServlet?action=createPart">Add</a>
        </div>
    </body>
</html>


