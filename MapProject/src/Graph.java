import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private HashMap<String, GraphNode> nodes;
	
	//Graph Basic Constructor
	public Graph() {
		nodes = new HashMap<>();
	}
	
	public boolean addNode(String name) {
		//depending on if we want to be able to update the planty's info
//		if(nodes.containsKey(name)) return false;
		nodes.put(name, new GraphNode(name));
		return true;
	}

	public boolean addEdge(String n1, String n2, int timeCost, int distanceCost) {
		if(!this.nodes.containsKey(n1) && !this.nodes.containsKey(n2)) return false;
		nodes.get(n1).addEdge(n2, timeCost, distanceCost);
		return true;
	}
	
	/**
	 * GraphNode class
	 * @author 
	 *
	 */
	public class GraphNode{
		String name;
		ArrayList<Edge> neighbors;
		
		//Node Basic Constructor
		public GraphNode(String name) {
			this.name = name;
			neighbors = new ArrayList<>();
		}
		
		/**
		 * insert a new edge from current node to the node with element e
		 * @param e element of the node that will be connected to
		 */
		public void addEdge(String name, int timeCost, int distanceCost) {
			
			GraphNode otherNode = nodes.get(name);
			this.neighbors.add(new Edge(otherNode, timeCost, distanceCost));

		}
		
	}
	
	
	/**
	 * Edge class
	 * @author 
	 *
	 */
	public class Edge{
		int dCost; 		//distance cost of the edge
		int tCost; 		//time cost of the edge
		
		private GraphNode otherEnd;		//two ends of the edge. can be modified if the use separated nodes needed
		
		
		//Edge Basic Constructor
		public Edge(GraphNode end, int time, int distance) {
			dCost = 0;
			tCost = 0;
			otherEnd = end;
		}
		
	}
	
	
	/**
	 * MyBoolean class (in case needed)
	 * @author Andrea
	 *
	 */
	private class MyBoolean{
		boolean b;
		
		public MyBoolean() {
			b = true;
		}
		
		public void setTrue() {
			b = true;
		}
		
		public void setFalse() {
			b = false;
		}
		
	}
}
