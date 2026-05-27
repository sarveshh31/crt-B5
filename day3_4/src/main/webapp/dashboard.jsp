<%--
  Created by IntelliJ IDEA.
  User: rohan
  Date: 20-05-2026
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h2>Destination Management</h2>

<h3>Add Destination</h3>
<form action="AddDestinationServlet" method="post">

    Place : <input type="text" name="place" required><br><br>

    Hotel : <input type="text" name="hotel" required><br><br>

    Cost : <input type="number" step="0.01" name="cost" required><br><br>

    Places To Visit :
    <textarea name="places" required></textarea><br><br>

    <input type="submit" value="Add Record">
</form>

<hr>

<h3>Search Destination By Place</h3>
<form action="SearchDestinationServlet" method="get">

    Select Place :
    <select name="place">
        <option value="Goa">Goa</option>
        <option value="Manali">Manali</option>
        <option value="Mumbai">Mumbai</option>
        <option value="Kashmir">Kashmir</option>
    </select>

    <input type="submit" value="Search">
</form>

<hr>

<h3>Update Destination</h3>
<form action="UpdateDestinationServlet" method="post">

    Place : <input type="text" name="place" required><br><br>

    New Hotel : <input type="text" name="hotel" required><br><br>

    New Cost : <input type="number" step="0.01" name="cost" required><br><br>

    New Places To Visit :
    <textarea name="places" required></textarea><br><br>

    <input type="submit" value="Update">
</form>

<hr>
</html>