package wordDisplayComponents;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import core.DisplayComponent;
import core.TypingLogic;
import test.Debugger;

public class WordDC implements DisplayComponent, KeyListener, KeyEventDispatcher {
	
	private WordDCPanel panel;
	private TypingLogic tl;
	private String typed = "";
	private String typedOld = "";
	private String[] words;
	
	public WordDC(){
		panel = new WordDCPanel();
		//panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.setRequestFocusEnabled(true);
		panel.requestFocus();
		panel.setEnabled(true);
	}
	
	@Override
	public void initialize() {
		words = tl.getWords();
		panel.initialize(words);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	manager.addKeyEventDispatcher(this);
	}
	
	@Override
	public void setClientTypingLogic(TypingLogic tl) {
		this.tl = tl;
	}
	
	@Override
	public void update() {
		//panel.requestFocus();
		typedOld = typed;
		typed = "";
		//Debugger.setDisplayString3(typed);
		//WDebugger.setDisplayString2(typedOld);
		/*try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		tl.update(typedOld);
		panel.update(tl.getCurrentInput(),tl.getCurrentWord());
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		typed += Character.toString(e.getKeyChar());
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_TYPED /*&& e.getKeyCode() != KeyEvent.VK_SHIFT*/){
			//Debugger.setDisplayString(Character.toString(e.getKeyChar()));
			typed += Character.toString(e.getKeyChar());
		}
		return false;
	}
}
