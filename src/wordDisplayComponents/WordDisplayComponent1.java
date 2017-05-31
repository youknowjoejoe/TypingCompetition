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

public class WordDisplayComponent1 extends JPanel implements DisplayComponent, IntervalUpdate, KeyEventDispatcher, ComponentListener{
	
	private boolean started = false;
	
	private TypingLogic tl;
	
	private int width;
	private int height;
	private int xPadding;
	private int textWidth;
	
	private double currentWordX;
	
	private Color clearColor = Color.white;
    private Color textColor = Color.black;
    private Font font = new Font("Georgia", Font.PLAIN, 48);
    
    private String displayText = "";
    
    private double weight1 = 1.0;
    private double weight2 = 3.0;
    
    public WordDisplayComponent1(){
    	this.addComponentListener(this);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	manager.addKeyEventDispatcher(this);
    }
    
	@Override
	public void initialize() {
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
        
        currentWordX = ((g.getFontMetrics().stringWidth(displayText.substring(0,Math.max(0,displayText.indexOf(currentWord))))+xPadding)*weight1+currentWordX*weight2)/(weight1+weight2);
        
        String tempText = "";
        displayText = "";
        for(int tempWord = currentWordIndex; g.getFontMetrics().stringWidth(tempText) < textWidth; tempWord++){
        	displayText = tempText;
        	tempText += tl.getWord(tempWord) + " ";
        }
        
        g2d.drawString(displayText, (int)currentWordX, 75);
        g2d.drawString(tl.getCurrentInput(), xPadding, 125);
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
        this.currentWordX = xPadding;
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
