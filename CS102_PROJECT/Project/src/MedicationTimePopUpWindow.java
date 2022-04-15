import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MedicationTimePopUpWindow implements ActionListener
{
    JButton ok = new JButton("Check Schedule");
    JLabel added = new JLabel("It is time for your medicine!");
    JFrame frame = new JFrame();
    ImageIcon medImage = new ImageIcon(("medPopUp.png"));
    JLabel medImageL = new JLabel(medImage);
    MedicationsWindow m;

    public MedicationTimePopUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        medImageL.setBounds(30, 30, 300, 311);
        ok.addActionListener(this);

        frame.add(medImageL);
        frame.add(ok, BorderLayout.PAGE_END);
        frame.add(added, BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == ok)
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
    }
}

