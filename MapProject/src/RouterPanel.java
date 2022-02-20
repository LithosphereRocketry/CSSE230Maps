import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RouterPanel extends JPanel {

	private Map map;
	private String cost;
	private boolean selectedTime = false;
	private boolean selectedDistance = true;
	private String selectedStart;
	private String selectedDest;
	
	private JComboBox<String> comboBoxStart, comboBoxEnd;
	
	//Basic Constructor
	public RouterPanel(Map map) {
		this.map = map;
		this.setLayout(new GridLayout());
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 3.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,  0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_1);
		
		JLabel lbStartLabel = new JLabel("Start:");
		GridBagConstraints gbc_lbStartLabel = new GridBagConstraints();
		gbc_lbStartLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lbStartLabel.anchor = GridBagConstraints.EAST;
		gbc_lbStartLabel.gridx = 4;
		gbc_lbStartLabel.gridy = 1;
		lbStartLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lbStartLabel, gbc_lbStartLabel);
		
		Collection<String> list = map.getNodeNames();
		String[] nodes = list.toArray(new String[list.size()]);
		
		comboBoxStart = new JComboBox<>(nodes);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(5, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		comboBoxStart.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		this.add(comboBoxStart, gbc_comboBox);
		
		comboBoxStart.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selectedStart = (String) comboBoxStart.getSelectedItem();
		    	setStart(selectedStart);
		    	map.reset();
		    }
		});
		comboBoxStart.setSelectedItem(nodes[23]);//set as default selected item 
		
		JLabel lbDestLabel = new JLabel("Destination:");
		GridBagConstraints gbc_lbDestLabel = new GridBagConstraints();
		gbc_lbDestLabel.insets = new Insets(0, 0, 10, 5);
		gbc_lbDestLabel.anchor = GridBagConstraints.EAST;
		gbc_lbDestLabel.gridx = 4;
		gbc_lbDestLabel.gridy = 3;
		lbDestLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lbDestLabel, gbc_lbDestLabel);
		
		comboBoxEnd = new JComboBox<>(nodes);
		GridBagConstraints gbc_comboBoxEnd = new GridBagConstraints();
		gbc_comboBoxEnd.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEnd.gridx = 5;
		gbc_comboBoxEnd.gridy = 3;
		comboBoxEnd.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		this.add(comboBoxEnd, gbc_comboBoxEnd);
		
		comboBoxEnd.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selectedDest = (String) comboBoxEnd.getSelectedItem();
		    	setDest(selectedDest);
		    	map.reset();
		    }
		});
		comboBoxEnd.setSelectedItem(nodes[1]);//set as default selected item
		
		JLabel lblCostLabel = new JLabel();
		GridBagConstraints gbc_lblCostLabel = new GridBagConstraints();
		gbc_lblCostLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblCostLabel.insets = new Insets(10, 0, 5, 5);
		gbc_lblCostLabel.gridx = 4;
		gbc_lblCostLabel.gridy = 5;
		lblCostLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lblCostLabel, gbc_lblCostLabel);
		
		JLabel lblCostLabel_2 = new JLabel(cost);
		GridBagConstraints gbc_lblCostLabel_2 = new GridBagConstraints();
		gbc_lblCostLabel_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCostLabel_2.insets = new Insets(10, 0, 5, 5);
		gbc_lblCostLabel_2.gridx = 5;
		gbc_lblCostLabel_2.gridy = 5;
		lblCostLabel_2.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lblCostLabel_2, gbc_lblCostLabel_2);
		
		JRadioButton rdbtnDistanceRadioButton = new JRadioButton("Distance");
		GridBagConstraints gbc_rdbtnDistanceRadioButton = new GridBagConstraints();
		gbc_rdbtnDistanceRadioButton.insets = new Insets(0, 0, 0, 10);
		gbc_rdbtnDistanceRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDistanceRadioButton.gridx = 5;
		gbc_rdbtnDistanceRadioButton.gridy = 4;
		rdbtnDistanceRadioButton.doClick();
		rdbtnDistanceRadioButton.setFont(new Font("Apple Chancery", Font.PLAIN, 15));
		this.add(rdbtnDistanceRadioButton, gbc_rdbtnDistanceRadioButton);
		
		
		JRadioButton rdbtnTimeRadioButton_1 = new JRadioButton("Time");
		GridBagConstraints gbc_rdbtnTimeRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnTimeRadioButton_1.insets = new Insets(0, 0, 0, 15);
		gbc_rdbtnTimeRadioButton_1.anchor = GridBagConstraints.EAST;
		gbc_rdbtnTimeRadioButton_1.gridx = 5;
		gbc_rdbtnTimeRadioButton_1.gridy = 4;
		rdbtnTimeRadioButton_1.setFont(new Font("Apple Chancery", Font.PLAIN, 15));
		this.add(rdbtnTimeRadioButton_1, gbc_rdbtnTimeRadioButton_1);
		
		rdbtnDistanceRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedDistance = rdbtnDistanceRadioButton.isSelected();
				if(rdbtnDistanceRadioButton.isSelected()) {
					rdbtnTimeRadioButton_1.setSelected(false);
					selectedTime = false;
					map.reset();
				}
			}
		});
		
		rdbtnTimeRadioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedTime = rdbtnTimeRadioButton_1.isSelected();
				if(rdbtnTimeRadioButton_1.isSelected()) {
					rdbtnDistanceRadioButton.setSelected(false);
					selectedDistance = false;
					map.reset();
				}
			}
		});
		
		JButton btnGOButton = new JButton("GO");
		GridBagConstraints gbc_btnGOButton = new GridBagConstraints();
		gbc_btnGOButton.insets = new Insets(0, 0, 0, 15);
		gbc_btnGOButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGOButton.anchor = GridBagConstraints.CENTER;
		gbc_btnGOButton.gridx = 5;
		gbc_btnGOButton.gridy = 6;
		btnGOButton.setFont(new Font("Apple Chancery", Font.BOLD, 20));
		btnGOButton.setForeground(Color.RED);
		this.add(btnGOButton, gbc_btnGOButton);
		
		btnGOButton.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(selectedDistance && !selectedTime) {
		    		cost = map.pathBetweenDist().getCost();
		    		lblCostLabel.setText("Distance:");
		    		lblCostLabel_2.setText(cost + " Parsecs");
				} else {
					cost = map.pathBetweenTime().getCost();
					lblCostLabel.setText("Time:");
					lblCostLabel_2.setText(cost + " Standard Time Units");
				}
		    }
		});
		
		ImageIcon routeIcon = new ImageIcon("src/4474360.png");
		Image routeimage = routeIcon.getImage();
		routeimage = routeimage.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
		routeIcon = new ImageIcon(routeimage);
		JButton drawAllButton = new JButton(routeIcon);
		
		GridBagConstraints gbc_drawAllButton = new GridBagConstraints();
		gbc_drawAllButton.insets = new Insets(0, 0, 0, 5);
		gbc_drawAllButton.gridx = 6;
		gbc_drawAllButton.gridy = 5;
		this.add(drawAllButton, gbc_drawAllButton);
		
		drawAllButton.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	map.displayAllPath();
		    }
		});
		
		JLabel drawAllLabel = new JLabel("Display/Hide");
		GridBagConstraints gbc_drawAllLabel = new GridBagConstraints();
		gbc_drawAllLabel.insets = new Insets(0, 0, 5, 5);
		gbc_drawAllLabel.anchor = GridBagConstraints.EAST;
		gbc_drawAllLabel.gridx = 5;
		gbc_drawAllLabel.gridy = 5;
		drawAllLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		this.add(drawAllLabel, gbc_drawAllLabel);
	}
	public void setStart(Object name) {
		comboBoxStart.setSelectedItem(name);
    	map.setStart((String) name);
	}
	public void setDest(Object name) {
    	comboBoxEnd.setSelectedItem(name);
    	map.setDes((String) name);
	}
	
	public String getSelectedStart() {
		return selectedStart;
	}
	
	public String getSelectedDest() {
		return selectedDest;
	}
}