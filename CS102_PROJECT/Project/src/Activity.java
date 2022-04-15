
import java.time.Duration;
import java.time.LocalTime;

/**
 * Program Description 
 * @author Program Author
 * @version 21.04.2021
*/ 
public abstract class Activity {
    
    // Properties
    
    
    private String name;
    private LocalTime startingTime;
    private LocalTime endingTime;
    LocalTime theMoment = LocalTime.now();
    
    // Constructors
    
    public Activity( String theName, LocalTime starting, LocalTime ending) {
        
        this.name = theName;
        this.startingTime = starting;
        this.endingTime = ending;
        
    }

    // Methods
    
    public void setBeginningTime( int hour, int minutes) {
        
        startingTime = LocalTime.of( hour, minutes, 0, 0);
        
    }
    
    public abstract int getPoints();
    
    public void setEndingTime(int hour, int minutes) {
        
        endingTime = LocalTime.of( hour, minutes, 0, 0);;
    }
    
    public abstract boolean showPopUp();
        
    
    public void giveAlarm() {
        
        if ( startingTime.equals(theMoment)) {
            
            showPopUp();
        }
        
    }
    
    public LocalTime getStartingTime() {
        
        return startingTime;
    }
    
    public LocalTime getEndingTime() {
        
        return endingTime;
    }
    
    public double calculateTimeInterval() {
        
        double interval;
        double hours;
        interval = (double) Duration.between(getStartingTime(), getEndingTime()).getSeconds();
        hours = interval / 3600;
        
        
        if (hours < 0) {
            
            hours = hours + 24;
        }
        
        
        return hours;
    }
 
    public abstract double calculateTotalPoints();
    
    public boolean equals( Activity a) {
        if (a.name == this.name && a.startingTime == this.startingTime && a.endingTime == this.endingTime ) {
            return true;
        } 
        return false;
    }
    
   
    
}