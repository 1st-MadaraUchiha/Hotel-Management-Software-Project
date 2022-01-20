package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {
    JCheckBox cb;
    JLabel l1;
    JComboBox cob;
    JTable t;
    JButton b1,b2;
    MySQLConnection conn;
    ResultSet set;

    SearchRoom(){
        t=new JTable();
        t.setBounds(10,70,665,330);
        add(t);

        l1=new JLabel("Room type");
        l1.setBounds(10,10,150,25);
        add(l1);

        String[] s={"Single Room","Multi Bed Room","Guest Hall"};
        cob=new JComboBox(s);
        cob.setBounds(10,40,150,25);
        add(cob);

        cb= new JCheckBox("Exclude 'Not Available'");
        cb.setBounds(230,10,160,25);
        cb.setBackground(Color.white);
//        cb.addItemListener()
        add(cb);

        b1=new JButton("Search");
        b1.setBounds(10,410,100,25);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        b2= new JButton("Cancel");
        b2.setBounds(120,410,100,25);
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

//        getContentPane().setBackground(Color.white);
        setBounds(500,150,700,480);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String b= (String) cob.getSelectedItem();
    if(e.getSource()==b1){
        if(cb.isSelected()){
            try {
                conn = new MySQLConnection();
                set=conn.s.executeQuery("select * from rooms where availability='Available' AND room_type='"+b+"'");
                t.setModel(DbUtils.resultSetToTableModel(set));
            }
            catch(Exception ee){
           JOptionPane.showMessageDialog(null,"Error Connection To Database");
            }
        }
        else {
            try {
                conn = new MySQLConnection();
                set=conn.s.executeQuery("select * from rooms where room_type='"+b+"'");
                t.setModel(DbUtils.resultSetToTableModel(set));
            }
            catch(Exception ee){
                JOptionPane.showMessageDialog(null,"Error Connection To Database");
            }
        }
    }
    else if(e.getSource()==b2){
        this.dispose();
    }
    }

    public static void main(String[] args) {
        new SearchRoom().setVisible(true);
    }
}
