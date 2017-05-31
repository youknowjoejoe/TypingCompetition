package test;

import core.MainPanel;
import progressDisplayComponents.ProgressDC;
import typingLogics.StandardTypingLogic;
import util.IntervalUpdater;
import wordDisplayComponents.WordDisplayComponent;
import wordDisplayComponents.WordDisplayComponent1;
import wordDisplayComponents.WordDisplayComponent2;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args){
		StandardTypingLogic tl = new StandardTypingLogic();
		ProgressDC tpdc = new ProgressDC();
		WordDisplayComponent2 twdc = new WordDisplayComponent2();
		MainPanel mp = new MainPanel(800,600,tpdc,twdc,tl);
		
		JFrame window = new JFrame("Typing Competition");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.add(mp);
		window.pack();
		
		IntervalUpdater updater = new IntervalUpdater(1.0/60.0);
		updater.addUpdate(tpdc);
		//updater.addUpdate(twdc);
		updater.start();
	}
}
