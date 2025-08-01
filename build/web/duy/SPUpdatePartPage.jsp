<%-- 
    Document   : SPUpdatePartPage
    Created on : Mar 14, 2025, 11:04:17 AM
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
        <title>Update Part Information</title>
        <link rel="stylesheet" href="css/update-car-and-part.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Update Part Information</h1>
            <c:if test="${not empty updateList}">
                <c:set var="part" value="${updateList[0]}" />
                <form action="SPUpdatePartServlet" method="POST">
                    <input type="hidden" name="txtid" value="${part.partID}">
                    <label>Part Name:</label>
                    <input type="text" name="txtname" placeholder="Part Name" value="${part.partName}"><br>
                    <label>Purchase Price:</label>
                    <input type="text" name="purprice" placeholder="Purchase Price" value="${part.purPrice}"><br>
                    <label>Retail Price:</label>
                    <input type="text" name="retprice" placeholder="Retail Price" value="${part.retPrice}"><br>
                    <label>Deleted Status:</label>
                    <select name="deleted">
                        <option value="false" ${!part.deleted ? 'selected' : ''}>False</option>
                        <option value="true" ${part.deleted ? 'selected' : ''}>True</option>
                    </select><br>
                    <input type="submit" value="Update">
                </form>
            </c:if>
            <p>${message}</p>
            <a class="btn" href="SalePersonServlet?action=showPart">Back</a>
        </div>
    </body>
</html>


