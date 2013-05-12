package asgn2Tests;

//import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;

public class DepartingTrainTest {
	private static final Integer PASSENGERCAR_SEAT_CAPACITY = 50;
	private static final Integer NEGATIVE_NUMBER = -5;

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
		departingTrain.addCarriage(new PassengerCar(100,PASSENGERCAR_SEAT_CAPACITY));
		assertTrue("Returns invald number of seats installed, expected "+PASSENGERCAR_SEAT_CAPACITY,
				    departingTrain.numberOfSeats() == PASSENGERCAR_SEAT_CAPACITY);
		
	}
	
	/**
	 * Check with negative number of people who wish to board the carriage
	 * 
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testNumberOnBoardWithNegativePeople() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(100,PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NEGATIVE_NUMBER);		
	}
	
	/**
	 * Check with more number of people who wish to board the carriage
	 * with more than installed seats capacity
	 * 
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testNumberOnBoardWithMoreThanSeatsCapacity() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(100,PASSENGERCAR_SEAT_CAPACITY));
		
		departingTrain.board(150);
	}
	
	/**
	 * Try to add new passenger to train which has passenger on board.
	 * Expected to return TrainException
	 * 
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testAddPassengerOnBoard() throws TrainException{
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(100,PASSENGERCAR_SEAT_CAPACITY));
		
		departingTrain.board(15);
		departingTrain.addCarriage(new PassengerCar(100,PASSENGERCAR_SEAT_CAPACITY));
	}
	
}
