<%-- 
    Document   : viewReport
    Created on : Mar 14, 2025, 8:47:59 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/view-report.css">
        <script>
            function showReport() {
                var reportType = document.getElementById("reportType").value;
                if (reportType) {
                    window.location.href = "ViewReportServlet?reportType=" + reportType;
                }
            }
        </script>
    </head>
    <body>
        <div class="header__nav">
            <div class="header__left">
                <a href="SalePersonServlet?action=home">Home</a>
            </div>
            <h2>Report</h2>
            <a href="SalePersonServlet?action=logout" class="header__right">Log Out</a>
        </div>

        <div class="report-container">
            <div class="report-dropdown">
                <label for="reportType">Select Report:</label>
                <select id="reportType" onchange="showReport()">
                    <option value="">-- Choose Report --</option>
                    <option value="carsSold" ${reportType == 'carsSold' ? 'selected' : ''}>Cars Sold by Year</option>
                    <option value="revenue" ${reportType == 'revenue' ? 'selected' : ''}>Revenue by Year</option>
                    <option value="bestCars" ${reportType == 'bestCars' ? 'selected' : ''}>Best-Selling Cars</option>
                    <option value="bestParts" ${reportType == 'bestParts' ? 'selected' : ''}>Most Used Parts</option>
                    <option value="topMechanics" ${reportType == 'topMechanics' ? 'selected' : ''}>Top 3 Mechanics</option>
                </select>
            </div>

            <div class="report-table">
                <c:if test="${not empty reportType}">
                    <h3>
                        <c:choose>
                            <c:when test="${reportType == 'carsSold'}">Cars Sold by Year</c:when>
                            <c:when test="${reportType == 'revenue'}">Revenue by Year</c:when>
                            <c:when test="${reportType == 'bestCars'}">Best-Selling Cars</c:when>
                            <c:when test="${reportType == 'bestParts'}">Most Used Parts</c:when>
                            <c:when test="${reportType == 'topMechanics'}">Top 3 Mechanics</c:when>
                        </c:choose>
                    </h3>

                    <c:choose>
                        <c:when test="${not empty reportData}">
                            <table>
                                <tr>
                                    <c:forEach var="column" items="${reportHeader}">
                                        <th>${column}</th>
                                        </c:forEach>
                                </tr>
                                <c:forEach var="row" items="${reportData}">
                                    <tr>
                                        <c:forEach var="col" items="${row}">
                                            <td>${col}</td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>No data available.</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </body>
</html>


