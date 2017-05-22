package util;

import java.util.ArrayList;
import java.util.List;

public class IntervalUpdater implements Runnable {
	
	private double dt;
	private List<IntervalUpdate> ius;
	private boolean running = true;
	
	public IntervalUpdater(double dt){
		ius = new ArrayList<IntervalUpdate>();
		this.dt = dt;
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
		double accumulator = 0.0;
		double timePassed = 0.0;
		while(running){
			while(accumulator >= dt){
				ius.forEach((iu)->iu.update());
				accumulator-=dt;
			}
			currentTime = Time.getTime();
			timePassed = currentTime-oldTime;
			accumulator += timePassed;
			oldTime = currentTime;
			try {
				Thread.sleep((int)((timePassed < dt) ? (dt-timePassed)*1000 : 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
