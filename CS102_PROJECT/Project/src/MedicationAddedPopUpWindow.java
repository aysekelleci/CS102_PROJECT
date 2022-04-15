import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicationAddedPopUpWindow implements ActionListener
{
    JButton ok = new JButton("OK");
    JLabel added = new JLabel("Medication successfully added!");
    JFrame frame = new JFrame();

    public MedicationAddedPopUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        ok.addActionListener(this);

        frame.add(ok, BorderLayout.PAGE_END);
        frame.add(added, BorderLayout.NORTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == ok)
        {
            frame.dispose();
            new LaunchPage();
        }
    }

}
