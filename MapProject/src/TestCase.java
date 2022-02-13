
public class TestCase {

	public static void main(String[] args) {
		try {
			Graph g = new Graph();

			g.addNode("1");
			g.addNode("2");
			g.addNode("3");
			g.addNode("4");
			g.addEdge("1", "2", 10, 10);
			g.addEdge("2", "3", 10, 10);
			g.addEdge("3", "4", 10, 10);
			g.addNode("0");
			g.addEdge("0", "1", 10, 10);
			g.addEdge("0", "4", 20, 20);
			System.out.println(g.pathBetweenDist("1", "4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
