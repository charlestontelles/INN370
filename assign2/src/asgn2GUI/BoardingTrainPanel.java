package asgn2GUI;

import java.awt.Color;
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
	 * The text boxes below are use to show information (read only)
	 * related to a departing train.
	 */
	private JTextField txtPassengersOut;
	private JTextField txtNumberOnBoard;
	private JTextField txtLocomotiveCapacity;
	private JTextField txtTrainCanMove;

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
	 * TODO: CONVERT TO GRIDBAGLAYOUT
	 */
	public void initComponents() {
		this.setLayout(new GridLayout(3, 4));
		this.setBackground(Color.LIGHT_GRAY);

		//JLabel panelTitle = new JLabel("Departing Train");
		//JLabel lblEmpty = new JLabel("");
		JLabel lblPassengers = new JLabel("Passengers:");
		JLabel lblNumberOfSeats = new JLabel("Passengers out:");
		JLabel lblNumberOnBoard = new JLabel("Number On Board:");
		JLabel lblCapacity = new JLabel("Capacity:");
		JLabel lblCanMove = new JLabel("Train Can move?");

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
	 * all carriages including itself.
	 */
	public void setLocomotiveCapacity(String locomotiveCapacityMsg){
		txtLocomotiveCapacity.setText(locomotiveCapacityMsg);
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
