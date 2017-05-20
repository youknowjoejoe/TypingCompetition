package typingLogics;

public interface WordList {
	//post condition: you should always get the same word given the same index
	public String get(int index);
}
