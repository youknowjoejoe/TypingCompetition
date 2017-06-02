package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import javax.swing.JPanel;

public interface DisplayComponent {
	
	public void initialize();
	
	public JPanel getPanel();
	public void setClientTypingLogic(TypingLogic tl);
	
}
