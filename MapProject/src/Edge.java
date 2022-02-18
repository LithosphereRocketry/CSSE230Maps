import java.awt.Color;
import java.awt.Graphics2D;

public class Edge{
	private double dCost; 		//distance cost of the edge
	private double tCost; 		//time cost of the edge
		
	GraphNode otherEnd;		//two ends of the edge. can be modified if the use separated nodes needed
				
	public Edge() {}
		
	//Edge Basic Constructor
	public Edge(GraphNode end, double distanceCost, double timeCost) {
		dCost = distanceCost;
		tCost = timeCost;
		otherEnd = end;
	}
	
	public void drawOn(Graphics2D g2d, int x, int y, boolean selected, boolean draw) {
		
		if(draw) {
			g2d.setColor(Color.YELLOW);
			g2d.drawLine(otherEnd.getX(), otherEnd.getY(), x, y);
		}
		if(selected && otherEnd.getSelected()) {
			g2d.setColor(Color.RED);
			g2d.drawLine(otherEnd.getX(), otherEnd.getY(), x, y);
		}
		
	}
	
	public double getDCost() {return dCost;}

	public void setDCost(double dCost) {this.dCost = dCost;}

	public double getTCost() {return tCost;}
	
	public void setTCost(double tCost) {this.tCost = tCost;}

	public String toString() {
		String s = "";
		s += "      Time: " + tCost + "\n";
		s += "      Distance: " + dCost + "\n";
		return s;
	}

	public GraphNode getOtherEnd() { return otherEnd; }

	public void setOtherEnd(GraphNode otherEnd) {this.otherEnd = otherEnd;}

}

