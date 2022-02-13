public class Edge {
	double dCost; 		//distance cost of the edge
	private double tCost; 		//time cost of the edge
	
	GraphNode otherEnd;		//two ends of the edge. can be modified if the use separated nodes needed
			
	public Edge() {}
		
		
	//Edge Basic Constructor
	public Edge(GraphNode end, double timeCost, double distanceCost) {
		dCost = distanceCost;
		tCost = timeCost;
		otherEnd = end;
	}
	
	public double getDCost() {return dCost;}

	public void setDCost(int dCost) {this.dCost = dCost;}

	public double getTCost() {return tCost;}
	
	public void setTCost(int tCost) {this.tCost = tCost;}

	public GraphNode getOtherEnd() { return otherEnd; }

	public void setOtherEnd(GraphNode otherEnd) {this.otherEnd = otherEnd;}

	public String toString() {
		String s = "";
		s += "Time: " + tCost + "\n";
		s += "Distance: " + dCost + "\n";
		return s;
	}
}