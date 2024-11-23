package org.example.authlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;


public class DBManager {

    final static String DB_URL = "jdbc:mysql://localhost:3306/csc411db";

    public static Connection getConnection() {
        Connection conn = null;
        String username = "";
        String password = "";
        try {
            File file = new File("csc411db_conf.txt");

            Scanner infile = new Scanner(file, "utf-8");


            username = infile.next();
            password = infile.next();



            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connect to DB");

        } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return conn;
    }

    public static void createUserTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Creating table...");
            conn = getConnection();
            stmt = conn.createStatement();
            String query =
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "email VARCHAR(32) NOT NULL PRIMARY KEY," +
                    "salt VARCHAR(64) NOT NULL, " +
                    "password VARCHAR(512) NOT NULL)";
            stmt.execute(query);
            System.out.println("The users table is ready");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addUser(User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO users (email, salt, password) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSalt());
            stmt.setString(3, user.getPassword());
            int row = stmt.executeUpdate();
            System.out.println(row + "record is added to the user table.");

        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String retrievePassword(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String password = "";
        try {
            conn = getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                password = rs.getString("password");

        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return password;
    }


    public static String retrieveSalt(String email) {

        String salt = "";
        String query = "SELECT * FROM users WHERE email = ?";
        //try-with-resources
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                salt = rs.getString("salt");
            } //end if
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //end catch SQL Exception
        return salt;
    }

}
