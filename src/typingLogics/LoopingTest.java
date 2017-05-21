package typingLogics;

public class LoopingTest implements WordList{
	private String[] words;
	
	public LoopingTest(String[] words){
		this.words = words;
	}
	
	public LoopingTest(String str){
		this.words = str.split(" ");
	}
	
	@Override
	public String get(int index) {
		index %= words.length;
		if(index < 0) index+=words.length;
		return words[index];
	}

}
