<%-- 
    Document   : SPCreateCarPage
    Created on : Mar 14, 2025, 11:07:01 AM
    Author     : legiang300304
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Car</title>
        <link rel="stylesheet" href="css/create-car-and-part.css">
    </head>
    <body>
        <div class="container">
            <h1>Information</h1>
            <form action="SPCreateCarServlet" accept-charset="utf-8" method="post">
                <label for="serialNumber">Serial Number:</label>
                <input type="text" id="serialNumber" name="serialNumber" placeholder="Enter serial number" >

                <label for="model">Model:</label>
                <input type="text" id="model" name="model" placeholder="Enter model" >

                <label for="colour">Colour:</label>
                <input type="text" id="colour" name="colour" placeholder="colour" >

                <label for="year">Year:</label>
                <input type="number" id="year" name="year" min="1900" max="2025" >
                
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" placeholder="Enter price" >
                
                <input type="submit" value="Create">
            </form>
            <p class="result">${msg}</p>
            <p><a class="btn btn-home" href="SalePersonServlet?action=showCar">Back</a></p>
        </div>
    </body>

</html>


