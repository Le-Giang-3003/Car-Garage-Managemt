<%-- 
    Document   : loginSalePersonPage
    Created on : Mar 6, 2025, 12:42:07 AM
    Author     : legiang300304
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login-saleperson.css">
        <title>Login Sale Person</title>
    </head>
    <body>
        <div class="form-container">
            <h1>Login as Sale Person</h1>
            <form action="LoginSalePersonServlet" method="post" accept-charset="utf-8">
                <input type="text" name="txtname" placeholder="Enter your name" />

                <div class="button-group">
                    <input type="submit" value="Login"/>
                    <button type="button" onclick="window.location.href = 'LoginServlet?action=cancel'">Cancel</button>
                </div>
            </form>
        </div>
    </body>
</html>

