package progressDisplayComponents;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

public class WPMStamp {
	private double progress;
	private double wpm;
	
	public WPMStamp(double progress, double wpm){
		this.progress = progress;
		this.wpm = wpm;
	}
	
	public double getProgress(){
		return progress;
	}
	
	public double getWPM(){
		return wpm;
	}
}
