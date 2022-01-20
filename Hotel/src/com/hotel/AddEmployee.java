package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField name1,age1,salary1,phone1,id1,email1;
    JLabel name2,age2,gender,salary2,job2,phone2,id2,email2,jl;
    JRadioButton r1,r2;
    JButton s1,c1;
    JComboBox comboBox;
    SQLException e;

    public AddEmployee(){
        name1=new JTextField("Max 30 Characters");
        name2 = new JLabel("Name");
        name2.setBounds(10,10,120,25);
        name2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(name2);
        name1.setBounds(150,10,120,25);
        add(name1);

        age1=new JTextField("Max 3 Characters");
        age2 = new JLabel("Age");
        age2.setBounds(10,40,120,25);
        age2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(age2);

        age1.setBounds(150,40,120,25);
        add(age1);

        gender= new JLabel("Gender");
        gender.setBounds(10,70,120,25);
        gender.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(gender);
        r1 = new JRadioButton("Male");
        r1.setBounds(140,70,60,25);
        r1.setFont(new Font("monocursiva",Font.PLAIN,15));
        r1.setBackground(Color.white);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(200,70,80,25);
        r2.setFont(new Font("monocursiva",Font.PLAIN,15));
        r2.setBackground(Color.white);
        add(r2);

//        job1=new JTextField();
        job2 = new JLabel("Job");
        job2.setBounds(10,100,120,25);
        job2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(job2);
        String[] str={null,"Front Desk Clerks", "Porters","House Keeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant"};
        comboBox =new JComboBox(str);
       comboBox.setBounds(150,100,120,25);
       comboBox.setBackground(Color.white);
        add(comboBox);

        salary1=new JTextField("Max 10 Digits");
        salary2 = new JLabel("Salary");
        salary2.setBounds(10,130,120,25);
        salary2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(salary2);

        salary1.setBounds(150,130,120,25);
        add(salary1);

        phone1=new JTextField("Max 15 Digits");
        phone2 = new JLabel("Phone");
        phone2.setBounds(10,160,120,25);
        phone2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(phone2);

        phone1.setBounds(150,160,120,25);
        add(phone1);

        id1=new JTextField("Max 15 Digits");
        id2 = new JLabel("Id Number");
        id2.setBounds(10,190,120,25);
        id2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(id2);

        id1.setBounds(150,190,120,25);
        add(id1);

        email1=new JTextField("Max 30 Characters");
        email2 = new JLabel("Email");
        email2.setBounds(10,220,120,25);
        email2.setFont(new Font("monocursiva",Font.PLAIN,20));
        add(email2);

        email1.setBounds(150,220,120,25);
        add(email1);

        ImageIcon ii =new ImageIcon(ClassLoader.getSystemResource("tenth.jpg"));
        Image im=ii.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(im);
        jl = new JLabel(i);
        jl.setBounds(290,10,300,270);
        add(jl);

        JLabel j2=new JLabel("Add Employee Details");
        j2.setBounds(50,10,200,30);
        j2.setFont(new Font("serif",NORMAL,20));
        jl.add(j2);

        s1 = new JButton("Submit");
        s1.setBackground(Color.black);
        s1.setForeground(Color.white);
        s1.setBounds(10,250,100,25);
        add(s1);
        s1.addActionListener(this);

        c1=new JButton("Cancel");
        c1.setForeground(Color.white);
        c1.setBackground(Color.black);
        c1.setBounds(150,250,120,25);
        add(c1);
        c1.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);
        setBounds(400,200,600,320);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==c1){
//            JOptionPane.showMessageDialog(null,"Do you want to cancel?","Select the options",JOptionPane.YES_NO_OPTION);
             this.dispose();
        }
        else if(ae.getSource()==s1){
           String name= name1.getText();
           String age=age1.getText();
           String salary= salary1.getText();
           String phone=phone1.getText();
           String id= id1.getText();
           String email=email1.getText();
           String gender=null;

           if(r1.isSelected()){
               gender="Male";
           }
           else if (r2.isSelected()){
               gender ="Female";
           }
            String job=(String)comboBox.getSelectedItem();
            try {
                if(name!=null && age!=null  && gender!=null && job!=null && salary!=null && phone!=null && id!=null && email!=null){
                    MySQLConnection conn =new MySQLConnection();
                    String sq ="insert into employee values ('"+name+"','"+age+"','"+gender+"','"+job+"','"+salary+"','"+phone+"','"+id+"','"+email+"')";
                    conn.s.executeUpdate(sq);
                    JOptionPane.showMessageDialog(null,"Employee Added Successfully");
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter valid information in all fields");
//                    this.setVisible(false);
//                    new AddEmployee();
                }

            } catch (SQLException e) {
               e.printStackTrace();
                if(e!=null){
                    JOptionPane.showMessageDialog(null,"Please Enter Values within Specified range");

                }
            }
        }
    }


    public static void main(String[] args) {
        AddEmployee ae = new AddEmployee();
    }
}
