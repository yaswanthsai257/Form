package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private static Connection con;
    String url = "jdbc:mysql://localhost:3306/newone";
    String username = "root";
    String password = "root";
    String INSERT_QUERY = "insert into form(name,email,maths,english) values(?,?,?,?)";
    String GET_ALL = "select * from form";

    public Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Connection is established");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public int save(String name, String email, int maths, int english) {
        try {
            Connection con = getConnection(); // Ensure connection is established
            PreparedStatement statement = con.prepareStatement(INSERT_QUERY);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, maths);
            statement.setInt(4, english);
            return statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return 0;
    }

    public List<List<Object>> getAll() {
        List<List<Object>> dataList = new ArrayList<>();

        try {
            Connection con = getConnection(); // Ensure connection is established
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                List<Object> rowData = new ArrayList<>();
                rowData.add(result.getString(1)); // Name
                rowData.add(result.getString(2)); // Email
                rowData.add(result.getInt(3));    // Maths
                rowData.add(result.getInt(4));    // English
                dataList.add(rowData);
            }

            return dataList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
