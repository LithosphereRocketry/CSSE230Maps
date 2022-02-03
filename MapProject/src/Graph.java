import java.util.ArrayList;

public class Graph {
	GraphNode start;
	
	//Graph Basic Constructor
	public Graph() {
		start = null;
	}
	
	/**
	 * insert a new node to the graph
	 * @param name of the new planet
	 * @return true if insert successfully, false otherwise
	 */
	public boolean insert(String name) {
		if(name == null) throw new IllegalArgumentException();
		
		if(this.start == null) {
			
		}
		
		MyBoolean b = new MyBoolean();
		this.start = this.start.insert(name, b);
		
		return b.b;
	}

	
	/**
	 * GraphNode class
	 * @author 
	 *
	 */
	public class GraphNode{
		String name;
		ArrayList<GraphNode> neighbors;
		ArrayList<Edge> edges;
		
		//Node Basic Constructor
		public GraphNode(String name) {
			this.name = name;
			neighbors = new ArrayList<>();
			edges = new ArrayList<>();
		}
		
		//insert a new vertex
		//need to determine where to insert it
		public GraphNode insert(String n, MyBoolean b) {
			
			
			
			return this;
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
		
		GraphNode[] ends;		//two ends of the edge. can be modified if the use separated nodes needed
		
		
		//Edge Basic Constructor
		public Edge(GraphNode start, GraphNode end) {
			dCost = 0;
			tCost = 0;
			ends = new GraphNode[2];
			ends[0] = start;
			ends[1] = end;
		}
		
	}
	
	
	/**
	 * MyBoolean class
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
