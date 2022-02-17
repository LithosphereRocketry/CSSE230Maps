import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Iterator;

public class Map extends ImagePanel{
	public Graph g;
	private String start;
	private String des;
	static final Color NORMAL_COLOR = Color.YELLOW;
	static final Color SELECTED_NODE = Color.RED;
	
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
			if(g.name.equals(start) || g.name.equals(des) || g.getSelected()) {
				g.drawOn(g2d, SELECTED_NODE);
			} else{
				g.drawOn(g2d, NORMAL_COLOR);
			}
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
		repaint();
	}
	
	public void setDes(String name) {
		if(start == "") {
			System.out.println("No starting planet selected");
			return;
		}
		des = name;
		repaint();
	}
	
	public Collection<String> getNodeNames(){
		return g.getNodeNames();
	}
	
	public Graph.Path pathBetweenDist() {
		g.reset(start, des);
		Graph.Path temp = g.pathBetweenDist(start, des);
		Iterator<GraphNode> ite = temp.iterator();
		while(ite.hasNext()) {
			ite.next().setSelected();
		}
		repaint();
		return temp;
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
