public class Edge{
		private int dCost; 		//distance cost of the edge
		private int tCost; 		//time cost of the edge
		
		private GraphNode otherEnd;		//two ends of the edge. can be modified if the use separated nodes needed
			
		public Edge() {}
		
		
		//Edge Basic Constructor
		public Edge(GraphNode end, int time, int distance) {
			dCost = distance;
			tCost = time;
			otherEnd = end;
		}
		
		public Edge(GraphNode end) {
			dCost = 0;
			tCost = 0;
			otherEnd = end;
		}
		
		public GraphNode getEnd() {
			return otherEnd;
		}
		
		public int getdCost() {
			return dCost;
		}


		public void setdCost(int dCost) {
			this.dCost = dCost;
		}


		public int gettCost() {
			return tCost;
		}


		public void settCost(int tCost) {
			this.tCost = tCost;
		}


		public GraphNode getOtherEnd() {
			return otherEnd;
		}


		public void setOtherEnd(GraphNode otherEnd) {
			this.otherEnd = otherEnd;
		}


		public String toString() {
			String s = "";
			s += "Time: " + tCost + "\n";
			s += "Distance: " + dCost + "\n";
			return s;
		}
	}