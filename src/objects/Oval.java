package objects;

import java.awt.*;

public class Oval extends GObject {

	private Color color;
	
	public Oval(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	@Override
	public void paintObject(Graphics g) {
		/* Set color */
		g.setColor(color);
		/* Draw an oval */
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillOval(x, y, width, height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Oval", this.x, this.y - this.height - 10);
	}
	
}
