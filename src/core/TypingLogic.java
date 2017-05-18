package core;

public interface TypingLogic {
	public int getID();
    public double getProgress(int id);
    public double[] getProgessesExcept(int id);
    public String[] getWords(int id);
    public void update(int id, String input);
    public String getCurrentInput(int id);
    public int getCurrentIndex(int id);
}
