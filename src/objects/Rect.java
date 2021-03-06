package objects;

import java.awt.*;

public class Rect extends GObject  {

	private Color color;
	
	public Rect(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(color);
		Graphics2D g2 = (Graphics2D) g;
		g.fillRect(x,y,width,height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Rect", this.x, this.y - this.height - 10);
	}
	
}
