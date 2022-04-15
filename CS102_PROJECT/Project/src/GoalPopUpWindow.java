import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GoalPopUpWindow implements ActionListener
{
    JButton ok = new JButton("Thanks!");
    JLabel added = new JLabel("Congratulations! You have reached 250 points today.");
    JFrame frame = new JFrame();
    ImageIcon goalImage = new ImageIcon(("forGoalPopUp.jpg"));
    JLabel goalImageL = new JLabel(goalImage);

    PointCalculatorWindow p;
    double sum = 0;

    public GoalPopUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        goalImageL.setBounds(30, 30, 300, 240);
        frame.add(goalImageL);
        ok.addActionListener(this);
        frame.getContentPane().setBackground(Color.WHITE);

        frame.add(ok, BorderLayout.PAGE_END);
        frame.add(added, BorderLayout.NORTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == ok)
        {
            frame.dispose();
            p = new PointCalculatorWindow();
            
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString = "";
                sqlString = "Select * from  Activity";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                 
                 
                while (rs.next()) {
                    
                    p.getModel().addRow(new Object[]{rs.getString(2), rs.getString(6), rs.getString(5), rs.getString(7) });
                    
                    sum = sum + Integer.parseInt(rs.getString(6)) * 
                        (Double.parseDouble(rs.getString(5)) / 60);
                        System.out.println(sum);
                    }
                    
                    String str = String.valueOf(Math.round( sum * 100.0) / 100.0);
                    p.updateLabel(str);
                    p.updatePointBar((int)sum);
                    
                 
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            
        }
    }

}