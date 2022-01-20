package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener{
    JLabel jl;
    public HotelManagementSystem() {
//        setSize(400,400);

//        setLocation(300,300);
        setBounds(200,200,1300,565);
        setResizable(false);
        setTitle("Hotel Management Software");
//        setIconImage();
        try{
        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("first.jpg"));
            this.jl = new JLabel(ii);}
        catch(Exception e) {
        JOptionPane.showMessageDialog(null,"Image first.jpg Not Found","Find Image",JOptionPane.ERROR_MESSAGE);
        }
        jl.setBounds(0,0,1366,565);
//        add(jl);
        JLabel jl2 = new JLabel("Hotel Management System");
        jl2.setBounds(0,450,1000,70);
        jl2.setForeground(Color.red);
        jl2.setFont(new Font("serif",Font.BOLD,50));
        jl.add(jl2);
        add(jl);
        JButton b1 = new JButton("Next");
        b1.setForeground(Color.BLACK);
//        b1.setBackground(Color.BLACK);
        b1.setBounds(750,470,110,40);
        b1.setFont(new Font("serif",Font.ITALIC,40));
        b1.addActionListener(this);
        jl.add(b1);

//        jl2.setBounds();
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
    HotelManagementSystem hms = new HotelManagementSystem();

    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.dispose();
        new Login().setVisible(true);

    }
}
