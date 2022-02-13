import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Hashtable;


public class GraphNode implements Serializable{
		private String name;
		private Hashtable<String,Edge> neighbors;
		private int x;
		private int y;
		
		public GraphNode() {
			name = "";
			this.neighbors = new Hashtable<>();
			x = 0;
			y = 0;
		}
		
		//Node Basic Constructor
		public GraphNode(String name, int x, int y) {
			this.name = name;
			this.neighbors = new Hashtable<>();
			this.x = x;
			this.y = y;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		public boolean addEdge(GraphNode o, int timeCost, int distanceCost){
			if(neighbors.contains(o.name)) return false;
			this.neighbors.put(o.name, new Edge(o, timeCost, distanceCost));
			return true;
		}
		
		/**
		 * update the edge's info
		 * @param name of the other end of the edge
		 * @param time cost of the edge
		 * @param dis cost of the edge
		 * @return
		 */
		public boolean updateEdge(String name, int time, int dis) {
			if(!neighbors.contains(name)) return false;
			neighbors.get(name).setTCost(time);
			neighbors.get(name).setDCost(dis);
			return true;
		}
		
		public void drawOn(Graphics2D g2d, Color color) {
			g2d.setColor(Color.BLUE);
			g2d.drawString(name, x + 5, + 5);
			g2d.setColor(color);
			g2d.fillOval(x - 3, y - 3, 6, 6);
			
			for(String key: neighbors.keySet()) {
				g2d.drawLine(x, y, neighbors.get(key).otherEnd.x, neighbors.get(key).otherEnd.y);
			}
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

		public Hashtable<String, Edge> getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(Hashtable<String, Edge> neighbors) {
			this.neighbors = neighbors;
		}

		public String toString() {
			String s = "";
			s += "    Planet: " + name;
			s += "\nCurrent location (" + x + ", " + y + ")\n\n"
					+ "       Edges\n\n";
			for(String key: neighbors.keySet()) {
				s += "From " + this.name + " to " + key + "\n";
				s += neighbors.get(key) + "\n";
			}
			return s;
		}
	}
	
	