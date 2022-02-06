import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Graph{
	private Hashtable<String, GraphNode> nodes = new Hashtable<String, GraphNode>();
	
	public Graph() {

	}
	
	public void setNodes(Hashtable<String, GraphNode> nodes) {
		this.nodes = nodes;
	}



	/**
	 * Add a new node to the graph
	 * @param name of the planet
	 * @return true if the node is added successfully, false otherwise
	 * @throws Exception 
	 */
	public boolean addNode(String name) throws Exception{
		if(nodes.containsKey(name)) return false;
		GraphNode temp = new GraphNode(name);
		nodes.put(name, temp);
		write(nodes, "graph.xml");
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
	
	public static void write(Hashtable<String, GraphNode> l, String filename) throws Exception{
	    XMLEncoder encoder =
	        new XMLEncoder(
	            new BufferedOutputStream(
	                new FileOutputStream(filename)));
	    encoder.writeObject(l);
	    encoder.close();
	}
	
	 public static Hashtable<String, GraphNode> read(String filename) throws Exception {
	        XMLDecoder decoder = 
	        	new XMLDecoder(
	        			new BufferedInputStream(
	        					new FileInputStream(filename)));
	        Hashtable<String, GraphNode> ll = (Hashtable<String, GraphNode>) decoder.readObject();
	        decoder.close();
	        return ll;
	    }
	
	public String toString() {
		String s = "";
		for(String key: nodes.keySet()) {
			s += nodes.get(key);
		}
		return s;
	}
	
	public Hashtable<String, GraphNode> getNodes() {
		
		return nodes;
	}
	
	public ArrayList<GraphNode> getNodeList() {
		ArrayList<GraphNode> s = new ArrayList<>();
		for(String key: nodes.keySet()) {
			s.add(nodes.get(key));
		}
		return s;
	}

	public class GraphNode implements Serializable{
		private String name;
		private ArrayList<Edge> neighbors;
		private int hValue;
		
		public GraphNode() {
			name = "";
			this.neighbors = new ArrayList<>();
			hValue = 0;
		}
		
		//Node Basic Constructor
		public GraphNode(String name) {
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
		private void addEdge(String name, int timeCost, int distanceCost){
			GraphNode otherNode = nodes.get(name);
			Edge temp = new Edge(otherNode, timeCost, distanceCost);
			this.neighbors.add(temp);
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

		public void setNeighbors(ArrayList<Edge> neighbors) {
			this.neighbors = neighbors;
		}

		
		public ArrayList<GraphNode> getNeighbors(){
			ArrayList<GraphNode> temp = new ArrayList<>();
			for(Edge i: neighbors) temp.add(i.otherEnd);
			return temp;
		}
		
		public ArrayList<Edge> getEdges(){
			return neighbors;
		}
		
		public int geth() {
			return hValue;
		}
		
		public void seth(int h) {
			hValue = h;
		}
		
		public String toString() {
			String s = "";
			s += "    Planet: " + name + "\n" + "       Edges\n\n";
			for(Edge e: neighbors) {
				s += e;
				s += "From " + this.name + " to " + e.otherEnd.name + "\n\n";
			}
			return s;
		}
	}
	
	
	/**
	 * Edge class
	 * @author 
	 *
	 */
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
}
