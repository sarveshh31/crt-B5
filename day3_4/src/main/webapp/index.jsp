<%@ page session="true" import="java.sql.*, com.example.day3_4.DBConnection" %>
<%
    // No login required - anyone can explore
    String username = (String) session.getAttribute("username");
    boolean isAdmin = "admin".equals(username);

    String selectedCity = request.getParameter("city");
    String selectedPlace = request.getParameter("place");

    Connection conn = DBConnection.getConnection();
%>
<!DOCTYPE html>
<html>
<head><title>Explore Destinations - Travel App</title></head>
<body>
<h2>Travel App - Explore Destinations</h2>

<% if (username != null) { %>
    <p>Welcome, <%= username %>!
        <% if (isAdmin) { %> | <a href="admin.jsp">Admin Panel</a><% } %>
        | <a href="LogoutServlet">Logout</a>
    </p>
<% } else { %>
    <p><a href="login.jsp">Login</a> | <a href="register.jsp">Register</a></p>
<% } %>

<hr/>

<h3>Step 1: Select a City</h3>
<form action="index.jsp" method="get">
    <select name="city">
        <option value="">-- Select City --</option>
        <%
            Statement cityStmt = conn.createStatement();
            ResultSet cityRs = cityStmt.executeQuery("SELECT DISTINCT city FROM destinations ORDER BY city");
            while (cityRs.next()) {
                String c = cityRs.getString("city");
                String sel = c.equals(selectedCity) ? "selected" : "";
        %>
        <option value="<%= c %>" <%= sel %>><%= c %></option>
        <%
            }
            cityRs.close();
        %>
    </select>
    <input type="submit" value="Show Places" />
</form>

<%
    if (selectedCity != null && !selectedCity.isEmpty()) {
%>
<hr/>
<h3>Step 2: Select a Place in <%= selectedCity %></h3>
<form action="index.jsp" method="get">
    <input type="hidden" name="city" value="<%= selectedCity %>" />
    <select name="place">
        <option value="">-- Select Place --</option>
        <%
            PreparedStatement placeStmt = conn.prepareStatement(
                "SELECT place FROM destinations WHERE city=? ORDER BY place");
            placeStmt.setString(1, selectedCity);
            ResultSet placeRs = placeStmt.executeQuery();
            while (placeRs.next()) {
                String p = placeRs.getString("place");
                String sel = p.equals(selectedPlace) ? "selected" : "";
        %>
        <option value="<%= p %>" <%= sel %>><%= p %></option>
        <%
            }
            placeRs.close();
        %>
    </select>
    <input type="submit" value="Show Details" />
</form>
<% } %>

<%
    if (selectedPlace != null && !selectedPlace.isEmpty()) {
        PreparedStatement detailStmt = conn.prepareStatement(
            "SELECT * FROM destinations WHERE city=? AND place=?");
        detailStmt.setString(1, selectedCity);
        detailStmt.setString(2, selectedPlace);
        ResultSet detailRs = detailStmt.executeQuery();

        if (detailRs.next()) {
%>
<hr/>
<h3>Details: <%= detailRs.getString("place") %>, <%= detailRs.getString("city") %></h3>
<table border="1" cellpadding="5">
    <tr><td><b>Place</b></td><td><%= detailRs.getString("place") %></td></tr>
    <tr><td><b>City</b></td><td><%= detailRs.getString("city") %></td></tr>
    <tr><td><b>Cost</b></td><td>Rs. <%= detailRs.getString("cost") %></td></tr>
    <tr><td><b>Places to Visit</b></td><td><%= detailRs.getString("places_to_visit") %></td></tr>
    <tr><td><b>Hotel</b></td><td><%= detailRs.getString("hotel") %></td></tr>
</table>
<%
        }
        detailRs.close();
    }
    conn.close();
%>

</body>
</html>
