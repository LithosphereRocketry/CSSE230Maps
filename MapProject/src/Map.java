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
	
	//Basic Constructor
	public Map() throws Exception{
		g = new Graph();
	}
	
	/**
	 * 
	 * @param g2d used to draw all nodes and edges
	 */
	public void drawOn(Graphics2D g2d) {
		for(GraphNode g: g.getNodeList()) {
			g.drawOn(g2d, Color.BLACK);
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
	public void addNode(String name, int x, int y) throws Exception {
		if(!g.addNode(name, x, y)) System.out.println(name + " is already in the graph");
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
	public void addEdge(String name1, String name2) throws Exception {
		if(! g.addEdge(name1, name2)) System.out.println(name1 + " and " + name2 + " are already linked");
	}
	

	/**
	 * update the name of a node
	 * @param oldName of the node
	 * @param newName of the node
	 * @return true if updated successfully, false otherwise
	 * @throws Exception
	 */
	public boolean updateNodeName(String oldName, String newName)throws Exception {
		return g.updateNodeName(oldName, newName);
	}
	
	public boolean updateNodePos(String name, int x, int y) throws Exception{
		return g.updateNodePos(name,x, y);
	}
	
	/**
	 * erase all existing data in xml files
	 * @throws Exception ignore it
	 */
	public void clear() throws Exception {
		g.clear();
	}
	
	/**
	 * not completed
	 * @param name of the start node
	 * @param g2d used to change the color of selected node and its edges
	 */
	public void chooseStart(String name, Graphics2D g2d) {
		GraphNode temp = g.setStart(name);
		temp.drawOn(g2d, Color.RED);
	}
	
	/**
	 * not completed
	 * @param name of the termination node
	 * @param g2d used to draw the path
	 */
	public void chooseTer(String name, Graphics2D g2d) {
		GraphNode temp = g.setDestination(name);
		temp.drawOn(g2d, Color.GREEN);
	}
	
	public String toString() {
		return g.toString();
	}
	
	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}
}
