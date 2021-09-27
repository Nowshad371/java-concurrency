package Tp057903;

import java.util.Date;

public class QueueAfterTicket implements Runnable {

	
	String name;
    Date inTime;
    PeopleInStation people;
    static int number = 0;
   
    public QueueAfterTicket(PeopleInStation people)
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
        	people.WaitingInQueue1(this);
        	number++;
        }
        else if(number == 1){
        	people.WaitingInQueue2(this);
        	number++;
        }
        else {
        	people.WaitingInQueue3(this);
        	number = 0;
        }
    	
    	
    }


}
