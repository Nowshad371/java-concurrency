package Tp057903;


public class Main {
	public static void main(String[] args) {
		
		/*PeopleInStation people = new PeopleInStation("Gate --1");
		station s = new station(people);
		pessengerentering pg = new pessengerentering(people);
		Thread thpg = new Thread(pg);
		Thread ts = new Thread(s);
		thpg.start();
		ts.start();*/
		
        OpenStation open = new OpenStation();
        Thread th = new Thread(open);
        th.start();
		 
	       
	       
	        
	        
	      
	      
		
		
	}
}
