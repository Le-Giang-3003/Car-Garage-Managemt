<%-- 
    Document   : createCustomer
    Created on : Feb 18, 2025, 2:21:56 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/create-customer.css"/>
        <title>Customer Form</title>
    </head>
    <body>
        <c:if test="${not empty ERROR}">
            <p style="color: red;">${ERROR}</p>
        </c:if>

        <c:if test="${not empty SUCCESS}">
            <p style="color: green;">${SUCCESS}</p>
        </c:if>

        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>

        <div class="container">
            <form action="SalePersonServlet" accept-charset="utf-8">
                <!-- Name -->
                <div class="input-group">
                    <label class="input-label">Name:</label>
                    <input type="text" name="txtname" placeholder="Customer name" required" required>
                </div>

                <!-- Phone -->
                <div class="input-group">
                    <label class="input-label">Phone:</label>
                    <input type="text" name="txtphone" placeholder="Customer phone" required>
                </div>
                <div class="radio-group">
                    <label class="custom-radio">
                        <input type="radio" name="txtsex" value="M" required>
                        <span class="radio-btn"></span>
                        Male
                    </label>

                    <label class="custom-radio">
                        <input type="radio" name="txtsex" value="F" required>
                        <span class="radio-btn"></span>
                        Female
                    </label>
                </div>

                <div class="input-group">
                    <label class="input-label">Name:</label>
                    <input type="text" name="txtaddress" placeholder="Customer address" required" required>
                </div>

                <p><input type="hidden" name="status" value="Active" ></p>
                <input type="hidden" name="action" value="createCust"/>
                <p><input type="submit" value="Create"></p>
            </form>

            <a href="SalePersonServlet?action=home" class="create-link">Back</a>

        </div>

    </body>
</html>

