import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class RouterPanel extends JPanel {
//	public String selectedStart;
//	public String selectedDest;
	Map map;
	//Basic Constructor
	public RouterPanel(Map map) {
		this.map = map;
		this.setLayout(new GridLayout());
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 3.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_1);
		
		JLabel lbStartLabel = new JLabel("Start:");
		GridBagConstraints gbc_lbStartLabel = new GridBagConstraints();
		gbc_lbStartLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lbStartLabel.anchor = GridBagConstraints.EAST;
		gbc_lbStartLabel.gridx = 4;
		gbc_lbStartLabel.gridy = 1;
		this.add(lbStartLabel, gbc_lbStartLabel);

		Collection<String> list = map.getNodeNames();
		Object[] nodes = list.toArray();
		
		JComboBox comboBox = new JComboBox(nodes);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		this.add(comboBox, gbc_comboBox);
		
		comboBox.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	comboBox.setSelectedItem(comboBox.getSelectedItem());
		    	map.setStart((String) comboBox.getSelectedItem());
		    }
		});
		comboBox.setSelectedItem(nodes[0]);//set as default selected item
		
		JLabel lbDestLabel = new JLabel("Destination:");
		GridBagConstraints gbc_lbDestLabel = new GridBagConstraints();
		gbc_lbDestLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lbDestLabel.anchor = GridBagConstraints.EAST;
		gbc_lbDestLabel.gridx = 4;
		gbc_lbDestLabel.gridy = 3;
		this.add(lbDestLabel, gbc_lbDestLabel);
		
		JComboBox comboBox_1 = new JComboBox(nodes);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 5;
		gbc_comboBox_1.gridy = 3;
		this.add(comboBox_1, gbc_comboBox_1);
		
		comboBox_1.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	comboBox_1.setSelectedItem(comboBox_1.getSelectedItem());
		    	map.setDes((String) comboBox_1.getSelectedItem());
		    }
		});
		comboBox_1.setSelectedItem(nodes[1]);//set as default selected item
		
		JButton btnGOButton = new JButton("GO");
		GridBagConstraints gbc_btnGOButton = new GridBagConstraints();
		gbc_btnGOButton.insets = new Insets(0, 0, 0, 60);
		gbc_btnGOButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGOButton.gridx = 5;
		gbc_btnGOButton.gridy = 4;
		this.add(btnGOButton, gbc_btnGOButton);
		
		btnGOButton.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println(map.pathBetweenDist());
		    }
		});
	}
//	
//	public String getSelectedStart() {
//		return selectedStart;
//	}
//	
//	public String getSelectedDest() {
//		return selectedDest;
//	}
}