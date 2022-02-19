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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AdvisorPanel extends JPanel {
	boolean selectedTime = false;
	boolean selectedDistance = true;
	private Map map;
	public String cost;
	private String input;
	
	//Basic Constructor
	public AdvisorPanel(Map map) {
		this.map = map;
		
		Collection<String> list = map.getNodeNames();
		Object[] nodes = list.toArray();
		
		this.setLayout(new GridLayout());
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_2);
		
		JComboBox comboBox = new JComboBox(nodes);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(5, 0, 5, 5);
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
		
		JFormattedTextField textField = new JFormattedTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 25);
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 3;
		this.add(textField, gbc_textField);
		textField.setColumns(5);
		
		input = textField.getText();
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Distance");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNewRadioButton.gridx = 4;
		gbc_rdbtnNewRadioButton.gridy = 4;
		this.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Time");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNewRadioButton_1.gridx = 8;
		gbc_rdbtnNewRadioButton_1.gridy = 4;
		this.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		
		JButton btnGOButton = new JButton("      GO      ");
		GridBagConstraints gbc_btnGOButton = new GridBagConstraints();
		gbc_btnGOButton.insets = new Insets(0, 0, 0, 35);
		gbc_btnGOButton.anchor = GridBagConstraints.EAST;
		gbc_btnGOButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGOButton.gridx = 5;
		gbc_btnGOButton.gridy = 5;
		this.add(btnGOButton, gbc_btnGOButton);
		
		btnGOButton.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
	}
}