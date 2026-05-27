<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Please enter your details</h2>
<form action="Process" method="POST">
    Username: <input type="text" name="uname"><br><br>
    Password: <input type="password" name="pwd"><br><br>
    <input type="checkbox" name="rem"> Remember Me<br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>