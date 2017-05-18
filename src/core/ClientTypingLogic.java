package core;

public class ClientTypingLogic {
	private TypingLogic tl;
	private int id;
	
	public ClientTypingLogic(TypingLogic tl){
		this.tl = tl;
		this.id = tl.getID();
	}
    public double getProgress(){
    	return tl.getProgress(id);
    }
    public double[] getProgessesExcept(){
    	return tl.getProgessesExcept(id);
    }
    public String[] getWords(){
    	return tl.getWords(id);
    }
    public void update(String input){
    	tl.update(id, input);
    }
    public String getCurrentInput(){
    	return tl.getCurrentInput(id);
    }
    public int getCurrentIndex(){
    	return tl.getCurrentIndex(id);
    }
}
