package typingLogics;

import core.TypingLogic;

public class TestTypingLogic implements TypingLogic {
    
	private String[] text = new String[]{"hi","I","really","like","dogs","thanks","for","typing","I","mean","playing","this","game","I","hope","you","enjoyed","it","bye","bye"};
	
	public int getID(){
		return 0;
	}
	
    public double getProgress(int id){
        return 0.5;
    }
    
    public String[] getWords(int id){
        return text;
    }

	@Override
	public double[] getProgessesExcept(int id) {
		return new double[]{0.1,0.2};
	}

	@Override
	public void update(int id, String input) {
		
	}

	@Override
	public int getCurrentIndex(int id) {
		return 0;
	}

	@Override
	public String getCurrentInput(int id) {
		return "dog";
	}
}
