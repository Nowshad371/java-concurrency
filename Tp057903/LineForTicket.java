package Tp057903;

public class LineForTicket implements Runnable {
		String name;
	    PeopleInStation PeopleTicketLine;
	   
	    public LineForTicket (PeopleInStation PeopleTicketLine)
	    {
	        this.PeopleTicketLine = PeopleTicketLine;
	    }
	 
	    public String getName() {
	        return name;
	    }  
	    public void setName(String name) {
	        this.name = name;
	    }
	    public void run()
	    {
	        enterIn();
	        
	    }
	    private synchronized void  enterIn()
	    {
	    
	    	PeopleTicketLine.PessengerForTicket(this);
	    }
	}



