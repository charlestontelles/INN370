package asgn2GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 * Graphical user interface to capture inputs related to a PassengerCar.
 * 
 * @author Charleston Telles
 * 
 */
public class PassengerCarPanel extends JPanel {
	
	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Addition Button
	 */
	private JButton btnAdd;
	/**
	 * Text box (spinner) for PassengerCar's number of seats
	 */
	private JSpinner spnSeats;
	/**
	 * Text box (spinner) for PassengerCar's weight
	 */
	private JSpinner spnWeight;

	/**
	 * Panel constructor receives a external listener to be added as observer
	 * 
	 * @param actionListener
	 *            external listener for panel's buttons
	 */
	public PassengerCarPanel(ActionListener actionListener) {
		initComponents();
		btnAdd.addActionListener(actionListener);
	}

	/**
	 * Set the panel layout and arrange the components within the panel
	 * 
	 * TODO: CONVERT TO GRIDBAGLAYOUT
	 */
	public void initComponents() {
		this.setLayout(new GridLayout(4, 2));
		this.setBackground(Color.LIGHT_GRAY);
		
		JLabel panelTitle = new JLabel("Passenger Car");
		JLabel lblEmpty = new JLabel("");
		JLabel lblSeats = new JLabel("Seats:");
		JLabel lblWeight = new JLabel("Weight:");
		
		spnWeight = new JSpinner();
		spnSeats = new JSpinner();
		
		btnAdd = new JButton("Add");
		
		this.add(panelTitle);
		this.add(lblEmpty);
		this.add(lblWeight);
		this.add(spnWeight);
		this.add(lblSeats);
		this.add(spnSeats);
		this.add(btnAdd);
	}
	
	/**
	 * Getter for Number of seats parameter
	 * 
	 * @return PassengerCar's number of seats
	 */
	public int getNumberOfSeats(){
		return spnSeats!=null?new Integer(spnSeats.getValue().toString()):0;
	}

	/**
	 * Getter for Weight parameter
	 * 
	 * @return locomotive's weight
	 */
	public int getWeight(){
		return spnWeight!=null?new Integer(spnWeight.getValue().toString()):0;
	}

}
