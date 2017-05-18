package progressDisplayComponents;

import javax.swing.JPanel;

import core.ClientTypingLogic;
import core.DisplayComponent;

public class ProgressDC implements DisplayComponent {
	
	private ProgressDCPanel panel;
	private ClientTypingLogic ctl;
	
	public ProgressDC(){
		panel = new ProgressDCPanel();
	}
	
	@Override
	public void initialize() {
		panel.initialize();
	}
	
	@Override
	public void setClientTypingLogic(ClientTypingLogic ctl) {
		this.ctl = ctl;
	}
	
	@Override
	public void update() {
		panel.update(ctl.getProgress(),ctl.getProgessesExcept());
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}
}
