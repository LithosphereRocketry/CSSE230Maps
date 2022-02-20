import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Map extends ImagePanel implements MouseListener {
	public Graph g;
	private String start;
	private String des;
	private int dis;
	private int time;
	static final Color NORMAL_COLOR = Color.YELLOW;
	static final Color SELECTED_NODE = Color.RED;
	private boolean displayAll;
	private boolean drawFirst;
	private boolean drawSecond;
	private boolean drawThird;
	private RouterPanel router;
	static final Color FIRST_PATH = Color.GREEN;
	static final Color SECOND_PATH = Color.PINK;
	static final Color THIRD_PATH = Color.ORANGE;
	
	//Basic Constructor
	public Map() throws Exception{
		super("src/SW Galaxy 1.jpg");
		g = new Graph();
		start = "";
		displayAll = false;
		drawFirst = false;
		drawSecond = false;
		drawThird = false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawOn(g2d);
		if(displayAll) drawAll(g2d);
		if(drawFirst) drawFirst(g2d);
		if(drawSecond) drawSecond(g2d);
		if(drawThird) drawThird(g2d);
	}
	
	public void drawAll(Graphics2D g2d) {
		for(GraphNode g: g.getNodeList()) {
			if(g.getSelected()) {
				g.drawOn(g2d, SELECTED_NODE, true);
			} else{
				g.drawOn(g2d, NORMAL_COLOR, true);
			}
		}
	}
	
	public void drawFirst(Graphics2D g2d) {
		
	}
	
	public void drawSecond(Graphics2D g2d) {
		
	}

	public void drawThird(Graphics2D g2d) {
	
	}

	/**
	 * 
	 * @param g2d used to draw all nodes and edges
	 */
	public void drawOn(Graphics2D g2d) {
		for(GraphNode g: g.getNodeList()) {
			if(g.getSelected()) {
				g.drawOn(g2d, SELECTED_NODE, false);
			} else{
				g.drawOn(g2d, NORMAL_COLOR, false);
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
	 * erase all existing data in xml files
	 * @throws Exception ignore it
	 */
	public void clear() throws Exception {
		g.clear();
	}
	
	public void setStart(String name) {
		g.reset(start, des);
		start = name;
		g.getNode(start).setSelected();
		if(des != null)g.getNode(des).setSelected();
		repaint();
	}
	
	public void setDes(String name) {
		if(start == "") {
			System.out.println("No starting planet selected");
			return;
		}
		g.reset(start, des);
		des = name;
		g.getNode(des).setSelected();
		g.getNode(start).setSelected();
		repaint();
	}
	
	public ArrayList<LinkedList<GraphNode>> travelPlannerDistance(){
		return g.travelPlannerDistance(dis, start);
	}
	
	public ArrayList<LinkedList<GraphNode>> travelPlannerTime(){
		return g.travelPlannerTime(time, start);
	}
	
	public Collection<String> getNodeNames(){
		return g.getNodeNames();
	}
	
	public void setDis(int dis) {
		this.dis = dis;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public ArrayList<ArrayList<String>> timePlanner() {
		return helper(g.travelPlannerTime(time, start));
	}
	
	public ArrayList<ArrayList<String>> disPlanner() {
		return helper(g.travelPlannerDistance(dis, start));
	}
	
	private ArrayList<ArrayList<String>> helper(ArrayList<LinkedList<GraphNode>> temp){
		g.reset(start, null);
		ArrayList<ArrayList<String>> temp1 = new ArrayList<ArrayList<String>>();
		
		for(LinkedList<GraphNode> list: temp) {
			Iterator<GraphNode> ite = list.iterator();
			ArrayList<String> str = new ArrayList<String>();
			
			while(ite.hasNext()) {
				GraphNode n = ite.next();
				n.setSelected();
				str.add(n.name);
			}
			temp1.add(str);
		}
		
		repaint();
		return temp1;
	}
	
	public void displayAllPath() {
		if(displayAll) displayAll = false;
		else displayAll = true;
		repaint();
	}

	public String pathsToStrings(ArrayList<LinkedList<GraphNode>> paths) {
		String pathsString = "";
		for (int i = 0; i < paths.size(); i++) {
			pathsString = pathsString + paths.get(i).toString();
			pathsString = pathsString + "\n";
		}
		return pathsString;
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
	
	public Graph.Path pathBetweenTime() {
		g.reset(start, des);
		Graph.Path temp = g.pathBetweenTime(start, des);
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
	
	public void setDrawFirst() {
		if(drawFirst) drawFirst = false;
		else this.drawFirst = true;
		setDrawSecond();
		setDrawThird();
		repaint();
	}

	public void setDrawSecond() {
		if(drawSecond) drawSecond = false;
		else this.drawSecond = true;
		setDrawFirst();
		setDrawThird();
		repaint();
	}

	public void setDrawThird() {
		if(drawThird) drawThird = false;
		else this.drawThird = true;
		setDrawFirst();
		setDrawSecond();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(GraphNode n : g.nodes.values()) {
			if(n.isTouching(e.getX(), e.getY())) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					router.setStart(n.getName());
				} else {
					router.setDest(n.getName());
				}
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setPanels(RouterPanel r) {
		router = r;
		addMouseListener(this);
	}
}
