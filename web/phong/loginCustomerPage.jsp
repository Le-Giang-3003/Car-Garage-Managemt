<%-- 
    Document   : loginStaff
    Created on : Feb 17, 2025, 10:21:05 PM
    Author     : legiang300304
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login-customer.css">
        <title>Login Customer</title>
    </head>
    <body>
        <div class="form-container">
            <h1>Login as Customer</h1>
            <form action="LoginCustomerServlet" method="post" accept-charset="utf-8">
                <input type="text" name="txtname" placeholder="Enter your name here" required="">
                <input type="text" name="txtphone" placeholder="Enter your phone here" required="">
                <div class="button-group">
                    <input type="submit" value="Login">
                </div>
                <button type="button" class="cancel-btn" onclick="window.location.href = 'LoginServlet?action=cancel'">Cancel</button>

            </form>
        </div>
    </body>
</html>
