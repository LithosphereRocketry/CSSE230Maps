import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph{
	protected HashMap<String, GraphNode> nodes;
	
	public Graph() throws Exception {
		nodes = read();
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
		write(nodes);
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
	public boolean addEdge(String name1, String name2, double timeCost, double distanceCost) throws Exception{
		if(!this.nodes.containsKey(name1) || !this.nodes.containsKey(name2)) return false;
		nodes.get(name1).addEdge(nodes.get(name2), timeCost, distanceCost);
		nodes.get(name2).addEdge(nodes.get(name1), timeCost, distanceCost);
		write(nodes);
		return true;
	}
	
	/**
	 * give a new name to an existing planet without changing the graph (haven't tested, don't use it yet)
	 * @param oldName for planet to be replaced
	 * @param newName that's to be given
	 * @return true if update successfully, false otherwise
	 * @throws Exception 
	 */
	public boolean updateNodeName(String oldName, String newName) throws Exception{
		if(!nodes.containsKey(oldName) || nodes.containsKey(newName)) return false;
		GraphNode temp = nodes.get(oldName);
		temp.name = newName;
		nodes.remove(oldName);
		nodes.put(newName, temp);
		write(nodes);
		return true;
	}
	
	/**
	 * erase all data in xml file
	 * @throws Exception ignore it
	 */
	public void clear() throws Exception {
		nodes = new HashMap<String, GraphNode>();
		write(nodes);
	}
	
	/**
	 * used to write data into xml file, should not be accessed by other classes
	 * @param l data to be written
	 * @throws Exception ignore it
	 */
	private void write(HashMap<String, GraphNode> l) throws Exception{
	    XMLEncoder encoder =
	        new XMLEncoder(
	            new BufferedOutputStream(
	                new FileOutputStream("graph.xml")));
	    encoder.writeObject(l);
	    encoder.close();
	}
	
	/**
	 * used to read existing data from the xml file, should not be accessed by other classes
	 * @return data
	 * @throws Exception ignore it
	 */
	 private HashMap<String, GraphNode> read() throws Exception {
	        XMLDecoder decoder = 
	        	new XMLDecoder(
	        			new BufferedInputStream(
	        					new FileInputStream("graph.xml")));
	        HashMap<String, GraphNode> ll = (HashMap<String, GraphNode>) decoder.readObject();
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
	
	public void setNodes(HashMap<String, GraphNode> nodes) {
		this.nodes = nodes;
	}
	
	public HashMap<String, GraphNode> getNodes() {
		return nodes;
	}
	
	
}
