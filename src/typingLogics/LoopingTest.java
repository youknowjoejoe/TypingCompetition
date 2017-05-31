package typingLogics;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

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
	
	
	public static LoopingTest standard = new LoopingTest("the be to of and a in that have I it for not on with as you do at this but his by from they we say her she or an will my one all would there their what so up out if about who get which go me when make can like time no just him know take people into year your good some could them see other than then now look only come its over think also back after use two how our work first well way  even new want because any these give day most us");
	public static LoopingTest rickAstley = new LoopingTest("We're no strangers to love, You know the rules and so do I, A full commitment's what I'm thinking of, You wouldn't get this from any other guy, I just want to tell you how I'm feeling, Gotta make you understand- Never gonna give you up, never gonna let you down, Never gonna run around and desert you, Never gonna make you cry, never gonna say goodbye, Never gonna tell a lie and hurt you- We've known each other for so long, Your heart's been aching but you're too shy to say it, Inside we both know what's been going on, We know the game and we're gonna play it, And if you ask me how I'm feeling, Don't tell me you're too blind to see- Never gonna give you up, never gonna let you down, Never gonna run around and desert you, Never gonna make you cry, never gonna say goodbye, Never gonna tell a lie and hurt you, Never gonna give you up, never gonna let you down, Never gonna run around and desert you, Never gonna make you cry, never gonna say goodbye, Never gonna tell a lie and hurt you- We've known each other for so long, Your heart's been aching but you're too shy to say it, Inside we both know what's been going on, We know the game and we're gonna play it, I just want to tell you how I'm feeling, Gotta make you understand- Never gonna give you up, never gonna let you down, Never gonna run around and desert you, Never gonna make you cry, never gonna say goodbye, Never gonna tell a lie and hurt you-");
	public static LoopingTest taleOfTwoCities = new LoopingTest("It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair");
	public static LoopingTest subliminalMessage = new LoopingTest("Joseph is really cool. His dog is the best dog. His typing test is cool. His typing test is not sending subliminal messages.");
	
}
