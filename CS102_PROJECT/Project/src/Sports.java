
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
    
public class Sports extends Activity {
    
    // Properties
    
    private final int POINTS = 30;
    private double totalPoints;
    
    // Constructors 
    
    public Sports( String theName, LocalTime starting, LocalTime ending) {
        
        super(theName, starting, ending);
        setTimer(starting);
        
    }
    
    // Methods
    
    public int getPoints() {
        
        return POINTS;
    }
    
    public boolean showPopUp() {
        
        return true;
         
    }
    
    public double calculateTotalPoints () {
        
        double duration = super.calculateTimeInterval();
        totalPoints = Math.round((duration * getPoints() * 100.0)) / 100.0;
        
        return (totalPoints);
        
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
            

            timer.schedule( new TimerTask(){public void run(){new ActivityTimePopUpWindow();}}, date.getTime());}
            
    }
    
    
}