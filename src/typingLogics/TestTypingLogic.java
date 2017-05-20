package typingLogics;

import core.TypingLogic;
import util.Belt;
import util.Time;

public class TestTypingLogic implements TypingLogic {
    
	//private String[] words = new String[]{"hi","I","really","like","dogs","thanks","for","typing","I","mean","playing","this","game","I","hope","you","enjoyed","it","bye","bye"};
	/*private String[] words = new String[] {
		"Abakan", "Adulino", "Achinsk", "Adygeysk", "Ak-Dovurak", "Alapayevsk", "Alexandrovsk", "Almetyevsk", "Arkhangelsk", "Babushkin", "Bagrationovsk", "Belokurikha", "Chaykovsky", "Cherembkhovo", "Dmitrovsk", "Gremyachinsk", "Krasnoarmeysk"
	};*/
	private String[] words = "It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair ".split(" ");
	private int currentWord = 0;
	private String currentInput = "";
	
	private int correctChars = 0;
	private int totalChars = 0;
	private Belt<TypedWord> previousTimes;
	
	private double startTime = 0;
	private double endTime = 0;
	private boolean started = false;
	private boolean finished = false;
	
	double wpm = 0;
    
	public TestTypingLogic(){
		previousTimes = new Belt<TypedWord>(new TypedWord[5]);
	}
	
    public String[] getWords(){
        return words;
    }
    
    @Override
	public double getProgress() {
		return ((double)currentWord)/((double)words.length-1);
	}

	@Override
	public double[] getOtherProgresses() {
		return null;
	}
    
    public double getWPM(){
    	return wpm;
    }

	@Override
	public double[] getOtherWPMs() {
		return new double[]{0.1,0.2};
	}

	@Override
	public void update(String input) {
		if(input.length() > 1){
			update(input.substring(0,1));
			update(input.substring(1));
			return;
		}
		
		if(input.equals("\b")){
			currentInput = currentInput.substring(0,Math.max(currentInput.length()-1,0));
		}
		else if(input.equals(" ") && currentWord < words.length-1){//why is it "-1"?
			
			double time = Time.getTime();
			int localCorrectChars = getCorrectChars(currentInput,words[currentWord]);
			
			previousTimes.rotate(1);
			previousTimes.set(0, new TypedWord(time,localCorrectChars,words[currentWord].length()));
			
			correctChars += localCorrectChars;
			totalChars += words[currentWord].length();
			
			currentWord++;
			currentInput = "";
		} if (currentWord == words.length-1 && !finished) {
			endTime = Time.getTime();
			finished = true;
		}
		
		
		if(input.length() > 0 && !input.equals("\b") && !input.equals(" ")) {
			currentInput += input;
			if(!started){
				started = true;
				startTime = Time.getTime();
				for(int rep = 0; rep < previousTimes.length(); rep++){
					previousTimes.set(rep, new TypedWord(startTime,0,0));
				}
			}
		}
		
		if(!(previousTimes.get(0) != null && previousTimes.get(0).getTotalChars()!=0)) wpm = 0; //is this logic good?
		else if(finished) wpm = (60/5.0/**words.length*/*((double)correctChars))/(endTime-startTime);
		else wpm = (60.0/5.0 * /*(previousTimes.length()-1) **/ (((double)sumCorrectCharsFromPreviousWords()))  )/(Time.getTime()-previousTimes.get(previousTimes.length()-1).getTime());
	}
	
	private int sumCorrectCharsFromPreviousWords(){
    	int sum = 0;
    	for(int rep = 0; rep < previousTimes.length(); rep++){
    		sum += previousTimes.get(rep).getCorrectChars();
    	}
    	return sum;
    }
	
	private int sumTotalCharsFromPreviousWords(){
    	int sum = 0;
    	for(int rep = 0; rep < previousTimes.length(); rep++){
    		sum += previousTimes.get(rep).getTotalChars();
    	}
    	return sum;
    }
	
	private int getCorrectChars(String a, String b){
		int count = 0;
		for(int rep = 0; rep < Math.min(a.length(), b.length())-1; rep++){
			if(a.substring(rep,rep+1).equals(b.substring(rep,rep+1))) count++;
		}
		return (count);
	}

	@Override
	public int getCurrentWord() {
		return currentWord;
	}

	@Override
	public String getCurrentInput() {
		return currentInput;
	}
}
