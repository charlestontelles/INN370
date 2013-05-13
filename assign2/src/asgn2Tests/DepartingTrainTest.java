package asgn2Tests;

//import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;

public class DepartingTrainTest {
	private static final int PASSENGERCAR_SEAT_CAPACITY = 50;
	private static final int NEGATIVE_NUMBER = -5;
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 5;
	private static final int GROSS_WEIGHT = 100;
	private static final String VALID_CLASSIFICATION = "4D";

	/**
	 * Create a new departing train and do not add a locomotive, then
	 * test if train can move without locomotive. 
	 * Expected false.
	 */
	@Test
	public void testTrainCannotMoveWithoutLocomotive(){
		DepartingTrain departingTrain = new DepartingTrain();
		assertFalse("Train cannot move without locomotive", departingTrain.trainCanMove());
	}

	/**
	 * Create a new departing train and test with not null.
	 * Expect true.
	 */
	@Test 
	public void testTrainConstructorNotNull(){
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("Train instance is null", departingTrain != null);
	}

	/**
	 * Create instance of departingTrain and check the initial seats capacity
	 * to return zero.
	 */
	/**
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeatsIsZero() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("PassengerCar seats can be zero", departingTrain.numberOfSeats() == 0);

	}


	/**
	 * Create instance of departingTrain and add number of seats on the
	 * PassengerCar carriage. Then check expected number of seats installed 
	 * is return
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeats() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT, PASSENGERCAR_SEAT_CAPACITY));
		assertTrue("Returns invald number of seats installed, expected "+PASSENGERCAR_SEAT_CAPACITY,
				departingTrain.numberOfSeats() == PASSENGERCAR_SEAT_CAPACITY);

	}

	/**
	 * Check with negative number of people who wish to board the carriage
	 * 
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testNumberOnBoardWithNegativePassenger() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT, PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NEGATIVE_NUMBER);		
	}

	/**
	 * Try to add new passenger to train which has passenger on board.
	 * Expected to return TrainException
	 * 
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testAddNewPassengerOnBoardWhichAlreadyHasPassenger() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT, PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT, PASSENGERCAR_SEAT_CAPACITY));
	}

	/**
	 * Test to add new Locomotive carriage to already existing. It is
	 * expected to throw TrainException message
	 * 
	 * @throws TrainException
	 */
	
	@Test(expected = TrainException.class)
	public void testAddLocomotiveToExistingLocomotive() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT, VALID_CLASSIFICATION));
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT, VALID_CLASSIFICATION));
			
	}
	
	/**
	 * Test perform to check Train can not move if its locomotive's pulling power
	 * is less than the train's total weight (including the locomotive itself). 
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testDepartingTraingCannotMoveIfTotalWeightExceedPullingPower()
			throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(4 * GROSS_WEIGHT, PASSENGERCAR_SEAT_CAPACITY));
		
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT, VALID_CLASSIFICATION));
		assertFalse("Train overloaded", departingTrain.trainCanMove());
	}
	
	

}
