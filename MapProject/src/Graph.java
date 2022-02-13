import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
	private HashMap<String, GraphNode> nodes= new HashMap<>();
	
	public Graph() {super();}

	/**
	 * Add a new node to the graph
	 * @param name of the planet
	 * @return true if the node is added successfully, false otherwise
	 */
	public boolean addNode(String name){
		if(nodes.containsKey(name)) return false;
		GraphNode temp = new GraphNode(name);
		nodes.put(name, temp);
		return true;
	}

	/**
	 * add a new edge between two nodes
	 * @param name1 of first node
	 * @param name2 of second node
	 * @param timeCost of the edge
	 * @param distanceCost of the edge
	 * @return true if insert successfully, false otherwise
	 */
	public boolean addEdge(String name1, String name2, int timeCost, int distanceCost){
		if(!this.nodes.containsKey(name1) || !this.nodes.containsKey(name2)) return false;
		nodes.get(name1).addEdge(name2, timeCost, distanceCost);
		nodes.get(name2).addEdge(name1, timeCost, distanceCost);
		return true;
	}
	
	/**
	 * give a new name to an existing planet without changing the map
	 * @param oldName for planet to be replaced
	 * @param newName that's to be given
	 * @return true if update successfully, false otherwise
	 * @throws Exception 
	 */
	public boolean updateNode(String oldName, String newName){
		if(!nodes.containsKey(oldName) || nodes.containsKey(newName)) return false;
		GraphNode temp = nodes.get(oldName);
		temp.name = newName;
		nodes.remove(oldName);
		nodes.put(newName, temp);
		return true;
	}
	
	public String toString() {
		String s = "";
		for(String key: nodes.keySet()) {
			s += nodes.get(key);
		}
		return s;
	}
	
	public ArrayList<GraphNode> getNodes() {
		ArrayList<GraphNode> s = new ArrayList<>();
		for(String key: nodes.keySet()) {
			s.add(nodes.get(key));
		}
		return s;
	}
	
	public enum Cost {
		TIME, DIST
	}
	
	public Path pathBetweenDist(String start, String end) {
		for(GraphNode n : nodes.values()) {
			n.hValue = Double.POSITIVE_INFINITY;
		}
		
		GraphNode startNode = nodes.get(start);
		GraphNode endNode = nodes.get(end);
		
		startNode.prevNode = null;
		startNode.hValue = startNode.heuristicDist(endNode);
		PriorityQueue<GraphNode> queue = new PriorityQueue<>();
		
		queue.add(startNode);
		do {
			GraphNode current = queue.poll();
			current.hValue -= current.heuristicDist(endNode);
			for(Edge e : current.neighbors) {
				GraphNode n = e.otherEnd;
				double newH = current.hValue+n.heuristicDist(endNode)+e.dCost;
				if(newH < n.hValue) {
					n.hValue = newH;
					n.prevNode = current;
					queue.add(n);
				}
			}
		} while(!queue.isEmpty() && queue.peek() != endNode);
		Path p = new Path();
		p.cost = endNode.hValue;
		p.push(endNode);
		GraphNode retrace = endNode;
		while(retrace.prevNode != null) {
			retrace = retrace.prevNode;
			p.push(retrace);
		}
		return p;
	}
	
	private class Path extends Stack<GraphNode> {
		double cost;
		public String toString() {
			return cost+": "+super.toString();
		}
	}
	
	public class GraphNode implements Comparable<GraphNode> {
		private GraphNode prevNode;
		String name;
		ArrayList<Edge> neighbors;
		double hValue;
		
		public GraphNode() {
			super();
		}
		
		//Node Basic Constructor
		public GraphNode(String name) {
			this.prevNode = null;
			this.name = name;
			this.neighbors = new ArrayList<>();
			hValue = 0;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		private void addEdge(String name, double timeCost, double distanceCost){
			GraphNode otherNode = nodes.get(name);
			Edge temp = new Edge(otherNode, timeCost, distanceCost);
			this.neighbors.add(temp);
		}
		
		public String getPlanetName() {
			return this.name;
		}
		
		public ArrayList<GraphNode> getNeighbors(){
			ArrayList<GraphNode> temp = new ArrayList<>();
			for(Edge i: neighbors) temp.add(i.otherEnd);
			return temp;
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
		public int compareTo(Graph.GraphNode o) {
			return ((Double) hValue).compareTo((Double) o.hValue);
		}
	}
	
	
	/**
	 * Edge class
	 * @author 
	 *
	 *
	 * Remember to add edges both directions as edges are singly linked... or maybe we have one-way paths in some cases? might be a fun addition
	 * - Ben
	 */
	public class Edge{
		double dCost; 		//distance cost of the edge
		double tCost; 		//time cost of the edge
		
		private GraphNode otherEnd;		//two ends of the edge. can be modified if the use separated nodes needed
			
		public Edge() {super();}
		
		
		//Edge Basic Constructor
		public Edge(GraphNode end, double timeCost, double distanceCost) {
			dCost = distanceCost;
			tCost = timeCost;
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
		
		public double getDistance() {
			return dCost;
		}
		
		public double getTime() {
			return tCost;
		}
		
		public String toString() {
			String s = "";
			s += "Time: " + tCost + "\n";
			s += "Distance: " + dCost + "\n";
			return s;
		}
	}
	
	
}
