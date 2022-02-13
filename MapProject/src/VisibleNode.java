import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class VisibleNode{
	private ArrayList<VisibleEdge> n;
	private int x;
	private int y;
	
	public VisibleNode(){
		
	}
	
	/**
	 * Constructor for visible node
	 * @param x position of the node
	 * @param y position of the node
	 */
	public VisibleNode(int x, int y) {
		n = new ArrayList<>();
		this.x = x;
		this.y = y;
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
		g2d.setColor(color);
		g2d.fillOval(x - 3, y - 3, 6, 6);
		for(VisibleEdge v: n) {
			g2d.drawLine(x, y, v.getOtherEnd().x, v.getOtherEnd().y);
		}
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
		String s = "";
		s += "Current location (" + x + ", " + y + ")\n";
		for(VisibleEdge e: n) {
			s += "From current location to " + e + "\n";
		}
		return s;
	}
}
