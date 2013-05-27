package asgn2GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 * @author  Charleston Telles(n8388342) (developer), 
 * 			Phurpa Wangchuk(n8448060) (developer, layout and formating)
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
	 * TODO: CONVERT TO GRIDBAGLAYOUT locomotivePnl panel is added on the WEST
	 * to display the locomotive control
	 */
	public void initComponents() {
		setLayout(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);

		JLabel panelTitle = new JLabel("Locomotive");
		JLabel lblType = new JLabel("Engine Type:");
		JLabel lblWeight = new JLabel("Gross Weight:");
		JLabel lblPowerClass = new JLabel("Power Class:");

		JRadioButton eletricButton = new JRadioButton("[E]-Eletric");
		eletricButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton dieselButton = new JRadioButton("[D]-Diesel");
		dieselButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton steamButton = new JRadioButton("[S]-Steam");
		steamButton.setBackground(Color.LIGHT_GRAY);

		checkBoxEngineType = new ButtonGroup();
		checkBoxEngineType.add(eletricButton);
		checkBoxEngineType.add(dieselButton);
		checkBoxEngineType.add(steamButton);

		btnAdd = new JButton("Add");
		spnWeight = new JSpinner();
		spnPowerClass = new JSpinner();

		panelTitle.setFont(new Font("Arial", Font.BOLD, 14));
		panelTitle.setForeground(Color.BLACK);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		addToPanel(this, panelTitle, constraints, 0, 10, 17, 17);
		addToPanel(this, lblType, constraints, 0, 12, 17, 17);
		addToPanel(this, eletricButton, constraints, 0, 13, 17, 17);
		addToPanel(this, dieselButton, constraints, 0, 14, 17, 17);
		addToPanel(this, steamButton, constraints, 0, 15, 17, 17);
		addToPanel(this, lblWeight, constraints, 0, 16, 20, 10);
		addToPanel(this, spnWeight, constraints, 1, 16, 20, 10);
		addToPanel(this, lblPowerClass, constraints, 0, 17, 20, 10);
		addToPanel(this, spnPowerClass, constraints, 1, 17, 20, 10);
		addToPanel(this, btnAdd, constraints, 1, 18, 20, 10);

	}

	/**
	 * 
	 * A convenience method to add a component to given grid bag layout
	 * locations.
	 * 
	 * @param c
	 *            the component to add
	 * @param constraints
	 *            the grid bag constraints to use
	 * @param x
	 *            the x grid position
	 * @param y
	 *            the y grid position
	 * @param w
	 *            the grid width
	 * @param h
	 *            the grid height
	 */
	private void addToPanel(JPanel jp, Component c,
			GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.ipadx = w;
		constraints.ipady = h;
		jp.add(c, constraints);
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
				return button.getText().substring(1, 2);
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
