import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class VisibleNode{
	private ArrayList<VisibleEdge> n;
	private int x;
	private int y;
	private String name;
	
	public VisibleNode(){
		
	}
	
	/**
	 * Constructor for visible node
	 * @param x position of the node
	 * @param y position of the node
	 */
	public VisibleNode(int x, int y, String name) {
		n = new ArrayList<>();
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * add a visible edge based on an existing edge
	 * @param end of the edge
	 */
	public void addVEdge(VisibleNode end) {
		n.add(new VisibleEdge(end));
	}
	
	/**
	 * fill the node with a circle and draw its edges
	 * @param g2d used to draw
	 * @param color of the node
	 */
	public void drawOn(Graphics2D g2d, Color color) {
		g2d.setColor(Color.BLUE);
		g2d.drawString(name, x + 5, + 5);
		g2d.setColor(color);
		g2d.fillOval(x - 3, y - 3, 6, 6);
		for(VisibleEdge v: n) {
			g2d.drawLine(x, y, v.getOtherEnd().x, v.getOtherEnd().y);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<VisibleEdge> getN() {
		return n;
	}

	public void setN(ArrayList<VisibleEdge> n) {
		this.n = n;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		String s = name;
		s += "\nCurrent location (" + x + ", " + y + ")\n";
		for(VisibleEdge e: n) {
			s += "From " + name + " to " + e + "\n";
		}
		return s;
	}
}
