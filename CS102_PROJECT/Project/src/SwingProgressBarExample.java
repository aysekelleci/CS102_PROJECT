import javax.swing.*;

public class SwingProgressBarExample extends JPanel {

    JProgressBar pbar;


    static final int MY_MINIMUM = 0;

    static final int MY_MAXIMUM = 250;

    public SwingProgressBarExample() {
      // initialize Progress Bar
      pbar = new JProgressBar(JProgressBar.VERTICAL, MY_MINIMUM, MY_MAXIMUM);

      // add to JPanel
      add(pbar);
    }

    public void updateBar(int newValue) {
      pbar.setValue(newValue);
    }
    
}

