package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateCheck  extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,o2,o3,o4,o5,o6;
    Choice c1;
    JTextField t6,t2,t3,t4,t5;
    MySQLConnection conn;
    ResultSet set,set1,set2;
    JButton b1,b2,b3;

    UpdateCheck(){
        l1 = new JLabel("Phone No");
        l1.setBounds(10,10,140,25);
        add(l1);
        c1 = new Choice();
        try{
           conn = new MySQLConnection() ;
           set=conn.s.executeQuery("select * from customer");
           while(set.next()) {
               c1.add(set.getString("mobileno"));
           }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error In Table");
        }
        c1.setBounds(150,10,140,25);
        add(c1);

        l2=new JLabel("Room No");
        l2.setBounds(10,40,140,25);
        add(l2);
        t2=new JTextField();
        t2.setBounds(150,40,140,25);
        add(t2);
        o2=new JLabel();
        o2.setBounds(300,40,140,25);
        add(o2);

        l3=new JLabel("Name");
        l3.setBounds(10,70,140,25);
        add(l3);
        t3=new JTextField();
        t3.setBounds(150,70,140,25);
        add(t3);
        o3=new JLabel();
        o3.setBounds(300,70,140,25);
        add(o3);

        l4=new JLabel("Check-In");
        l4.setBounds(10,100,140,25);
        add(l4);
        t4=new JTextField();
        t4.setBounds(150,100,140,25);
        add(t4);
        o4=new JLabel();
        o4.setBounds(300,100,140,25);
        add(o4);

        l5 = new JLabel("Paid Amount");
        l5.setBounds(10,130,140,25);
        add(l5);
        t5=new JTextField();
        t5.setBounds(150,130,140,25);
        add(t5);
        o5=new JLabel();
        o5.setBounds(300,130,140,25);
        add(o5);

        l6=new JLabel("Pending Amount");
        l6.setBounds(10,160,140,25);
        add(l6);
        t6=new JTextField();
        t6.setBounds(150,160,140,25);
        add(t6);
        o6=new JLabel();
        o6.setBounds(300,160,140,25);
        add(o6);

        b1=new JButton("Check");
        b1.setBounds(70,190,100,25);
        b1.setForeground(Color.white);
        b1.setBackground(Color.black);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("Update");
        b2.setBounds(10,220,100,25);
        b2.setForeground(Color.white);
        b2.setBackground(Color.black);
        add(b2);

        b3=new JButton("Back");
        b3.setBounds(120,220,100,25);
        b3.setForeground(Color.white);
        b3.setBackground(Color.black);
        b3.addActionListener(this);
        add(b3);

        getContentPane().setBackground(Color.white);
        setBounds(550,300,450,300);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String number = c1.getSelectedItem();
            String dep=null;
            String de=null;
            String deposit;
            int diff;
        try{
            conn = new MySQLConnection();
            set1 = conn.s.executeQuery("select * from customer where mobileno='"+number+"'");
            while(set1.next()){
                o2.setText(set1.getString("roomno"));
                o3.setText(set1.getString("name"));
                o4.setText(set1.getString("checkedin"));
                o5.setText(set1.getString("deposit"));
                deposit=set1.getString("deposit");

               dep = set1.getString("roomno");
                conn=new MySQLConnection();
                set2=conn.s.executeQuery("select * from rooms where roomno='"+dep+"'");
                while(set2.next()){
                    de=set2.getString("price");
                    diff = Integer.parseInt(de)-Integer.parseInt(deposit);
                    o6.setText(Integer.toString(diff));
                }
            }
        }catch(Exception aee){
        JOptionPane.showMessageDialog(null,"Error in table customer");
        }
        }
        else if(e.getSource()==b2){

        }
        else if(e.getSource()==b3){
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new UpdateCheck().setVisible(true);
    }
}
