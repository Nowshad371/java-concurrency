package Tp057903;
import java.util.Date;
import java.util.concurrent.TimeUnit;





class pessengerentering implements Runnable{

	PeopleInStation people;
    public boolean closingTime = false;
    static int id = 1;
    
    public pessengerentering(PeopleInStation people)
    {
        this.people = people;
    }
 
    public void run()
    {
        while(!closingTime)
        {
        	
            people p = new people(people);
            p.setInTime(new Date());
            Thread tp = new Thread(p);
            p.setName("pessenger "+ id + " ");
            tp.start();
            
            
            try {
				Thread.sleep(500);
				LineForTicket  lt = new LineForTicket (people);
	            Thread lft = new Thread(lt);
	            lt.setName("pessenger "+ id+ " ");
	            lft.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            
            try {
				Thread.sleep(500);
				TicketMachine  tm = new TicketMachine(people);
				tm.setInTime(new Date());
	            Thread ttm1 = new Thread(tm);
	            tm.setName("pessenger "+ id+ " ");
	            ttm1.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            try {
				Thread.sleep(500);
				QueueAfterTicket QA = new QueueAfterTicket(people);
				QA.setInTime(new Date());
	            Thread QT = new Thread(QA);
	            QA.setName("pessenger "+ id + " ");
	            QT.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            try {
				Thread.sleep(500);
				Inspector  Ins = new Inspector(people);
				Ins.setInTime(new Date());
	            Thread It = new Thread(Ins);
	            Ins.setName("pessenger "+ id+ " ");
	            It.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Inspector  Ins = new Inspector(people);
            
           try {
				Thread.sleep(500);
				 BusSit  bus = new  BusSit(people);
				bus.setInTime(new Date());
	            Thread busthread = new Thread(bus);
	            bus.setName("pessenger "+ id+ " ");
	            busthread.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
           WaitingArea wa = new WaitingArea(people);
           wa.setInTime(new Date());
           Thread waThread = new Thread(wa);
           wa.setName("pessenger "+ id+ " ");
           waThread.start();
           
           WaitingForBus WaB = new WaitingForBus(people);
           WaB.setInTime(new Date());
           Thread waBThread = new Thread(WaB);
           WaB.setName("pessenger "+ id+ " ");
           waBThread.start();
           
           
            id++;
                
   
            try
            {
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
            
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
    System.out.println("Sorry no service for today");
    }
    
	
	
}
