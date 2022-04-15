import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalTime;
import java.awt.*;


public class ActivityWindow implements ActionListener
{
    JMenuBar jmb = new JMenuBar();

    JMenu profile = new JMenu("Profile");
    JMenu add = new JMenu("Add");
    JMenu info = new JMenu("Info");
    String typeActivity;

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

    JFrame frame = new JFrame("Activity");

    JTextField activityNameText = new JTextField();
    JTextField activityText = new JTextField();
    JTextField startingText = new JTextField();
    JTextField endingText = new JTextField();
    JTextField descriptionText = new JTextField();
    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JLabel mainLabel = new JLabel("Add an Activity");
    JLabel activityName = new JLabel("Activity name");
    JLabel activityType = new JLabel("Activity type");
    JLabel startingTime = new JLabel("Starting time");
    JLabel endingTime = new JLabel("Ending time");
   

    JLabel hourLabel = new JLabel("Select the hour");
    JLabel minuteLabel = new JLabel("Select the minute");

    JLabel hourLabel2 = new JLabel("Select the hour");
    JLabel minuteLabel2 = new JLabel("Select the minute");
    
    JLabel typeLabel = new JLabel("Select the activity type: ");

    Activity activity;

    JComboBox<String> hour = new JComboBox<String>();
    JComboBox<String> minute = new JComboBox<String>();

    JComboBox<String> hour2 = new JComboBox<String>();
    JComboBox<String> minute2 = new JComboBox<String>();
    JComboBox<String> type = new JComboBox<String>();
    public ScheduleWindow s;
    Color background = new Color(0,0,204);
    Color background2 = new Color(204,0,0);
    Color lightBlue = new Color(51, 204, 255);
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    public AccountWindow a;
    double sum;
    
    double pointsOfSports = 0;
    double pointsOfRest = 0;
    double pointsOfEducation = 0;
    double pointsOfFun = 0;
    
    double timeOfSports = 0;
    double timeOfRest = 0;
    double timeOfEducation = 0;
    double timeOfFun = 0;
    int winCounter = 0;

    public ActivityWindow()
    {
        frame.setJMenuBar(jmb);

        frame.getContentPane().setBackground(lightBlue);
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

        activityNameText.setBounds(100,200,300,50);
        activityText.setBounds(100,300,300,50);
        startingText.setBounds(100,400,300,50);
        endingText.setBounds(100,500,300,50);

        mainLabel.setForeground(background); 
        activityName.setForeground(background);
        activityType.setForeground(background);
        startingTime.setForeground(background); 
        endingTime.setForeground(background);
        
        frame.add(activityNameText);

        addButton.setBounds(110,625,80,30);
        addButton.addActionListener(this);

        cancelButton.setBounds(310,625,80,30);
        cancelButton.addActionListener(this);
        
        frame.add(addButton);
        frame.add(cancelButton);

        mainLabel.setBounds(130,70,300,100);
        mainLabel.setFont( new Font("Consolas",Font.BOLD,35));
        
        activityName.setBounds(100,160,300,30);
        activityType.setBounds(100,260,300,30);
        startingTime.setBounds(100,360,300,30);
        endingTime.setBounds(100,460,300,30);

        
        frame.add(mainLabel);
        frame.add(activityName);
        frame.add(activityType);
        frame.add(startingTime);
        frame.add(endingTime);
        

        hourLabel.setBounds(100,380,100,30);
        frame.add(hourLabel);

        minuteLabel.setBounds(300,380,100,30);
        frame.add(minuteLabel);

        hourLabel2.setBounds(100,480,100,30);
        frame.add(hourLabel2);

        minuteLabel2.setBounds(300,480,100,30);
        frame.add(minuteLabel2);
        
        typeLabel.setBounds(100,300,300,50);
        frame.add(typeLabel);
        
        hourLabel.setForeground(background2);
        hourLabel2.setForeground(background2);
        minuteLabel.setForeground(background2);
        minuteLabel2.setForeground(background2);
        typeLabel.setForeground(background2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
        frame.setLayout(null);
        frame.setVisible(true);

        createComboBox();
        createComboBox2();
        createComboBox3();
        createComboBox4();
        createComboBox5();
        
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
        if(e.getSource() == addButton)
        {
            frame.dispose();
            new ActivityAddedPopUpWindow();
            
            if ( type.getSelectedItem().toString().equals("Fun")) {
                
                typeActivity = "Fun";
                activity = new Fun( activityNameText.getText(),LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
                Integer.parseInt(minute.getSelectedItem().toString()), 0, 0), LocalTime.of( Integer.parseInt
                (hour2.getSelectedItem().toString()),
                Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0));
                
                pointsOfFun = pointsOfFun + (activity.calculateTotalPoints());
                timeOfFun = timeOfFun + (activity.calculateTimeInterval());
                
            }
            
            else if ( type.getSelectedItem().toString().equals("Education")) {
                
                typeActivity = "Education";
                activity = new Education( activityNameText.getText(),LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
                Integer.parseInt(minute.getSelectedItem().toString()), 0, 0), LocalTime.of( Integer.parseInt
                (hour2.getSelectedItem().toString()),
                Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0));
                
                pointsOfEducation = pointsOfEducation + (activity.calculateTotalPoints());
                timeOfEducation = timeOfEducation + (activity.calculateTimeInterval());
                
            }
            
            else if ( type.getSelectedItem().toString().equals("Sports")) {
                
                typeActivity = "Sports";
                activity = new Sports( activityNameText.getText(),LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
                Integer.parseInt(minute.getSelectedItem().toString()), 0, 0), LocalTime.of( Integer.parseInt
                (hour2.getSelectedItem().toString()),
                Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0));
                
                
                pointsOfSports = pointsOfSports + (activity.calculateTotalPoints());
                timeOfSports = timeOfSports + (activity.calculateTimeInterval());
            }
            
            else if ( type.getSelectedItem().toString().equals("Rest")) {
                
                 
                typeActivity = "Rest";
                activity = new Rest( activityNameText.getText(),LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
                Integer.parseInt(minute.getSelectedItem().toString()), 0, 0), LocalTime.of( Integer.parseInt
                (hour2.getSelectedItem().toString()),
                Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0));
                
                pointsOfRest = pointsOfRest + (activity.calculateTotalPoints());
                timeOfRest = timeOfRest + (activity.calculateTimeInterval());
                
            }
             
            try 
            {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                 Statement stmt = con.createStatement();
                 String sqlString= "";
                 sqlString = "Insert into Report (sport, fun, rest, education) values ('";
                 sqlString = sqlString + getSportsPoint() + "','" + getFunPoint() + "'";
                 sqlString = sqlString + ",' " + getRestPoint() + "','" +  getEducationPoint() + "')";
                 
                 System.out.println(sqlString);
                 stmt.executeUpdate(sqlString); 
                 
                
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            
            
            String interval = LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
            Integer.parseInt(minute.getSelectedItem().toString()), 0, 0) + " - " +  LocalTime.of( Integer.parseInt
            (hour2.getSelectedItem().toString()),Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0);
            System.out.println(interval);
            
            try 
            {
                 
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString= "";
                sqlString = "Insert into Recommend (sport, fun, rest, education) values ('";
                sqlString = sqlString + getSportsTime() + "','" + getFunTime() + "'";
                sqlString = sqlString + ",' " + getRestTime() + "','" +  getEducationTime() + "')";
                
                System.out.println(sqlString);
                stmt.executeUpdate(sqlString); 
                
                ResultSet rs =  stmt.executeQuery(sqlString); 
                
                
               
                con.close();
           } catch (Exception ex) {
               
               System.out.println(ex);
           }
            try 
            {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                 Statement stmt = con.createStatement();
                 String sqlString="";
                 sqlString = "Insert into Activity (Name, Activity_Type, Time_Interval, Duration, Point, Total) values ('";
                 sqlString = sqlString + activityNameText.getText() + "','" + typeActivity + "'";
                 sqlString = sqlString + ",' " + interval + "','" + Math.round(((activity.calculateTimeInterval() * 60) * 100)) / 100 + "','" + activity.getPoints() + 
                 "','" + activity.calculateTotalPoints() + "')";
                 
                 System.out.println(sqlString);
                 stmt.executeUpdate(sqlString); 
                
                
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
        }  
        
        if(e.getSource() == cancelButton)
        {
            frame.dispose();
            new LaunchPage();
        }
    }

    public JPanel createComboBox() 
    {
        hour.addItem("00");
        hour.addItem("01");
        hour.addItem("02");
        hour.addItem("03");
        hour.addItem("04");
        hour.addItem("05");
        hour.addItem("06");
        hour.addItem("07");
        hour.addItem("08");
        hour.addItem("09");
        hour.addItem("10");
        hour.addItem("11");
        hour.addItem("12");
        hour.addItem("13");
        hour.addItem("14");
        hour.addItem("15");
        hour.addItem("16");
        hour.addItem("17");
        hour.addItem("18");
        hour.addItem("19");
        hour.addItem("20");
        hour.addItem("21");
        hour.addItem("22");
        hour.addItem("23");

        hour.setEditable(false);
        hour.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(hour);
        panel.setBounds(65,400,170,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }

    public JPanel createComboBox2() 
    {
        minute2.addItem("00");
        minute2.addItem("01");
        minute2.addItem("02");
        minute2.addItem("03");
        minute2.addItem("04");
        minute2.addItem("05");
        minute2.addItem("06");
        minute2.addItem("07");
        minute2.addItem("08");
        minute2.addItem("09");
        minute2.addItem("10");
        minute2.addItem("11");
        minute2.addItem("12");
        minute2.addItem("13");
        minute2.addItem("14");
        minute2.addItem("15");
        minute2.addItem("16");
        minute2.addItem("17");
        minute2.addItem("18");
        minute2.addItem("19");
        minute2.addItem("20");
        minute2.addItem("21");
        minute2.addItem("22");
        minute2.addItem("23");
        minute2.addItem("24");
        minute2.addItem("25");
        minute2.addItem("26");
        minute2.addItem("27");
        minute2.addItem("28");
        minute2.addItem("29");
        minute2.addItem("30");
        minute2.addItem("31");
        minute2.addItem("32");
        minute2.addItem("33");
        minute2.addItem("34");
        minute2.addItem("35");
        minute2.addItem("36");
        minute2.addItem("37");
        minute2.addItem("38");
        minute2.addItem("39");
        minute2.addItem("40");
        minute2.addItem("41");
        minute2.addItem("42");
        minute2.addItem("43");
        minute2.addItem("44");
        minute2.addItem("45");
        minute2.addItem("46");
        minute2.addItem("47");
        minute2.addItem("48");
        minute2.addItem("49");
        minute2.addItem("50");
        minute2.addItem("51");
        minute2.addItem("52");
        minute2.addItem("53");
        minute2.addItem("54");
        minute2.addItem("55");
        minute2.addItem("56");
        minute2.addItem("57");
        minute2.addItem("58");
        minute2.addItem("59"); 
        
        
        minute2.setEditable(false);
        minute2.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.add(minute2);
        panel.setBounds(200,500,300,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }

    public JPanel createComboBox3() 
    {
        hour2.addItem("00");
        hour2.addItem("00");
        hour2.addItem("01");
        hour2.addItem("02");
        hour2.addItem("03");
        hour2.addItem("04");
        hour2.addItem("05");
        hour2.addItem("06");
        hour2.addItem("07");
        hour2.addItem("08");
        hour2.addItem("09");
        hour2.addItem("10");
        hour2.addItem("11");
        hour2.addItem("12");
        hour2.addItem("13");
        hour2.addItem("14");
        hour2.addItem("15");
        hour2.addItem("16");
        hour2.addItem("17");
        hour2.addItem("18");
        hour2.addItem("19");
        hour2.addItem("20");
        hour2.addItem("21");
        hour2.addItem("22");
        hour2.addItem("23");

        hour2.setEditable(false);
        hour2.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(hour2);
        panel.setBounds(65,500,170,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }

    public JPanel createComboBox4() 
    {
        minute.addItem("00");
        minute.addItem("01");
        minute.addItem("02");
        minute.addItem("03");
        minute.addItem("04");
        minute.addItem("05");
        minute.addItem("06");
        minute.addItem("07");
        minute.addItem("08");
        minute.addItem("09");
        minute.addItem("10");
        minute.addItem("11");
        minute.addItem("12");
        minute.addItem("13");
        minute.addItem("14");
        minute.addItem("15");
        minute.addItem("16");
        minute.addItem("17");
        minute.addItem("18");
        minute.addItem("19");
        minute.addItem("20");
        minute.addItem("21");
        minute.addItem("22");
        minute.addItem("23");
        minute.addItem("24");
        minute.addItem("25");
        minute.addItem("26");
        minute.addItem("27");
        minute.addItem("28");
        minute.addItem("29");
        minute.addItem("30");
        minute.addItem("31");
        minute.addItem("32");
        minute.addItem("33");
        minute.addItem("34");
        minute.addItem("35");
        minute.addItem("36");
        minute.addItem("37");
        minute.addItem("38");
        minute.addItem("39");
        minute.addItem("40");
        minute.addItem("41");
        minute.addItem("42");
        minute.addItem("43");
        minute.addItem("44");
        minute.addItem("45");
        minute.addItem("46");
        minute.addItem("47");
        minute.addItem("48");
        minute.addItem("49");
        minute.addItem("50");
        minute.addItem("51");
        minute.addItem("52");
        minute.addItem("53");
        minute.addItem("54");
        minute.addItem("55");
        minute.addItem("56");
        minute.addItem("57");
        minute.addItem("58");
        minute.addItem("59"); 
        
        
        minute.setEditable(false);
        minute.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.add(minute);
        panel.setBounds(200,400,300,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }
    
    public JPanel createComboBox5() 
    {
        type.addItem("Rest");  
        type.addItem("Sports"); 
        type.addItem("Fun"); 
        type.addItem("Education"); 
        
        
        minute.setEditable(false);
        minute.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.add(type);
        panel.setBounds(250,310,100,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }
    
    public double getFunPoint() {
        
        return pointsOfFun;
    }
    
    public double getEducationPoint() {
        
        return pointsOfEducation;
    }
    
    public double getRestPoint() {
        
        return pointsOfRest;
    }
    
    public double getSportsPoint() {
        
        return pointsOfSports;
    }
    
    public double getFunTime() {
        
        return timeOfFun;
    }
    
    public double getEducationTime() {
        
        return timeOfEducation;
    }
    
    public double getRestTime() {
        
        return timeOfRest;
    }
    
    public double getSportsTime() {
        
        return timeOfSports;
    }
    
    
}

