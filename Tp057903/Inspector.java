package Tp057903;

import java.util.Date;

public class Inspector implements Runnable {

	
	String name;
    Date inTime;
    PeopleInStation people;
   
    public Inspector(PeopleInStation people)
    {
        this.people = people;
    }
 
    public String getName() {
        return name;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run()
    {
        enterIn();
        
    }
    private synchronized void  enterIn()
    {
        people.CheckingTicketAndTemperature(this);
    }


}
