package Tp057903;

public class GoingToBusFromWaiting implements Runnable {

	PeopleInStation people;
	//goingforQueue q = new goingforQueue();
    public boolean closingTime = false;

    public GoingToBusFromWaiting(PeopleInStation people)
    {
        this.people = people;
    }
    public void run()
    {
    	 try
         {
             Thread.sleep(1000);
         }
         catch(InterruptedException iex)
         {
             iex.printStackTrace();
         }
      
        while(!closingTime)
        {
            people.GoingtoBusAgainFromWatingBus();
            
        
           // q.goToQueue();
        }
        if (closingTime) { try {
     			Thread.sleep(5000);
     		} catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} return;}
    }
    
    public synchronized void setclosingTime()
    {
    closingTime = true;
    
    }
}
