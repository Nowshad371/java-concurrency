package Tp057903;

public class GivingBreak extends Thread{

	protected PeopleInStation pessengerNo;

	
	public GivingBreak(PeopleInStation pessengerNo) {
		this.pessengerNo = pessengerNo;
	
	}
	public void run() {
		try {
			Thread.sleep(15000);
			notifyclose();
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	//codition to close the gate 1
	private void notifyclose() {
		try {
			Thread.sleep(15000);
			pessengerNo.setBreakTime();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
