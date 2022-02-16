
import javax.swing.JFrame;


public class TestCase{
	
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		
		Map m = new Map();
		m.clear();
		m.addNode("Earth", 200, 100);
		m.addNode("Mars", 300, 100);
//		frame.add(m);
//		m.repaint();
		
		System.out.print(m);
//			Map g = new Map();

//			g.addNode("1");
//			g.addNode("2");
//			g.addNode("3");
//			g.addNode("4");
//			g.addEdge("1", "2", 10, 10);
//			g.addEdge("2", "3", 10, 10);
//			g.addEdge("3", "4", 10, 10);
//			g.addNode("0");
//			g.addEdge("0", "1", 10, 10);
//			g.addEdge("0", "4", 20, 20);
//			System.out.println(g.pathBetweenDist("1", "4"));
		
		
		frame.setSize(500, 500);


		// Produces Frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.requestFocus();
	}

}
