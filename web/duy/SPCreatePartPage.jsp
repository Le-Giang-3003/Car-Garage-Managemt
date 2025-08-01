<%-- 
    Document   : SPCreatePartPage
    Created on : Mar 14, 2025, 11:02:38 AM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add new part</title>
        <link rel="stylesheet" href="css/create-car-and-part.css">
    </head>
    <body>
        <div class="container">
            <h1>Information</h1>
            <form action="SPCreatePartServlet" method="post">
                <label for="txtname">Part Name:</label>
                <input type="text" id="txtname" name="txtname" placeholder="Enter part name" required>

                <label for="purprice">Purchase Price:</label>
                <input type="text" id="purprice" name="purprice" placeholder="Enter purchase price" required>

                <label for="retprice">Retail Price:</label>
                <input type="text" id="retprice" name="retprice" placeholder="Enter retail price" required>

                <input type="submit" value="Create">
            </form>
            <p class="result">${msg}</p>
            <a class="btn-home" href="SalePersonServlet?action=showPart">Back</a>
        </div>
    </body>
</html>