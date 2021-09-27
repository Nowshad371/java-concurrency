package Tp057903;

public class BoothBreak extends Thread{

	protected PeopleInStation pessengerNo;
	static int num = 0;
	
	public BoothBreak(PeopleInStation pessengerNo) {
		this.pessengerNo = pessengerNo;
	
	}
	public void run() {
		try {
			Thread.sleep(25000);
			notifyclose();
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	//codition to close the gate 1
	private void notifyclose() {
		
		if(num == 0) {
			
				pessengerNo.setBooth1();
			
			num++;
		}
		else if(num == 1) {
			
				pessengerNo.setBooth2();
			
			num++;
		}
		else {
			
				pessengerNo.setBooth3();
		
			
			num = 0;
		}
		
		
		
		
		
	}
}
