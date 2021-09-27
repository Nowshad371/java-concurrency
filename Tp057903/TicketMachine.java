package Tp057903;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TicketMachine implements Runnable {

	
	String name;
    Date inTime;
    PeopleInStation people;
    static int number = 0;
   
    public TicketMachine(PeopleInStation people)
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
        if(number == 0) {
        	 
        	people.TakingTicketBooth1(this);
        	number++;
        }
        else if(number == 1){
        	 
        	people.TakingTicketBooth2(this);
        	number++;
        }
        else {
        	
        	people.TakingTicketBooth3(this);
        	number = 0;
        }
    }


}
