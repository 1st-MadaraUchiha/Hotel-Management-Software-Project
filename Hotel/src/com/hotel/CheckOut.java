package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CheckOut extends JFrame implements ActionListener{
    JLabel l1,l2;
    Choice c1;
    JLabel t1;
    MySQLConnection conn;
    ResultSet set;
    JButton b1,b2,b3;
    String no;

    CheckOut(){

        l1=new JLabel("Customer Mobile");
        l1.setBounds(10,10,130,25);
        add(l1);
        c1=new Choice();
        try{
            conn = new MySQLConnection();
            set= conn.s.executeQuery("select * from customer");
            while(set.next()){
                c1.add(set.getString("mobileno"));
            }
        }catch(Exception e){

        }
        c1.setBounds(150,10,150,25);
        add(c1);

        l2=new JLabel("Room Number");
        l2.setBounds(10,40,140,25);
        add(l2);
        t1=new JLabel("Click Check And Checkout");
        t1.setBounds(150,40,150,25);
        add(t1);

        b1=new JButton("Checkout");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setBounds(10,100,100,25);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.setBounds(150,100,100,25);
        b2.addActionListener(this);
        add(b2);

        b3=new JButton("Check");
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.setBounds(100,70,100,25);
        b3.addActionListener(this);
        add(b3);

        setBounds(400,200,400,200);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()==b1) {
         if(t1.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"Check The Room Number");
         }
         else{
             try {
                 conn = new MySQLConnection();
                 conn.s.executeUpdate("update rooms set availability='Available' where roomno='"+t1.getText()+"'");
                 conn = new MySQLConnection();
                 conn.s.executeUpdate("update customer set checkedin='Yes' where mobileno='"+no+"'AND roomno='"+t1.getText()+"'");
                 JOptionPane.showMessageDialog(null, "Room Is Now Available. Be Sure To Clean It");

             } catch (Exception aee) {
                 aee.printStackTrace();
                 JOptionPane.showMessageDialog(null, "Mistake In Room No");
             }
         }

     }
     if(e.getSource()==b2){
         this.dispose();
     }
     if(e.getSource()==b3){
         this.no = c1.getSelectedItem();
         try{
           conn=new MySQLConnection();
           set = conn.s.executeQuery("select roomno from customer where mobileno='"+no+"'");
           while(set.next()){
               this.t1.setText(set.getString("roomno"));
           }
         }
         catch (Exception ae){
            JOptionPane.showMessageDialog(null,"Error In Database");
         }
     }
    }

    public static void main(String[] args) {
    new CheckOut().setVisible(true);
    }
}
