import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GraphicsComponent extends JComponent {
	private Graphics2D g2d;
	
	//Basic Constructor
	public GraphicsComponent() {
		
	}
	
	public void draw(Graphics2D g2d){
		this.g2d = g2d;
		g2d.create();
		
	}
	
	public void update() {
		
	}
}
