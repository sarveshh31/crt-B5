<%@ page session="true" %>
<%
    if (session.getAttribute("username") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head><title>Register - Travel App</title></head>
<body>
<h2>Register</h2>
<% if (error != null) { %>
    <p><b><%= error %></b></p>
<% } %>
<% if (success != null) { %>
    <p><b><%= success %></b> <a href="login.jsp">Login now</a></p>
<% } %>
<form action="RegisterServlet" method="post">
    <table border="1">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" required /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" required /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Register" />
            </td>
        </tr>
    </table>
</form>
<br/>
<a href="login.jsp">Already have an account? Login</a>
</body>
</html>
