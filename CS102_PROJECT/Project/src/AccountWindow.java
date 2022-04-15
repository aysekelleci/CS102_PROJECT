import java.text.DecimalFormat;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.*;
import java.sql.*;

public class AccountWindow extends JComponent implements ActionListener 
{
    //creating menu bar
    JMenuBar jmb = new JMenuBar();
    
    //adding components to menu
    JMenu profile = new JMenu("Profile");
    JMenu add = new JMenu("Add");
    JMenu info = new JMenu("Info");

    //adding directive components
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

    //creating the account for account window
    Account account = new Account(1 , 1 );
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    //creating profile components
    JFrame frame = new JFrame("Account");
    JLabel accLabel = new JLabel( "Your Account" );
    JLabel weight = new JLabel("Your weight: ");
    JLabel height = new JLabel("Your height: ");
    JLabel bmi = new JLabel();
    JLabel daily = new JLabel();
    JLabel bodyType = new JLabel("Your body type is: Not Determined" );
    ImageIcon image = new ImageIcon(("account.png"));
    JLabel accountPage = new JLabel(image);
    //JLabel imageLabel;
    JPanel tablePanel = new JPanel(); 
    Color background = new Color(206,165,226);
    Color background2 = new Color(171,232,236);
    Color background3 = new Color(234, 236, 174);
    Color acLColour = new Color(153,0,0);
    Color DGreen = new Color(0,153,0);
    JLabel welcome = new JLabel( "User Name: ");
    JButton edit = new JButton("EDIT PROFILE");
    JButton deleteAccount = new JButton("DELETE ACCOUNT");
    JPanel panel = new JPanel();
     

    JTextField intervalText = new JTextField();
    JTextField activityText = new JTextField();
    JTextField startingText = new JTextField();
    JTextField endingText = new JTextField();
    JTextField descriptionText = new JTextField();
    PointCalculatorWindow p;
    public ScheduleWindow s;
    public MedicationsWindow m;
    double sum;
    public AccountWindow a;

    String activityName;

    public AccountWindow()
    {
        //adding components to their locations
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
        edit.addActionListener(this);
        deleteAccount.addActionListener(this);

        profile.add(accountButton);
        profile.add(reccomendationsButton);
        profile.add(scheduleButton);
        profile.add(medicationsButton);
        profile.add(pointCalculatorButton);

        add.add(activityButton);
        add.add(medicationButton);
        
        info.add(aboutButton);
        info.add(contactButton);

        //setting the properties for each component
        
        accLabel.setBounds(350,60,300,50);
        
        accLabel.setFont(new Font ("Calibri", Font.BOLD, 30));
        accLabel.setForeground(acLColour);

        //adding the labels to the frame
        frame.add(accLabel);
        frame.add(accountPage);
        frame.add(weight);
        frame.add(height);
        frame.add(bmi);
        frame.add(daily);
        frame.add(bodyType);

        try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString = "";
                sqlString = "Select * from  Account";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                 
                weight.setText("No Account. Create one. ");
                height.setText("No Account. Create one.");
                bmi.setText("BMI: Not Calculated");
                daily.setText("Daily Point: 0");
                while (rs.next()) {
                    welcome.setFont(new Font("Calibri", Font.PLAIN, 20));
                    welcome.setText("<html>User Name:" + " <i><b>" +  rs.getString(2) + "</html>");
                    welcome.setForeground(DGreen);
                    weight.setText("Your weight(kg): "+ rs.getDouble(3));
                    height.setText("Your height(cm): "+ rs.getDouble(4));
                    account.setHeight(rs.getDouble(4));
                    account.setWeight(rs.getDouble(3));

                    bmi.setText("BMI: " + String.valueOf(df2.format(account.calculateBMI()))); 
                    bodyType.setText("<html>Your body type is: " + "<i>" + account.getBodyType(account.calculateBMI())  +"</html>");
                    bodyType.setForeground(DGreen);

                }
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }

        //
    

        intervalText.setPreferredSize(new Dimension(250,40));
        activityText.setPreferredSize(new Dimension(250,40));
        startingText.setPreferredSize(new Dimension(250,40));
        endingText.setPreferredSize(new Dimension(250,40));
        descriptionText.setPreferredSize(new Dimension(250,40));

        frame.add(intervalText);
        frame.add(activityText);
        frame.add(startingText);
        frame.add(endingText);
        frame.add(descriptionText);

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLayout(null);
        frame.setVisible(true);

        tablePanel.setBackground(background);
        weight.setBackground(background2);
        bmi.setBackground(background2);
        frame.getContentPane().setBackground(background3);
        accountPage.setBounds(20,20,100,100);
        
        tablePanel.setBounds(300, 130, 300, 200);
        tablePanel.setLayout( new GridLayout(2,2));
        tablePanel.add(weight);
        tablePanel.add(height);
        tablePanel.add(daily);
        tablePanel.add(bmi);
        weight.setOpaque(true);
        bmi.setOpaque(true);
        
        accountPage.setBounds(20, 20, 150 ,200);
        welcome.setBounds(20, 250, 180, 50);
        edit.setBounds(20, 350, 150, 40);
        edit.setBackground(background2);

        deleteAccount.setBounds(20, 400, 150, 40);
        deleteAccount.setBackground(background2);
        daily.setBounds(300,350,300,30);
        bodyType.setFont(new Font("Calibri", Font.PLAIN, 20));
        bodyType.setBounds(300,360,350,40);
        
        frame.add(accountPage);
        frame.add(welcome);
        frame.add(edit);
        frame.add(deleteAccount);
        frame.add(bodyType);
        frame.add(tablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == accountButton)
        {
            frame.dispose();
            a = new AccountWindow();
            a.setDailyPoint();

           
            a.height.setText("Height: " + String.valueOf(account.getHeight()));
            a.weight.setText("Weight: " + String.valueOf(account.getWeight()));
        }
        
        if(e.getSource() == edit)
        {
            frame.dispose();
           
            new EditWindow();
        }

        if(e.getSource() == deleteAccount) {

            int result = JOptionPane.showConfirmDialog(frame,"Sure? Account will be deleted !!!", "Warning",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);

                if(result == JOptionPane.YES_OPTION){
                    try 
                    {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                        Statement stmt = con.createStatement();
                        String sqlString= "";
                        sqlString = "delete from  Account";
                        stmt.executeUpdate(sqlString); 
                        System.out.println(sqlString);
                        con.close();

                        account.setHeight(0);
                        account.setWeight(0);
                        account.calculateBMI();

                        frame.dispose();
                        a = new AccountWindow();
                    }   catch (Exception ex) {
                            System.out.println(ex);
                    } 
                }

                else {
                    
                    System.out.println("You selected: No");
                }
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
                sum = 0;
                 
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
    
    public void setDailyPoint() {
            
        double sumSport = 0;
        double sumFun = 0;
        double sumEducation = 0;
        double sumRest = 0;
        double total = 0;
        if (account.getHeight() < 2 && account.getWeight() < 2) {

            daily.setText("Your daily point is: 0");
        } 
        else {

            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + Globals.DBConnection_String, Globals.DBConnection_user, Globals.DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select * from  Report";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
                
                
                while (rs.next()) {
                    
                    sumSport = sumSport + Double.parseDouble(rs.getString(2));
                    sumFun = sumFun + Double.parseDouble(rs.getString(3));
                    sumEducation = sumEducation + Double.parseDouble(rs.getString(5));
                    sumRest = sumRest + Double.parseDouble(rs.getString(4));
                    
                    
                }
                con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            
            total = sumEducation + sumFun + sumSport + sumRest;
            daily.setText("Your daily point is: " + Math.round(total * 100.0) / 100.0);
        }
    }
    
    public Account getAccount() {
        
        return account;
    }
    
    
}