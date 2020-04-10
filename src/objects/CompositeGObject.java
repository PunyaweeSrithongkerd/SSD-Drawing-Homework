package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	@Override
	public List<GObject> get_object() {
		return gObjects;
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		if (this.selected) {
			this.x += dX;
			this.y += dY;
		}
	}
	
	public void recalculateRegion() {
		int left_x = gObjects.get(0).x;
		int right_x = gObjects.get(0).x;
		int upper_y = gObjects.get(0).y;
		int lower_y = gObjects.get(0).y;
		for (int i = 0; i<gObjects.size()-1;i++){
			if (left_x > gObjects.get(i).x){
				left_x = gObjects.get(i).x;
			}
			if (right_x < gObjects.get(i).x){
				right_x = gObjects.get(i).x;
			}
			if (upper_y < gObjects.get(i).y){
				upper_y = gObjects.get(i).y;
			}
			if (lower_y > gObjects.get(i).y){
				lower_y = gObjects.get(i).y;
		}
	}
		this.x = left_x;
		this.y = upper_y;
		this.width = left_x + right_x;
		this.height = upper_y + lower_y;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject gobject: gObjects){
			gobject.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Group",this.x,this.y-this.height - 10);
	}
}
