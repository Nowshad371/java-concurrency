package Tp057903;

import java.util.concurrent.TimeUnit;



public class OpenStation implements Runnable{

	boolean openStationGates = false;
	//initializing static number to give access to the gate randomly
	static int number = 0;

	@Override
	public void run() {
		
		while(!openStationGates){
			long duration=0;
	        try
	        {   
	            //setting up time to open station after 3 second of calling the main class
	            TimeUnit.SECONDS.sleep(3);
	        }
	        catch(InterruptedException iex)
	        {
	            iex.printStackTrace();
	        }
	        //setting up when to enter in west and east gates
			if(number == 1) {
				//calling class call peopleInStation where we set up a constructor for it (west and east gate)
				PeopleInStation people = new PeopleInStation(" EAST GATES");
				//calling station class which will alert the going for ticket method
				station s = new station(people);
				//calling pessengerentering class to generate treats
				pessengerentering pg = new pessengerentering(people);
				//calling station class which will alert the LineToTicketCounter() method
				AheadOfTcketMachine ahead = new AheadOfTcketMachine(people);
				//calling station class which will alert the CompletedTakingTicketBooth() method
				ticketGeneratingMachine tm1 = new ticketGeneratingMachine(people);
				//calling station class which will alert the GoingtoScan() method
				GoingForScan gfs = new GoingForScan(people);
				//calling station class which will alert the GoingForBus() method
				GoingToBus gtb = new GoingToBus(people);
				//calling station class which will alert the GoingtoQueueAgainFromWating() method
				WaitingAreaForQueue watingforQueue = new WaitingAreaForQueue(people);
				
				//calling station class which will alert the GoingtoBusAgainFromWatingBus() method
				GoingToBusFromWaiting watingforBus = new GoingToBusFromWaiting(people);
				
				
				Thread thpg = new Thread(pg); //here thpg indicates thread for pessenger generaing class
				Thread ts = new Thread(s);//thread for station
				Thread Aht = new Thread(ahead);//thread for AheadOfTcketMachine class
				Thread TM1 = new Thread(tm1);//thread for ticketGeneratingMachine class
				Thread tg = new Thread(gfs);//thread for GoingForScan class
				Thread gfbt = new Thread(gtb);//thread for GoingToBus class
				Thread wTQ = new Thread(watingforQueue);//thread for WaitingAreaForQueue class
				Thread wtB = new Thread(watingforBus);//thread for watingforBus class
				
				
				GivingBreak time = new GivingBreak(people);//calling class for the break time of inspector
				BoothBreak booth = new BoothBreak(people);//calling class to show machine is broken 
				
				thpg.start();
				ts.start();
				Aht.start();
				TM1.start();
				tg.start();
				gfbt.start();
				wTQ.start();
				wtB.start();
				time.start();
				booth.start();
				number++;
				//close.start();
				
			
			}
			//below this part will follow the similar rule discussed above
			else {
				PeopleInStation people = new PeopleInStation(" WEST GATES");
				station s = new station(people);
				pessengerentering pg = new pessengerentering(people);
				AheadOfTcketMachine ahead = new AheadOfTcketMachine(people);
				ticketGeneratingMachine tm1 = new ticketGeneratingMachine(people);
				GoingForScan gfs = new GoingForScan(people);
				GoingToBus gtb = new GoingToBus(people);
				WaitingAreaForQueue watingforQueue = new WaitingAreaForQueue(people);
				GoingToBusFromWaiting watingforBus = new GoingToBusFromWaiting(people);
				GivingBreak time = new GivingBreak(people);
				BoothBreak booth = new BoothBreak(people);
				/*ClosingTime close = new ClosingTime(pg,s,watingforQueue,tm1,
						watingforBus,gtb,gfs,ahead);*/
				
				Thread thpg = new Thread(pg);
				Thread ts = new Thread(s);
				Thread Aht = new Thread(ahead);
				Thread TM1 = new Thread(tm1);
				Thread tg = new Thread(gfs);
				Thread gfbt = new Thread(gtb);
				Thread wTQ = new Thread(watingforQueue);
				Thread wtB = new Thread(watingforBus);
				
				
				thpg.start();
				ts.start();
				Aht.start();
				TM1.start();
				tg.start();
				gfbt.start();
				wTQ.start();
				wtB.start();
				time.start();
				booth.start();
				//close.start();
				number = 1;
				
			}
			
			
			
			
		}
		
	}
	
}
