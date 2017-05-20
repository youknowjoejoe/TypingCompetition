package progressDisplayComponents;

import javax.swing.JPanel;

import core.DisplayComponent;
import core.TypingLogic;

public class ProgressDC implements DisplayComponent {
	
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
