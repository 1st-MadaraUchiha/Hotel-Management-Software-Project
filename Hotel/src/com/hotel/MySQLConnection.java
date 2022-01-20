package com.hotel;

import javax.swing.*;
import java.sql.*;


public class MySQLConnection {
    Connection c;
    Statement s;

    public MySQLConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "iamdaidon3461#");
            s = c.createStatement();
            this.s=s;
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Check ReadMe And Initialize Database");
        }

    }

    public static void main(String[] args) {
        try {
            new MySQLConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
