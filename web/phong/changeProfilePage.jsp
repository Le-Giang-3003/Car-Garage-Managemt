<%-- 
    Document   : changProfilePage
    Created on : Mar 14, 2025, 8:24:18 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/change-profile.css">
        <script>
            function confirmUpdate(event) {
                event.preventDefault();
                let confirmAction = confirm("Do you want to change your profile?");
                if (confirmAction) {
                    document.getElementById("profileForm").submit();
                }
            }
        </script>
    </head>
    <body>
        <div class="header__nav">
            <a href="CustomerServlet?action=home" class="home-link">Home</a>
            <h2>Update Profile</h2>
            <a href="CustomerServlet?action=logout" class="logout-link">Log Out</a>
        </div>

        <div class="container">
            <c:choose>
                <c:when test="${empty sessionScope.customer}">
                    <c:redirect url="MainServlet?action=loginfailed"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty sessionScope.message}">
                        <p class="error">${sessionScope.message}</p>
                        <c:remove var="message" scope="session"/>
                    </c:if>

                    <form id="profileForm" action="ChangeProfileServlet" method="post">
                        <input type="hidden" name="custID" value="${sessionScope.customer.custID}"/>

                        <label for="custName">Name:</label>
                        <input type="text" id="custName" name="custName" class="input-field" value="${sessionScope.customer.custName}" required/>

                        <label for="phone">Phone:</label>
                        <input type="text" id="phone" name="phone" class="input-field" value="${sessionScope.customer.phone}" required/>

                        <label for="sex">Sex:</label>
                        <select id="sex" name="sex" class="input-field">
                            <option value="M" ${sessionScope.customer.sex eq 'M' ? 'selected' : ''}>Male</option>
                            <option value="F" ${sessionScope.customer.sex eq 'F' ? 'selected' : ''}>Female</option>
                        </select>

                        <label for="custAddress">Address:</label>
                        <input type="text" id="custAddress" name="custAddress" class="input-field" value="${sessionScope.customer.cusAddress}" required/>

                        <input type="submit" value="Update" onclick="confirmUpdate(event)"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

