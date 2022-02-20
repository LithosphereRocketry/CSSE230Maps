
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class GraphicsComponent extends JComponent {
	private Graphics2D g2d;
	public ArrayList<GraphNode> graphNodes = new ArrayList<GraphNode>();
	
	//Basic Constructor
	public GraphicsComponent() {
		
	}
	
	public void draw(Graphics2D g2d){
		this.g2d = g2d;
		g2d.create();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Map map;
		try {
			map = new Map();
			map.drawOn(g2d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
