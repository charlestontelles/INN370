package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	 * panel 
	 */
	private JPanel passengerPnl;
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

		setLayout(new GridLayout());
		setBackground(Color.LIGHT_GRAY);

		JLabel panelTitle = new JLabel("Passenger Car Panel");
		JLabel lblEmpty = new JLabel("");
		JLabel lblSeats = new JLabel("Seats Capacity:");
		JLabel lblWeight = new JLabel("Gross Weight:");

		spnWeight = new JSpinner();
		spnSeats  = new JSpinner();
		btnAdd    = new JButton("Add");

		panelTitle.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		panelTitle.setForeground(Color.BLUE);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		passengerPnl = new JPanel(layout);
		add(passengerPnl, BorderLayout.SOUTH);
	    passengerPnl.setBackground(Color.LIGHT_GRAY);
		
	    addToPanel(passengerPnl, panelTitle,constraints,0,0,7,7);
		addToPanel(passengerPnl, lblEmpty,constraints,0,0,7,7);
		addToPanel(passengerPnl, lblWeight,constraints,10,10,7,7);
		addToPanel(passengerPnl, spnWeight,constraints,11,10,20,10);
		addToPanel(passengerPnl, lblSeats,constraints,10,11,7,7);
		addToPanel(passengerPnl, spnSeats,constraints,11,11,10,10);
		addToPanel(passengerPnl, btnAdd,constraints,12,11,5,5);
		repaint();

/*		this.add(panelTitle);
		this.add(lblEmpty);
		this.add(lblWeight);
		this.add(spnWeight);
		this.add(lblSeats);
		this.add(spnSeats);
		this.add(btnAdd);
	*/	 
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
