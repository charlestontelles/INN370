package asgn2GUI;

import java.awt.Color;
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
	 * TODO: CONVERT TO GRIDBAGLAYOUT
	 */
	public void initComponents() {
		this.setLayout(new GridLayout(10, 2));
		this.setBackground(Color.LIGHT_GRAY);
		JLabel panelTitle = new JLabel("Freight Car");
		JLabel lblType = new JLabel("Kind of Goods:");
		JLabel lblWeight = new JLabel("Weight:");
		
		JRadioButton generalButton = new JRadioButton("General");
		generalButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton refrigeratedButton = new JRadioButton("Refrigerated");
		refrigeratedButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton dangerousButton = new JRadioButton("Dangerous");
		dangerousButton.setBackground(Color.LIGHT_GRAY);
		
		checkBoxGoodsType = new ButtonGroup();
		checkBoxGoodsType.add(generalButton);
		checkBoxGoodsType.add(refrigeratedButton);
		checkBoxGoodsType.add(dangerousButton);
		
		
		spnWeight = new JSpinner();
		
		btnAdd = new JButton("Add");
		
		this.add(panelTitle);
		this.add(lblType);
		this.add(generalButton);
		this.add(refrigeratedButton);
		this.add(dangerousButton);
		this.add(lblWeight);
		this.add(spnWeight);
		this.add(btnAdd);
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
