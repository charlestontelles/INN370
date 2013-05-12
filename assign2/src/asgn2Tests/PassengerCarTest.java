/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.PassengerCar;

/**
 * @author Phurpa
 *
 */
public class PassengerCarTest {

	private static final int NEGATIVE_GROSS_WEIGHT = -100;
	private static final int NEGATIVE_SEATS_CAPACITY = -1;
	private static final int SEATS_CAPACITY = 50;
	private static final int GROSS_WEIGHT = 100;
	private static final int NEGATIVE_PASSENGER = -5;
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 70;
	private static final int NUMBER_OF_PASSENGER_TO_DEPART = 70;
	private static final int NEGATIVE_NUMBER_OF_PASSENGER_TO_DEPART = -1;


	/**
	 * Create Passenger car constructor with negative seats capacity.
	 *  Expected to throw TrainException
	 *  
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarConstructorWithNegativeSeatsCapacity() 
			throws TrainException {
		new PassengerCar(GROSS_WEIGHT, NEGATIVE_SEATS_CAPACITY);		
	}

	/**
	 * Create Passenger car constructor with negative gross weight.
	 * Expected to throw TrainException
	 *  
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarConstructorWithNegativeGrossWeight() 
			throws TrainException {
		new PassengerCar(NEGATIVE_GROSS_WEIGHT, SEATS_CAPACITY);		
	}

	/** create instance of valid PassengerCar and test for the return
	 *  of number of installed seats capacity
	 * 
	 */
	@Test 
	public void testPassengerCarNumberOfSeats() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, SEATS_CAPACITY);	
		assertTrue("Invalid return of installed seats capacity", passengerCar.numberOfSeats() == SEATS_CAPACITY);
	}

	/** Call PassengerCar constructor with negative the number of 
	 *  people who wish to board the carriage.
	 *  Expected to throw TrainException message
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarConstructorWithNegativeNumberOfSeats() 
			throws TrainException {
		new PassengerCar(GROSS_WEIGHT, NEGATIVE_SEATS_CAPACITY);	
	}
	
	/** Create instance of PassengerCar and call board() with
	 *  negative new passenger to board.Expected to throw TrainException message
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testWithNegativeNumberOfPeopleToBoard() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, SEATS_CAPACITY);	
		passengerCar.board(NEGATIVE_PASSENGER);
	}

	/**
	 * Test to return its seats capacity of the carriage when more people try to
	 * board than its seats capacity. Meaning to say that carriage can accommodate 
	 * only maximum of its seats capacity. For instance, if seats capacity is 50 and people
	 * try to board is 70, then is can take only 50.
	 * 
	 */
	@Test 
	public void testToReturnMaximumPersonCanBoardOnCarriage() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.board(NUMBER_OF_PASSENGER_TO_BOARD);  //passenger to board is more than seats capacity
		assertTrue("Cannot accomodate more than its seats capacity", passengerCar.numberOfSeats() == SEATS_CAPACITY);
	}
	
	/**
	 * Create passenger car instance and try to align with negative passenger to
	 * depart from carriage. Then it should throw TrainException
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerAlightmentWithNegativePassenger() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.alight(NEGATIVE_NUMBER_OF_PASSENGER_TO_DEPART);
	}
	
	/**
	 * Create passenger car instance and try to call align with 
	 * number of departing passengers exceeds the number on board.
	 * Then it should throw TrainException
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerAlightmentWithExceedsNumberOnBoard() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		
		passengerCar.board(SEATS_CAPACITY);
		passengerCar.alight(NUMBER_OF_PASSENGER_TO_DEPART);
	}
}
