package core;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2949687444255909471L;
	
	private DisplayComponent[] dcs;
	private ClientTypingLogic ctl;
	private boolean running = true;
	
	public MainPanel(int width, int height, DisplayComponent progressDisplay, DisplayComponent wordDisplay, ClientTypingLogic ctl){
		dcs = new DisplayComponent[2];
		dcs[0] = progressDisplay;
		dcs[1] = wordDisplay;
		
		this.ctl = ctl;
		
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
			dc.setClientTypingLogic(ctl);
		    dc.initialize();
		}
	}
	
	public void update(){
	    repaint();
		for(DisplayComponent dc: dcs){
		    dc.update();
		}
	}

	@Override
	public void run() {
		while(running){
		    update();
		}
	}
}
