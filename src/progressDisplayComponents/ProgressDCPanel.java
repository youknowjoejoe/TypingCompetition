package progressDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ProgressDCPanel extends JPanel {
	
	private Color clearColor = Color.white;
	private Color textColor = Color.black;
	private Font font = new Font("Georgia", Font.PLAIN, 48);
	
	private double progress;
	private double[] otherProgresses;
	
	public void initialize() {
		
	}

	public void update(double progress, double[] otherProgresses) {
		this.progress = progress;
		this.otherProgresses = otherProgresses;
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(clearColor);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(textColor);
		g2d.setFont(font);
		g2d.drawString("" + progress, 100, 100);
	}
}
