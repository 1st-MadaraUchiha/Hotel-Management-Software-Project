package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCustomer extends JFrame implements ActionListener {
    JLabel j1,j2,j3,j4,j5,j6,j7,j8;
    JTextField t2,t3,t5,t8;
    JComboBox c1,c6,c7;
    JRadioButton rb1,rb2;
    JButton b1,b2;
   ResultSet se,see;
   MySQLConnection connection;
   Choice ch;
   String avail,clea,okie,se2;
    AddCustomer()  {
        j1=new JLabel("Id");
        j1.setBounds(10,10,150,25);
        add(j1);
        String[] str={null,"Passport","License","Voter ID","Citizenship"};
        c1 = new JComboBox(str);
        c1.setBounds(150,10,150,25);
        c1.setBackground(Color.WHITE);
        add(c1);

        j2=new JLabel("Mobile Number");
        j2.setBounds(10,40,150,25);
        add(j2);
        t2 = new JTextField("Max 15 digits");
        t2.setBounds(150,40,150,25);
        t2.setBackground(Color.WHITE);
        add(t2);

        j3=new JLabel("Name");
        j3.setBounds(10,70,150,25);
        add(j3);
        t3 = new JTextField("Max 30 Characters");
        t3.setBounds(150,70,150,25);
        t3.setBackground(Color.WHITE);
        add(t3);

        j4=new JLabel("Gender");
        j4.setBounds(10,100,150,25);
        add(j4);
        rb1 =new JRadioButton("Male");
        rb1.setBounds(150,100,55,25);
        rb1.setBackground(Color.WHITE);
        add(rb1);
        rb2 = new JRadioButton("Female");
        rb2.setBounds(205,100,80,25);
        rb2.setBackground(Color.white);
        add(rb2);

        j5=new JLabel("Country");
        j5.setBounds(10,130,150,25);
        add(j5);
        t5 = new JTextField("Max 15 digits");
        t5.setBounds(150,130,150,25);
        t5.setBackground(Color.WHITE);
        add(t5);

        j6=new JLabel("Room No");
        j6.setBounds(10,160,140,25);
        add(j6);
        ch = new Choice();
        try{
           MySQLConnection c=new MySQLConnection();
           String s="select * from rooms";
           ResultSet st = c.s.executeQuery(s);
           while ((st.next())){
           ch.add(st.getString("roomno"));
           }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error In Table 'rooms' Or Column 'roomno'");
        }
        ch.setBounds(150,160,150,25);
        ch.setBackground(Color.white);
        ch.setBounds(150,160,150,25);
        add(ch);


        j7 = new JLabel("Checked In");
        j7.setBounds(10,190,150,25);
        add(j7);
        String[] c77={null,"Yes","No"};
        c7 = new JComboBox(c77);
        c7.setBounds(150,190,150,25);
        add(c7);

        j8 = new JLabel("Deposit");
        j8.setBounds(10,220,150,25);
        add(j8);
        t8 = new JTextField("Max 5 Digits");
        t8.setBounds(150,220,150,25);
        add(t8);

        b1 = new JButton("Add Customer");
        b1.setBounds(10,250,120,25);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(160,250,90,25);
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("fifth.png"));
        Image ii = i.getImage().getScaledInstance(300,450,Image.SCALE_DEFAULT);
        ImageIcon iii = new ImageIcon(ii);
        JLabel jl = new JLabel(iii);
        jl.setBounds(270,0,300,450);
        add(jl);

        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(400,200,550,500);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String id=(String) c1.getSelectedItem();
            String mobileno = t2.getText();
            String name = t3.getText();
            String gender =null ;
            if(rb1.isSelected()){
                gender="Male";
            }
            else if (rb2.isSelected()){
                gender="Female";
            }
            String country=t5.getText();
            String roomno= ch.getSelectedItem();
            String checkedin=(String) c7.getSelectedItem();
            String deposit = t8.getText();

            if (id != null && mobileno != null && name != null && gender != null && country != null && roomno!=null && checkedin!=null && deposit!=null) {
                if(roomno.length()<=4 && deposit.length()<=5 && country.length()<=15 && name.length()<=30 && mobileno.length()<=15) {
                    try {
                       connection = new MySQLConnection();
                    } catch (Exception ae) {
                        if (ae != null) {
                            JOptionPane.showMessageDialog(null, "Error Connection To Database");
                        }
                    }
                    String avai ="select availability from rooms where roomno='"+roomno+"'";
                    String clea ="select clean_status from rooms where roomno='"+roomno+"'";

                       try {
                          see = connection.s.executeQuery(clea);
                           while(see.next())
                           {
                               clea=see.getString("clean_status");
                               this.clea=clea;
                           }
                           se = connection.s.executeQuery(avai);
                           while(se.next())
                           {
                               avail=se.getString("availability");
                               this.avail=avail;
                           }
                       }
                       catch (SQLException ex) {
                           JOptionPane.showMessageDialog(null,"Error In Column Of Table");
                       }
                        if (avail.equals("Available") && clea.equals("Cleaned")){
                            String str = "insert into customer values('" +id + "','" +mobileno +"','" +name +"','" +gender+"','"+country+"','" +roomno+"','"+checkedin+"','" +deposit+"')";
                            try {
                                connection.s.executeUpdate(str);
                                JOptionPane.showMessageDialog(null, "Data Added To Database");
                                okie ="update rooms set availability='Not Available' where roomno='"+roomno+"'";
                                connection.s.executeUpdate(okie);
                                se2 ="update rooms set clean_status='Dirty' where roomno='"+roomno+"'";
                                connection.s.executeUpdate(se2);

                            }
                            catch (Exception eu) {
                                eu.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Error In Database Table", "Message", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else if (avail.equals("Not Available") || clea.equals("Dirty")){
                            JOptionPane.showMessageDialog(null,"Room Is Dirty or Not Available");
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
        else if (e.getSource()==b2){
           this.dispose();
        }
    }

    public static void main(String[] args) {

        new AddCustomer();
    }
}
