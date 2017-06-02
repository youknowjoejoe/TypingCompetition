package progressDisplayComponents;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ColorfulProgressDCPanel extends ProgressDCPanel implements ComponentListener {
	
	private double currentProgress;
	private double currentWPM;
	
	private Color clearColor = Color.white;
	private Color displayColor1 = Color.black;
	
	private Font font = new Font("Consolas", Font.PLAIN, 16);
	
	private int width = 0;
	private int height = 0;
	private int xPadding = 0;
	private int displayWidth = 0;
	
	public ColorfulProgressDCPanel(){
		this.addComponentListener(this);
	}
	
	@Override
	public void initialize() {
		currentProgress = 0;
		currentWPM = 0;
	}

	@Override
	public void update(double progress, double[] progresses, double wpm, double[] wpms) {
		currentProgress = progress;
		currentWPM = wpm;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		g2d.setColor(clearColor);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(displayColor1);
		g2d.fillRect(xPadding-10, height/2-40, 10, 80);
		g2d.fillRect(width-xPadding, height/2-40, 10, 80);
		
		g2d.setColor(Color.getHSBColor((float)(currentWPM*0.01), 1f, 1f));
		g2d.fillRect(xPadding, height/2-40, (int)(displayWidth*currentProgress), 80);
		
		g2d.setFont(font);
		g2d.setColor(displayColor1);
		g2d.drawString("WPM: "+((int)(currentWPM*10))/10.0, xPadding-10, height/2+100);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		updateFields();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		updateFields();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		updateFields();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		updateFields();
	}
	
	public void updateFields(){
		this.width = this.getWidth();
        this.height = this.getHeight();
        this.xPadding = (int)(width*0.1);
        this.displayWidth = width-(2*xPadding);
	}
	
}
