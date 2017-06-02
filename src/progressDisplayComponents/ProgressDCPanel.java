package progressDisplayComponents;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class ProgressDCPanel extends JPanel {
	
	public abstract void initialize();

	public abstract void update(double progress, double[] progresses, double wpm, double[] wpms);
	
	@Override
	public abstract void paintComponent(Graphics g);
}
