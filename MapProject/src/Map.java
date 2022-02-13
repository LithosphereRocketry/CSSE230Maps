import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

public class Map{
	private Graph g;
	private Hashtable<String, VisibleNode> gNodes;
	
	//Basic Constructor
	public Map() throws Exception{
		g = new Graph();
		gNodes = read();
	}
	
	/**
	 * 
	 * @param g2d used to draw all nodes and edges
	 */
	public void drawOn(Graphics2D g2d) {
		for(String key: gNodes.keySet()) {
			gNodes.get(key).drawOn(g2d, Color.BLACK);
		}
	}
	
	/**
	 * add a new node to the map and graph
	 * @param name of the new node
	 * @param x position of the node
	 * @param y position of the node
	 * @return true if insert successfully, false otherwise
	 * @throws Exception ignore it
	 */
	public boolean addNode(String name, int x, int y) throws Exception {
		if(!g.addNode(name)) return false;
		
		gNodes.put(name, new VisibleNode(x, y, name));
		write(gNodes);
		return true;
	}
	
	/**
	 * add an edge between two nodes (it's double direction)
	 * @param name1 of the first node
	 * @param name2 of the second node
	 * @param time cost of the edge
	 * @param distance of the edge
	 * @return true if insert successfully, false otherwise
	 * @throws Exception ignore it
	 */
	public boolean addEdge(String name1, String name2, int time, int distance) throws Exception {
		if(!g.addEdge(name1, name2, time, distance)) return false;
		gNodes.get(name1).addVEdge(gNodes.get(name2));
		gNodes.get(name2).addVEdge(gNodes.get(name1));
		write(gNodes);
		return true;
	}
	
	/**
	 * update the name of a node
	 * @param oldName of the node
	 * @param newName of the node
	 * @return true if updated successfully, false otherwise
	 * @throws Exception
	 */
	public boolean updateNodeName(String oldName, String newName)throws Exception {
		if(!g.updateNodeName(oldName, newName)) return false;
		
		VisibleNode temp = gNodes.get(oldName);
		temp.setName(newName);
		gNodes.remove(oldName);
		gNodes.put(newName, temp);
		return true;
	}
	
	/**
	 * not completed
	 * @return
	 */
	public boolean updateEdge(String name1, String name2, int time, int distance) {
		
		return true;
	}
	
	/**
	 * erase all existing data in xml files
	 * @throws Exception ignore it
	 */
	public void clear() throws Exception {
		gNodes = new Hashtable<String, VisibleNode>();
		g.clear();
		write(gNodes);
	}
	
	/**
	 * not completed
	 * @param name of the start node
	 * @param g2d used to change the color of selected node and its edges
	 */
	public void chooseStart(String name, Graphics2D g2d) {
		gNodes.get(name).drawOn(g2d, Color.red);
	}
	
	/**
	 * not completed
	 * @param name of the termination node
	 * @param g2d used to draw the path
	 */
	public void chooseTer(String name, Graphics2D g2d) {
		gNodes.get(name).drawOn(g2d, Color.red);
		
	}
	
	/**
	 * used to write current nodes into xml file, should not be accessed by other classes
	 * @param l nodes
	 * @throws Exception ignore it
	 */
	private void write(Hashtable<String, VisibleNode> l) throws Exception{
	    XMLEncoder encoder =
	        new XMLEncoder(
	            new BufferedOutputStream(
	                new FileOutputStream("map.xml")));
	    encoder.writeObject(l);
	    encoder.close();
	}
	
	/**
	 * used to read existing xml file, should not be accessed by other classes
	 * @return data stored in the file
	 * @throws Exception ignore it
	 */
	private Hashtable<String, VisibleNode> read() throws Exception {
        XMLDecoder decoder = 
        	new XMLDecoder(
        			new BufferedInputStream(
        					new FileInputStream("map.xml")));
        Hashtable<String, VisibleNode> ll = (Hashtable<String, VisibleNode>) decoder.readObject();
        decoder.close();
        return ll;
    }
	
	public String toString() {
		String s = "";
		for(String key: gNodes.keySet()) {
			s += gNodes.get(key) + "\n";
		}
		return s;
	}
	
	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public Hashtable<String, VisibleNode> getGNodes() {
		return gNodes;
	}

	public void setGNodes(Hashtable<String, VisibleNode> nodes) {
		this.gNodes = nodes;
	}
}
