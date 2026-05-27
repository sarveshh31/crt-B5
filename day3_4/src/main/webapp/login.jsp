<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username != null) {
        if ("admin".equals(username)) {
            response.sendRedirect("admin.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
        return;
    }
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head><title>Login - Travel App</title></head>
<body>
<h2>Travel App - Login</h2>
<% if (error != null) { %>
    <p><b><%= error %></b></p>
<% } %>
<form action="LoginServlet" method="post">
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
                <input type="submit" value="Login" />
            </td>
        </tr>
    </table>
</form>
<br/>
<a href="index.jsp">Continue as Guest (Explore without login)</a>
<br/><br/>
<a href="register.jsp">New user? Register here</a>
</body>
</html>
