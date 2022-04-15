import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;

public class LaunchPage implements ActionListener
{
    JFrame frame = new JFrame();
    Clock agahan = new Clock();
    JMenuBar jmb = new JMenuBar();
    JPanel panel = new JPanel();

    JMenu profile = new JMenu("Profile");
    JMenu add = new JMenu("Add");
    JMenu info = new JMenu("Info");

    Color background = new Color(153,0,0);
    JMenuItem accountButton = new JMenuItem("Account");
    JMenuItem reccomendationsButton = new JMenuItem("Reccomendations");
    JMenuItem scheduleButton = new JMenuItem("Schedule");
    JMenuItem medicationsButton = new JMenuItem("Medications");
    JMenuItem reportButton = new JMenuItem("Report");
    JMenuItem pointCalculatorButton = new JMenuItem("Point Calculator");
    JMenuItem activityButton = new JMenuItem("Activity");
    JMenuItem medicationButton = new JMenuItem("Medication");
    JMenuItem aboutButton = new JMenuItem("About");
    JMenuItem contactButton = new JMenuItem("Contact");
    JLabel welcome = new JLabel("Welcome to Managezilla!");
    public ScheduleWindow s;
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    public AccountWindow a;
    double sum;
    
    public LaunchPage()
    {
        frame.setJMenuBar(jmb);
        agahan.setBounds(450, 300, 200, 200);
        
        panel.setBackground(Color.GRAY);
        frame.setLayout(new BorderLayout());
        panel.add(agahan, BorderLayout.NORTH);
        panel.add(welcome, BorderLayout.SOUTH);
        
        panel.setBounds(400,400,100,100);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.add(panel);
        frame.setTitle("Managezilla");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        welcome.setFont(new Font("Serif", Font.BOLD, 80));
        welcome.setForeground(background);
        
        welcome.setBounds(400, 600, 200, 100);
        panel.add(welcome);

        jmb.add(profile);
        jmb.add(add);
        jmb.add(info);
        
        accountButton.addActionListener(this);
        reccomendationsButton.addActionListener(this);
        scheduleButton.addActionListener(this);
        medicationsButton.addActionListener(this);
        reportButton.addActionListener(this);
        pointCalculatorButton.addActionListener(this);
        activityButton.addActionListener(this);
        medicationButton.addActionListener(this);
        aboutButton.addActionListener(this);
        contactButton.addActionListener(this);

        profile.add(accountButton);
        profile.add(reccomendationsButton);
        profile.add(scheduleButton);
        profile.add(medicationsButton);
        profile.add(pointCalculatorButton);
        profile.add(reportButton);

        add.add(activityButton);
        add.add(medicationButton);
        
        info.add(aboutButton);
        info.add(contactButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == accountButton)
        {
            frame.dispose();
            a = new AccountWindow();
            a.setDailyPoint();
        }
        
        if(e.getSource() == reccomendationsButton)
        {
            frame.dispose();
            new RecommendationsWindow();
        }

        if(e.getSource() == scheduleButton)
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

        if(e.getSource() == medicationsButton)
        {
            frame.dispose();
            m = new MedicationsWindow();
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select * from  Medication";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                 
                 
                while (rs.next()) {
                    
                    m.getModel().addRow(new Object[]{rs.getString(2), rs.getString(3)});
                    
                 }
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
        }

        if(e.getSource() == reportButton)
        {
            frame.dispose();
            new ReportWindow();
        }

        if(e.getSource() == pointCalculatorButton)
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
                    if (sum >= 250) 
                {
                    frame.dispose();
                    new GoalPopUpWindow();
                }
                   
                 
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
        }

        if(e.getSource() == activityButton)
        {
            frame.dispose();
            new ActivityWindow();
        }

        if(e.getSource() == medicationButton)
        {
            frame.dispose();
            new MedicationWindow();
        }

        if(e.getSource() == aboutButton)
        {
            frame.dispose();
            new AboutWindow();
        }

        if(e.getSource() == contactButton)
        {
            frame.dispose();
            new ContactWindow();
        }
    }
}
