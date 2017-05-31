package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2949687444255909471L;
	
	private DisplayComponent[] dcs;
	private TypingLogic tl;
	private boolean running = true;
	
	private double dt;
	
	public MainPanel(int width, int height, DisplayComponent progressDisplay, DisplayComponent wordDisplay, TypingLogic tl){
		this(width,height,progressDisplay,wordDisplay,tl,1.0/60.0);
	}
	
	public MainPanel(int width, int height, DisplayComponent progressDisplay, DisplayComponent wordDisplay, TypingLogic tl, double dt){
		this.dt = dt;
		
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
	}
	private void initializeDisplayComponents() {
		for(DisplayComponent dc: dcs){
			dc.setClientTypingLogic(tl);
			dc.initialize();
		}
	}
}
