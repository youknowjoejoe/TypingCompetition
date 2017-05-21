package util;

import java.util.ArrayList;
import java.util.List;

public class IntervalUpdater implements Runnable {
	
	private double dt;
	private List<IntervalUpdate> ius;
	private boolean running = true;
	
	public IntervalUpdater(double dt){
		ius = new ArrayList<IntervalUpdate>();
	}
	
	public void addUpdate(IntervalUpdate iu){
		ius.add(iu);
	}
	
	public void start(){
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		double oldTime = Time.getTime();
		double currentTime = oldTime;
		double timePassed = 0.0;
		while(running){
			ius.forEach((iu)->iu.update());
			currentTime = Time.getTime();
			timePassed = currentTime-oldTime;
			oldTime = currentTime;
			try {
				Thread.sleep((int)((timePassed < dt) ? (dt-timePassed)*1000 : 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
