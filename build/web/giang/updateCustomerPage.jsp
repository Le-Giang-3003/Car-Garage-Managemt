<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Update Customer Page</title>
        <link rel="stylesheet" href="css/update-customer.css">
    </head>
    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <div class="container">

            <c:choose>
                <c:when test="${not empty updateCustomer}">
                    <form action="UpdateCustomerServlet" method="post" accept-charset="utf-8">
                        <!-- ID -->
                        <div class="input-group">
                            <label class="input-label">ID:</label>
                            <input type="text" name="txtid" value="${updateCustomer.custID}" readonly>
                        </div>

                        <!-- Name -->
                        <div class="input-group">
                            <label class="input-label">Name:</label>
                            <input type="text" name="txtname" value="${updateCustomer.custName}" required>
                        </div>

                        <!-- Phone -->
                        <div class="input-group">
                            <label class="input-label">Phone:</label>
                            <input type="text" name="txtphone" value="${updateCustomer.phone}" required>
                        </div>



                        <!-- Sex selection -->
                        <div class="radio-group">
                            <label class="radio-label">Sex:</label>
                            <label class="custom-radio">
                                <input type="radio" name="txtsex" value="M" ${updateCustomer.sex.trim() == 'M' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Male
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="txtsex" value="F" ${updateCustomer.sex.trim() == 'F' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Female
                            </label>
                        </div>

                        <!-- Status selection -->
                        <div class="radio-group">
                            <label class="radio-label">Status:</label>
                            <label class="custom-radio">
                                <input type="radio" name="txtstatus" value="Active" ${updateCustomer.status.trim() == 'Active' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Active
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="txtstatus" value="Inactive" ${updateCustomer.status.trim() == 'Inactive' ? 'checked' : ''}>
                                <span class="radio-btn"></span> Inactive
                            </label>
                        </div>
                        <!-- Address -->
                        <div class="input-group">
                            <label class="input-label">Address:</label>
                            <input type="text" name="txtaddress" value="${updateCustomer.cusAddress}" required>
                        </div>
                        <p><input type="submit" value="Update"></p>
                    </form>
                    <a href="ListCustomerServlet" class="create-link">View Customer List</a>

                </c:when>
                <c:otherwise>
                    <p>Customer not found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
