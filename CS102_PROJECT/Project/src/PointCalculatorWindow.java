import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class PointCalculatorWindow  implements ActionListener
{
    JMenuBar jmb = new JMenuBar();

    //creating the menu components
    JMenu profile = new JMenu("Profile");
    JMenu add = new JMenu("Add");
    JMenu info = new JMenu("Info");
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

    //creating the componentsof this window
    ImageIcon rewardImage = new ImageIcon(("reward.jpg"));
    JLabel rewardImageL = new JLabel(rewardImage);
    JFrame frame = new JFrame("Point Calculator");
    JLabel label2 = new JLabel("Point Calculator");
    JLabel currentPoint = new JLabel("Currentpoint is: ");
    JLabel progress = new JLabel( "This is Your Progress");
    
    double sum = 0;
    public ScheduleWindow s;
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    public AccountWindow a;
    DefaultTableModel model = new DefaultTableModel();
    SwingProgressBarExample bar = new SwingProgressBarExample();
    Color background = new Color(153,0,0);
    
    int winCounter = 0;
    

    public PointCalculatorWindow()
    {
        frame.setJMenuBar(jmb);

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

        //placing the components
        rewardImageL.setBounds(30, 30, 133, 100);
        label2.setBounds(370, 15 , 250 , 40);
        bar.setBounds(45, 150, 100, 180);
        progress.setBounds(40, 330, 150, 50);
        
        progress.setForeground(Color.RED);
        progress.setFont(new Font ("Courier", Font.BOLD, 12));
        frame.add(progress);
        
        //adding the components to the window (frame)
        frame.add(rewardImageL);
        frame.add(bar);
        
        
        currentPoint.setBounds(35 , 380, 350, 60);
        frame.add(currentPoint);
       
        
        //setting fonts, colours, and sizes
        label2.setForeground(background);
        label2.setFont(new Font ("Calibri", Font.BOLD, 30));
        frame.add(label2);
        
        
    
        //table model columns
        model.addColumn("Activity Name");
        model.addColumn("Point per Hour");
        model.addColumn("Duration");
        model.addColumn("Total Points");
       
        //creating the table
        JTable jt = new JTable(model);
        jt.setBounds(200,40,500,450);
        
        
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds( 200,50, 500,450);        
        frame.add(sp); 
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }

    //opening other windows
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
                String sqlString = "";
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
                    }
                    
                    
                    if (sum >= 250) 
                    {
                        frame.dispose();
                        new GoalPopUpWindow();
                    }
                    
                    String str = String.valueOf(Math.round( sum * 100.0) / 100.0);
                    p.updateLabel(str);
                    p.updatePointBar((int)sum);
                        
                    
                con.close();
                
                if (sum >= 250) {
                    
                
                }
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
    
    //displaying the model table
    public DefaultTableModel getModel() {
        
        return model;
    }
    
    //updating the points
    public void updateLabel( String s) {
        
        currentPoint.setText("The current point is: " + (s));
    }
    
    //in order to update the progress bar
    public void updatePointBar( int point) {
        
        bar.updateBar(point);
        
    }


}
