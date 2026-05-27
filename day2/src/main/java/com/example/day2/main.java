package com.example.day2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;


public class main extends JFrame{

    //GUI Components
    JTextField tfName,tfAge,tfGender,tfCourse,tfYear,
            tfEmail,tfPhone,tfAddress,tfGPA;

    JButton btnInsert, btnFetch,btnUpdate, btnDelete ,btnOrderby;
    JTable table;
    DefaultTableModel model;
    JTextField tfSearch,tfsearch2;
    JComboBox<String> cbSearchType;
    JButton btnSearch;

    Connection conn;
    Statement stmt;
    ResultSet rs;

    RCOEM_TEAMMANAGEMENT(
            setTitle()

    )

    public static void main(String[] args) {


    }

}