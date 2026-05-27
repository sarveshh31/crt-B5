<%@ page import="java.sql.*" %>

<%
    String url = "jdbc:mysql://localhost:3306/rcoem";
    String user = "root";
    String pass = "pass@123";

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);

    String action = request.getParameter("action");

    if ("insert".equals(action)) {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String course = request.getParameter("course");
        int year = Integer.parseInt(request.getParameter("year"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        double gpa = Double.parseDouble(request.getParameter("gpa"));

        ps = con.prepareStatement(
                "INSERT INTO students(name,age,gender,course,year,email,phone,address,gpa) VALUES(?,?,?,?,?,?,?,?,?)"
        );

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, gender);
        ps.setString(4, course);
        ps.setInt(5, year);
        ps.setString(6, email);
        ps.setString(7, phone);
        ps.setString(8, address);
        ps.setDouble(9, gpa);

        ps.executeUpdate();
    }

    if ("delete".equals(action)) {

        int id = Integer.parseInt(request.getParameter("id"));

        ps = con.prepareStatement(
                "DELETE FROM students WHERE id=?"
        );

        ps.setInt(1, id);

        ps.executeUpdate();
    }

    String sort = request.getParameter("sort");

    if(sort == null){
        sort = "gpa";
    }
%>

<html>

<head>
    <title>Student CRUD</title>
</head>

<body>

<center>

    <h2>Student Management System</h2>

    <form method="post">

        <input type="hidden" name="action" value="insert">

        <table border="1" cellpadding="10">

            <tr>
                <td>Name</td>
                <td><input type="text" name="name" required></td>

                <td>Age</td>
                <td><input type="number" name="age" required></td>
            </tr>

            <tr>
                <td>Gender</td>

                <td>
                    <select name="gender">
                        <option>Male</option>
                        <option>Female</option>
                    </select>
                </td>

                <td>Course</td>
                <td><input type="text" name="course" required></td>
            </tr>

            <tr>
                <td>Year</td>
                <td><input type="number" name="year" required></td>

                <td>Email</td>
                <td><input type="email" name="email" required></td>
            </tr>

            <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" required></td>

                <td>City</td>

                <td>
                    <select name="address">

                        <option>Nagpur</option>
                        <option>Pune</option>
                        <option>Mumbai</option>
                        <option>Nashik</option>
                        <option>Amravati</option>

                    </select>
                </td>
            </tr>

            <tr>
                <td>GPA</td>
                <td><input type="text" name="gpa" required></td>

                <td colspan="2">
                    <input type="submit" value="Add Student">
                </td>
            </tr>

        </table>

    </form>

    <br><br>

    <form method="get">

        Sort By :

        <select name="sort">

            <option value="id">ID</option>
            <option value="name">Name</option>
            <option value="age">Age</option>
            <option value="gender">Gender</option>
            <option value="course">Course</option>
            <option value="year">Year</option>
            <option value="email">Email</option>
            <option value="address">City</option>
            <option value="gpa">GPA</option>

        </select>

        <input type="submit" value="Sort">

    </form>

    <br>

    <h2>Student Records</h2>

    <table border="1" cellpadding="10">

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Course</th>
            <th>Year</th>
            <th>Email</th>
            <th>Phone</th>
            <th>City</th>
            <th>GPA</th>
            <th>Delete</th>
        </tr>

        <%
            String query = "SELECT * FROM students ORDER BY " + sort;

            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while(rs.next()) {
        %>

        <tr>

            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getInt("age") %></td>
            <td><%= rs.getString("gender") %></td>
            <td><%= rs.getString("course") %></td>
            <td><%= rs.getInt("year") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("phone") %></td>
            <td><%= rs.getString("address") %></td>
            <td><%= rs.getDouble("gpa") %></td>

            <td>
                <a href="index.jsp?action=delete&id=<%= rs.getInt("id") %>">
                    Delete
                </a>
            </td>

        </tr>

        <%
            }
        %>

    </table>

</center>

</body>
</html>