package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 * Graphical user interface to capture inputs related to a passenger's boarding.
 * 
 * This panel also show information about the departing train status
 * (ie. passenger on board, passenger remaining, can move (yes/no) and total capacity)
 * 
 * @author Charleston Telles
 * 
 */
public class BoardingTrainPanel extends JPanel {

	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Button related to boarding events
	 */
	private JButton btnBoard;
	/**
	 * Button related to carriage removal events
	 */
	private JButton btnRemoveCar;
	/**
	 * Button related departing train reset events
	 */
	private JButton btnReset;
	/**
	 * Text box (spinner) for number of passenger boarding
	 */
	private JSpinner spnPassengers;
	/**
	 * Boarding panel, place at the NORTH
	 */
	private JPanel boardingtPnl;
	/**
	 * The text boxes below are use to show information (read only)
	 * related to a departing train.
	 */
	private JTextField txtPassengersOut;
	private JTextField txtNumberOnBoard;
	private JTextField txtLocomotiveCapacity;
	private JTextField txtTrainCanMove;
	private JTextField txtTotalWeight;

	/**
	 * Panel constructor receives a external listener to be added as observer
	 * 
	 * @param actionListener
	 *            external listener for panel's buttons
	 */
	public BoardingTrainPanel(ActionListener actionListener) {
		initComponents();
		btnBoard.addActionListener(actionListener);
		btnRemoveCar.addActionListener(actionListener);
		btnReset.addActionListener(actionListener);
	}

	/**
	 * Set the panel layout and arrange the components within the panel
	 * 
	 * boardingtPnl panel is added on the NORTH to display the DepartingTrain
	 * control
	 */
	public void initComponents() {
		this.setLayout(new GridLayout()); //3,3
		this.setBackground(Color.LIGHT_GRAY);

		JLabel panelTitle = new JLabel("Departing Train");
		JLabel lblPassengers = new JLabel("Passengers:");
		JLabel lblNumberOfSeats = new JLabel("Passengers out:");
		JLabel lblNumberOnBoard = new JLabel("Number On Board:");
		JLabel lblCapacity = new JLabel(" Pulling Power:");
		JLabel lblCanMove = new JLabel("  Train Can move?");
		JLabel lblTotalWeight = new JLabel("Total Gross Weight:");
		
		spnPassengers = new JSpinner();

		btnBoard = new JButton("Board");
		btnRemoveCar = new JButton("Remove Car");
		btnReset = new JButton("Reset");
		
		
		txtPassengersOut = new JTextField();
		txtPassengersOut.setEditable(false);
		txtNumberOnBoard = new JTextField();
		txtNumberOnBoard.setEditable(false);
		txtLocomotiveCapacity = new JTextField();
		txtLocomotiveCapacity.setEditable(false);
		txtTrainCanMove = new JTextField();
		txtTrainCanMove.setEditable(false);
		txtTotalWeight = new JTextField();
		txtTotalWeight.setEditable(false);
		
		/*
		this.add(lblNumberOfSeats);
		this.add(txtPassengersOut);
		this.add(lblCanMove);
		this.add(txtTrainCanMove);
		this.add(lblNumberOnBoard);
		this.add(txtNumberOnBoard);
		this.add(lblCapacity);
		this.add(txtLocomotiveCapacity);
		this.add(lblPassengers);
		//this.add(btnReset); // TODO: reset button is not working
		this.add(spnPassengers);
		this.add(btnBoard);
		this.add(btnRemoveCar);		
		*/
		
		panelTitle.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		panelTitle.setForeground(Color.BLUE);

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints(); 
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		boardingtPnl = new JPanel(layout);
	    boardingtPnl.setBackground(Color.LIGHT_GRAY);
		add(boardingtPnl, BorderLayout.NORTH);
			
		addToPanel(boardingtPnl, panelTitle,constraints,0,0,0,5);
		addToPanel(boardingtPnl, lblNumberOfSeats,constraints,1,1,0,5);
		addToPanel(boardingtPnl, txtPassengersOut,constraints,2,1,50,5);
		addToPanel(boardingtPnl, lblCanMove,constraints,3,1,0,5);
		addToPanel(boardingtPnl, txtTrainCanMove,constraints,4,1,50,5);
		addToPanel(boardingtPnl, lblNumberOnBoard,constraints,1,3,0,5);
		addToPanel(boardingtPnl, txtNumberOnBoard,constraints,2,3,50,5);
		addToPanel(boardingtPnl, lblCapacity,constraints,3,3,0,15);
		addToPanel(boardingtPnl, txtLocomotiveCapacity,constraints,4,3,50,5);
		addToPanel(boardingtPnl, lblTotalWeight,constraints,1,4,0,5);
		addToPanel(boardingtPnl, txtTotalWeight,constraints,2,4,50,5);
		addToPanel(boardingtPnl, lblPassengers,constraints,3,4,0,5);
		addToPanel(boardingtPnl, spnPassengers,constraints,4,4,50,5);
		addToPanel(boardingtPnl, btnBoard,constraints,5,4,0,5);
		addToPanel(boardingtPnl, btnRemoveCar,constraints,6,4,0,5);
			
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
	 * @param w the grid internal padding width
	 * @param h the grid internal padding height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.ipadx = w;
		constraints.ipady = h;
		jp.add(c, constraints);
	}
	/**
	 * Getter for Number Of Passenger
	 * 
	 * @return number of passenger to be boarded
	 */
	public int getNumberOfPassenger() {
		return spnPassengers != null ? new Integer(spnPassengers.getValue()
				.toString()) : 0;
	}
	
	/**
	 * Setter for number of passenger not boarding.
	 * 
	 * @param passengersOutMsg number of passengers left out
	 */
	public void setPassengerOut(String passengersOutMsg){
		txtPassengersOut.setText(passengersOutMsg);
	}
	
	/**
	 * Setter for Number of passengers already boarded
	 * 
	 * @param numberOnBoardMsg number of passengers boarded
	 */
	public void setNumberOnBoard(String numberOnBoardMsg){
		txtNumberOnBoard.setText(numberOnBoardMsg);
	}
	
	/**
	 * Setter for locomotive's capacity
	 * 
	 * @param locomotiveCapacityMsg total capacity of locomotive considering
	 * all carriages including itself in tonnes
	 */
	public void setLocomotiveCapacity(String locomotiveCapacityMsg){
		txtLocomotiveCapacity.setText(locomotiveCapacityMsg + " Tonnes");
	}
	
	/**
	 * Setter for information about train can move or cannot move.
	 * 
	 * In order to help users to identify the train can move or move
	 * this method also changes to textbox color (ie green = can move,
	 * red = cannot move)
	 * 
	 * @param trainCanMoveMsg information about train can move or not
	 */
	public void setTrainCanMove(String trainCanMoveMsg){
		if(trainCanMoveMsg.equalsIgnoreCase("true")){
			txtTrainCanMove.setText("Yes");
			txtTrainCanMove.setBackground(Color.GREEN);
		} else {
			txtTrainCanMove.setText("No");
			txtTrainCanMove.setBackground(Color.RED);
		}
	}
}
