import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class MedicationsWindow implements ActionListener
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

    JFrame frame = new JFrame("Medications");
    JLabel label = new JLabel("Medications Info");
    Color background = new Color(153,0,0);
    Color background3 = new Color(234, 236, 174);

    DefaultTableModel model = new DefaultTableModel();
    public ScheduleWindow s;
    public PointCalculatorWindow p;
    public MedicationsWindow m;
    public AccountWindow a;
    JButton deleteAllButton = new JButton("Delete All");
    double sum;

    public MedicationsWindow()
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

        model.addColumn("Medicine");
        model.addColumn("Time");

        frame.getContentPane().setBackground(background3);
      
        
        label.setFont(new Font("CAlibri", Font.PLAIN, 30));
        label.setBounds( 330, 30, 250, 50);
        label.setForeground(background);

        JTable jt = new JTable(model);
        jt.setBounds(30,40,800,700);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds( 45,90, 800,450);

        frame.add(sp); 
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLayout(null);
        frame.setVisible(true);
        
        deleteAllButton.setBounds(380,625,140,30);
        deleteAllButton.addActionListener(this);
        frame.add(deleteAllButton);
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
        
        if(e.getSource() == deleteAllButton)
        {
            
            int result = JOptionPane.showConfirmDialog(frame,"Sure? All data will be deleted !!!", "Swing Tester",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);

               if(result == JOptionPane.YES_OPTION){
                try 
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                    Statement stmt = con.createStatement();
                    String sqlString= "";
                    sqlString = "delete from  Medication";
                    stmt.executeUpdate(sqlString); 
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                frame.dispose();
                m = new MedicationsWindow();
            

            } else {
                System.out.println("You selected: No");
            }

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
                String sqlString= "";
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
    }
    public DefaultTableModel getModel() {
        
        return model;
    }
}