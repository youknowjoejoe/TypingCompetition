package test;

import core.MainPanel;
import progressDisplayComponents.ProgressDC;
import typingLogics.TestTypingLogic;
import wordDisplayComponents.WordDC;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args){
		//Debugger.initialize();
		
		TestTypingLogic tl = new TestTypingLogic();
		ProgressDC tpdc = new ProgressDC();
		WordDC twdc = new WordDC();
		MainPanel mp = new MainPanel(800,600,tpdc,twdc,tl);
		
		JFrame window = new JFrame("Typing Competition");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.add(mp);
		window.pack();
		(new Thread(mp)).run();
	}
}
