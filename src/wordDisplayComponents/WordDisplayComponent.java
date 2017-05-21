package wordDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import core.DisplayComponent;
import core.TypingLogic;
import test.Debugger;

public class WordDisplayComponent extends JPanel implements DisplayComponent, KeyEventDispatcher, ComponentListener{

	private TypingLogic tl;
	
	private int width;
	private int height;
	private int xPadding;
	private int textWidth;
	
	private Color clearColor = Color.white;
    private Color textColor = Color.black;
    private Font font = new Font("Georgia", Font.PLAIN, 48);
	
	@Override
	public void initialize() {
		this.addComponentListener(this);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	manager.addKeyEventDispatcher(this);
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
		if(e.getID() == KeyEvent.KEY_TYPED){
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
		
		g2d.setColor(clearColor);
        g2d.fillRect(0, 0, width, height);
        
        g2d.setColor(textColor);
        g2d.setFont(font);
		
        int currentWord = tl.getCurrentWord();
        
		String tempText = "";
        String displayText = "";
        for(int tempWord = currentWord; g.getFontMetrics().stringWidth(tempText) < textWidth; tempWord++){
        	displayText = tempText;
        	tempText += tl.getWord(tempWord) + " ";
        }
        g2d.drawString(displayText, xPadding, 75);
        g2d.drawString(tl.getCurrentInput(), xPadding, 125);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		this.width = this.getWidth();
        this.height = this.getHeight();
        this.xPadding = (int)(width*0.1);
        this.textWidth = width-(2*xPadding);
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
	
}
