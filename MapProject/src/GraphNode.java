import java.io.Serializable;
import java.util.Hashtable;


public class GraphNode implements Serializable{
		private String name;
		private Hashtable<String,Edge> neighbors;
		private int hValue;
		
		public GraphNode() {
			name = "";
			this.neighbors = new Hashtable<>();
			hValue = 0;
		}
		
		//Node Basic Constructor
		public GraphNode(String name) {
			this.name = name;
			this.neighbors = new Hashtable<>();
			hValue = 0;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		public boolean addEdge(GraphNode o, int timeCost, int distanceCost){
			if(neighbors.contains(o.name)) return false;
			this.neighbors.put(o.name, new Edge(o, timeCost, distanceCost));
			return true;
		}
		
		/**
		 * update the edge's info
		 * @param name of the other end of the edge
		 * @param time cost of the edge
		 * @param dis cost of the edge
		 * @return
		 */
		public boolean updateEdge(String name, int time, int dis) {
			if(!neighbors.contains(name)) return false;
			neighbors.get(name).setTCost(time);
			neighbors.get(name).setDCost(dis);
			return true;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int gethValue() {
			return hValue;
		}

		public void sethValue(int hValue) {
			this.hValue = hValue;
		}
		
		public Hashtable<String, Edge> getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(Hashtable<String, Edge> neighbors) {
			this.neighbors = neighbors;
		}

		public String toString() {
			String s = "";
			s += "    Planet: " + name + "\n\n" + "       Edges\n";
			for(String key: neighbors.keySet()) {
				s += "From " + this.name + " to " + key + "\n\n";
				s += neighbors.get(key);
			}
			return s;
		}
	}
	
	