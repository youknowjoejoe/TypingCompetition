package core;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private List<TypingLogicBuilder> tlbs;
	
	public Menu(){
		tlbs = new ArrayList<TypingLogicBuilder>();
	}
	
	public void addTypingLogicBuilder(TypingLogicBuilder tlb){
		tlbs.add(tlb);
	}
	
	
}
