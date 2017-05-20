package progressDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class ProgressDCPanel extends JPanel implements ComponentListener {
	
	private Color clearColor = Color.white;
	private Color displayColor1 = new Color(200,200,255);
	private Color displayColor2 = Color.black;
	
	private int width = 0;
	private int height = 0;
	private int xPadding = 0;
	private int displayWidth = 0;
	
	private double progress;
	private double[] progresses;
	private double wpm;
	private double[] wpms;
	
	public ProgressDCPanel(){
		this.addComponentListener(this);
	}
	
	public void initialize() {
		
	}

	public void update(double progress, double[] progresses, double wpm, double[] wpms) {
		this.progress = progress;
		this.progresses = progresses;
		this.wpm = wpm;
		this.wpms = wpms;
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(clearColor);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(displayColor2);
		g2d.fillRect(xPadding-10, height/2-20, 10, 40);
		g2d.fillRect(width-xPadding, height/2-20, 10, 40);
		
		g2d.setColor(Color.getHSBColor((float) ((wpm/100f)), 1, 1));
		g2d.fillRect(xPadding, height/2-20, (int)(displayWidth*progress), 40);
		g2d.drawString(""+wpm,xPadding-10, height/2+80);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.width = this.getWidth();
        this.height = this.getHeight();
        this.xPadding = (int)(width*0.1);
        this.displayWidth = width-(2*xPadding);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
}
