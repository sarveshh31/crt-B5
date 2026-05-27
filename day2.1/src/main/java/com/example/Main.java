package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main extends JFrame implements ActionListener {

    //GUI Components
    JTextField tfName,tfAge,tfGender,tfCourse,tfYear,
            tfEmail,tfPhone,tfAddress,tfGPA;

    JButton btnInsert, btnFetch,btnUpdate, btnDelete ,btnOrderby;
    JTable table;
    DefaultTableModel model;
    JTextField tfSearch1,tfsearch2;
    JComboBox<String> cbSearchType;
    JButton btnSearch;

    // JDBC

    Connection con;
    Statement stmt;
    ResultSet rs;

    //Default constructor
    Main(){
        setTitle("Rohan's Team Managment System");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10,2,5,5));

        formPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        formPanel.add(tfName);
        formPanel.add(new JLabel("Age:"));
        tfAge = new JTextField();
        formPanel.add(tfAge);
        formPanel.add(new JLabel("Gender:"));
        tfGender = new JTextField();
        formPanel.add(tfGender);
        formPanel.add(new JLabel("Course:"));       // FIX 1: added missing Course field
        tfCourse = new JTextField();
        formPanel.add(tfCourse);
        formPanel.add(new JLabel("Year:"));
        tfYear = new JTextField();
        formPanel.add(tfYear);
        formPanel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Phone:"));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);
        formPanel.add(new JLabel("Address:"));
        tfAddress = new JTextField();
        formPanel.add(tfAddress);
        formPanel.add(new JLabel("GPA:"));
        tfGPA = new JTextField();
        formPanel.add(tfGPA);

        //Buttons
        btnInsert=new  JButton("Insert");
        btnFetch=new  JButton("Fetch");
        btnUpdate=new  JButton("Update");
        btnDelete =new  JButton("Delete");
        btnOrderby=new  JButton("Orderby GPA");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnInsert);
        btnPanel.add(btnFetch);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnOrderby);

        cbSearchType = new JComboBox<>(new String[]{"WHERE GPA >",
                "LIKE Name","BETWEEN GPA"});

        tfSearch1 = new JTextField(10);
        tfsearch2 = new JTextField(10);
        btnSearch = new  JButton("Search");

        JPanel searchPanel = new JPanel();
        searchPanel.add(new  JLabel("Search Type"));
        searchPanel.add(cbSearchType);
        searchPanel.add(tfSearch1);
        searchPanel.add(tfsearch2);
        searchPanel.add(btnSearch);

        formPanel.add(btnPanel);

        JPanel northPanel = new JPanel(new BorderLayout());   // FIX 2: searchPanel added to frame
        northPanel.add(formPanel, BorderLayout.CENTER);
        northPanel.add(searchPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        //Table

        model = new DefaultTableModel();
        table = new JTable(model);                            // FIX 3: model passed to JTable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        //Event
        btnInsert.addActionListener(this);
        btnFetch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnOrderby.addActionListener(this);

        btnSearch.addActionListener(this);

        //DB Connection
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rcoem","root","pass@123");
            stmt=con.createStatement();

            JOptionPane.showMessageDialog(this,"Connected to database successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"DB Error" +e.getMessage());
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try{
            if(ae.getSource()==btnInsert)
            {
                String q = "INSERT INTO students(name,age,gender,course,year,email,phone,address,gpa) VALUES('"
                        + tfName.getText() + "',"
                        + tfAge.getText() + ",'"
                        + tfGender.getText() + "','"
                        + tfCourse.getText() + "',"
                        + tfYear.getText() + ",'"
                        + tfEmail.getText() + "','"
                        + tfPhone.getText() + "','"
                        + tfAddress.getText() + "',"
                        + tfGPA.getText() + ")";
                stmt.executeUpdate(q);
                JOptionPane.showMessageDialog(this,"Record Inserted Successfully");
            }
            else if(ae.getSource()==btnFetch)
            {
                rs=stmt.executeQuery("SELECT * FROM students");
                ResultSetMetaData rsmd = rs.getMetaData();

                int cols = rsmd.getColumnCount();
                model.setRowCount(0);
                model.setColumnCount(0);

                for (int i = 1; i <= cols; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                while(rs.next())
                {
                    Object[] row = new Object[cols];
                    for (int i = 1; i <= cols; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
            } else if (ae.getSource()==btnUpdate) {
                String q = "UPDATE students SET gpa="+tfGPA.getText()+" WHERE name='"+tfName.getText()+"'"; // FIX 4: removed extra quote
                stmt.executeUpdate(q);
                JOptionPane.showMessageDialog(this,"Record Updated Successfully");
            }
            else if (ae.getSource()==btnDelete) {
                String q = "DELETE FROM students WHERE name='"+tfName.getText()+"'";
                stmt.executeUpdate(q);
                JOptionPane.showMessageDialog(this,"Record Deleted Successfully");
            }
            else if (ae.getSource()==btnOrderby) {
                rs=stmt.executeQuery("SELECT * FROM students"+ " ORDER BY gpa DESC");
                ResultSetMetaData rsmd = rs.getMetaData();

                int cols = rsmd.getColumnCount();
                model.setRowCount(0);
                model.setColumnCount(0);

                for (int i = 1; i <= cols; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                while (rs.next()) {
                    Object[] row = new Object[cols];
                    for (int i = 1; i <= cols; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
            }
            else if(ae.getSource() == btnSearch)
            {
                String Type = (String)cbSearchType.getSelectedItem();
                String q = "";
                if(Type.equals("WHERE GPA >"))
                {
                    q="SELECT * FROM students WHERE gpa > " + tfSearch1.getText();
                }
                else if(Type.equals("LIKE Name")) {
                    q = "SELECT * FROM students WHERE name LIKE '" + tfSearch1.getText() + "%'";
                }
                else if(Type.equals("BETWEEN GPA")) {
                    q = "SELECT * FROM students WHERE gpa BETWEEN " + tfSearch1.getText() + " AND " + tfsearch2.getText();
                }
                rs = stmt.executeQuery(q);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                model.setRowCount(0);
                model.setColumnCount(0);
                for (int i = 1; i <= cols; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                while (rs.next()) {
                    Object[] row = new Object[cols];
                    for (int i = 1; i <= cols; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"error" +e.getMessage());
        }
    }
}