package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AddRooms extends JFrame implements ActionListener {
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jt1,jt4;
    JComboBox jc2,jc3,jc5;
    JButton js,jc;
    MySQLConnection connection;
    AddRooms(){
        jl1 = new JLabel("Room No");
        jl1.setBounds(10,10,120,25);
        jl1.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(jl1);
        jt1=new JTextField("Max 4 Digits");
        jt1.setBounds(150,10,120,25);
        add(jt1);

        jl2 = new JLabel("Availability");
        jl2.setBounds(10,40,120,25);
        jl2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(jl2);
        String[] st={null,"Available","Not Available"};
        jc2 = new JComboBox(st);
        jc2.setBounds(150,40,120,25);
        jc2.setBackground(Color.white);
        add(jc2);

        jl3 = new JLabel("Clean Status");
        jl3.setBounds(10,70,120,25);
        jl3.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(jl3);
        String[] st1={null,"Cleaned","Dirty"};
        jc3 = new JComboBox(st1);
        jc3.setBounds(150,70,120,25);
        jc3.setBackground(Color.white);
        add(jc3);

        jl4 = new JLabel("Price");
        jl4.setBounds(10,100,120,25);
        jl4.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(jl4);
        jt4=new JTextField("Max 6 Digits");
        jt4.setBounds(150,100,120,25);
        add(jt4);

        jl5 = new JLabel("Room Type");
        jl5.setBounds(10,130,120,25);
        jl5.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(jl5);
        String[] st3={null,"Single Room","Multi Bed Room","Guest Hall"};
        jc5 = new JComboBox(st3);
        jc5.setBounds(150,130,120,25);
        jc5.setBackground(Color.white);
        add(jc5);

        js = new JButton("Submit");
        js.setBounds(10,170,120,25);
        js.setBackground(Color.black);
        js.setForeground(Color.white);
        js.addActionListener(this);
        add(js);

        jc = new JButton("Cancel");
        jc.setBounds(150,170,120,25);
        jc.setBackground(Color.black);
        jc.setForeground(Color.white);
        jc.addActionListener(this);
        add(jc);

        jl6 =new JLabel("Add Rooms");
        jl6.setBounds(100,10,200,30);
//        jl6.setBounds(400,10,200,30);
        jl6.setFont(new Font("serif",NORMAL,20));
//        add(jl6);

        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("twelve.jpg"));
        Image ii =i.getImage().getScaledInstance(290,190,Image.SCALE_DEFAULT);
        ImageIcon iii =new ImageIcon(ii);
        jl7 = new JLabel(iii);
        jl7.setBounds(290,10,290,190);
        jl7.add(jl6);
        add(jl7);

        getContentPane().setBackground(Color.white);
        setResizable(false);
        setLayout(null);
        setBounds(400,200,600,250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==js){
            String roomno=jt1.getText();
            String available = (String) jc2.getSelectedItem();
            String clean = (String) jc3.getSelectedItem();
            String price=jt4.getText();
            String type =(String)jc5.getSelectedItem();
            if (roomno != null && available != null && clean != null && price != null && type != null) {
                if(roomno.length()<=4 && price.length()<=6) {
                   try {
                       connection = new MySQLConnection();
                   } catch (Exception ae) {
//                    ae.printStackTrace();
                       if (ae != null) {
                           JOptionPane.showMessageDialog(null, "Error Connection To Database");
                       }
                   }
                   String str = "insert into rooms values('" + roomno + "','" + available + "','" + clean + "','" + price + "','" + type + "')";
                   try {
                       connection.s.executeUpdate(str);
                       JOptionPane.showMessageDialog(null, "Data Added To Database");
                   } catch (Exception eu) {
                       eu.printStackTrace();
                       JOptionPane.showMessageDialog(this, "Error In Database Table", "Message", JOptionPane.ERROR_MESSAGE);
                   }
               }
               else {
                   JOptionPane.showMessageDialog(null,"Data Too Long For One Or More Entries");

               }
           }
           else{
               JOptionPane.showMessageDialog(null, "Pleas Enter All Values");
           }
        }
        else if(e.getSource()==jc){
           this.dispose();
        }
    }

    public static void main(String[] args) {
        new AddRooms();
    }

}
