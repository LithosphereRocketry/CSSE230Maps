import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


public class GraphNode implements Serializable, Comparable<GraphNode>{
		public String name;
		private Hashtable<String,Edge> neighbors;
		private GraphNode lastNode;
		private int x;
		private int y;
		private double hValue;
		private boolean selected;
		
		public GraphNode() {
			neighbors = new Hashtable<>();
			hValue = 0;
			selected = false;
		}
		
		//Node Basic Constructor
		public GraphNode(String name, int x, int y) {
			this.lastNode = null;
			this.name = name;
			this.neighbors = new Hashtable<>();
			this.x = x;
			this.y = y;
			this.hValue = 0;
			selected = false;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		public boolean addEdge(GraphNode o){
			if(neighbors.contains(o.name)) {
				System.out.println(name + " and " + o.name + " are already linked");
				return false;
			}
						
			this.neighbors.put(o.name, new Edge(o, heuristicDist(o),heuristicTime(o)));

			return true;
		}
		
		public void setSelected() {
			selected = true;
		}
		
		public boolean getSelected() {
			return selected;
		}
		
		public void reset() {
			selected = false;
		}
		
		/**
		 * update the edge's info
		 * @param name of the other end of the edge
		 * @param time cost of the edge
		 * @param dis cost of the edge
		 * @return
		 */
		public boolean updateEdge(String name, double time, double dis) {
			if(!neighbors.contains(name)) return false;
			neighbors.get(name).setTCost(time);
			neighbors.get(name).setDCost(dis);
			return true;
		}
		
		public void drawOn(Graphics2D g2d, Color color) {
//			g2d.setColor(Color.RED);
//			g2d.drawString(name, x + 5, y + 5);
			g2d.setColor(Color.YELLOW);
			
			for(String key: neighbors.keySet()) {
				neighbors.get(key).drawOn(g2d, x, y, selected);
			}
			if(color.equals(Color.RED)) {
				g2d.setColor(color);
				g2d.fillOval(x - 5, y - 5, 10, 10);
			}
				
			else g2d.fillOval(x - 3, y - 3, 6, 6);
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public double gethValue() {
			return hValue;
		}

		public void sethValue(double hValue) {
			this.hValue = hValue;
		}

		public Hashtable<String, Edge> getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(Hashtable<String, Edge> neighbors) {
			this.neighbors = neighbors;
		}
		
		//for test/debug use
		public String testString() {
			String s = "";
			s += "    Planet: " + name +" H: " + hValue;
			s += "\nCurrent location (" + x + ", " + y + ")\n\n"
					+ "       Edges\n\n";
			for(String key: neighbors.keySet()) {
				s += "From " + this.name + " to " + key + "\n";
				s += neighbors.get(key) + "\n";
			}
			return s;
		}
		
		//for xml use, don't use for testing
		public String toString() {
			String s = "";
//			s += "Planet: " + name;
			s += name;
//			s += "\nCurrent location (" + x + ", " + y + ")\n\n"
//					+ "       Edges\n\n";
//			for(String key: neighbors.keySet()) {
//				s += "From " + this.name + " to " + key + "\n";
//				s += neighbors.get(key) + "\n";
//			}
			return s;
		}
		
		public int compareTo(GraphNode o) {
			return ((Double) hValue).compareTo((Double) o.hValue);
		}


		public GraphNode getLastNode() {
			return lastNode;
		}

		public void setLastNode(GraphNode lastNode) {
			this.lastNode = lastNode;
		}
	

	public double heuristicDist(GraphNode dest) {
		double straightLineDistance = Math.sqrt(Math.pow(this.x-dest.x, 2) + Math.pow(this.y - dest.y, 2));
		return straightLineDistance;
	}
	public double heuristicTime(GraphNode dest) {
		return Math.sqrt(this.heuristicDist(dest));
	}
	
	/**
	 * Gets Node paths for travel advisory, gets paths that have a total distance less then the given constraint
	 * 
	 * @param distanceConstraint
	 * @param startingNode
	 * @param list
	 * @param totalCost
	 * @param firstPathEdited
	 * @param paths
	 */
	public void getNodesWithinDistance(int distanceConstraint, LinkedList<GraphNode> list, double totalCost, boolean firstPathEdited, ArrayList<LinkedList<GraphNode>> paths) {
		LinkedList<GraphNode> listCopy = (LinkedList<GraphNode>) list.clone(); //this is for checking a condition in the else condition below (need 
		//since we will be modifying list during this method) and is also needed for creating each new linkedList for each path
		for(String key: this.neighbors.keySet()) {
			Edge neighborEdge = this.getNeighbors().get(key);
			if(!firstPathEdited) {
				if(!list.contains(neighborEdge.otherEnd)) {
					if(neighborEdge.getDCost() + totalCost <= distanceConstraint) {
						list.add(neighborEdge.getOtherEnd());
						firstPathEdited = true;
						neighborEdge.getOtherEnd().getNodesWithinDistance(distanceConstraint, list, totalCost + neighborEdge.getDCost(), false, paths);
					}
				}
			} else { //create a new LinkedList for a new path
				LinkedList<GraphNode> anotherList = (LinkedList<GraphNode>) listCopy.clone();
				if(!listCopy.contains(neighborEdge.otherEnd)) {
					if(neighborEdge.getDCost() + totalCost <= distanceConstraint) {
						anotherList.add(neighborEdge.getOtherEnd());
						totalCost += neighborEdge.getDCost();
						paths.add(anotherList);
						neighborEdge.getOtherEnd().getNodesWithinDistance(distanceConstraint, anotherList, totalCost + neighborEdge.getDCost(), false, paths);
					}
				}
			}
		}
	}
	
	public void getNodesWithinTime (int timeConstraint, LinkedList<GraphNode> list, double totalCost, boolean firstPathEdited, ArrayList<LinkedList<GraphNode>> paths) {
		LinkedList<GraphNode> listCopy = (LinkedList<GraphNode>) list.clone(); //this is for checking a condition in the else condition below (need 
		//since we will be modifying list during this method) and is also needed for creating a new linkedList for each path
		for(String key: this.neighbors.keySet()) {
			Edge neighborEdge = this.getNeighbors().get(key);
			if(!firstPathEdited) {
				if(!list.contains(neighborEdge.otherEnd)) {
					if(neighborEdge.getTCost() + totalCost <= timeConstraint) {
						list.add(neighborEdge.getOtherEnd());
						firstPathEdited = true;
						neighborEdge.getOtherEnd().getNodesWithinDistance(timeConstraint, list, totalCost + neighborEdge.getTCost(), false, paths);
					}
				}
			} else { //create a new LinkedList for a new path
				LinkedList<GraphNode> anotherList = (LinkedList<GraphNode>) listCopy.clone();
				if(!listCopy.contains(neighborEdge.otherEnd)) {
					if(neighborEdge.getTCost() + totalCost <= timeConstraint) {
						anotherList.add(neighborEdge.getOtherEnd());
						totalCost += neighborEdge.getTCost();
						paths.add(anotherList);
						neighborEdge.getOtherEnd().getNodesWithinDistance(timeConstraint,anotherList, totalCost + neighborEdge.getTCost(), false, paths);
					}
				}
			}
		}
	}
	
	
	boolean isTouching(int x, int y) {
		return Math.hypot(x-this.x, y-this.y) < 5;
	}
	
	

}
	
	