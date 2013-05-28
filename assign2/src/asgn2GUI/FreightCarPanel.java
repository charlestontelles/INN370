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
 * Graphical user interface to capture inputs related to a FreightCar.
 * 
 * @author  Charleston Telles(n8388342) (developer), 
 * 			Phurpa Wangchuk(n8448060) (developer, layout and formating)
 * 
 */
public class FreightCarPanel extends JPanel {

	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label and radio button label related to FreightCar Panel
	 */
	private static final String LABEL_PANEL_TITLE = "Freight Car";
	private static final String LABEL_KIND_OF_GOODS = "Kind of Goods:";
	private static final String LABEL_GROSS_WEIGT = "Gross Weight:";
	private static final String RADIO_BUTTON_GENERAL_GOOD = "[G]-General";
	private static final String RADIO_BUTTON_REFRIGATOR_GOOD = "[R]-Refrigerated";
	private static final String RADIO_BUTTON_DANGEROUS_GOOD = "[D]-Dangerous";
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
	 */
	public void initComponents() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.LIGHT_GRAY);

		JLabel panelTitle = new JLabel(LABEL_PANEL_TITLE);
		JLabel lblType = new JLabel(LABEL_KIND_OF_GOODS);
		JLabel lblWeight = new JLabel(LABEL_GROSS_WEIGT);

		JRadioButton generalButton = new JRadioButton(RADIO_BUTTON_GENERAL_GOOD);
		generalButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton refrigeratedButton = new JRadioButton(RADIO_BUTTON_REFRIGATOR_GOOD);
		refrigeratedButton.setBackground(Color.LIGHT_GRAY);
		JRadioButton dangerousButton = new JRadioButton(RADIO_BUTTON_DANGEROUS_GOOD);
		dangerousButton.setBackground(Color.LIGHT_GRAY);

		checkBoxGoodsType = new ButtonGroup();
		checkBoxGoodsType.add(generalButton);
		checkBoxGoodsType.add(refrigeratedButton);
		checkBoxGoodsType.add(dangerousButton);

		spnWeight = new JSpinner();

		btnAdd = new JButton("Add");

		panelTitle.setFont(new Font("Arial", Font.BOLD, 14));
		panelTitle.setForeground(Color.BLACK);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		addToPanel(this, panelTitle, constraints, 0, 10, 17, 17);
		addToPanel(this, lblType, constraints, 0, 11, 17, 17);
		addToPanel(this, generalButton, constraints, 0, 12, 20, 17);
		addToPanel(this, refrigeratedButton, constraints, 0, 13, 0, 20);
		addToPanel(this, dangerousButton, constraints, 0, 14, 10, 15);
		addToPanel(this, lblWeight, constraints, 0, 15, 20, 10);
		addToPanel(this, spnWeight, constraints, 1, 15, 20, 10);
		addToPanel(this, btnAdd, constraints, 1, 16, 10, 10);
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
	 * Getter for Goods Type
	 * 
	 * @return FreightCar's goods type
	 */
	public String getGoodsType() {
		for (Enumeration<AbstractButton> buttons = checkBoxGoodsType
				.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText().substring(1, 2);
			}
		}
		return "";
	}

	/**
	 * Getter for Weight parameter
	 * 
	 * @return FreightCar's weight
	 */
	public int getWeight() {
		return spnWeight != null ? new Integer(spnWeight.getValue().toString())
				: 0;
	}

}
