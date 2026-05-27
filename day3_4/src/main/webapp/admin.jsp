<%@ page session="true" import="java.sql.*, com.example.day3_4.DBConnection" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null || !"admin".equals(username)) {
        response.sendRedirect("login.jsp");
        return;
    }

    // For edit mode
    String editId = request.getParameter("editId");
    ResultSet editRs = null;
    Connection editConn = null;
    String eid = "", eplace = "", ecity = "", ecost = "", eplaces = "", ehotel = "";

    if (editId != null) {
        editConn = DBConnection.getConnection();
        PreparedStatement eps = editConn.prepareStatement("SELECT * FROM destinations WHERE id=?");
        eps.setInt(1, Integer.parseInt(editId));
        editRs = eps.executeQuery();
        if (editRs.next()) {
            eid = String.valueOf(editRs.getInt("id"));
            eplace = editRs.getString("place");
            ecity = editRs.getString("city");
            ecost = editRs.getString("cost");
            eplaces = editRs.getString("places_to_visit");
            ehotel = editRs.getString("hotel");
        }
    }
%>
<!DOCTYPE html>
<html>
<head><title>Admin Dashboard - Travel App</title></head>
<body>
<h2>Admin Dashboard</h2>
<p>Welcome, admin! | <a href="LogoutServlet">Logout</a></p>
<hr/>

<h3>Add New Destination</h3>
<form action="AddDestinationServlet" method="post">
    <table border="1">
        <tr>
            <td>Place Name</td>
            <td><input type="text" name="place" required /></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="city" required /></td>
        </tr>
        <tr>
            <td>Cost (Rs.)</td>
            <td><input type="number" name="cost" step="0.01" required /></td>
        </tr>
        <tr>
            <td>Places to Visit</td>
            <td><textarea name="places_to_visit" rows="3" cols="40" required></textarea></td>
        </tr>
        <tr>
            <td>Hotel</td>
            <td><input type="text" name="hotel" required /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Add Record" />
            </td>
        </tr>
    </table>
</form>

<hr/>

<h3>Delete Destination by Name</h3>
<form action="DeleteDestinationServlet" method="post">
    <table border="1">
        <tr>
            <td>Place Name</td>
            <td><input type="text" name="place" required /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Delete" />
            </td>
        </tr>
    </table>
</form>

<hr/>

<% if (editId != null && !eid.isEmpty()) { %>
<h3>Edit Destination</h3>
<form action="UpdateDestinationServlet" method="post">
    <input type="hidden" name="id" value="<%= eid %>" />
    <table border="1">
        <tr>
            <td>Place Name</td>
            <td><input type="text" name="place" value="<%= eplace %>" required /></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="city" value="<%= ecity %>" required /></td>
        </tr>
        <tr>
            <td>Cost (Rs.)</td>
            <td><input type="number" name="cost" step="0.01" value="<%= ecost %>" required /></td>
        </tr>
        <tr>
            <td>Places to Visit</td>
            <td><textarea name="places_to_visit" rows="3" cols="40" required><%= eplaces %></textarea></td>
        </tr>
        <tr>
            <td>Hotel</td>
            <td><input type="text" name="hotel" value="<%= ehotel %>" required /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Update Record" />
                <a href="admin.jsp">Cancel</a>
            </td>
        </tr>
    </table>
</form>
<hr/>
<% } %>

<h3>All Destinations</h3>
<%
    Connection conn = DBConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM destinations ORDER BY city, place");
%>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Place</th>
        <th>City</th>
        <th>Cost (Rs.)</th>
        <th>Places to Visit</th>
        <th>Hotel</th>
        <th>Edit</th>
    </tr>
    <%
        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("place") %></td>
        <td><%= rs.getString("city") %></td>
        <td>Rs. <%= rs.getString("cost") %></td>
        <td><%= rs.getString("places_to_visit") %></td>
        <td><%= rs.getString("hotel") %></td>
        <td><a href="admin.jsp?editId=<%= rs.getInt("id") %>">Edit</a></td>
    </tr>
    <% } %>
    <% if (!hasData) { %>
    <tr><td colspan="7" align="center">No destinations added yet.</td></tr>
    <% } %>
</table>

<%
    rs.close();
    conn.close();
    if (editConn != null) editConn.close();
%>
</body>
</html>
