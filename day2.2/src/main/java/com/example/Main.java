package com.example;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends Frame implements ActionListener
{
    TextField empId, empName, managerId, deptId;
    TextArea result;

    Button selfJoin, crossJoin, compositeJoin, multiJoin;

    static final String URL = "jdbc:mysql://localhost:3306/rcoem";
    static final String USER = "root";
    static final String PASSWORD = "pass@123";

    Main()
    {
        setTitle("Employee Management System");
        setSize(700, 500);
        setLayout(new FlowLayout());

        add(new Label("Employee ID"));
        empId = new TextField(20);
        add(empId);

        add(new Label("Employee Name"));
        empName = new TextField(20);
        add(empName);

        add(new Label("Manager ID"));
        managerId = new TextField(20);
        add(managerId);

        add(new Label("Department ID"));
        deptId = new TextField(20);
        add(deptId);

        selfJoin      = new Button("Self Join");
        crossJoin     = new Button("Cross Join");
        compositeJoin = new Button("Composite Join");
        multiJoin     = new Button("Multi Join");

        add(selfJoin);
        add(crossJoin);
        add(compositeJoin);
        add(multiJoin);

        result = new TextArea(15, 70);
        add(result);

        selfJoin.addActionListener(this);
        crossJoin.addActionListener(this);
        compositeJoin.addActionListener(this);
        multiJoin.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public Connection getConnection()
    {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            result.setText("Connection Error: " + e);
        }
        return con;
    }

    public void actionPerformed(ActionEvent ae)
    {
        Object src = ae.getSource();

        if      (src == selfJoin)      selfJoinQuery();
        else if (src == crossJoin)     crossJoinQuery();
        else if (src == compositeJoin) compositeJoinQuery();
        else if (src == multiJoin)     multiJoinQuery();
    }

    // Self Join — employee with their manager
    // Self Join — employee with their manager
    public void selfJoinQuery()
    {
        String query =
                "SELECT e1.empname AS Employee, " +
                        "IFNULL(e2.empname, 'No Manager') AS Manager " +
                        "FROM employee e1 " +
                        "LEFT JOIN employee e2 ON e1.managerid = e2.empid";

        try (Connection con = getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(query))
        {
            result.setText("SELF JOIN RESULT\n\n");
            while (rs.next()) {
                result.append(
                        rs.getString("Employee") +
                                " --> Manager: " +
                                rs.getString("Manager") + "\n");
            }
        } catch (Exception e) {
            result.setText("Error: " + e);
        }
    }

    // Cross Join — every employee with every department
    public void crossJoinQuery()
    {
        String query =
                "SELECT employee.empname, department.deptname " +
                        "FROM employee CROSS JOIN department";

        try (Connection con = getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(query))
        {
            result.setText("CROSS JOIN RESULT\n\n");
            while (rs.next()) {
                result.append(
                        rs.getString("empname") +
                                " - " +
                                rs.getString("deptname") + "\n");
            }
        } catch (Exception e) {
            result.setText("Error: " + e);
        }
    }

    public void compositeJoinQuery()
    {
        String query =
                "SELECT e.empid, e.empname, d.deptname " +
                        "FROM employee e " +
                        "JOIN department d " +
                        "ON e.deptid = d.deptid AND e.empid IS NOT NULL";

        try (Connection con = getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(query))
        {
            result.setText("COMPOSITE JOIN RESULT\n\n");
            while (rs.next()) {
                result.append(
                        rs.getString("empid")    + " | " +
                                rs.getString("empname")  + " --> " +
                                rs.getString("deptname") + "\n");
            }
        } catch (Exception e) {
            result.setText("Error: " + e);
        }
    }

    public void multiJoinQuery()
    {
        String query =
                "SELECT e.empname, d.deptname, p.projname " +
                        "FROM employee e " +
                        "JOIN department d ON e.deptid = d.deptid " +
                        "JOIN project   p ON d.deptid  = p.deptid";

        try (Connection con = getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(query))
        {
            result.setText("MULTI JOIN RESULT\n\n");
            while (rs.next()) {
                result.append(
                        rs.getString("empname")  + " | " +
                                rs.getString("deptname") + " | " +
                                rs.getString("projname") + "\n");
            }
        } catch (Exception e) {
            result.setText("Error: " + e);
        }
    }

    public static void main(String[] args)
    {
        new Main();
    }
}