import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextField;


public class EditWindow implements ActionListener 
{
    JFrame jf = new JFrame("Create/Edit profile");
    JLabel labelName = new JLabel("Enter Your Name: ");
    JTextField textName = new JTextField(3);
    JLabel label1 = new JLabel("Weight(kg): ");
    JTextField text1 = new JTextField(3);
    JLabel label2 = new JLabel("Height(cm): "); 
    JTextField text2 = new JTextField(3);
    JButton editButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    Color background1 = new Color(171,232,236);
    AccountWindow a;
    
    public EditWindow()
    {
        jf.setSize( 500 , 350);
        jf.setLocation(100, 300);
        jf.setLayout(null);
        
        labelName.setBounds (60, 60 , 180, 30);
        textName.setBounds  (180, 60 , 150, 30);

        label1.setBounds (60, 100 , 80, 30);
        text1.setBounds  (130, 100 , 80, 30);
        label2.setBounds (250, 100, 80, 30);
        text2.setBounds  (320, 100, 80, 30);


        editButton.setBounds(150, 250, 80, 40);
        cancelButton.setBounds(250, 250, 80, 40);
        
        editButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        
        jf.add(labelName);
        jf.add(textName);
        jf.add(label1);
        jf.add(text1);
        jf.add(label2);
        jf.add(text2);

        jf.add(editButton);
        jf.add(cancelButton);
        
        jf.getContentPane().setBackground(background1);
        
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setPreferredSize( new Dimension( 450,350));
        jf.setResizable(false);
        jf.setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == editButton )
        {
        
            //a.setDailyPoint();
            
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString = "";
                sqlString = "Select * from  Account";
                
                ResultSet rs =  stmt.executeQuery(sqlString); 
                Boolean existRecord= false;
               
                while (rs.next()) {
                    existRecord=true;
                }
                if (!existRecord) {
                    // New Entry
                    sqlString = "insert into Account (name,height,weight) values ('"+  textName.getText() + "', "+text1.getText() +", "+ text2.getText()+")";
                } else {
                    // Update Entry
                    sqlString = "update Account set name='"+textName.getText() +"', height="+text1.getText() +", weight="+text2.getText() ;
                }
                System.out.println(sqlString);
                stmt.executeUpdate(sqlString); 
                System.out.println("Saved!!");
                con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            

            jf.dispose();
            JOptionPane.showMessageDialog(jf, "Account information saved !!!.", "Information",
            JOptionPane.PLAIN_MESSAGE);
            a = new AccountWindow();
            a.setDailyPoint();


            
        }
        
        if(e.getSource() == cancelButton)
        {
            jf.dispose();
            
            a = new AccountWindow();
            a.setDailyPoint();
        }
    }
    
   
}