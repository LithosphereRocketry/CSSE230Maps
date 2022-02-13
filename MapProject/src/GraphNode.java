import java.io.Serializable;
import java.util.HashMap;

public class GraphNode implements Serializable, Comparable<GraphNode> {
	private GraphNode lastNode;
	private String name;
	private HashMap<String,Edge> neighbors;
	private double hValue;
	
	public GraphNode() {
		name = "";
		this.neighbors = new HashMap<>();
		hValue = 0;
	}
	
	//Node Basic Constructor
	public GraphNode(String name) {
		lastNode = null;
		this.name = name;
		this.neighbors = new HashMap<>();
		hValue = 0;
	}
	
	public GraphNode getLastNode() {
		return lastNode;
	}

	public void setLastNode(GraphNode lastNode) {
		this.lastNode = lastNode;
	}
	
	/**
	 * insert a new edge between current node and another node
	 * @param name of another planet to be connected
	 * @param timeCost of the edge
	 * @param distanceCost of the edge
	 */
	public boolean addEdge(GraphNode o, double timeCost, double distanceCost){
		if(neighbors.containsKey(o.name)) return false;
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
		if(!neighbors.containsKey(name)) return false;
		neighbors.get(name).setTCost(time);
		neighbors.get(name).setDCost(dis);
		return true;
	}

	public void setNeighbors(HashMap<String, Edge> neighbors) {
		this.neighbors = neighbors;
	}
	public HashMap<String, Edge> getNeighbors() {
		return neighbors;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double gethValue() {
		return hValue;
	}

	public void sethValue(double hValue) {
		this.hValue = hValue;
	}
	
	public String toString() {
		String s = ""; // commented some stuff because the toString being this long makes it really unreadable for debugging
		s += "    Planet: " + name +" H: "+hValue/*+ "\n" + "       Edges\n\n"*/;
/*		for(Edge e: neighbors) {
			s += e;
			s += "From " + this.name + " to " + e.otherEnd.name + "\n\n";
		}	*/
		return s;
	}
	
	public double heuristicDist(GraphNode dest) {
		// TODO: write this
		return 4-Integer.parseInt(name); // scuffed heuristic for testing
	}
	public double heuristicTime(GraphNode dest) {
		// TODO: write this
		return 1000;
	}

	@Override
	public int compareTo(GraphNode o) {
		return ((Double) hValue).compareTo((Double) o.hValue);
	}
}
	
	