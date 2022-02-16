
import javax.swing.JFrame;


public class TestCase{
	
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		
//		Map m = new Map();
//		m.clear();
//		m.addNode("Earth", 200, 100);
//		m.addNode("Coruscant", 540, 205);
//		m.addEdge("Earth", "Corunscant");
//		frame.add(m);
//		m.repaint();
		
//		System.out.print(m);
			Map g = new Map();
			g.clear();
			g.addNode("1", 20, 30);
			g.addNode("2", 40, 50);
			g.addNode("3", 60, 70);
			g.addNode("4", 80, 90);
			g.addEdge("1", "2");
//			g.addEdge("2", "3");
			g.addEdge("2", "4");
//			g.addNode("0");
//			g.addEdge("0", "1");
//			g.addEdge("0", "4");
			System.out.println(g.getG().pathBetweenDist("1", "4"));
		
		
		frame.setSize(500, 500);


		// Produces Frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.requestFocus();
	}

}
