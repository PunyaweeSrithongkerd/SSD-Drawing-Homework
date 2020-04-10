package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;
	
	private int gridSize = 10;
	
	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}
	
	public void addGObject(GObject gObject) {
		gObjects.add(gObject);
	}
	
	public void groupAll() {
		CompositeGObject group = new CompositeGObject();
		for (GObject gobj: gObjects){
			for (GObject split_gobj:gobj.get_object()){
				group.add(split_gobj);
			}
		}
		gObjects = new ArrayList<GObject>();
		gObjects.add(group);
	}

	public void deleteSelected() {
		for (GObject gobj: gObjects){
			if (gobj.is_selected()) {
				gObjects.remove(gobj);
			}
		}
	}
	
	public void clear() {
		gObjects = new ArrayList<GObject>();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		private GObject select;

		private void deselectAll() {
			for (GObject gobj: gObjects){
				gobj.deselected();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			boolean check = false;
			for (GObject gobj: gObjects){
				if(gobj.pointerHit(e.getX(),e.getY())){
					check = true;
					select = gobj;
				}
			}
			if (check){
				deselectAll();
				select.selected();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			select.move(e.getX(),e.getY());
		}
	}
	
}