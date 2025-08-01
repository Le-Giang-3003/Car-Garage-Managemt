<%-- 
    Document   : loginPage
    Created on : Feb 18, 2025, 3:14:22 PM
    Author     : legiang300304
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css"/>
        <title>Login</title>
    </head>
    <body>
        <div class="form-container">
            <h1>Choose Your Role</h1>
            <c:if test="${not empty requestScope.loginError}">
                <div class="error-message">${requestScope.loginError}</div>
                <c:remove var="loginError" scope="request"/>
            </c:if>
            <form action="LoginServlet" method="post">
                <button type="submit" name="action" value="logCust">Customer</button>
                <button type="submit" name="action" value="logSale">Sale Person</button>
                <button type="submit" name="action" value="logMec">Mechanic</button>
            </form>
        </div>
    </body>
</html>


