import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class AdvisorPanel extends JPanel {
	private boolean selectedTime = false;
	private boolean selectedDistance = true;
	private String selectedStart;
	
	//Basic Constructor
	public AdvisorPanel(Map map) {
		Collection<String> list = map.getNodeNames();
		Object[] nodes = list.toArray();
		
		this.setLayout(new GridLayout());
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE};
		this.setLayout(gbl_panel_2);
		
		JLabel lbStartLabel = new JLabel("Start:");
		GridBagConstraints gbc_lbStartLabel = new GridBagConstraints();
		gbc_lbStartLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lbStartLabel.anchor = GridBagConstraints.EAST;
		gbc_lbStartLabel.gridx = 4;
		gbc_lbStartLabel.gridy = 1;
		lbStartLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lbStartLabel, gbc_lbStartLabel);
		
		JComboBox<Object> comboBox = new JComboBox<>(nodes);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(5, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		comboBox.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		this.add(comboBox, gbc_comboBox);
		selectedStart = (String) comboBox.getSelectedItem();
		
		comboBox.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	map.reset();
		    	selectedStart = (String) comboBox.getSelectedItem();
		    	comboBox.setSelectedItem(selectedStart);
		    	map.setAdStart((String) selectedStart);
		    }
		});
		
		JLabel lbDestLabel = new JLabel("Constraint:");
		GridBagConstraints gbc_lbDestLabel = new GridBagConstraints();
		gbc_lbDestLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lbDestLabel.anchor = GridBagConstraints.EAST;
		gbc_lbDestLabel.gridx = 4;
		gbc_lbDestLabel.gridy = 3;
		lbDestLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lbDestLabel, gbc_lbDestLabel);
		
		JFormattedTextField textField = new JFormattedTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 0, 5, 20);
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 3;
		textField.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(textField, gbc_textField);
		textField.setColumns(5);
		
		JLabel lbINVALIDLabel = new JLabel();
		GridBagConstraints gbc_lbINVALIDLabel = new GridBagConstraints();
		gbc_lbINVALIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lbINVALIDLabel.anchor = GridBagConstraints.CENTER;
		gbc_lbINVALIDLabel.gridx = 5;
		gbc_lbINVALIDLabel.gridy = 6;
		lbINVALIDLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(lbINVALIDLabel, gbc_lbINVALIDLabel);
		
		textField.addFocusListener(new FocusListener() {
			@Override
		    public void focusGained(FocusEvent e) {
		        textField.setText(null); // Empty the text field when it receives focus
		        lbINVALIDLabel.setText(null);
		    }
			

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		 
		JRadioButton rdbtnDistanceRadioButton = new JRadioButton("Distance");
		GridBagConstraints gbc_rdbtnDistanceRadioButton = new GridBagConstraints();
		gbc_rdbtnDistanceRadioButton.insets = new Insets(0, 0, 0, 10);
		gbc_rdbtnDistanceRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDistanceRadioButton.gridx = 4;
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
		
		ImageIcon routeIcon = new ImageIcon("src/router.png");
		Image routeimage = routeIcon.getImage();
		routeimage = routeimage.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
		routeIcon = new ImageIcon(routeimage);
		JButton btnGOButton = new JButton();
		btnGOButton.setIcon(routeIcon);
		
		GridBagConstraints gbc_btnGOButton = new GridBagConstraints();
		gbc_btnGOButton.insets = new Insets(0, 0, 0, 10);
		gbc_btnGOButton.anchor = GridBagConstraints.CENTER;
		gbc_btnGOButton.gridx = 6;
		gbc_btnGOButton.gridy = 3;
		btnGOButton.setFont(new Font("Apple Chancery", Font.BOLD, 20));
		btnGOButton.setForeground(Color.RED);
		this.add(btnGOButton, gbc_btnGOButton);
		
		btnGOButton.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String input = textField.getText();
		    	int temp = 0;
		    	if(input != "") {
		    		try {
		    			temp = Integer.parseInt(input);
		    			if(selectedDistance && !selectedTime) {
		    				map.setDis(temp);
		    				map.travelPlannerDistance();
		    			} else {
		    				map.setTime(temp);
		    				map.travelPlannerTime();
		    			}
		    			map.drawThreePaths();
		    		}
		    		catch (NumberFormatException e1) {
						lbINVALIDLabel.setText("Invalid Input");
		    		}

		    	}
		    }
		});
		
		JLabel goDestLabel = new JLabel("Go!");
		GridBagConstraints gbc_goDestLabel = new GridBagConstraints();
		gbc_goDestLabel.insets = new Insets(0, 0, 5, 5);
		gbc_goDestLabel.anchor = GridBagConstraints.EAST;
		gbc_goDestLabel.gridx = 7;
		gbc_goDestLabel.gridy = 3;
		goDestLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		this.add(goDestLabel, gbc_goDestLabel);
		
		JButton btnPath1Button = new JButton("Path1");
		GridBagConstraints gbc_btnPath1Button = new GridBagConstraints();
		btnPath1Button.setBackground(Color.GREEN);
		gbc_btnPath1Button.insets = new Insets(80, 0, 0, 0);
		gbc_btnPath1Button.anchor = GridBagConstraints.CENTER;
		gbc_btnPath1Button.gridx = 5;
		gbc_btnPath1Button.gridy = 7;
		btnPath1Button.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		btnPath1Button.setForeground(Color.GREEN);
		this.add(btnPath1Button, gbc_btnPath1Button);
		btnPath1Button.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(map.getPath1().size() > 0) map.setDrawFirst();
		    }
		});
		
		JButton btnPath2Button = new JButton("Path2");
		GridBagConstraints gbc_btnPath2Button = new GridBagConstraints();
		btnPath2Button.setBackground(Color.MAGENTA);
		gbc_btnPath2Button.insets = new Insets(11, 0, 0, 0);
		gbc_btnPath2Button.anchor = GridBagConstraints.CENTER;
		gbc_btnPath2Button.gridx = 5;
		gbc_btnPath2Button.gridy = 8;
		btnPath2Button.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		btnPath2Button.setForeground(Color.MAGENTA);
		this.add(btnPath2Button, gbc_btnPath2Button);
		btnPath2Button.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(map.getPath2().size() > 0)map.setDrawSecond();
		    }
		});
		

		JButton btnPath3Button = new JButton("Path3");
		GridBagConstraints gbc_btnPath3Button = new GridBagConstraints();
		btnPath3Button.setBackground(Color.CYAN);
		gbc_btnPath3Button.insets = new Insets(0, 0, 270, 0);
		gbc_btnPath3Button.anchor = GridBagConstraints.CENTER;
		gbc_btnPath3Button.gridx = 5;
		gbc_btnPath3Button.gridy = 9;
		btnPath3Button.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		btnPath3Button.setForeground(Color.CYAN);
		this.add(btnPath3Button, gbc_btnPath3Button);
		btnPath3Button.addActionListener(new ActionListener() {//add actionlistner to listen for change
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(map.getPath3().size() > 0)map.setDrawThird();
		    }
		});
	}

	public String getSelectedStart() {
		return selectedStart;
	}
}