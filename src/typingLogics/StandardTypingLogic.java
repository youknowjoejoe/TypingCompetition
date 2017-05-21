package typingLogics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import core.TypingLogic;
import test.Debugger;
import util.Belt;
import util.Time;

public class StandardTypingLogic implements TypingLogic, ActionListener{
	private WordList words;
	private int currentWordIndex = 0;
	private String currentInput = "";
	
	private int charactersCorrect = 0;
	private int totalCharacters = 0;
	
	private Timer timer;
	private double timeInterval;
	private double elapsedTime = 0;
	private double timeLimit;
	
	private Belt<TypedWord> previousTypedWords;
	
	private boolean started = false;
	private boolean finished = false;
	
	public StandardTypingLogic(){
		this(
				new LoopingTest("It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair"),
				1.0,
				20.0
			);
	}
	
	public StandardTypingLogic(WordList words, double timeInterval, double timeLimit){
		this.words = words;
		this.timeInterval = timeInterval;
		this.timeLimit = timeLimit;
		timer = new Timer((int)(this.timeInterval*1000.0),this);
		
		previousTypedWords = new Belt<TypedWord>(new TypedWord[30]);
	}
	
	@Override
	public void addInput(String input) {
		if(!finished){
			processLetter(input.substring(0,1));
			if(elapsedTime >= timeLimit) stop();
		}
	}
	
	private void processLetter(String str){
		if(str.equals(" ")){
			finishWord();
			currentWordIndex++;
		} else if(str.equals("\b")){
			currentInput = currentInput.substring(0,Math.max(currentInput.length()-1,0));
		} else {
			currentInput += str;
		}
		
		if(!str.equals("\b") && !started){
			start();
		}
	}
	
	private void finishWord(){
		
		int localCorrectChars = getCorrectChars(currentInput,getWord(currentWordIndex));
		String currentWord = getWord(currentWordIndex);
		
		double time = Time.getTime();
		previousTypedWords.rotate(1);
		previousTypedWords.set(0, new TypedWord(time,localCorrectChars,currentWord.length()));
		
		charactersCorrect+=getCorrectChars(currentInput,currentWord);
		totalCharacters+=currentWord.length();
		
		currentInput = "";
	}
	
	private int getCorrectChars(String a, String b){
		int count = 0;
		for(int rep = 0; rep < Math.min(a.length(), b.length())-1; rep++){
			if(a.substring(rep,rep+1).equals(b.substring(rep,rep+1))) count++;
		}
		return (count);
	}
	
	private double calculateWPM(int correct, int total, int words, double time){
		double averageWordLength = ((double)total)/((double)words);
		double timeInMinutes = time/60.0;
		return ((double)correct/averageWordLength)/timeInMinutes;
	}
	
	private void start(){
		double startTime = Time.getTime();
		for(int rep = 0; rep < previousTypedWords.length(); rep++){
			previousTypedWords.set(rep, new TypedWord(startTime,0,1));
		}
		started = true;
		timer.setInitialDelay(0);
		timer.start();
	}
	
	private void stop(){
		timer.stop();
		finished = true;
		finishWord();
	}
	
	@Override
	public double getWPM() {
		if(!started) return 0;
		if(finished) return getAverageWPM();
		return getCurrentWPM();
	}
	
	public double getAverageWPM(){
		return calculateWPM(charactersCorrect,totalCharacters,currentWordIndex-1,elapsedTime);
	}
	
	public double getCurrentWPM(){
		return calculateWPM(sumCorrectCharacters(),sumTotalCharacters(),previousTypedWords.length(),previousTypedWords.get(0).getTime()-previousTypedWords.get(previousTypedWords.length()-1).getTime());
	}
	
	private int sumCorrectCharacters(){
		int count = 0;
		for(int rep = 0; rep < previousTypedWords.length(); rep++){
			count+=previousTypedWords.get(rep).getCorrectChars();
		}
		return count;
	}
	
	private int sumTotalCharacters(){
		int count = 0;
		for(int rep = 0; rep < previousTypedWords.length(); rep++){
			count+=previousTypedWords.get(rep).getTotalChars();
		}
		return count;
	}
	
	@Override
	public double[] getOtherWPMs() {
		return null;
	}
	
	@Override
	public double getProgress() {
		return ((double)elapsedTime)/((double)timeLimit);
	}
	
	@Override
	public double[] getOtherProgresses() {
		return null;
	}
	
	@Override
	public String getWord(int index) {
		return words.get(index);
	}
	
	@Override
	public String getCurrentInput() {
		return currentInput;
	}
	
	@Override
	public int getCurrentWord() {
		return currentWordIndex;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		elapsedTime+=timeInterval;
	}
}
