package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

public interface TypingLogic {
    public double getWPM();
    public double[] getOtherWPMs();
    public double getProgress();
    public double[] getOtherProgresses();
    public String getWord(int index);
    public void addInput(String input);
    public String getCurrentInput();
    public int getCurrentWord();
}
