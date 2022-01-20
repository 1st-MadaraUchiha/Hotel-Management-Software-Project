package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Reception extends JFrame implements ActionListener {
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    JLabel jl;

 Reception(){
 b1=new JButton("New Customer Form");
 b1.setBackground(Color.black);
 b1.setForeground(Color.white);
 b1.setBounds(10,10,160,25);
 b1.addActionListener(this);
 add(b1);

 b2=new JButton("Room");
 b2.setBackground(Color.black);
 b2.setForeground(Color.white);
 b2.setBounds(10,40,160,25);
 b2.addActionListener(this);
 add(b2);

 b3=new JButton("Department");
 b3.setBackground(Color.black);
 b3.setForeground(Color.white);
 b3.setBounds(10,70,160,25);
 b3.addActionListener(this);
 add(b3);

 b4=new JButton("All Employee Info");
 b4.setBackground(Color.black);
 b4.setForeground(Color.white);
 b4.setBounds(10,100,160,25);
 b4.addActionListener(this);
 add(b4);

 b5=new JButton("Customer Info");
 b5.setBackground(Color.black);
 b5.setForeground(Color.white);
 b5.setBounds(10,130,160,25);
 b5.addActionListener(this);
 add(b5);

 b6=new JButton("Manager Info");
 b6.setBackground(Color.black);
 b6.setForeground(Color.white);
 b6.setBounds(10,160,160,25);
 b6.addActionListener(this);
 add(b6);

 b7=new JButton("Check Out");
 b7.setBackground(Color.black);
 b7.setForeground(Color.white);
 b7.setBounds(10,190,160,25);
 b7.addActionListener(this);
 add(b7);

 b8=new JButton("Update Check Status");
 b8.setBackground(Color.black);
 b8.setForeground(Color.white);
 b8.setBounds(10,220,160,25);
 b8.addActionListener(this);
 add(b8);

 b9=new JButton("Update Room Status");
 b9.setBackground(Color.black);
 b9.setForeground(Color.white);
 b9.setBounds(10,250,160,25);
 b9.addActionListener(this);
 add(b9);

 b10=new JButton("Pick Up Services");
 b10.setBackground(Color.black);
 b10.setForeground(Color.white);
 b10.setBounds(10,280,160,25);
 b10.addActionListener(this);
 add(b10);

 b11=new JButton("Search Room");
 b11.setBackground(Color.black);
 b11.setForeground(Color.white);
 b11.setBounds(10,310,160,25);
 b11.addActionListener(this);
 add(b11);

 b12=new JButton("Log Out");
 b12.setBackground(Color.black);
 b12.setForeground(Color.white);
 b12.setBounds(10,340,160,25);
 b12.addActionListener(this);
 add(b12);

 ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("fourth.jpg"));
 Image ii = i.getImage().getScaledInstance(300,355,Image.SCALE_DEFAULT);
 ImageIcon iii = new ImageIcon(ii);
 jl = new JLabel(iii);
 jl.setBounds(180,10,300,355);
 add(jl);

getContentPane().setBackground(Color.white);
 setBounds(550,150,500,410);
 setLayout(null);
 setResizable(false);
 setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource()==b1) {
        new AddCustomer().setVisible(true);
    }
        else if (e.getSource()==b2) {
        new Room().setVisible(true);
        }
    else if (e.getSource()==b3) {

        }
        else if (e.getSource()==b4) {
        new EmployeeInfo();
        }
    else if (e.getSource()==b5) {
        new CustomerInfo().setVisible(true);
        }
    else if (e.getSource()==b6) {
        new ManagerInfo();
        }
    else if (e.getSource()==b7) {
        new CheckOut().setVisible(true);
        }
    else if (e.getSource()==b8) {
        new UpdateCheck().setVisible(true);
        }
    else if (e.getSource()==b9) {
        new UpdateRoom().setVisible(true);
        }
    else if (e.getSource()==b10) {
        try {
            new PickUp().setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    else if (e.getSource()==b11) {
        new SearchRoom().setVisible(true);
        }
    else if (e.getSource()==b12) {
       int a= JOptionPane.showConfirmDialog(null,"Do You Want To Exit?","Are You Sure?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       if(a==0){
           System.exit(0);
       }
       }
    }

    public static void main(String[] args) {
    new Reception();
    }
}
