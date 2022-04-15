import java.util.ArrayList;
import javax.swing.*;


public class Account {
    
    //Properties
    public  ImageIcon image1 = new ImageIcon();
    private String name;
    private double weight;
    private double height;
    private double point;
    private double bmi;
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private double durationOfEducationalActivities;
    private double durationOfFunActivities;
    private double durationOfRestActivities;
    private double durationOfSportActivities;
    private double durationOfSleep;
    
    //Constructor
    
    public Account ( double theWeight, double theHeight )
    {
        weight = theWeight;
        height = theHeight;
    }
    
    //Methods
    
    public double getWeight()
    {
        return weight;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    
    public void setWeight( double theWeight)
    {
        weight = theWeight;
    }
    
    public void setHeight( double theHeight)
    {
        height = theHeight;
    }
    
    public double calculateBMI()
    {
        return getWeight() / Math.pow(getHeight() / 100, 2);
    }
   
    
    public void checkDurationActivityOnDay() {
        
        for ( Activity a : activities) {
            
            if ( a.getClass().getName().equals("Education")) {
                
                durationOfEducationalActivities = durationOfEducationalActivities + a.calculateTimeInterval(); 
            }
            
            else if ( a.getClass().getName().equals("Fun")) {
                
                durationOfFunActivities = durationOfFunActivities + a.calculateTimeInterval();
            }
            
            else if ( a.getClass().getName().equals("Rest")) {
                
                durationOfRestActivities = durationOfRestActivities +  a.calculateTimeInterval();
                
            }
            
            else if (  a.getClass().getName().equals("Sports")) {
                
                durationOfSportActivities = durationOfSportActivities + a.calculateTimeInterval();
            }
            
            else if (  a.getClass().getName().equals("Sleep")) {
                
                durationOfSleep = durationOfSleep +  a.calculateTimeInterval();
            }   
        }   
    }

    public String getBodyType( double bmi )
    {
        
        if( bmi <= 0 )
        {
            return "Invalid";
        }
        if( bmi >= 30 )
        {
            return "Obese";
        }
        if( bmi > 25 && bmi < 30 )
        {
            return "Overweighted";
        }
        if( bmi > 18.5 && bmi <= 25 )
        {
            return "Normal";
        }
        else
        {
            return "Underweight";
        }
    
    }
}
