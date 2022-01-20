package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddDriver extends JFrame implements ActionListener {
    JLabel nl,al,gl,coml,modl,avail,locl;
    JTextField nt,at,comt,modt,loct;
    JComboBox gc,avaic;
    JButton bc,bs;
    MySQLConnection conn;

    public AddDriver(){
        nl = new JLabel("Name");
        nl.setBounds(10,10,120,25);
        nl.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(nl);
        nt = new JTextField("Max 30 Characters");
        nt.setBounds(150,10,120,25);
        add(nt);

        al = new JLabel("Age");
        al.setBounds(10,40,120,25);
        al.setFont(new Font("a",Font.PLAIN,20));
        add(al);
        at=new JTextField("Max 3 Digits");
        at.setBounds(150,40,120,25);
        add(at);

        gl = new JLabel("Gender");
        gl.setBounds(10,70,120,25);
        gl.setFont(new Font("a",Font.PLAIN,20));
        add(gl);
        String[] str ={null,"Male","Female"};
        gc = new JComboBox(str);
        gc.setBounds(150,70,120,25);
        gc.setBackground(Color.white);
        add(gc);

        coml = new JLabel("Car Company");
        coml.setBounds(10,100,120,25);
        coml.setFont(new Font("a",Font.PLAIN,20));
        add(coml);
        comt = new JTextField("Max 20 Character");
        comt.setBounds(150,100,120,25);
        add(comt);

        modl = new JLabel("Car Model");
        modl.setBounds(10,130,120,25);
        modl.setFont(new Font("serif",Font.PLAIN,20));
        add(modl);
        modt = new JTextField("Max 20 Character");
        modt.setBounds(150,130,120,25);
        add(modt);

        avail = new JLabel("Availability");
        avail.setBounds(10,160,120,25);
        avail.setFont(new Font("serif",Font.PLAIN,20));
        add(avail);
        String[] str2={null,"Available","Not Available"};
        avaic=new JComboBox(str2);
        avaic.setBounds(150,160,120,25);
        avaic.setBackground(Color.white);
        add(avaic);

        locl = new JLabel("Location");
        locl.setFont(new Font("a",Font.PLAIN,20));
        locl.setBounds(10,190,120,25);
        add(locl);
        loct = new JTextField("Max 30 Character");
        loct.setBounds(150,190,120,25);
        add(loct);

        bs = new JButton("Submit");
        bs.setBounds(10,220,120,25);
        bs.setForeground(Color.white);
        bs.setBackground(Color.BLACK);
        bs.setFont(new Font("serif",Font.PLAIN,20));
        bs.addActionListener(this);
        add(bs);

        bc = new JButton("Cancel");
        bc.setFont(new Font("serif",Font.PLAIN,20));
        bc.setBounds(150,220,120,25);
        bc.setForeground(Color.white);
        bc.setBackground(Color.black);
        bc.addActionListener(this);
        add(bc);

        JLabel text = new JLabel("Add Drivers");
        text.setBounds(400,10,120,25);
        text.setFont(new Font("serif",Font.PLAIN,20));
        add(text);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("eleven.jpg"));
        Image iii= ii.getImage().getScaledInstance(250,280,Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(iii);
        JLabel image= new JLabel(i);
        image.setBounds(300,40,250,280);
        add(image);

        getContentPane().setBackground(Color.white);
        setLayout(null);
        setResizable(false);
        setBounds(400,200,600,300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bs) {
            String name = nt.getText();
            String age = at.getText();
            String gender = (String) gc.getSelectedItem();
            String carcompany = comt.getText();
            String carmodel = modt.getText();
            String availability = (String) avaic.getSelectedItem();
            String location = loct.getText();
            if (name != null && age != null && gender != null && carcompany != null && carmodel != null && availability != null && location != null) {
            if(name.length()<=30 && age.length()<=3 && carcompany.length()<=20 && carmodel.length()<=20 && location.length()<=30){
                try {
                    this.conn = new MySQLConnection();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error Connection With Database");
                }
                String qury ="insert into driver values('"+name+"','"+age+"','"+gender+"','"+carcompany+"','"+carmodel+"','"+availability+"','"+location+"')";
                try {
                    conn.s.executeUpdate(qury);
                    JOptionPane.showMessageDialog(null,"Data Successfully Added To Database");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"(One Or More Column) Or Table Doesn't Exist");
                }
            }
            else {
            JOptionPane.showMessageDialog(null,"Characters Limit Or Digits Limit Exceed In One Or More Fields");
            }
            }
            else {
                JOptionPane.showMessageDialog(null, "Please Enter Data In All Fields");
            }
        }
        else if(e.getSource()==bc){
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}
