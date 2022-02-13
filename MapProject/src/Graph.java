import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

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
		boolean temp1 = nodes.get(name1).addEdge(nodes.get(name2), timeCost, distanceCost);
		boolean temp2 = nodes.get(name2).addEdge(nodes.get(name1), timeCost, distanceCost);
		write(nodes);
		return temp1 && temp2;
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
		temp.setName(newName);
		nodes.remove(oldName);
		nodes.put(newName, temp);
		write(nodes);
		return true;
	}
	
	public boolean updateEdgeInfo(String name1, String name2, int time, int dis) throws Exception{
		if(!nodes.get(name1).updateEdge(name2, time, dis) && !nodes.get(name2).updateEdge(name1, time, dis)) {
			return false;
		}
		write(nodes);
		return true;
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
	
	public String toString() {
		String s = "";
		for(String key: nodes.keySet()) {
			s += nodes.get(key);
		}
		return s;
	}
	
	public void setNodes(Hashtable<String, GraphNode> nodes) {
		this.nodes = nodes;
	}
	
	public Hashtable<String, GraphNode> getNodes() {
		return nodes;
	}
}
