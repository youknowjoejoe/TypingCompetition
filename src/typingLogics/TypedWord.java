package typingLogics;

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