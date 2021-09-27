package Tp057903;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BusSit implements Runnable {

	
	String name;
    Date inTime;
    PeopleInStation people;
    static int number = 0;
   
    public BusSit(PeopleInStation people)
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
        try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    private synchronized void  enterIn()
    {
    	if(number == 0) {
    		people.BUS1(this);
        	number++;
        }
        else if(number == 1){
        	people.BUS2(this);
        	number++;
        }
        else {
        	people.BUS3(this);
        	number = 0;
        }
        
    }


}