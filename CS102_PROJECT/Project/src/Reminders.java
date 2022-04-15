import java.time.LocalTime;
public abstract class Reminders 
{

    //Properties
    LocalTime currentTime = LocalTime.now(); 
    private LocalTime reminderTime;

    //Constructor
    protected Reminders( LocalTime theReminderTime)
    {
        reminderTime = theReminderTime;
    }

    //Methods
    public boolean isActivityTime()
    {
        if (reminderTime == currentTime)
        {
            return true;
        }
        return false;
    }

    public LocalTime getTime()
    {
        return reminderTime;
    }

    public void setTime( LocalTime theNewTime)
    {
        reminderTime = theNewTime;
    }

    public abstract void giveAlarm();

    public abstract void showPopUp();

}   
