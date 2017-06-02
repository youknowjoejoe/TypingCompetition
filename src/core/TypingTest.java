package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TypingTest extends JPanel {
	
	private DisplayComponent[] dcs;
	private TypingLogic tl;
	
	public TypingTest(int width, int height, DisplayComponent progressDisplay, DisplayComponent wordDisplay, TypingLogic tl){
		
		dcs = new DisplayComponent[2];
		dcs[0] = progressDisplay;
		dcs[1] = wordDisplay;
		
		this.tl = tl;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel top = new JPanel();
		Dimension topDim = new Dimension(width,height/3);
		top.setPreferredSize(topDim);
		wordDisplay.getPanel().setPreferredSize(topDim);
		top.add(wordDisplay.getPanel());
		
		JPanel bottom = new JPanel();
		Dimension bottomDim = new Dimension(width,height-(height/3));
		bottom.setPreferredSize(bottomDim);
		progressDisplay.getPanel().setPreferredSize(bottomDim);
		bottom.add(progressDisplay.getPanel());
		
		this.add(top);
		this.add(bottom);
		
		this.initializeDisplayComponents();
		
		JFrame frame = new JFrame("Typing Test");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void initializeDisplayComponents() {
		for(DisplayComponent dc: dcs){
			dc.setClientTypingLogic(tl);
			dc.initialize();
		}
	}
}
