package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b1,b2;
    JLabel l;
    Login(){
        l1=new JLabel("User Name");
        l1.setBounds(40,20,100,30);
        l2 = new JLabel("Password");
        l2.setBounds(40,70,100,30);
        t1 = new JTextField();
        t1.setBounds(150,20,100,30);
        t2 =new JPasswordField();
        t2.setBounds(150,70,100,30);
        b1=new JButton("Login");
        b1.setBounds(40,120,100,30);
        b1.addActionListener(this);
        b2=new JButton("Cancel");
        b2.setBounds(150,120,100,30);
        b2.addActionListener(this);
        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("second.jpg"));
        Image i =ii.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon iii = new ImageIcon(i);
        l = new JLabel(iii);
        l.setBounds(250,00,200,200);
        getContentPane().setBackground(Color.WHITE);
        add(l1);
        add(t1);
        add(t2);
        add(l2);
        add(b1);
        add(b2);
        add(l);
        setResizable(false);
        setLayout(null);
        setBounds(500,300,450,200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
          String user=t1.getText();
          String password =t2.getText();
          try {
            MySQLConnection conn = new MySQLConnection();

            String st = "select * from login where user='" + user + "' and password='" + password + "'";
            ResultSet se = conn.s.executeQuery(st);
            if(se.next()){
                this.dispose();
                new Dashboard().setVisible(true);

            }
            else{
                 JOptionPane.showMessageDialog(null,"Invalid username or password");
             }
          }
            catch(SQLException sq){
            sq.printStackTrace();
                                  }
        }
           else if(ae.getSource()==b2){
            System.exit(0);
           }
    }
}
