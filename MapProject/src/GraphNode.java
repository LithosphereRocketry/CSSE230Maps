import java.io.Serializable;
import java.util.ArrayList;

import Graph.Edge;

public class GraphNode implements Serializable{
		private String name;
		private ArrayList<Edge> neighbors;
		private int hValue;
		
		public GraphNode() {
			name = "";
			this.neighbors = new ArrayList<>();
			hValue = 0;
		}
		
		//Node Basic Constructor
		public GraphNode(String name) {
			this.name = name;
			this.neighbors = new ArrayList<>();
			hValue = 0;
		}
		
		/**
		 * insert a new edge between current node and another node
		 * @param name of another planet to be connected
		 * @param timeCost of the edge
		 * @param distanceCost of the edge
		 */
		private void addEdge(String name, int timeCost, int distanceCost){
			GraphNode otherNode = nodes.get(name);
			Edge temp = new Edge(otherNode, timeCost, distanceCost);
			this.neighbors.add(temp);
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int gethValue() {
			return hValue;
		}

		public void sethValue(int hValue) {
			this.hValue = hValue;
		}

		public void setNeighbors(ArrayList<Edge> neighbors) {
			this.neighbors = neighbors;
		}

		
		public ArrayList<GraphNode> getNeighbors(){
			ArrayList<GraphNode> temp = new ArrayList<>();
			for(Edge i: neighbors) temp.add(i.otherEnd);
			return temp;
		}
		
		public ArrayList<Edge> getEdges(){
			return neighbors;
		}
		
		public int geth() {
			return hValue;
		}
		
		public void seth(int h) {
			hValue = h;
		}
		
		public String toString() {
			String s = "";
			s += "    Planet: " + name + "\n" + "       Edges\n\n";
			for(Edge e: neighbors) {
				s += e;
				s += "From " + this.name + " to " + e.otherEnd.name + "\n\n";
			}
			return s;
		}
	}