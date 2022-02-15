import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GraphicsComponent extends JComponent {
	private Graphics2D g2d;
	public ArrayList<GraphNode> graphNodes = new ArrayList<GraphNode>();
	
	//Basic Constructor
	public GraphicsComponent() {
		
	}
	
	public void draw(Graphics2D g2d){
		this.g2d = g2d;
		g2d.create();
		
	}
	
	public void update() {
		/*for (int i = 0; i < this.graphNodes.size(); i++) {
			this.graphNodes.get(i).drawOn(g2d, Color.red);
		} */
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Map map;
		try {
			map = new Map();
			map.drawOn(g2d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
