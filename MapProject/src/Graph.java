import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Graph{
	protected Hashtable<String, GraphNode> nodes;

	public Graph() throws Exception {
		nodes = read();
	}
	
	/**
	 * Add a new node to the graph
	 * @param name of the planet
	 * @return true if the node is added successfully, false otherwise
	 * @throws Exception 
	 */
	public boolean addNode(String name, int x, int y) throws Exception{
		if(nodes.containsKey(name)) return false;
		GraphNode temp = new GraphNode(name, x, y);
		nodes.put(name, temp);
		write(nodes);
		return true;
	}

	/**
	 * add a new edge between two nodes
	 * @param name1 of first node
	 * @param name2 of second node
	 * @return true if insert successfully, false otherwise
	 */

	public void addEdge(String name1, String name2) throws Exception{
		if(!this.nodes.containsKey(name1) || !this.nodes.containsKey(name2)) {
			System.out.println("At least one invalid choice!");
			return ;
		}
		
		if(nodes.get(name1).addEdge(nodes.get(name2))) {
			nodes.get(name2).addEdge(nodes.get(name1));
			write(nodes);
		}
	}
	
	/**
	 * erase all data in xml file
	 * @throws Exception ignore it
	 */
	public void clear() throws Exception {
		nodes = new Hashtable<String, GraphNode>();
		write(nodes);
	}
	
	/**
	 * used to write data into xml file, should not be accessed by other classes
	 * @param l data to be written
	 * @throws Exception ignore it
	 */
	private void write(Hashtable<String, GraphNode> l) throws Exception{
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
	 private Hashtable<String, GraphNode> read() throws Exception {
	        XMLDecoder decoder = 
	        	new XMLDecoder(
	        			new BufferedInputStream(
	        					new FileInputStream("graph.xml")));
	        Hashtable<String, GraphNode> ll = (Hashtable<String, GraphNode>) decoder.readObject();
	        decoder.close();
	        return ll;
	    }
	 
	 public void reset() {
		 for(String key: nodes.keySet()) {
			nodes.get(key).reset();
		 }
	 }
	 
	public Collection<GraphNode> getNodeList(){
		return nodes.values();
	}
	
	public Collection<String> getNodeNames(){
		return nodes.keySet();
	}
	
	public GraphNode getNode(String n) {
		return nodes.get(n);
	}
	
	public Hashtable<String, GraphNode> getNodes() {
		return nodes;
	}

	public void setNodes(Hashtable<String, GraphNode> nodes) {
		this.nodes = nodes;
	}
	
	public ArrayList<LinkedList<GraphNode>> travelPlannerDistance(int distanceConstraint, String startingNode) { //copy over to temp file then copy back later
		ArrayList<LinkedList<GraphNode>> pathList = new ArrayList<LinkedList<GraphNode>>();
		LinkedList<GraphNode> list = new LinkedList<GraphNode>();
		list.add(nodes.get(startingNode));
		pathList.add(list);
		nodes.get(startingNode).getNodesWithinDistance(distanceConstraint, list, 0, false, pathList);
		
		return returnThreeRandomPaths(pathList);

	}
	
	public ArrayList<LinkedList<GraphNode>> returnThreeRandomPaths(ArrayList<LinkedList<GraphNode>> paths) {
		Random rand = new Random();
		ArrayList<LinkedList<GraphNode>> returnList = new ArrayList<LinkedList<GraphNode>>();
		if (paths.size() <= 3) {
			return paths;
		}
		while (true) {
			int firstPathIndex = rand.nextInt(paths.size());
			int secondPathIndex = rand.nextInt(paths.size());
			int thirdPathIndex = rand.nextInt(paths.size());
			if (firstPathIndex != secondPathIndex && firstPathIndex != thirdPathIndex
					&& secondPathIndex != thirdPathIndex) {
				returnList.add(paths.get(firstPathIndex));
				returnList.add(paths.get(secondPathIndex));
				returnList.add(paths.get(thirdPathIndex));
				return returnList;
			}
		}

	}
	
	public ArrayList<LinkedList<GraphNode>> travelPlannerTime(int timeConstraint, String startingNode) { //copy over to temp file then copy back later
		ArrayList<LinkedList<GraphNode>> pathList = new ArrayList<LinkedList<GraphNode>>();
		LinkedList<GraphNode> list = new LinkedList<GraphNode>();
		list.add(nodes.get(startingNode));
		pathList.add(list);
		nodes.get(startingNode).getNodesWithinDistance(timeConstraint, list, 0, false, pathList);
		return returnThreeRandomPaths(pathList);

	}
	
	

	public enum Cost {
		TIME, DIST
	}
	
	public Path pathBetweenDist(String start, String end) {
		for(GraphNode n : nodes.values()) {
			n.sethValue(Double.POSITIVE_INFINITY);
		}
		
		GraphNode startNode = nodes.get(start);
		GraphNode endNode = nodes.get(end);
		
		startNode.setLastNode(null);
		startNode.sethValue(startNode.heuristicDist(endNode));
		PriorityQueue<GraphNode> queue = new PriorityQueue<>();
		
		queue.add(startNode);
		
		GraphNode current;
		while((current = queue.poll()) != endNode) {
			for(Edge e : current.getNeighbors().values()) {
				GraphNode n = e.getOtherEnd();
				double newH = current.gethValue()-current.heuristicDist(endNode)+n.heuristicDist(endNode)+e.getDCost();
				if(newH < n.gethValue()) {
					n.setLastNode(current);
					n.sethValue(newH);
					queue.add(n);
				}
			}
		}
		Path p = new Path();
		p.cost = endNode.gethValue();
		p.push(endNode);
		GraphNode retrace = endNode;
		while(retrace.getLastNode() != null) {
			retrace = retrace.getLastNode();
			p.push(retrace);
		}
		return p;
	}
	
	public Path pathBetweenTime(String start, String end) {
		for(GraphNode n : nodes.values()) {
			n.sethValue(Double.POSITIVE_INFINITY);
		}
		
		GraphNode startNode = nodes.get(start);
		GraphNode endNode = nodes.get(end);
		
		startNode.setLastNode(null);
		startNode.sethValue(startNode.heuristicTime(endNode));
		PriorityQueue<GraphNode> queue = new PriorityQueue<>();
		
		queue.add(startNode);
		
		GraphNode current;
		while((current = queue.poll()) != endNode) {
			for(Edge e : current.getNeighbors().values()) {
				GraphNode n = e.getOtherEnd();
				double newH = current.gethValue()-current.heuristicTime(endNode)+n.heuristicTime(endNode)+e.getTCost();
				if(newH < n.gethValue()) {
					n.setLastNode(current);
					n.sethValue(newH);
					queue.add(n);
				}
			}
		}
		
		Path p = new Path();
		p.cost = endNode.gethValue();
		p.push(endNode);
		GraphNode retrace = endNode;
		while(retrace.getLastNode() != null) {
			retrace = retrace.getLastNode();
			p.push(retrace);
		}
		return p;
	}

	class Path extends Stack<GraphNode> {

		double cost;
		public String toString() {
			return cost+": "+super.toString();
		}
		
		public String getCost() {
			String result = String.format("%.2f", cost);
			return result;
		}
		
	}
}
