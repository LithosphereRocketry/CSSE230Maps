public class Edge{
	private double dCost; 		//distance cost of the edge
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

