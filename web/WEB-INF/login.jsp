<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login">
            <h3>Username:</h3>
            <input type="text" id="uBox" name="ubox" value="${usernameSaver}" required></input>
            <h3>Password:</h3>
            <input type="text" id="pBox" name="pbox" value="${passwordSaver}" required></input>
            <br>
            <br>
            <div hidden>Incorrect, please try again!</div>
            <input type="submit" value="Log in">
            <p>${responseMessage}</p>
    </body>
</html>
