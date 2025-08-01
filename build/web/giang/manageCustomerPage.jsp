<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Manage Customer</title>
        <link rel="stylesheet" href="css/manage-customer.css"/>
    </head>
    <body>
        <nav class="header__nav">
            <a href="SalePersonServlet?action=home" class="header_link">Home</a>
            <a href="SalePersonServlet?action=logout" class="header_link">Log Out</a>
        </nav>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <div class="container">
            <div class="search-form">
                <form action="SearchCustomerServlet" accept-charset="utf-8">
                    <input type="text" name="txtname" placeholder="Enter customer name" value="${param.txtname}">
                    <button type="submit">Search</button>
                </form>
                <a href="SalePersonServlet?action=createForm" class="create-link">Create customer</a>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Sex</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty listCustomer}">
                            <c:forEach var="customer" items="${listCustomer}">
                                <tr>
                                    <td>${customer.custID}</td>
                                    <td>${customer.custName}</td>
                                    <td>${customer.phone}</td>
                                    <td>${customer.sex}</td>
                                    <td>${customer.cusAddress}</td>
                                    <td>${customer.status}</td>
                                    <td class="actions">
                                        <a href="UpdateCustomerServlet?id=${customer.custID}">Update</a>
                                        <a href="#" onclick="confirmDelete('${customer.custID}')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6">No customers found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>


        <!-- Delete confirmation script -->
        <script>
            function confirmDelete(custID) {
                if (confirm('Chắc chưa người đẹp ơi?')) {
                    window.location.href = 'DeleteCustomerServlet?id=' + encodeURIComponent(custID);
                }
            }
        </script>
    </body>
</html>
