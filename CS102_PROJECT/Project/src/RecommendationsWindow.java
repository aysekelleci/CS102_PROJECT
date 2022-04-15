import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class RecommendationsWindow implements ActionListener
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

    JFrame frame = new JFrame("Recommendations");
    JLabel label = new JLabel("Recommendations Info");
    JLabel rec = new JLabel("NOTE:  Recommendations are made when you decide you are finished with the day.");
    JPanel panel = new JPanel();
    
    JPanel panelS = new JPanel();
    JPanel panelF = new JPanel();
    JPanel panelR = new JPanel();
    JPanel panelE = new JPanel();
    
    JLabel sportRec = new JLabel("You should do more sports!");
    JLabel funRec = new JLabel("You should start having more fun!");
    JLabel restRec = new JLabel("You work a lot! How about some rest?");
    JLabel educationRec = new JLabel("How about learning something new tomorrow?");
    
    Color background = new Color(153,0,0);
    Color background3 = new Color(234, 236, 174);
    Color background4 = new Color(204, 0, 0);
    Color background5 = new Color(0, 153, 0);
    
    JButton recommend = new JButton("Recommend");
    public ScheduleWindow s;
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    public AccountWindow a;
    public RecommendationsWindow r;
    double sum;
    DefaultTableModel model = new DefaultTableModel();

    public RecommendationsWindow()
    {
        frame.setJMenuBar(jmb);

        frame.getContentPane().setBackground(background3);
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
        recommend.addActionListener(this);

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
        rec.setFont(new Font("Times new Roman", Font.PLAIN, 18));

        label.setFont(new Font("CAlibri", Font.PLAIN, 30));
        label.setBounds( 300, 30, 350, 50);
        label.setForeground(background);
        
        rec.setBounds(100, 100, 700, 100);
        recommend.setBounds(370,215,150,30);
        rec.setForeground(background);
        
        model.addColumn("Type");
        model.addColumn("You");
        model.addColumn("Average Person");
        
        
        
        JTable jt = new JTable(model);
        jt.setBounds(30,40,50,200);
        
        
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds( 50,50, 20, 200);
        
        panel.add(sp);
        panel.setVisible(false);
        panel.setBounds(150, 275, 600, 90 );
        
        panel.setBackground(background3);
        panelS.setBackground(background3);
        panelR.setBackground(background3);
        panelF.setBackground(background3);
        panelE.setBackground(background3);

        panelS.add(sportRec);
        panelF.add(funRec);
        panelE.add(educationRec);
        panelR.add(restRec);
        
        sportRec.setForeground(background4);
        funRec.setForeground(background4);
        educationRec.setForeground(background4);
        restRec.setForeground(background4);
        
        panelS.setVisible(false);
        panelR.setVisible(false);
        panelF.setVisible(false);
        panelE.setVisible(false);
        
        panelS.setBounds(130, 450, 600, 20);
        panelR.setBounds(130, 500, 600, 20);
        panelF.setBounds(130, 550, 600, 20);
        panelE.setBounds(130, 600, 600, 20);
        
        frame.add(panelS);
        frame.add(panelR);
        frame.add(panelE);
        frame.add(panelF);
        frame.add(panel);
        frame.add(recommend);
        frame.add(label);
        frame.add(rec);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLayout(null);
        frame.setVisible(true);
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
        
        if(e.getSource() == recommend) {
            
            int result = JOptionPane.showConfirmDialog(frame,"Are you sure? ", "Swing Tester",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

            if(result == JOptionPane.YES_OPTION){
                
                showTable();
            }
        }
        
        if(e.getSource() == reccomendationsButton)
        {
            frame.dispose();
            r = new RecommendationsWindow();
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
    
    public void showTable() {
        
        double sumSport = 0;
        double sumFun = 0;
        double sumEducation = 0;
        double sumRest = 0;
        
        
        try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString= "";
                sqlString = "Select * from  Recommend";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                 
                 
                while (rs.next()) {
                    
                    sumSport = sumSport + Double.parseDouble(rs.getString(2));
                    sumFun = sumFun + Double.parseDouble(rs.getString(3));
                    sumEducation = sumEducation + Double.parseDouble(rs.getString(5));
                    sumRest = sumRest + Double.parseDouble(rs.getString(4));
                
                 }
                 
                 model.addRow(new Object[]{"Sports", Math.round(sumSport * 100.0) / 100.0 + " hours", "2 hours" });
                 model.addRow(new Object[]{"Fun" ,  Math.round(sumFun * 100.0) / 100.0  + " hours", "4 hours" });
                 model.addRow(new Object[]{"Education" , Math.round(sumEducation * 100.0) / 100.0  + " hours", "4 hours" });
                 model.addRow(new Object[]{"Rest" ,  Math.round(sumRest * 100.0) / 100.0  + " hours", "5 hours" });
                 
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            
            if ( sumSport > 2) {
                
                sportRec.setText("Well Done, must be a very tiring day");
                sportRec.setForeground(background5);
            }
            if ( sumFun > 4) {
                
                funRec.setText("Glad you didn't forget to laugh today");
                funRec.setForeground(background5);
            }
            if ( sumEducation > 4) {
                
                educationRec.setText("I respect you, the wise one");
                educationRec.setForeground(background5);
            }
            if ( sumRest > 5) {
                
                restRec.setText("You must be really energetic right now");
                restRec.setForeground(background5);
            }
            
            panel.setVisible(true);
            panelS.setVisible(true);
            panelF.setVisible(true);
            panelE.setVisible(true);
            panelR.setVisible(true);
    }
}
