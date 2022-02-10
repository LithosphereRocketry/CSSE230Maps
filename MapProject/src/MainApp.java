import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;

import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.JScrollPane;

public class MainApp {
	static JFrame frame = new JFrame("Hyerspace Route");
	
	MainApp() {
		
	}
	
	/**
	 * Launch the application.
	 */

	private void runApp() {

		// Frame
		frame.setSize(500, 500);

		// Component
		initialize();

		// Produces Frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.requestFocus();

	} // runApp

	/**
	 * The main creates new instance of MainApp
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MainApp mainApp = new MainApp();
		mainApp.runApp();
	} // main

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 750, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		ImagePanel panel = new ImagePanel(
				new ImageIcon("\\Users\\bhasinn\\OneDrive - Rose-Hulman Institute of Technology\\Documents\\CSSE\\CSSE230W21\\Maps\\src\\Mapimage.png").getImage());
		JScrollPane scrollPane = new JScrollPane(panel);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane, gbc_scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 5;
		gbc_tabbedPane.gridx = 10;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel tabPanel1 = new JPanel();
		tabPanel1.setLayout(new GridLayout());
		tabbedPane.add("Recommended Route", tabPanel1);
		JPanel tabPanel2 = new JPanel();
		tabPanel2.setLayout(new GridLayout());
		tabbedPane.add("Trip Advisor", tabPanel2);

	}
	
	class ImagePanel extends JPanel {

		private Image img;
		

		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null) / 100, img.getHeight(null) / 100);
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, -200, -200, null);
		}
		
	}
}
