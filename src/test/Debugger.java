package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Debugger extends JPanel {
	private static Debugger d;
	
	public static void initialize(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d = new Debugger();
		frame.add(d);
		frame.pack();
		frame.setAlwaysOnTop(true);
		
	}
	
	public static void setDisplayString(String str1){
		d.update(str1);
	}
	
	public static void setDisplayString2(String str2){
		d.update2(str2);
	}
	
	public static void setDisplayString3(String str3){
		d.update3(str3);
	}
	
	private String str = "";
	private String str2 = "";
	private String str3 = "";
	
	public Debugger(){
		this.setPreferredSize(new Dimension(500,100));
	}
	
	public void update(String str){
		this.str += str;
		repaint();
	}
	
	public void update2(String str){
		this.str2 += str;
		repaint();
	}
	public void update3(String str){
		this.str3 += str;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.black);
		g.drawString(str, 10, 10);
		g.drawString(str2, 10, 30);
		g.drawString(str3, 10, 50);
	}
}
