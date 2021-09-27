package Tp057903;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;





public class PeopleInStation {
	
	
	//To generate the pessengerNumber.
	String PessengerNo;
	
	//Declaring capacity for each queue that is 10
	int capacity = 35;
	
	//boolean condition to give break time to the inspector
	boolean breakSec = false;
	
	//boolean condition to break the machine 
	//here I made three condition to break machine randomly not to break at a time.
	boolean BoothBreak = false;
	boolean BoothBreak2 = false;
	boolean BoothBreak3 = false;
	
	//initial stage of bus
	static int Bussit1 = 0;
	static int Bussit2 = 0;
	static int Bussit3 = 0;
	
	//after pessengers getting into bus if size become 6 it will left the station
	int finalBusSit = 6;
	
	//capacity of the queue for ticket.
	int ticketLine = 5;
	
	//taking pessenger in the people list as entering treats(pessenger)
    List<people> noPeople;
    
    //storing people in line
    List<LineForTicket>Line;
    
    //creating linkedlist to store pessengers in booth machine to take ticket.
    List<TicketMachine>booth1;
    List<TicketMachine>booth2;
    List<TicketMachine>booth3;
    
    
    //After taking ticket list to store these pessengers in queue.
    List<QueueAfterTicket>queue1;
    List<QueueAfterTicket>queue2;
    List<QueueAfterTicket>queue3;
    
    //list for the bus sit
    List<BusSit> Bus1;
    List<BusSit> Bus2;
    List<BusSit> Bus3;
    
    //Creating list for the waiting area from queue and bus waiting area
    List<WaitingArea>Area;
    List<WaitingForBus>AreaBus;
    
    //machine capacity at a time
    int machineCapacity = 1;
    
    //capacity of the queue before the checking and scaning temperature
    int QueueSize = 10;
    
    //size of the inspectore who will check the ticket
    int NumberOfInspector = 1;
    
    //list for the inspector
    List<Inspector>Check;
    
    
    //constructor for the gates
    public PeopleInStation(String PessengerNo)
    {
    	
    	//Declaring all the needed linked list and treats in constructor  
    	noPeople = new LinkedList<people>(); 
    	Line = new LinkedList<LineForTicket>();
    	booth1 = new LinkedList<TicketMachine>();
    	booth2 = new LinkedList<TicketMachine>();
    	booth3 = new LinkedList<TicketMachine>();
    	
    	
    	queue1 = new LinkedList<QueueAfterTicket>();
    	queue2 = new LinkedList<QueueAfterTicket>();
    	queue3 = new LinkedList<QueueAfterTicket>();
    	Check = new LinkedList<Inspector>();
    	
    	Bus1 = new LinkedList<BusSit>();
    	Bus2 = new LinkedList<BusSit>();
    	Bus3 = new LinkedList<BusSit>();
    	
    	Area = new LinkedList<WaitingArea>();
    	AreaBus = new LinkedList<WaitingForBus>();
    	this.PessengerNo= PessengerNo;
    }
    
    //method to remove pessenger from the gates.
    public void GoingForTicket() {
    	people p;
        synchronized (noPeople)
        {
 
            while(noPeople.size()==0)
            {
                System.out.println("Currently No One in the station");
                try
                {
                	noPeople.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
        
            //removing pessenger from the gates
            p = (people)((LinkedList<?>)noPeople).poll();
            System.out.println(p.getName() + "Going to ticket line");
            
            
        }
	

    }
    
    
  //method to take pessenger into the station 
    
  	public void PessengerEntering(people people) {
 
          synchronized (noPeople)
          {
        	  //condition for the capacity of the station
              if(noPeople.size() == capacity)
              {
              	
              	System.out.println("currently no available place in station for the  "+people.getName());
                  System.out.println("Please wait");
                  return ;
              }
              System.out.println("pessenger no  "+people.getName()+
            		  "Enter to the station  "+people.getInTime() +
            		  "from  " + this.PessengerNo);
              ((LinkedList<people>)noPeople).offer(people);
     
              //after going to take ticket to empty gate 1 and 2 
              //notifying nopeople linked list to remove pessenger from gates
              if(noPeople.size() <= capacity)
                  noPeople.notify();
              
             
          }
      }
  	public void LineToTicketCounter() {
  		LineForTicket Lt;
        synchronized (Line)
        {
 
            while(Line.size()==0)
            {
                System.out.println("Currently No One in the Line for Ticket");
                try
                {
                	Line.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
        
            
            Lt = (LineForTicket)((LinkedList<?>)Line).poll();
            System.out.println(Lt.getName() + "Going to Take ticket");
            
            
        }
	

    }
  	
  	public void PessengerForTicket(LineForTicket TicketLine) {
  		 
        synchronized (Line)
        {
            if(Line.size() == ticketLine)
            {
            	
            	System.out.println("currently no available place in the Ticket Line for  "+TicketLine.getName());
                System.out.println("Please wait");
                return ;
            }
            System.out.println("pessenger no  "+TicketLine.getName()+ "Enter to the Ticket Line   ");
            ((LinkedList<LineForTicket>)Line).offer(TicketLine);
   
            //after going to take ticket to empty gate 1 and 2 
            //notifying nopeople linked list to remove pessenger from gates
            if(Line.size() < ticketLine)
                Line.notify();
            
           
        }
    }
  	
  	public void CompletedTakingTicketBooth() {
  		TicketMachine ct1;
        synchronized (booth1)
        {
 
            while(booth1.size()==0)
            {
                System.out.println("Currently No One Using booth 1");
                try
                {
                	booth1.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
           
          
            ct1 = (TicketMachine)((LinkedList<?>)booth1).poll();
            System.out.println( ct1.getName()  + "  Completed Taking ticket  from booth 1(manual) at  "+  ct1.getInTime());
            System.out.println(ct1.getName() + "Going to the queue");
            
            
        }
        
        synchronized (booth2)
        {
 
            while(booth2.size()==0)
            {
                System.out.println("Currently No One Using booth 2");
                try
                {
                	booth2.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
           
          
            ct1 = (TicketMachine)((LinkedList<?>)booth2).poll();
            System.out.println( ct1.getName()  + "  Completed Taking ticket from booth 2 (manual) at "+  ct1.getInTime());
            System.out.println(ct1.getName() + "Going to the queue");
            
            
        }
        synchronized (booth3)
        {
        	
            while(booth3.size()==0)
            {
                System.out.println("Currently No One Using booth 3");
                try
                {
                	booth3.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
        
           
 			
            ct1 = (TicketMachine)((LinkedList<?>)booth3).poll();
            System.out.println( ct1.getName()  + "  Completed Taking ticket from booth 3 (Automatic Machine) at "+  ct1.getInTime());
            System.out.println(ct1.getName() + "Going to the queue");
            
            
        }

    }
  
	
  	public void TakingTicketBooth1(TicketMachine takingTicket) {
  		
  		 if(BoothBreak) {
     		
     		try {
					Thread.sleep(3000);
					System.out.println("it Seems Booth 1 needs repair please wait");
	        		System.out.println("Please Wait");
	        		return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     	}
        synchronized (booth1)
        {
            if(booth1.size() == machineCapacity)
            {
            	
            	System.out.println("currently Booth 1 is in use  "+takingTicket.getName() + "needs to wait");
                System.out.println("Please wait");
                return ;
            }
            System.out.println(takingTicket.getName()+ "  Enter to take ticket in booth 1 at "+  takingTicket.getInTime());
            ((LinkedList<TicketMachine>)booth1).offer(takingTicket);
   
            //after going to take ticket to empty gate 1 and 2 
            //notifying nopeople linked list to remove pessenger from gates
            if(booth1.size() <= machineCapacity)
            	booth1.notify();
        }
        
  
    }
  	public void TakingTicketBooth2(TicketMachine takingTicket) {
  		 if(BoothBreak2) {
     		
     		try {
					Thread.sleep(2000);
					System.out.println("it Seems Booth 2 needs repair please wait");
	        		System.out.println("Please Wait");
	        		return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     	}
        synchronized (booth2)
        {
            if(booth2.size() == machineCapacity)
            {
            	
            	System.out.println("currently Booth 2 is in use  "+takingTicket.getName() + "needs to wait");
                System.out.println("Please wait");
                return ;
            }
            System.out.println(takingTicket.getName()+ "  Enter to take ticket in booth 2 at "+  takingTicket.getInTime());
            ((LinkedList<TicketMachine>)booth2).offer(takingTicket);
   
            //after going to take ticket to empty gate 1 and 2 
            //notifying nopeople linked list to remove pessenger from gates
            if(booth2.size() <= machineCapacity) {
            	booth2.notify();
            }
            	
        }
           
        }
        
        public void TakingTicketBooth3(TicketMachine takingTicket) {
        if(BoothBreak3) {
        		
        		try {
					Thread.sleep(2000);
					System.out.println("it Seems Booth 3 needs repair please wait");
	        		System.out.println("Please Wait");
	        		return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            synchronized (booth3)
            {
                if(booth3.size() == machineCapacity)
                {
                	
                	System.out.println("currently Booth 3 is in use  "+takingTicket.getName() + "needs to wait");
                    System.out.println("Please wait");
                    return ;
                }
                System.out.println(takingTicket.getName()+ "  Enter to take ticket in booth 3 at "+  takingTicket.getInTime());
                ((LinkedList<TicketMachine>)booth3).offer(takingTicket);
       
                //after going to take ticket to empty gate 1 and 2 
                //notifying nopeople linked list to remove pessenger from gates
                if(booth3.size() <= machineCapacity)
                	booth3.notify();
                
               
            }
    }
     
        
        public void WaitingInQueue1(QueueAfterTicket queue) {
   		 
            synchronized (queue1)
            {
            
                if(queue1.size() == QueueSize)
                {
                	
                	System.out.println("currently Queue1 is Full  "+queue.getName() + "needs to waitin waiting area");
                    System.out.println("Please wait");
                    return ;
                }
              
                System.out.println(queue.getName()+ "  Enter to the queue 1  at "+  queue.getInTime());
                ((LinkedList<QueueAfterTicket>)queue1).offer(queue);
       
                //after going to take ticket to empty gate 1 and 2 
                //notifying nopeople linked list to remove pessenger from gates
                if(queue1.size() <= QueueSize)
                	queue1.notify();
                
               
            }
    }
        
        public void WaitingInQueue2(QueueAfterTicket queue) {
      		 
            synchronized (queue2)
            {
                if(queue2.size() == QueueSize)
                {
                	
                	System.out.println("currently Queue2 is Full  "+queue.getName() + "needs to waitin waiting area");
                    System.out.println("Please wait");
                    return ;
                }
              
                System.out.println(queue.getName()+ "  Enter to the queue 2  at "+  queue.getInTime());
                ((LinkedList<QueueAfterTicket>)queue2).offer(queue);
       
                //after going to take ticket to empty gate 1 and 2 
                //notifying nopeople linked list to remove pessenger from gates
                if(queue2.size() <= QueueSize)
                	queue2.notify();
                
               
            }
    }
        public void WaitingInQueue3(QueueAfterTicket queue) {
     		 
            synchronized (queue3)
            {
                if(queue3.size() == QueueSize)
                {
                	
                	System.out.println("currently Queue3 is Full  "+queue.getName() + "needs to waitin waiting area");
                    System.out.println("Please wait");
                    return ;
                }
              
                System.out.println(queue.getName()+ "  Enter to the queue 3  at "+  queue.getInTime());
                ((LinkedList<QueueAfterTicket>)queue3).offer(queue);
       
                //after going to take ticket to empty gate 1 and 2 
                //notifying nopeople linked list to remove pessenger from gates
                if(queue3.size() <= QueueSize)
                	queue3.notify();
                
               
            }
    }
             
        public void GoingtoScan() {
        	QueueAfterTicket  ct1;
            synchronized (queue1)
            {
     
                while(queue1.size()==0)
                {
                    System.out.println("Currently No One is in queue  1");
                    try
                    {
                    	queue1.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                }
            
                
                ct1 = (QueueAfterTicket)((LinkedList<?>)queue1).poll();
               
                System.out.println(ct1.getName() + "Going to the Scan their Temperature from queue 1");
                
                
            }
            
            synchronized (queue2)
            {
     
                while(queue2 .size()==0)
                {
                    System.out.println("Currently No One is in Queue 2");
                    try
                    {
                    	queue2.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                }
            
              
                ct1 = (QueueAfterTicket)((LinkedList<?>)queue2).poll();
               
                System.out.println(ct1.getName() + "Going to the Scan their Temperature from queue 2");
                
                
            }
            synchronized (queue3)
            {
     
                while(queue3.size()==0)
                {
                    System.out.println("Currently No One is in Queue 3");
                    try
                    {
                    	queue3.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                }
            
              
                ct1 = (QueueAfterTicket)((LinkedList<?>)queue3).poll();
                System.out.println(ct1.getName() + "Going to the Scan their Temperature from queue 3");
                
            }

        }
        
        public void CheckingTicketAndTemperature(Inspector p) {
        	 
            synchronized (Check)
            {
                if(Check.size() == NumberOfInspector)
                {
                	
                	System.out.println("Current Inspector is Busy.  "+p.getName());
                    System.out.println("Please wait");
                    return ;
                }
                
                try {
					Thread.sleep(2000);
					System.out.println(p.getName()+ "Completed Chicking Temperature ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
                System.out.println(p.getName()+ "Enter for Checking Ticket  "+p.getInTime());
                ((LinkedList<Inspector>)Check).offer(p);
               
                //after going to take ticket to empty gate 1 and 2 
                //notifying nopeople linked list to remove pessenger from gates
                if(Check.size() == NumberOfInspector)
                	Check.notify();
                
               
            }
        }
        
        
        public void GoingForBus() {
        	Inspector p;
        	
        	if(breakSec) {
        		
        		try {
					Thread.sleep(4000);
					System.out.println("it Seems Inspector is in Washroom or break");
	        		System.out.println("Please Wait");
	        		return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            synchronized (Check)
            {
     
                while(Check.size()==0)
                {
                    System.out.println("Currently No One going For Bus");
                    try
                    {
                    	Check.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                }
            
                
                p = (Inspector)((LinkedList<?>)Check).poll();
                System.out.println(p.getName() + "Competed Checking");
                
                System.out.println(p.getName() + "Going to Bus");
                
                
            }
    	

        }
        
        public void BUS1(BusSit takingsit) {
   		 	BusSit ct1;
            synchronized (Bus1)
            {
               
                if(Bus1.size() == finalBusSit)
                {
                	
                	
                	System.out.println("Bus 1 Went for the Destination "
                	+takingsit.getName() + "needs to wait");
                    System.out.println("Please wait");
       	
                    return ;
                }
                System.out.println("\n\n");
                if(Bussit1 == 6) {
                    for(int i = 0;i<6;i++) {
                    	ct1 = (BusSit)((LinkedList<?>)Bus1).poll();
                    	
                    	Bussit1 = 0;
                    	
                    } 
                    
                    System.out.println("Bus 1 Went for the Destination"+takingsit.getName() + "needs to wait");
                   
                }
                try {
     				TimeUnit.SECONDS.sleep(1);
     			} catch (InterruptedException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
           
                
                if(Bussit1 == 0) {
                		try {
							Thread.sleep(1000);
							System.out.println("New Bus 1  Arrive");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		
                	}
                }
            
                System.out.println(takingsit.getName()+ "Enter to the Bus 1 "+  takingsit.getInTime());
                ((LinkedList<BusSit>)Bus1).offer(takingsit);
     
                Bussit1++;
                	
               
                
            }
        
        public void BUS2(BusSit takingsit) {
   		 	BusSit ct1;
            synchronized (Bus2)
            {
                if(Bus2.size() >= finalBusSit)
                {
                	
                	System.out.println("Bus 2 Went for the Destination  "+takingsit.getName() + "needs to wait");
                    System.out.println("Please wait");
                    
          	
                    return ;
                }
                System.out.println("\n\n");
                if(Bussit1 == 6) {
                    for(int i = 0;i<6;i++) {
                    	ct1 = (BusSit)((LinkedList<?>)Bus1).poll();
                    	
                    	Bussit1 = 0;
                    	
                    } 
                    
                    System.out.println("Bus 1 Went for the Destination"+takingsit.getName() + "needs to wait");
                   
                }
                    
                if(Bussit2 == 0) {
                		try {
							Thread.sleep(1000);
							System.out.println("New Bus 2 Arrive");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		
                	}
                }
            try {
 				TimeUnit.SECONDS.sleep(1);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
                System.out.println(takingsit.getName()+ "Enter to the Bus 2 "+  takingsit.getInTime());
                ((LinkedList<BusSit>)Bus2).offer(takingsit);
     
                Bussit2++;
                	
               
                
            }
        
        public void BUS3(BusSit takingsit) {
   		 	BusSit ct1;
            synchronized (Bus3)
            {
                if(Bus3.size() >= finalBusSit)
                {
                	
                	System.out.println("Bus 3 Went for the Destination  "+takingsit.getName() + "needs to wait");
                    System.out.println("Please wait");
                    
     
                    	
                    return ;
                }
                System.out.println("\n\n");
                if(Bussit1 == 6) {
                    for(int i = 0;i<6;i++) {
                    	ct1 = (BusSit)((LinkedList<?>)Bus1).poll();
                    	
                    	Bussit1 = 0;
                    	
                    } 
                    
                    System.out.println("Bus 1 Went for the Destination"+takingsit.getName() + "needs to wait");
                   
                }
                    
                if(Bussit3 == 0) {
                		try {
							Thread.sleep(1000);
							System.out.println("New Bus 3 Arrive");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		
                	}
                }
            try {
 				TimeUnit.SECONDS.sleep(1);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
                System.out.println(takingsit.getName()+ "Enter to the Bus 1 "+  takingsit.getInTime());
                ((LinkedList<BusSit>)Bus3).offer(takingsit);
     
                Bussit3++;
                	
               
                
            }
        
        public void WaitingAreafORQueue(WaitingArea wa) {
            synchronized (Area)
            {
            	while(Area.size()==5)
                {
            		System.out.print("Waiting of queue Area is Full");
					try {
						Area.wait();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
                }
     
                while(queue1.size()==10|| queue2.size()==10 || queue3.size()==10)
                {
                	((LinkedList<WaitingArea>)Area).offer(wa);
                 
					System.out.println(wa.getName() + "People Enter to "
							+ "Waiting area");
					
					try {
						queue1.wait();
						queue2.wait();
						queue3.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
                }
                
    
            }
    	

        }
        
        public void GoingtoQueueAgainFromWating() {
        	WaitingArea w;
        	synchronized (Area) {
        		
        		while(Area.size()==0)
                {
                    System.out.println("Currently No One in the queue Waiting Area");
                    try
                    {
                    	Area.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                }
            while(queue1.size()<10 || queue2.size()<10 || queue3.size()<10)
            {
                
            	if(Area.size() > 0) {
                w = (WaitingArea)((LinkedList<?>)Area).poll();
				System.out.println(w.getName() + "Going to Queue Again");
				
				queue1.notify();
				queue2.notify();
				queue3.notify();
            	}
				
            }
            
            
        	}

        }
        
        public void WaitingAreafORBus(WaitingForBus wa) {
            synchronized (AreaBus)
            {
            	while(AreaBus.size()==5)
                {
            		System.out.print("Waiting Area of Bus is Full");
					try {
						AreaBus.wait();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
                }
     
                while(Bus1.size()==6 || Bus2.size()==6 || Bus3.size()==6)
                {
                	((LinkedList<WaitingForBus>)AreaBus).offer(wa);
                 
					System.out.println(wa.getName() + "People Enterint to Bus Waiting area");
					
					try {
						Bus1.wait();
						Bus2.wait();
						Bus3.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
                }
                
    
            }
    	

        }
        
        public void GoingtoBusAgainFromWatingBus() {
        	WaitingForBus w;
        	synchronized (AreaBus) {
            while(Bus1.size()<6 || Bus2.size()<6 || Bus3.size()<6)
            {
                
            	if(AreaBus.size() > 0) {
                w = (WaitingForBus)((LinkedList<?>)AreaBus).poll();
				System.out.println(w.getName() + "Going to Bus from Bus Waiting Area Again");
				
				Bus1.notify();
				Bus2.notify();
				Bus3.notify();
            	}
				
            }
            
            while(AreaBus.size()==0)
            {
                System.out.println("Currently No One in the Bus Waiting Area");
                try
                {
                	AreaBus.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
        	}

        }
        
        //when boothBreak and Giving break class will call this method than these 
        //value will the true by which I am giving break time
        public void setBreakTime() {
        	breakSec = true;
        }
        public void setBooth1() {
        	BoothBreak = true;
        }
        public void setBooth2() {
        	BoothBreak2 = true;
        }
        public void setBooth3() {
        	BoothBreak3 = true;
        }

    }
        
     

