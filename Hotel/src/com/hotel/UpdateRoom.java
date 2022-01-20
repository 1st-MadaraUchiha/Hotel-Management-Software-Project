package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateRoom extends JFrame implements ActionListener {
    JLabel j1,j2,j3,j4;
    JLabel t1,t2,t3;
    Choice ch1,ch2;
    JComboBox c3,c4;
    JButton b1,b2,b3;
    ResultSet set,set2,set3,set4;
    MySQLConnection conn;
    UpdateRoom() {
      j1=new JLabel("Phone Number");
      j1.setBounds(10,10,140,25);
      add(j1);
      ch1=new Choice();
      try {
          conn = new MySQLConnection();
          set = conn.s.executeQuery("select * from customer");
          this.set=set;
      }
      catch(Exception e){
          e.printStackTrace();
      }
      try {
          while (set.next()) {
              ch1.add(set.getString("mobileno"));
          }
      }
      catch (Exception eee){
          eee.printStackTrace();
      }
      ch1.setBounds(150,10,150,25);
      add(ch1);

      j2= new JLabel("Room Number");
      j2.setBounds(10,40,140,25);
      add(j2);
      ch2=new Choice();
      try {
          MySQLConnection connection = new MySQLConnection();
          set2 = connection.s.executeQuery("select * from rooms");
          while (set2.next()) {
              ch2.add(set2.getString("roomno"));
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Either Table 'rooms' or Column 'roomno' Doesn't Exists ");
          }
      ch2.setBounds(150,40,150,25);
      add(ch2);
        t1=new JLabel();
        t1.setBounds(300,40,150,25);
        add(t1);

      j3= new JLabel("Availability");
      j3.setBounds(10,70,140,25);
      add(j3);
      String[] str={null,"Available","Not Available"};
      c3 = new JComboBox(str);
      c3.setBounds(150,70,150,25);
      c3.setBackground(Color.white);
      add(c3);
        t2=new JLabel();
        t2.setBounds(300,70,150,25);
        add(t2);

      j4 = new JLabel("Clean Status");
      j4.setBounds(10,100,140,25);
      add(j4);
      String[] stt ={null,"Cleaned","Dirty"};
      c4 = new JComboBox(stt);
      c4.setBackground(Color.white);
      c4.setBounds(150,100,150,25);
      add(c4);
        t3=new JLabel();
        t3.setBounds(300,100,150,25);
        add(t3);

      b1 = new JButton("Check");
      b1.setBounds(100,130,100,25);
      b1.setForeground(Color.white);
      b1.setBackground(Color.black);
      b1.addActionListener(this);
      add(b1);

      b2=new JButton("Update");
      b2.setBounds(10,160,100,25);
      b2.setForeground(Color.white);
      b2.setBackground(Color.black);
      b2.addActionListener(this);
      add(b2);

      b3=new JButton("Back");
      b3.setBackground(Color.black);
      b3.setForeground(Color.white);
      b3.setBounds(150,160,100,25);
      b3.addActionListener(this);
      add(b3);

      getContentPane().setBackground(Color.white);
      setBounds(400,200,400,250);
      setLayout(null);
      setResizable(false);
      setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String number=ch1.getSelectedItem();
            String room2=null;
            try{
                set3=conn.s.executeQuery("select * from customer where mobileno='"+number+"'");
                while(set3.next()){
                    t1.setText(set3.getString("roomno"));
                    room2=set3.getString("roomno");
                }
                set4= conn.s.executeQuery("select * from rooms where roomno='"+room2+"'");
                while(set4.next()){
                    t2.setText(set4.getString("availability"));
                    t3.setText(set4.getString("clean_status"));
                }
            }
            catch (Exception ae){
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error scanning database");
            }
        }
        else if (e.getSource()==b2){
            String roomno=ch2.getSelectedItem();
            String availability = (String) c3.getSelectedItem();
            String cleanstatus=(String) c4.getSelectedItem();
        if(availability!=null && cleanstatus!=null) {
            try {
                conn.s.executeUpdate("update rooms set availability='"+availability+"', clean_status='"+cleanstatus+"' where roomno='"+roomno+"'");
                JOptionPane.showMessageDialog(null,"Data Updated In Table 'rooms'");
            }
            catch(Exception ee){
                JOptionPane.showMessageDialog(null,"Table Or Columns Doesn't Exits");
                }
        }
        else if (availability==null || cleanstatus==null){
            JOptionPane.showMessageDialog(null,"Enter Values In All Fields");
        }
        }
        else if (e.getSource()==b3){
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
