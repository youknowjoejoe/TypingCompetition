package typingLogics;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import core.TypingLogic;
//import test.Debugger;
import util.Belt;
import util.MathUtils;
import util.Time;

public class StandardTypingLogic implements TypingLogic, ActionListener{
	private WordList words;
	private int currentWordIndex = 0;
	private String currentInput = "";
	
	private int charactersCorrect = 0;
	private int totalCharacters = 0;
	
	private Timer timer;
	private double startTime = 0;
	private double timeInterval;
	private double elapsedTime = 0;
	private double timeLimit;
	
	private Belt<TypedWord> previousTypedWords;
	
	private boolean started = false;
	private boolean finished = false;
	
	
	public StandardTypingLogic(){
		this(
				//new LoopingTest[]{LoopingTest.standard,LoopingTest.rickAstley,LoopingTest.taleOfTwoCities,LoopingTest.subliminalMessage}[(int)(Math.random()*4)],
				LoopingTest.standard,
				0.1,
				30.0
			);
	}
	
	public StandardTypingLogic(WordList words, double timeInterval, double timeLimit){
		this.words = words;
		this.timeInterval = timeInterval;
		this.timeLimit = timeLimit;
		timer = new Timer((int)(this.timeInterval*1000.0),this);
		
		previousTypedWords = new Belt<TypedWord>(new TypedWord[5]);
	}
	
	@Override
	public void addInput(String input) {
		if(!finished){
			processLetter(input.substring(0,1));
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
		
		String currentWord = getWord(currentWordIndex);
		int localCorrectChars = Math.max(0,currentWord.length()-getLevenshteinDistnace(currentInput,currentWord));
		int spaceBonus = 0;
		if(localCorrectChars >= 1){
			spaceBonus++;
		}
		
		double time = Time.getTime();
		previousTypedWords.rotate(1);
		previousTypedWords.set(0, new TypedWord(time,localCorrectChars+spaceBonus,currentWord.length()));
		
		charactersCorrect+=localCorrectChars+spaceBonus;
		totalCharacters+=currentWord.length()+1;
		
		currentInput = "";
	}
	
	public static int getCorrectChars1(String a, String b){
		int count = 0;
		for(int rep = 0; rep < Math.min(a.length(), b.length()); rep++){
			if(a.substring(rep,rep+1).equals(b.substring(rep,rep+1))) count++;
		}
		return (count);
	}
	
	public static int getCorrectChars2(String a, String b){
		if(a.length() == 0 || b.length() == 0) return 0;
		if(a.equals(b)) return a.length();
		return Math.max(getCorrectChars2(a.substring(1),b), getCorrectChars2(a,b.substring(1)));
	}
	
	public static int getCorrectChars3(String a, String b){
		return Math.max(getCorrectChars1(a,b), getCorrectChars2(a,b));
	}
	
	public static int getLevenshteinDistnace(String a, String b){
		int[][] values = new int[a.length()+1][b.length()+1];
		values[0][0] = 0;
		for(int x = 1; x < values.length; x++){
			values[x][0] = x;
		}
		for(int y = 1; y < values[0].length; y++){
			values[0][y] = y;
		}
		for(int x = 1; x < values.length; x++){
			for(int y = 1; y < values[0].length; y++){
				int substitutionCost = 0;
				if(!a.substring(x-1,x).equals(b.substring(y-1,y))){
					substitutionCost = 1;
				}
				values[x][y] = MathUtils.min(
							values[x-1][y]+1,
							values[x][y-1]+1,
							values[x-1][y-1]+substitutionCost
						);
			}
		}
		return values[a.length()][b.length()];
	}
	
	private double calculateWPM(int correct, int total, int words, double time){
		//double averageWordLength = ((double)total)/((double)words);
		double averageWordLength = 5.0;
		double timeInMinutes = time/60.0;
		double product = averageWordLength*timeInMinutes;
		if(product == 0) return 0;
		return ((double)correct/product);
	}
	
	private void start(){
		startTime = Time.getTime();
		for(int rep = 0; rep < previousTypedWords.length(); rep++){
			previousTypedWords.set(rep, new TypedWord(startTime,0,5));
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
		if(finished){
			return getAverageWPM();
		}
		return getCurrentWPM();
	}
	
	public double getAverageWPM(){
		return calculateWPM(charactersCorrect,totalCharacters,currentWordIndex-1,elapsedTime);
	}
	
	public double getCurrentWPM(){
		//return calculateWPM(sumCorrectCharacters(),sumTotalCharacters(),previousTypedWords.length(),previousTypedWords.get(0).getTime()-previousTypedWords.get(previousTypedWords.length()-1).getTime());
		return calculateWPM(sumCorrectCharacters(),sumTotalCharacters(),previousTypedWords.length(),(startTime+elapsedTime)-previousTypedWords.get(previousTypedWords.length()-1).getTime());
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
		return new double[0];
	}
	
	@Override
	public double getProgress() {
		return ((double)elapsedTime)/((double)timeLimit);
	}
	
	@Override
	public double[] getOtherProgresses() {
		return new double[0];
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
		if(elapsedTime >= timeLimit) stop();
	}
}
