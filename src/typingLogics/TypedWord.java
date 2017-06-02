package typingLogics;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

public class TypedWord {
	private double time;
	private int correctChars;
	private int totalChars;
	
	public TypedWord(double time, int correctChars, int totalChars){
		this.time = time;
		this.correctChars = correctChars;
		this.totalChars = totalChars;
	}
	
	public double getTime(){
		return time;
	}
	
	public int getCorrectChars(){
		return correctChars;
	}
	
	public int getTotalChars(){
		return totalChars;
	}
}
