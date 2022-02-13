import java.io.Serializable;
import java.util.ArrayList;

public class GraphNode implements Serializable, Comparable<GraphNode> {
	GraphNode prevNode;
	String name;
	ArrayList<Edge> neighbors;
	double hValue;
	
	public GraphNode() {
		name = "";
		this.neighbors = new ArrayList<>();
		hValue = 0;
	}
	
	//Node Basic Constructor
	public GraphNode(String name) {
		this.prevNode = null;
		this.name = name;
		this.neighbors = new ArrayList<>();
		hValue = 0;
	}
	
	public ArrayList<Edge> getEdges(){
		return neighbors;
	}
	
	public double getH() {
		return hValue;
	}
	
	public void setH(double h) {
		hValue = h;
	}

	
	/**
	 * insert a new edge between current node and another node
	 * @param name of another planet to be connected
	 * @param timeCost of the edge
	 * @param distanceCost of the edge
	 */
	public void addEdge(GraphNode o, double timeCost, double distanceCost){
		Edge temp = new Edge(o, timeCost, distanceCost);
		this.neighbors.add(temp);
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

	public void sethValue(int hValue) {
		this.hValue = hValue;
	}

	public void setNeighbors(ArrayList<Edge> neighbors) {
		this.neighbors = neighbors;
	}
	
	public ArrayList<Edge> getNeighbors(){
		return neighbors;
	}
	
	public String toString() {
		String s = "";
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
	
	