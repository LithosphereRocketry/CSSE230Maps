import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GraphicsComponent extends JComponent {
	public Image bgImage;
	private Graphics2D g2d;
	
	//Basic Constructor
	public GraphicsComponent() {
		
	}
	
	public void draw(Graphics2D g2d){
		this.g2d = g2d;
		g2d.create();
		
		try {
			this.bgImage = ImageIO.read(new File("Images/Mapimage.png"));
			g2d.drawImage(bgImage, -200, -200, this.getWidth(), this.getHeight() - 50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
	}
}
