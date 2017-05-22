package progressDisplayComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class AltProgressDCPanel extends JPanel implements ComponentListener {
	
	private Color clearColor = Color.white;
	private Color displayColor1 = new Color(200,200,255);
	private Color displayColor2 = Color.black;
	
	private int width = 0;
	private int height = 0;
	private int xPadding = 0;
	private int displayWidth = 0;
	
	private List<WPMStamp> wpmStamps;
	
	public AltProgressDCPanel(){
		this.addComponentListener(this);
		wpmStamps = new LinkedList<WPMStamp>();
	}
	
	public void initialize() {
		
	}

	public void update(double progress, double[] progresses, double wpm, double[] wpms) {
		if(progress != 0){
			wpmStamps.add(new WPMStamp(progress,wpm));
			
			this.repaint();
		}
	}
	
	public Polygon generateWPMGraph(int width,int height){
		if(wpmStamps.size()==0)return new Polygon();
		int npts = wpmStamps.size()+3;
		int[] xpts = new int[npts];
		int[] ypts = new int[npts];
		
		double max = getMaxWPM();
		
		xpts[0] = (int) (width*wpmStamps.get(wpmStamps.size()-1).getProgress());
		ypts[0] = height;
		
		xpts[1] = 0;
		ypts[1] = height;
		
		xpts[2] = 0;
		ypts[2] = (int)(height*(1.0-(wpmStamps.get(0).getWPM()/max)));
		
		int rep = 3;
		for(WPMStamp ws: wpmStamps){
			xpts[rep]=(int)(width*ws.getProgress());
			ypts[rep]=(int)(height*(1.0-(ws.getWPM()/max)));
			rep++;
		}
		
		Polygon p = new Polygon(xpts,ypts,npts);
		return p;
	}
	
	public double getMaxWPM(){
		if(wpmStamps.size()==0) return 0;
		double max = wpmStamps.get(0).getWPM();
		for(WPMStamp ws: wpmStamps){
			max = Math.max(max, ws.getWPM());
		}
		return max;
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(clearColor);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(displayColor2);
		g2d.fillRect(xPadding-10, height/2-40, 10, 80);
		g2d.fillRect(width-xPadding, height/2-40, 10, 80);
		
		g2d.setColor(Color.magenta);
		AffineTransform save = g2d.getTransform();
		g2d.translate(xPadding, height/2-40);
		g2d.fill(generateWPMGraph(displayWidth,80));
		g2d.setTransform(save);
		
		if(wpmStamps.size() > 0){
			g2d.drawString(""+wpmStamps.get(wpmStamps.size()-1).getWPM(), xPadding, height/2+80);
		}
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
        this.displayWidth = width-(2*xPadding);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
}
