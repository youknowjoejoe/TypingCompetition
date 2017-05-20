package wordDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class AltWordDCPanel extends JPanel implements ComponentListener {
	
	private String[] words;
    private String currentInput = "";
    private int currentWord = 0;
    
    private Color clearColor = Color.white;
    private Color textColor = Color.black;
    private Font font = new Font("Georgia", Font.PLAIN, 48);
    
    private int width = 0;
    private int height = 0;
    private int xPadding = 0;
    private int textWidth = 0;
    
    public AltWordDCPanel(){
    	this.addComponentListener(this);
    	this.currentInput = "";
    	this.currentWord = 0;
    }
    
    public void initialize(String[] words) {
        this.words = words;
    }

    public void update(String currentInput, int currentWord) {
        this.currentInput = currentInput;
        this.currentWord = currentWord;
        
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(clearColor);
        g2d.fillRect(0, 0, width, height);
        
        g2d.setColor(textColor);
        g2d.setFont(font);
        
        /*int startIndex = Math.max(text.lastIndexOf(" ", currentIndex),0);
        //System.out.println(startIndex);
        //System.out.println(text.substring(startIndex));
        int tempIndex = startIndex;
        int endIndex = tempIndex;
        while((tempIndex >= 0) && (tempIndex < text.length()) && (g.getFontMetrics().stringWidth(text.substring(startIndex,tempIndex))<textWidth)){
        	endIndex = tempIndex;
        	tempIndex = text.indexOf(" ", tempIndex+1);
        }
        g2d.drawString(text.substring(startIndex,endIndex), xPadding, 75);
        g2d.drawString(currentInput, xPadding, 125);*/
        
        String tempText = "";
        String displayText = "";
        for(int tempWord = currentWord; tempWord < words.length && g.getFontMetrics().stringWidth(tempText) < textWidth; tempWord++){
        	displayText = tempText;
        	tempText += words[tempWord] + " ";
        }
        g2d.drawString(displayText, xPadding, 75);
        g2d.drawString(currentInput, xPadding, 125);
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
        this.textWidth = width-(2*xPadding);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
}
