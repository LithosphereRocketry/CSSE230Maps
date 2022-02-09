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
	private Hashtable<String, GraphNode> nodes;
	
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
	public boolean addEdge(String name1, String name2, int timeCost, int distanceCost) throws Exception{
		if(!this.nodes.containsKey(name1) || !this.nodes.containsKey(name2)) return false;
		nodes.get(name1).addEdge(nodes.get(name2), timeCost, distanceCost);
		nodes.get(name2).addEdge(nodes.get(name1), timeCost, distanceCost);
		write(nodes);
		return true;
	}
	
	/**
	 * give a new name to an existing planet without changing the map
	 * @param oldName for planet to be replaced
	 * @param newName that's to be given
	 * @return true if update successfully, false otherwise
	 * @throws Exception 
	 */
	public boolean updateNode(String oldName, String newName) throws Exception{
		if(!nodes.containsKey(oldName) || nodes.containsKey(newName)) return false;
		GraphNode temp = nodes.get(oldName);
		temp.setName(newName);
		nodes.remove(oldName);
		nodes.put(newName, temp);
		write(nodes);
		return true;
	}
	
	public ArrayList<GraphNode> getNodeList() {
		ArrayList<GraphNode> s = new ArrayList<>();
		for(String key: nodes.keySet()) {
			s.add(nodes.get(key));
		}
		return s;
	}
	
	public static void write(Hashtable<String, GraphNode> l) throws Exception{
	    XMLEncoder encoder =
	        new XMLEncoder(
	            new BufferedOutputStream(
	                new FileOutputStream("graph.xml")));
	    encoder.writeObject(l);
	    encoder.close();
	}
	
	 public static Hashtable<String, GraphNode> read() throws Exception {
	        XMLDecoder decoder = 
	        	new XMLDecoder(
	        			new BufferedInputStream(
	        					new FileInputStream("graph.xml")));
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
}
