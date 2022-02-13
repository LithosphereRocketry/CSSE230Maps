import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Hashtable;


public class GraphNode implements Serializable, Comparable<GraphNode>{
		private String name;
		private Hashtable<String,Edge> neighbors;
		private GraphNode lastNode;
		private int x;
		private int y;
		private double hValue;
		
		public GraphNode() {
			neighbors = new Hashtable<>();
			hValue = 0;
		}
		
		//Node Basic Constructor
		public GraphNode(String name, int x, int y) {
			this.lastNode = null;
			this.name = name;
			this.neighbors = new Hashtable<>();
			this.x = x;
			this.y = y;
			this.hValue = 0;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		public boolean addEdge(GraphNode o){
			if(neighbors.contains(o.name)) return false;
			
			//need update
			this.neighbors.put(o.name, new Edge(o, 0,0));
			
			
			return true;
		}
		
		/**
		 * update the edge's info
		 * @param name of the other end of the edge
		 * @param time cost of the edge
		 * @param dis cost of the edge
		 * @return
		 */
		public boolean updateEdge(String name, double time, double dis) {
			if(!neighbors.contains(name)) return false;
			neighbors.get(name).setTCost(time);
			neighbors.get(name).setDCost(dis);
			return true;
		}
		
		public void drawOn(Graphics2D g2d, Color color) {
			g2d.setColor(Color.BLUE);
			g2d.drawString(name, x + 5, + 5);
			g2d.setColor(color);
			g2d.fillOval(x - 3, y - 3, 6, 6);
			
			for(String key: neighbors.keySet()) {
				g2d.drawLine(x, y, neighbors.get(key).otherEnd.x, neighbors.get(key).otherEnd.y);
			}
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
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

		public double gethValue() {
			return hValue;
		}

		public void sethValue(double hValue) {
			this.hValue = hValue;
		}

		public Hashtable<String, Edge> getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(Hashtable<String, Edge> neighbors) {
			this.neighbors = neighbors;
		}
		
		//for test/debug use
		public String testString() {
			String s = "";
			s += "    Planet: " + name +" H: " + hValue;
			s += "\nCurrent location (" + x + ", " + y + ")\n\n"
					+ "       Edges\n\n";
			for(String key: neighbors.keySet()) {
				s += "From " + this.name + " to " + key + "\n";
				s += neighbors.get(key) + "\n";
			}
			return s;
		}
		
		//for xml use, don't use for testing
		public String toString() {
			String s = "";
			s += "    Planet: " + name;
			s += "\nCurrent location (" + x + ", " + y + ")\n\n"
					+ "       Edges\n\n";
			for(String key: neighbors.keySet()) {
				s += "From " + this.name + " to " + key + "\n";
				s += neighbors.get(key) + "\n";
			}
			return s;
		}
		
		public int compareTo(GraphNode o) {
			return ((Double) hValue).compareTo((Double) o.hValue);
		}


		public GraphNode getLastNode() {
			return lastNode;
		}

		public void setLastNode(GraphNode lastNode) {
			this.lastNode = lastNode;
		}
	

	public double heuristicDist(GraphNode dest) {
		// TODO: write this
		return 4; // scuffed heuristic for testing
	}
	public double heuristicTime(GraphNode dest) {
		// TODO: write this
		return 1000;
	}

}
	
	