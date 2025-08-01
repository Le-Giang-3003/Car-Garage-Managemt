<%-- 
    Document   : loginMechanicPage
    Created on : Mar 5, 2025, 11:58:53 PM
    Author     : legiang300304
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login-mechanic.css">
        <title>Login Mechanic</title>
    </head>
    <body>
        <div class="form-container">
            <h1>Login as Mechanic</h1>
            <form action="LoginMechanicServlet" method="post" accept-charset="utf-8">
                <input type="text" name="txtname" placeholder="Enter your name" required>
                <div class="button-group">
                    <input type="submit" value="Login"/>
                    <button type="button" onclick="window.location.href = 'LoginServlet?action=cancel'">Cancel</button>
                </div>
            </form>
        </div>
    </body>
</html>

