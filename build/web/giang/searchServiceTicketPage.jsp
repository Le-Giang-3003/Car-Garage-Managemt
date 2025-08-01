<%-- 
    Document   : searchServiceTicketPage
    Created on : Mar 7, 2025, 12:23:49 AM
    Author     : legiang300304
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Search Service Ticket</title>
    </head>
    <body>
        <h1>Search Service Ticket</h1>
        <form action="MechanicServlet">
            <label for="custID">Customer ID:</label>
            <input type="text" id="custID" name="custID" placeholder="Enter customer ID"><br>

            <label for="carID">Car ID:</label>
            <input type="text" id="carID" name="carID" placeholder="Enter car ID"><br>

            <label for="dateReceived">Date Received:</label>
            <input type="date" id="dateReceived" name="dateReceived"><br>
            <input type="hidden" name="action" value="searchServiceTicket">
            <button type="submit">Search</button>
        </form>
    </body>
</html>

