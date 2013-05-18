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
 * Graphical user interface to capture inputs related to a Locomotive.
 * 
 * @author Charleston Telles
 * 
 */
public class LocomotivePanel extends JPanel {

	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Addition Button
	 */
	private JButton btnAdd;
	/**
	 * Check box for locomotive engine type
	 */
	private ButtonGroup checkBoxEngineType;
	/**
	 * Text box (spinner) for locomotive's power class
	 */
	private JSpinner spnPowerClass;
	/**
	 * Text box (spinner) for locomotive's weight
	 */
	private JSpinner spnWeight;

	/**
	 * Panel constructor receives a external listener to be added as observer
	 * 
	 * @param actionListener
	 *            external listener for panel's buttons
	 */
	public LocomotivePanel(ActionListener actionListener) {
		initComponents();
		btnAdd.addActionListener(actionListener);
	}

	/**
	 * Set the panel layout and arrange the components within the panel
	 * 
	 * TODO: CONVERT TO GRIDBAGLAYOUT
	 */
	public void initComponents() {
		this.setLayout(new GridLayout(10, 3));
		this.setBackground(Color.LIGHT_GRAY);

		JLabel panelTitle = new JLabel("Locomotive Settings");
		JLabel lblType = new JLabel("Engine Type:");
		JLabel lblWeight = new JLabel("Weight:");
		JLabel lblPowerClass = new JLabel("Power Class:");

		JRadioButton eletricButton = new JRadioButton("Eletric");
		eletricButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton dieselButton = new JRadioButton("Diesel");
		dieselButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton steamButton = new JRadioButton("Steam");
		steamButton.setBackground(Color.LIGHT_GRAY);

		checkBoxEngineType = new ButtonGroup();
		checkBoxEngineType.add(eletricButton);
		checkBoxEngineType.add(dieselButton);
		checkBoxEngineType.add(steamButton);

		btnAdd = new JButton("Add");
		spnWeight = new JSpinner();
		spnPowerClass = new JSpinner();

		this.add(panelTitle);
		this.add(lblType);
		this.add(eletricButton);
		this.add(dieselButton);
		this.add(steamButton);
		this.add(lblWeight);
		this.add(spnWeight);
		this.add(lblPowerClass);
		this.add(spnPowerClass);
		this.add(btnAdd);
	}

	/**
	 * Getter for EngineType
	 * 
	 * @return locomotive's engine Type
	 */
	public String getEngineType() {
		for (Enumeration<AbstractButton> buttons = checkBoxEngineType
				.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText().substring(0, 1);
			}
		}
		return "";
	}

	/**
	 * Getter for Power Class
	 * 
	 * @return locomotive's power class
	 */
	public int getPowerClass() {
		return spnPowerClass != null ? new Integer(spnPowerClass.getValue()
				.toString()) : 0;
	}

	/**
	 * Getter for Weight parameter
	 * 
	 * @return locomotive's weight
	 */
	public int getWeight() {
		return spnWeight != null ? new Integer(spnWeight.getValue().toString())
				: 0;
	}

}
