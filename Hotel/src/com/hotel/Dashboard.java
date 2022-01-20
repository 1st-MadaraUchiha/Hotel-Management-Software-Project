package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu m1,m2;
    JMenuItem i1,i2,i3,i4;
    public Dashboard(){
        mb=new JMenuBar();
        add(mb);
        m1=new JMenu("Hotel Management");
        m1.setForeground(Color.RED);

        m2=new JMenu("Admin");
        m2.setForeground(Color.green);
        mb.add(m1);
        mb.add(m2);

        i1=new JMenuItem("Reception");
        m1.add(i1);
        i1.addActionListener(this);

        i2=new JMenuItem("Add Employee");
        i2.addActionListener(this);
        m2.add(i2);

        i3=new JMenuItem("Add Rooms");
        m2.add(i3);
        i3.addActionListener(this);

        i4=new JMenuItem("Add Drivers");
        i4.addActionListener(this);
        m2.add(i4);
        mb.setBounds(0,0,1920,30);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("third.jpg"));
        Image i = ii.getImage().getScaledInstance(1920,1080,Image.SCALE_DEFAULT) ;
        ImageIcon iii = new ImageIcon(i);
        JLabel l = new JLabel(iii);
        l.setBounds(0,0,1920,1080);
        add(l);

        JLabel jLabel = new JLabel("WELCOME TO KHALIFA GROUP");
        jLabel.setBounds(500,50,700,40);
        jLabel.setForeground(Color.orange);
        jLabel.setFont(new Font("newtimesroman",Font.BOLD,40));
        l.add(jLabel);

        setLayout(null);
        setBounds(0,0,1920,1080);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==i2){
            new AddEmployee().setVisible(true);
        }
        else if(e.getSource()==i3){
            new AddRooms().setVisible(true);
        }
        else if(e.getSource()==i4){
            new AddDriver().setVisible(true);
        }
        else if(e.getSource()==i1){
            new Reception().setVisible(true);
        }
    }

    public static void main(String[] args) {
        Dashboard db = new Dashboard();
    }
}
