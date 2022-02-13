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
	protected GraphNode start;
	protected GraphNode destination;
	
	public Graph() throws Exception {
		nodes = read();
		start = null;
		destination = null;
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
	public boolean addEdge(String name1, String name2) throws Exception{
		if(!this.nodes.containsKey(name1) || !this.nodes.containsKey(name2)) return false;
		
		
		//need update
		boolean temp1 = nodes.get(name1).addEdge(nodes.get(name2), 0, 0);
		boolean temp2 = nodes.get(name2).addEdge(nodes.get(name1), 0, 0);
		
		
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
	
	public boolean updateNodePos(String name, int x, int y) throws Exception{
		if(!nodes.containsKey(name)) return false;
		nodes.get(name).setX(x);
		nodes.get(name).setY(y);
		write(nodes);
		return true;
	}
	
	public GraphNode setStart(String s) {
		start = nodes.get(s);
		return start;
	}
	
	public GraphNode setDestination(String s) {
		this.destination = nodes.get(s);
		return destination;
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
	
	public ArrayList<GraphNode> getNodeList(){
		ArrayList<GraphNode> temp = new ArrayList<>();
		for(String key: nodes.keySet()) {
			temp.add(nodes.get(key));
		}
		return temp;
	}
	
	public void setNodes(Hashtable<String, GraphNode> nodes) {
		this.nodes = nodes;
	}
	
	public Hashtable<String, GraphNode> getNodes() {
		return nodes;
	}
}
