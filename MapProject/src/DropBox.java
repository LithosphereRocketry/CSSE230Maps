import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DropBox extends JComboBox<String> {
	String[] strArr = new String[] {"item1", "item2"};
	
	//Basic Constructor
	public void DropBox(String[] strArr) {
		new JComboBox<>(strArr);
	}
}