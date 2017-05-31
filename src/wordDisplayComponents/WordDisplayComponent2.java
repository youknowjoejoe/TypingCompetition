package wordDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import core.DisplayComponent;
import core.TypingLogic;
import test.Debugger;
import util.IntervalUpdate;

public class WordDisplayComponent2 extends JPanel implements DisplayComponent, IntervalUpdate, KeyEventDispatcher, ComponentListener{
	
	private boolean started = false;
	
	private TypingLogic tl;
	
	private int width;
	private int height;
	private int xPadding;
	private int textWidth;
	
	private Color clearColor = Color.white;
    private Color textColor = Color.black;
    private Color highlightColor = Color.green;
    private Font font = new Font("Georgia", Font.PLAIN, 48);
    
    private int startIndex;
    private int endIndex;
    
    private String displayText;
    private String displayText2;
    
    private int wordX;
    private int wordWidth;
    
    public WordDisplayComponent2(){
    	this.addComponentListener(this);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	manager.addKeyEventDispatcher(this);
    }
    
	@Override
	public void initialize() {
		startIndex = -1;
		endIndex = -1;
		displayText = "";
		wordX = 0;
		wordWidth = 0;
		started = true;
	}
	
	@Override
	public JPanel getPanel() {
		return this;
	}
	
	@Override
	public void setClientTypingLogic(TypingLogic tl) {
		this.tl = tl;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_TYPED && started){
			String input = Character.toString(e.getKeyChar());
			if(input.length() > 0){
				tl.addInput(input);
				this.repaint();
			}
		}
		return false;
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		g2d.setColor(clearColor);
        g2d.fillRect(0, 0, width, height);
        
        g2d.setColor(textColor);
        g2d.setFont(font);
        
        int currentWordIndex = tl.getCurrentWord();
        String currentWord = tl.getWord(currentWordIndex);
        if(textWidth < 1){
        	displayText = "";
        	displayText2 = "";
        } else while(!(currentWordIndex >= startIndex && currentWordIndex <= endIndex)){
        	displayText = "";
        	startIndex = endIndex+1;
        	//System.out.println(startIndex);
        	String tempText = "";
            for(int tempWord = startIndex; g.getFontMetrics().stringWidth(tempText) < textWidth; tempWord++){
            	endIndex = tempWord;
            	displayText = tempText;
            	tempText += tl.getWord(tempWord) + " ";
            }
            endIndex--;
            tempText = "";
            displayText2 = "";
            for(int tempWord = endIndex+1; g.getFontMetrics().stringWidth(tempText) < textWidth; tempWord++){
            	displayText2 = tempText;
            	tempText += tl.getWord(tempWord) + " ";
            }
        }
        String tempText = "";
        for(int tempWord = startIndex; tempWord < currentWordIndex; tempWord++){
        	tempText += tl.getWord(tempWord) + " ";
        }
        wordX = g.getFontMetrics().stringWidth(tempText);
        wordWidth = g.getFontMetrics().stringWidth(currentWord);
        
        g2d.setColor(highlightColor);
        g2d.fillRect(wordX+xPadding, 0, wordWidth, 50);
        
        g2d.setColor(textColor);
        g2d.setFont(font);
        g2d.drawString(displayText, (int)xPadding, 50);
        g2d.drawString(displayText2, (int)xPadding, 100);
        g2d.drawString(tl.getCurrentInput(), xPadding, 150);
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
        this.xPadding = width/10;
        this.textWidth = width-(2*xPadding);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}

	@Override
	public void update() {
		repaint();
	}
	
}
