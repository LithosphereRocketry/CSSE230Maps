import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;

import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

public class MainApp {
	public static final int DELAY = 50;
	static JFrame frame = new JFrame("Hyerspace Route");
	private Image bgImage;
	
	MainApp() {
		
	}
	
	/**
	 * Launch the application.
	 * @throws Exception 
	 */

	private void runApp() throws Exception {

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
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		GraphicsComponent component = new GraphicsComponent();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 750, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		
		Map panel = new Map();
//		panel.clear();
//		panel.addNode("Null", 818, 104);
//		panel.addNode("Coruscant", 510, 205);
//		panel.addNode("Mobus", 255, 14);
//		panel.addNode("Selvaris", 403, 113);
//		panel.addNode("Reecee", 457, 120);
//		panel.addNode("Dorin", 542, 76);
//		panel.addNode("Glee Anselm", 547, 27);
//		panel.addNode("Bilbringi", 531, 99);
//		panel.addNode("Aphran", 541, 115);
//		panel.addNode("Corratos", 536, 133);
//		panel.addNode("Vortex", 585, 76);
//		panel.addNode("Tatooine", 534, 918);
//		panel.addNode("Dagobah", 98, 941);
//		panel.addNode("Naboo", 336, 813);
//		panel.addNode("Geonosis", 539, 945);
//		panel.addNode("Kessel", 1091, 461);
//		panel.addNode("Alderaan", 594, 245);
//		panel.addNode("Yavin", 1073, 96);
//		panel.addNode("Kamino", 737,  836);
//		panel.addNode("Gamorr", 839, 810);
//		panel.addNode("Corelia", 512, 381);
//		panel.addNode("Tarhassan", 731, 422);
//		panel.addNode("Ruusan", 833, 476);
//		panel.addNode("Tholatin", 791, 428);
//		panel.addNode("Roche Asteroids", 909, 281);
//		panel.addNode("Ord Mantell", 698, 57);
//		panel.addNode("Thyferra", 239, 527);
//		panel.addNode("Umbara", 747, 378);
//		panel.addNode("Honoghr", 1101, 533);
//		panel.addNode("Byss", 383, 278);
//		
//		
//		panel.addEdge("Tatooine", "Geonosis");
//		panel.addEdge("Reecee", "Aphran");
//		panel.addEdge("Reecee", "Selvaris");
//		panel.addEdge("Reecee", "Coruscant");
//		panel.addEdge("Bilbringi", "Aphran");
//		panel.addEdge("Corratos", "Aphran");
//		panel.addEdge("Reecee", "Bilbringi");
//		panel.addEdge("Reecee", "Corratos");
//		panel.addEdge("Selvaris", "Mobus");
//		panel.addEdge("Bilbringi", "Dorin");
//		panel.addEdge("Dorin", "Vortex");
//		panel.addEdge("Dorin", "Glee Anselm");
//		panel.addEdge("Glee Anselm", "Vortex");
//		panel.addEdge("Glee Anselm", "Mobus");
//		panel.addEdge("Coruscant", "Corratos");
//		panel.addEdge("Coruscant", "Alderaan");
//		panel.addEdge("Alderaan", "Corelia");
//		panel.addEdge("Coruscant", "Corelia");
//		panel.addEdge("Coruscant", "Byss");
//		panel.addEdge("Corelia", "Byss");
//		panel.addEdge("Byss", "Thyferra");
//		panel.addEdge("Corelia", "Thyferra");
//		panel.addEdge("Corelia", "Naboo");
//		panel.addEdge("Thyferra", "Naboo");
//		panel.addEdge("Selvaris", "Byss");
//		panel.addEdge("Dagobah", "Naboo");
//		panel.addEdge("Dagobah", "Thyferra");
//		panel.addEdge("Naboo", "Tatooine");
//		panel.addEdge("Geonosis", "Dagobah");
//		panel.addEdge("Naboo", "Dagobah");
//		panel.addEdge("Naboo", "Geonosis");
//		panel.addEdge("Tatooine", "Kamino");
//		panel.addEdge("Kamino", "Gamorr");
//		panel.addEdge("Ruusan", "Gamorr");
//		panel.addEdge("Kamino", "Ruusan");
//		panel.addEdge("Tarhassan", "Umbara");
//		panel.addEdge("Ruusan", "Tarhassan");
//		panel.addEdge("Ruusan", "Tholatin");
//		panel.addEdge("Tholatin", "Umbara");
//		panel.addEdge("Tarhassan", "Corelia");
//		panel.addEdge("Umbara", "Alderaan");
//		panel.addEdge("Ruusan", "Roche Asteroids");
//		panel.addEdge("Umbara", "Roche Asteroids");
//		panel.addEdge("Ruusan", "Kessel");
//		panel.addEdge("Kessel", "Honoghr");
//		panel.addEdge("Honoghr", "Gamorr");
//		panel.addEdge("Roche Asteroids", "Yavin");
//		panel.addEdge("Kessel", "Yavin");
//		panel.addEdge("Yavin", "Null");
//		panel.addEdge("Null", "Alderaan");
//		panel.addEdge("Null", "Ord Mantell");
//		panel.addEdge("Ord Mantell", "Vortex");
//		panel.addEdge("Ord Mantell", "Glee Anselm");
		
		
		
//		Graph graph = panel.g;
		
		JScrollPane scrollPane = new JScrollPane(panel);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		scrollPane.add(component);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 5;
		gbc_tabbedPane.gridx = 10;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		RouterPanel tabPanel1 = new RouterPanel(panel);
//		String start = tabPanel1.getSelectedStart();
//		panel.setStart(start);
//		String dest = tabPanel1.getSelectedDest();
//		panel.setDes(dest);
		tabbedPane.addTab("Recommended Route", null, tabPanel1, null);
		
		AdvisorPanel tabPanel2 = new AdvisorPanel();
		tabbedPane.addTab("Trip Advisor", null, tabPanel2, null);

	}
}