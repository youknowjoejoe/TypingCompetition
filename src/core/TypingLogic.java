package core;

public interface TypingLogic {
    public double getWPM();
    public double[] getOtherWPMs();
    public double getProgress();
    public double[] getOtherProgresses();
    public String[] getWords();
    public void update(String input);
    public String getCurrentInput();
    public int getCurrentWord();
}
