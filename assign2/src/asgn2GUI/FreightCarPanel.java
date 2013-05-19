package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 * Graphical user interface to capture inputs related to a FreightCar.
 * 
 * @author Charleston Telles
 * 
 */
public class FreightCarPanel extends JPanel {

	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Addition Button
	 */
	private JButton btnAdd;
	/**
	 * Check box for FreightCar's goods type
	 */
	private ButtonGroup checkBoxGoodsType;
	/**
	 * Text box (spinner) for FreightCar's weight
	 */
	private JSpinner spnWeight;
	/**
	 * freight panel, place at the EAST
	 */
	private JPanel freightPnl;
	/**
	 * Panel constructor receives a external listener to be added as observer
	 * 
	 * @param actionListener
	 *            external listener for panel's buttons
	 */
	public FreightCarPanel(ActionListener actionListener) {
		initComponents();
		btnAdd.addActionListener(actionListener);
	}

	/**
	 * Set the panel layout and arrange the components within the panel
	 * 
	 * freightPnl panel is added on the EAST to display the FreightCar control
	 */
	public void initComponents() {
		this.setLayout(new GridLayout());
		this.setBackground(Color.LIGHT_GRAY);
		
		JLabel panelTitle = new JLabel("Freight Car");
		JLabel lblType = new JLabel("Kind of Goods:");
		JLabel lblWeight = new JLabel("Gross Weight:");

		JRadioButton generalButton = new JRadioButton("General-G");
		generalButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton refrigeratedButton = new JRadioButton("Refrigerated-R");
		refrigeratedButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton dangerousButton = new JRadioButton("Dangerous-D");
		dangerousButton.setBackground(Color.LIGHT_GRAY);

		checkBoxGoodsType = new ButtonGroup();
		checkBoxGoodsType.add(generalButton);
		checkBoxGoodsType.add(refrigeratedButton);
		checkBoxGoodsType.add(dangerousButton);


		spnWeight = new JSpinner();

		btnAdd = new JButton("Add");

		/*
		this.add(panelTitle);
		this.add(lblType);
		this.add(generalButton);
		this.add(refrigeratedButton);
		this.add(dangerousButton);
		this.add(lblWeight);
		this.add(spnWeight);
		this.add(btnAdd);
		 */

		panelTitle.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		panelTitle.setForeground(Color.BLUE);

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints(); 

		constraints.fill = GridBagConstraints.NONE;
		freightPnl = new JPanel(layout);
		freightPnl.setBackground(Color.LIGHT_GRAY);
		add(freightPnl, BorderLayout.EAST);
		
		addToPanel(freightPnl, panelTitle,constraints,0,10,17,17);
		addToPanel(freightPnl, lblType,constraints,0,11,17,17);
		addToPanel(freightPnl, generalButton,constraints,0,12,20,17);
		addToPanel(freightPnl, refrigeratedButton,constraints,0,13,0,20);
		addToPanel(freightPnl, dangerousButton,constraints,0,14,10,15);
		addToPanel(freightPnl, lblWeight,constraints,0,15,20,10);
		addToPanel(freightPnl, spnWeight,constraints,1,15,20,10);
		addToPanel(freightPnl, btnAdd,constraints,0,16,10,10);
		//repaint();

	}

	/**
	 * 
	 * A convenience method to add a component to given grid bag
	 * layout locations. 
	 *
	 * @param c the component to add
	 * @param constraints the grid bag constraints to use
	 * @param x the x grid position
	 * @param y the y grid position
	 * @param w the grid width
	 * @param h the grid height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.ipadx = w;
		constraints.ipady = h;
		jp.add(c, constraints);
	}


	/**
	 * Getter for Goods Type
	 * 
	 * @return FreightCar's goods type
	 */
	public String getGoodsType(){
		for (Enumeration<AbstractButton> buttons = checkBoxGoodsType.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText().substring(0, 1);
			}
		}
		return "";
	}

	/**
	 * Getter for Weight parameter
	 * 
	 * @return FreightCar's weight
	 */
	public int getWeight(){
		return spnWeight!=null?new Integer(spnWeight.getValue().toString()):0;
	}

}
