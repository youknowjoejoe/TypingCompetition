package wordDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class WordDCPanel extends JPanel {
	
	private String[] words;
	private String text;
	private String currentInput;
	private int currentIndex;
	
	private Color clearColor = Color.white;
	private Color textColor = Color.black;
	private Font font = new Font("Georgia", Font.PLAIN, 48);
	
	public void initialize(String[] words) {
		this.words = words;
		this.text = "";
		for(int rep = 0; rep < words.length-1; rep++){
			text += words[rep] + " ";
		}
		text+=words[words.length-1];
		this.currentInput = "";
		this.currentIndex = 0;
	}

	public void update(String currentInput, int currentIndex) {
		this.currentInput = currentInput;
		this.currentIndex = currentIndex;
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(clearColor);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(textColor);
		g2d.setFont(font);
		g2d.drawString(text.substring(Math.max(text.lastIndexOf(" ", currentIndex),0),text.indexOf(" ", currentIndex)), 100, 100);
	}
}
