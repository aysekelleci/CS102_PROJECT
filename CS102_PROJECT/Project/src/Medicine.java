
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Medicine extends Reminders
{
    //Properties
    private String name;

    //Constructor
    public Medicine( String theName, LocalTime theTime )
    {
        super( theTime );
        name = theName;
        setTimer(theTime);
    }

    //Methods
    public void giveAlarm()
    {
        showPopUp();
    }

    public void showPopUp()
    {
        System.out.println();
    }

    public String getName()
    {
        return name;
    }

    public void setName( String theName )
    {
        name = theName;
    }
    
    private void setTimer( LocalTime start )
    {
        
        if ((double) Duration.between(LocalTime.now(), start).getSeconds() >= -1) {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, LocalDateTime.now().getYear());
            date.set(Calendar.MONTH, LocalDateTime.now().getMonthValue() - 1);
            date.set(Calendar.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth());
            date.set(Calendar.HOUR_OF_DAY, start.getHour());
            date.set(Calendar.MINUTE, start.getMinute());
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
            Timer timer = new Timer();
            timer.schedule( new TimerTask(){public void run(){new MedicationTimePopUpWindow(); }}, date.getTime());}
    }

}
