import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Map extends ImagePanel{
	public Graph g;
	private String start;
	
	//Basic Constructor
	public Map() throws Exception{
		super("src/SW Galaxy 1.jpg");
		g = new Graph();
		start = "";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawOn(g2d);
	}
	
	/**
	 * 
	 * @param g2d used to draw all nodes and edges
	 */
	public void drawOn(Graphics2D g2d) {
		for(GraphNode g: g.getNodeList()) {
			g.drawOn(g2d, Color.YELLOW);
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
		g.addEdge(name1, name2);
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
	
	public void setStart(String name) {
		start = name;
//		System.out.println(start);
	}
	
	public void setDes(String name) {
		if(start == "") {
			System.out.println("No starting planet selected");
			return;
		}
		System.out.println(g.pathBetweenDist(start, name));
	}
	
	/**
	 * called when the user choose a start node, path finding will not start
	 * @param name of the start node
	 * @param g2d used to change the color of selected node and its edges
	 */
	public void chooseStart(String name, Graphics2D g2d) {
		start = name;
		GraphNode temp = g.getNode(name);
		temp.drawOn(g2d, Color.RED);
	}
	
	/**
	 * called when the user choose the destination, pathFinding will start
	 * not complete
	 * @param name of the termination node
	 * @param g2d used to draw the path
	 */
	public void chooseDes(String name, Graphics2D g2d) {
		GraphNode temp = g.getNode(name);
		temp.drawOn(g2d, Color.GREEN);
		g.pathBetweenDist(start, name);
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
