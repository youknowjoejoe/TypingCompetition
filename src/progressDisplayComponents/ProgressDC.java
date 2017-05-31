package progressDisplayComponents;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

import javax.swing.JPanel;

import core.DisplayComponent;
import core.TypingLogic;
import util.IntervalUpdate;

public class ProgressDC implements DisplayComponent, IntervalUpdate{
	
	private ProgressDCPanel panel;
	private TypingLogic tl;
	
	public ProgressDC(){
		panel = new ProgressDCPanel();
	}
	
	@Override
	public void initialize() {
		panel.initialize();
	}
	
	@Override
	public void setClientTypingLogic(TypingLogic tl) {
		this.tl = tl;
	}
	
	@Override
	public void update() {
		panel.update(tl.getProgress(),tl.getOtherProgresses(),tl.getWPM(),tl.getOtherWPMs());
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}
}
