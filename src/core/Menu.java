package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu implements ActionListener{
	
	private List<Builder<TypingLogic>> tlbs;
	
	private OptionActionListener<TypingLogic> typingLogicsOptions;
	private OptionActionListener<DisplayComponent> progressDisplayOptions;
	private OptionActionListener<DisplayComponent> wordDisplayOptions;
	
	public Menu(OptionActionListener<TypingLogic> typingLogicsOptions, OptionActionListener<DisplayComponent> progressDisplayOptions, OptionActionListener<DisplayComponent> wordDisplayOptions){
		tlbs = new ArrayList<Builder<TypingLogic>>();
		this.typingLogicsOptions = typingLogicsOptions;
		this.progressDisplayOptions = progressDisplayOptions;
		this.wordDisplayOptions = wordDisplayOptions;
	}
	
	public void addTypingLogicBuilder(Builder<TypingLogic> tlb){
		tlbs.add(tlb);
	}
	
	public void show(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	    JFrame frame = new JFrame("Typing Test Options");
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    
	    JPanel typingLogicsPanel = createOptionPanel(typingLogicsOptions);
	    JPanel progressDisplaysPanel = createOptionPanel(progressDisplayOptions);
	    JPanel wordDisplaysPanel = createOptionPanel(wordDisplayOptions);
	    JPanel startPanel = new JPanel();
	    JButton startButton = new JButton("Start");
	    startButton.setActionCommand("start");
	    startButton.addActionListener(this);
	    startPanel.add(startButton);
	    
	    typingLogicsPanel.setAlignmentX(0);
	    progressDisplaysPanel.setAlignmentX(0);
	    wordDisplaysPanel.setAlignmentX(0);
	    startPanel.setAlignmentX(0.5f);
	    
	    panel.add(typingLogicsPanel);
	    panel.add(progressDisplaysPanel);
	    panel.add(wordDisplaysPanel);
	    panel.add(startPanel);
	    
	    frame.add(panel);
	    frame.setVisible(true);
	    frame.pack();
	}
	
	public<A> JPanel createOptionPanel(OptionActionListener<A> oal){
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
	    panel.add(new JLabel(oal.getName()+":"));
	    for(Builder<A> a: oal.get()){
	    	JButton b = createButton(a);
	    	b.addActionListener(oal);
	    	panel.add(b);
	    }
	    return panel;
	}
	
	public JButton createButton(Builder<?> builder){
		JButton b = new JButton(builder.getName());
		b.setToolTipText(builder.getSubtext());
		b.setActionCommand(builder.getKey().toString());
		return b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			new TypingTest(800, 600, progressDisplayOptions.getCurrentOption().build(), wordDisplayOptions.getCurrentOption().build(), typingLogicsOptions.getCurrentOption().build());
		}
	}
}
