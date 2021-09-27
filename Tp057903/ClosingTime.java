package Tp057903;


public class ClosingTime extends Thread{

	protected pessengerentering pessenger;
	protected station s;
	WaitingAreaForQueue w;
	ticketGeneratingMachine ti;
	GoingToBusFromWaiting Gbw;
	GoingToBus gb;
	GoingForScan Gs;
	AheadOfTcketMachine Ah;
	public ClosingTime(pessengerentering pessenger,
			station s,WaitingAreaForQueue w,
			ticketGeneratingMachine ti,GoingToBusFromWaiting Gbw,
			GoingToBus gb, GoingForScan Gs,AheadOfTcketMachine Ah) {
		this.pessenger = pessenger;
		this.s = s;
		this.w = w;
		this.ti =ti;
		this.Gbw = Gbw;
		this.gb = gb;
		this.Gs = Gs;
		this.Ah = Ah;
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
		System.out.println("Its time to close the station.See you--------):");
		System.out.println("");
		pessenger.setclosingTime();
		s.setclosingTime();
		w.setclosingTime();
		ti.setclosingTime();
		Gbw.setclosingTime();
		gb.setclosingTime();
		Gs.setclosingTime();
		Ah.setclosingTime();
	}
}
