package Tp057903;


class station implements Runnable {

	PeopleInStation people;
	//goingforQueue q = new goingforQueue();
    public boolean closingTime = false;

    public station(PeopleInStation people)
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
            people.GoingForTicket();
            
        
           // q.goToQueue();
        }
        if (closingTime) { try {
     			Thread.sleep(500);
     		} catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} return;}
    }
    
    public synchronized void setclosingTime()
    {
    closingTime = true;
    System.out.println("Guide1 : We're closing now!");
    }
}

