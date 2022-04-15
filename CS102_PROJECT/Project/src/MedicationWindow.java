import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalTime;
import java.awt.*;

public class MedicationWindow implements ActionListener
{
    JMenuBar jmb = new JMenuBar();

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

    JFrame frame = new JFrame("Activity");

    JTextField medicationNameText = new JTextField();
    JTextField activityText = new JTextField();
    JTextField startingText = new JTextField();
    JTextField endingText = new JTextField();
    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JLabel mainLabel = new JLabel("Add a Medicine");
    JLabel medicationName = new JLabel("Medication name");

    JLabel startingTime = new JLabel("Medication time");
    

    JLabel hourLabel = new JLabel("Select the hour");
    JLabel minuteLabel = new JLabel("Select the minute");


    Activity activity;
    Reminders medicine;

    JComboBox<String> hour = new JComboBox<String>();
    JComboBox<String> minute = new JComboBox<String>();

    JComboBox<String> hour2 = new JComboBox<String>();
    JComboBox<String> minute2 = new JComboBox<String>();
    JComboBox<String> type = new JComboBox<String>();
    public ScheduleWindow s;
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    Color background = new Color(0,0,204);
    Color background2 = new Color(204,0,0);
    Color lightBlue = new Color(51, 204, 255);
    public AccountWindow a;
    double sum;

    public MedicationWindow()
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

        mainLabel.setBounds(130,70,300,40);
        mainLabel.setFont( new Font("Consolas",Font.BOLD,35));
        
        medicationNameText.setBounds(100,200,300,50);;
        
        mainLabel.setForeground(background); 
        startingTime.setForeground(background);
        medicationName.setForeground(background);
        hourLabel.setForeground(background2);
        minuteLabel.setForeground(background2);

        frame.add(medicationNameText);

        addButton.setBounds(115,475,80,30);
        addButton.addActionListener(this);

        cancelButton.setBounds(315,475,80,30);
        cancelButton.addActionListener(this);
        
        frame.add(addButton);
        frame.add(cancelButton);

        medicationName.setBounds(100,160,300,30);
        startingTime.setBounds(100,300,300,30);
       
        
        frame.add(mainLabel);
        frame.add(medicationName);
        frame.add(startingTime);
        

        hourLabel.setBounds(100,340,100,30);
        frame.add(hourLabel);

        minuteLabel.setBounds(300,340,100,30);
        frame.add(minuteLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
        frame.setLayout(null);
        frame.setVisible(true);

        createComboBox();
        createComboBox2();      
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
                    
                    p.getModel().addRow(new Object[]{rs.getString(2), rs.getString(6), rs.getString(5),rs.getString(7) });
                        
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
            new MedicationAddedPopUpWindow();
            
            String interval = "" + LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
            Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0);
            
            
            medicine = new Medicine(medicationNameText.getText(), LocalTime.of( Integer.parseInt(hour.getSelectedItem().toString()),
            Integer.parseInt(minute2.getSelectedItem().toString()), 0, 0) );
            
            try 
            {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                 Statement stmt = con.createStatement();
                 String sqlString="";
                 sqlString = "Insert into Medication (Name, Time_Interval) values ('";
                 sqlString = sqlString + medicationNameText.getText() + "','" + interval + "')";
                 
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
        panel.setBounds(100,365,100,50);
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
        panel.setBounds(300,365,100,50);
        panel.setBackground(lightBlue);
        frame.add(panel);
        return panel;
    }
}
