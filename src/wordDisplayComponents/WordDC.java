package wordDisplayComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import core.ClientTypingLogic;
import core.DisplayComponent;

public class WordDC implements DisplayComponent, KeyListener {
	
	private WordDCPanel panel;
	private ClientTypingLogic ctl;
	private String typed;
	private String[] words;
	
	public WordDC(){
		panel = new WordDCPanel();
	}
	
	@Override
	public void initialize() {
		words = ctl.getWords();
		panel.initialize(words);
	}
	
	@Override
	public void setClientTypingLogic(ClientTypingLogic ctl) {
		this.ctl = ctl;
	}
	
	@Override
	public void update() {
		ctl.update(typed);
		typed = "";
		panel.update(ctl.getCurrentInput(),ctl.getCurrentIndex());
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
}
