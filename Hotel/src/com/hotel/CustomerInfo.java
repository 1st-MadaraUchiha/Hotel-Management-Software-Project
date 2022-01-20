package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.*;


public class CustomerInfo extends JFrame implements ActionListener {
    JTable t;
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    MySQLConnection conn;
    ResultSet set;
    CustomerInfo(){
        l1=new JLabel("Document Type");
        l1.setBounds(25,0,150,30);
        add(l1);

        l2=new JLabel("Number");
        l2.setBounds(155,0,150,30);
        add(l2);

        l3=new JLabel("Name");
        l3.setBounds(265,0,150,30);
        add(l3);

        l4=new JLabel("Gender");
        l4.setBounds(385,0,150,30);
        add(l4);

        l5=new JLabel("Country");
        l5.setBounds(495,0,150,30);
        add(l5);

        l6=new JLabel("Room No");
        l6.setBounds(605,0,150,30);
        add(l6);

        l7=new JLabel("Status");
        l7.setBounds(705,0,150,30);
        add(l7);

        l8=new JLabel("Deposit");
        l8.setBounds(825,0,150,30);
        add(l8);

        t=new JTable();
        t.setBounds(10,30,900,400);
        add(t);

        b1=new JButton("Load Data");
        b1.setBounds(150,450,150,30);
        b1.setForeground(Color.white);
        b1.setBackground(Color.black);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.setBounds(400,450,150,30);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(Color.white);
        setBounds(400,200,920,530);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==b1){
            try {
                this.conn = new MySQLConnection();
            }
            catch (SQLException ex) {
                if (ex != null) {
                    JOptionPane.showMessageDialog(null, "Error Connection To Database");
                }
            }
            String str ="select * from customer";
            try {
                set=conn.s.executeQuery(str);
                this.set=set;
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error In Database Table", "Message", JOptionPane.ERROR_MESSAGE);
            }
            t.setModel(DbUtils.resultSetToTableModel(set));
        }
        else if (e.getSource()==b2){
            this.dispose();
        }
    }
    public static void main(String[] args) {

        new CustomerInfo();
    }
}
