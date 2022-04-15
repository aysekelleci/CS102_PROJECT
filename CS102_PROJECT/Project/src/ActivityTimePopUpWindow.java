import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActivityTimePopUpWindow implements ActionListener
{
    JButton ok = new JButton("Check Schedule");
    JLabel added = new JLabel("It is time for an activity!");
    JFrame frame = new JFrame();
    ImageIcon activityImage = new ImageIcon(("ActivityImage.jpg"));
    JLabel activityImageL = new JLabel(activityImage);
    ScheduleWindow s;

    public ActivityTimePopUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        ok.addActionListener(this);
        activityImageL.setBounds(100, 90, 300, 206);
        frame.add(ok, BorderLayout.PAGE_END);
        frame.add(added, BorderLayout.NORTH);
        frame.add(activityImageL);
        frame.getContentPane().setBackground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == ok)
        {
            frame.dispose();
            s = new ScheduleWindow();
            
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select * from  Activity";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                 
                 
                while (rs.next()) {
                    
                    s.getModel().addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) });
                    
                 }
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
        }
    }

}