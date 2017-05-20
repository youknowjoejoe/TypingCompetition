package core;
import javax.swing.JPanel;

public interface DisplayComponent {
	
	public void initialize();
	
	public JPanel getPanel();
	public void update();
	public void setClientTypingLogic(TypingLogic tl);
	
}