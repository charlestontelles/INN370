package asgn2Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;


/**
 * Test Cases for Rolling Stocks.
 * 
 * 
 * @author Phurpa and Charleston
 * 
 */
public class RollingStockTests {

	private static final int ZERO_GROSS_WEIGHT = 0;
	private static final int NEGATIVE_GROSS_WEIGHT = -1;
	private static final int VALID_GROSS_WEIGHT = 100;
	private static final int VALID_NUMBER_PASSENGERS = 10;
	private static final int NEGATIVE_NUMBER_PASSENGERS = -1;
	private static final String VALID_CLASSIFICATION = "4D";
	private static final String INVALID_LOCOMOTIVE_CODE = "4Z";
	private static final String VALID_GOOD_TYPE = "G"; //G,R OR D
	private static final String INVALID_GOOD_TYPE = "A";
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 70;
	private static final int NUMBER_OF_PASSENGER_TO_DEPART = 70;
	private static final int NEGATIVE_NUMBER_OF_PASSENGER_TO_DEPART = -1;
	private static final int NUMBER_OF_PASSENGER_TO_BOARD2 = 30; //A 
	private static final int NUMBER_OF_PASSENGER_TO_ALIGHT = 5; //B
	private static final int PESSENGER_ON_BOARD = 25; //Difference of A-B
	private static final int SEATS_CAPACITY = 50;
	private static final int NEGATIVE_SEATS_CAPACITY = -1;


	/**
	 * Try to create a new Locomotive with negative gross weight.
	 * Expected Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testNegativeGrossWeightInLocomotiveIsDisallowed() throws TrainException{
		new Locomotive(NEGATIVE_GROSS_WEIGHT, VALID_CLASSIFICATION);
	}

	/**
	 * Try to create a new Locomotive with zero gross weight.
	 * Expected Train exception because gross weight must be positive.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testZeroGrossWeightInLocomotiveIsDisallowed() throws TrainException{
		new Locomotive(ZERO_GROSS_WEIGHT, VALID_CLASSIFICATION);
	}

	/**
	 * Try to create a new Locomotive with power equal zero (allowed range is [1-9]).
	 * Expected Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testZeroPullingPowerIsDisallowed() throws TrainException{
		new Locomotive(ZERO_GROSS_WEIGHT, VALID_CLASSIFICATION);
	}

	/**
	 * Try to create a new Locomotive with a type other than D/E/S.It is 
	 * Expected to throw Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidLocomotiveTypeDetected() throws TrainException{
		new Locomotive(VALID_GROSS_WEIGHT, INVALID_LOCOMOTIVE_CODE);
	}

	/**
	 * Test to perform that locomotive pulling power by adding 
	 * one locomotive, three passenger car and freight car with 
	 * VALID_GROSS_WEIGHT=100 tonnes. It is expected to return false
	 * 
	 */
	@Test 
	public void testLocomotivePowerTooLow() throws TrainException{

		DepartingTrain deptTrain = new DepartingTrain();

		deptTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT, VALID_CLASSIFICATION));

		deptTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY));
		deptTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY));
		deptTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY));

		deptTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT, VALID_GOOD_TYPE));

		assertFalse("Locomotive pulling power is low", deptTrain.trainCanMove() );

	}


	/**
	 * Creates a new passenger car with valid gross weight and valid number of passenger.
	 * Then boards a valid number of passenger. And then try to aligh a negative number of
	 * passengers. Expected train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidNumberOfPassengersAlighIsDisallowed() throws TrainException{
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, VALID_NUMBER_PASSENGERS);
		passengerCar.board(VALID_NUMBER_PASSENGERS);
		passengerCar.alight(NEGATIVE_NUMBER_PASSENGERS);
	}

	/**
	 * Create a new FreightCar using a negative gross weight.
	 * Expected to throw trainException.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarWithNegativeWeightDisallowed() throws TrainException{
		new FreightCar(NEGATIVE_GROSS_WEIGHT, VALID_GOOD_TYPE);
	}


	/**
	 * Create a new FreightCar with a zero gross weight.
	 * Expected train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarWithZeroWeightDisallowed() throws TrainException {
		new FreightCar(ZERO_GROSS_WEIGHT, VALID_GOOD_TYPE);
	}

	/**
	 * Create new FreightCar with valid gross weight and valid 
	 * goodtype
	 * Expected  to return true
	 * 
	 *@throws TrainException
	 */

	@Test 
	public void testFreightCarWithValidGoodType() throws TrainException{
		FreightCar freightCar = new FreightCar(VALID_GROSS_WEIGHT , VALID_GOOD_TYPE);
		assertTrue("Invalid good type", freightCar.goodsType() == VALID_GOOD_TYPE);
	}

	/** Create FreightCar instance with valid gross weight and valid 
	 * good type. Then compare with invalid good type.Then expect to 
	 * return false for the statement
	 * 
	 * @throws TrainException 
	 * 
	 */
	@Test
	public void testFreightCaWithInvalidGoodTypeDetected() throws TrainException{
		FreightCar freightCar = new FreightCar(VALID_GROSS_WEIGHT , VALID_GOOD_TYPE);
		assertFalse("Invalid good type",freightCar.goodsType() == INVALID_GOOD_TYPE);
	}

	/**
	 * Creates a new locomotive instance  and verifies the toString call contains the
	 * valid goodtype
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotivetoStringPrintsCorrectely() throws TrainException {
		Locomotive locomotive = new Locomotive(VALID_GROSS_WEIGHT , VALID_CLASSIFICATION);
		assertTrue("Method toString() has not been overrided ",locomotive.toString().contains(VALID_CLASSIFICATION));
	}


	/**
	 * Creates a Freight instance  and verifies the toString call contains the
	 * valid good code
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testFreighttoStringPrintsCorrectely() throws TrainException {
		FreightCar freightCar = new FreightCar(VALID_GROSS_WEIGHT , VALID_GOOD_TYPE);
		assertTrue("Method toString() has not been overrided ",freightCar.toString().contains(VALID_GOOD_TYPE));
	}

	/**
	 * Create Passenger car constructor with negative seats capacity.
	 *  Expected to throw TrainException
	 *  
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarWithNegativeSeatsCapacity() 
			throws TrainException {
		new PassengerCar(VALID_GROSS_WEIGHT, NEGATIVE_SEATS_CAPACITY);		
	}

	/**
	 * Create Passenger car constructor with negative gross weight.
	 * Expected to throw TrainException
	 *  
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarWithNegativeGrossWeight() 
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
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	
		assertTrue("Invalid return of installed seats capacity", passengerCar.numberOfSeats() == SEATS_CAPACITY);
	}

	/** Call PassengerCar constructor with negative the number of 
	 *  people who wish to board the carriage.
	 *  Expected to throw TrainException message
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCarWithNegativeNumberOfSeatsCapacity() 
			throws TrainException {
		new PassengerCar(VALID_GROSS_WEIGHT, NEGATIVE_SEATS_CAPACITY);	
	}

	/** Create instance of PassengerCar and call board() with
	 *  negative new passenger to board.Expected to throw TrainException message
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testNegativeNumberOfPassengerToBoardIsInvalid() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	
		passengerCar.board(NEGATIVE_NUMBER_PASSENGERS);
	}


	/**
	 * Test to return its seats capacity of the carriage when more passenger try to
	 * board than its seats capacity. Meaning to say that carriage can accommodate 
	 * only maximum of its seats capacity. For instance, if seats capacity is 50 and people
	 * try to board is 70, then is can take only 50.
	 * 
	 */
	@Test 
	public void testMaximumPassengerCanBoard() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.board(NUMBER_OF_PASSENGER_TO_BOARD);  //passenger to board is more than seats capacity
		assertTrue("Cannot accomodate more than its seats capacity", 
				passengerCar.numberOfSeats() == SEATS_CAPACITY);
	}


	/**
	 * Create passenger car instance and try to call alight with negative passenger to
	 * depart from carriage. Then it should throw TrainException
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAlighWithNegativePassengerIsDisallowed() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.alight(NEGATIVE_NUMBER_OF_PASSENGER_TO_DEPART);
	}



	/**
	 * Test perform to return number of person on board after performing sequence of action.
	 * For instance if there are 50 seats capacity, 30 person board on train, and 5 person alight
	 * from carriage, then it is expected to return 25. 
	 *
	 * @throws TrainException
	 */
	@Test 
	public void testNumberOfPassengerOnBoardAfterValidAlight() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50

		passengerCar.board(NUMBER_OF_PASSENGER_TO_BOARD2);//30 person on board

		passengerCar.alight(NUMBER_OF_PASSENGER_TO_ALIGHT);//5 person alight/depart

		assertTrue("Invalid alight from the train", passengerCar.numberOnBoard() == PESSENGER_ON_BOARD);
	}

	/**
	 * Creates a new passengerCar with capacity to 50 seat. Then board two set of 30 passengers.
	 * Expected 10 passenger to be left out. 
	 *
	 * @throws TrainException
	 */
	@Test 
	public void testNumberOfPassengerLeftOut() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50

		passengerCar.board(NUMBER_OF_PASSENGER_TO_BOARD2);//30 person on board

		int leftOut = passengerCar.board(NUMBER_OF_PASSENGER_TO_BOARD2);//30 person on board

		assertTrue("Invalid number of passenger left out", leftOut == 10);
	}

	/**
	 * Creates a new passengerCar instance  and verifies the toString call contains the
	 * valid passengerOnBoard and numberOfSeats
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCartoStringPrintsCorrectely() throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	
		assertTrue("Method toString() has not been overrided ",
				passengerCar.toString().contains(passengerCar.numberOnBoard() + "/" + passengerCar.numberOfSeats()));
	}

	/**
	 * Create passenger car instance and try to call alight with 
	 * number of departing passengers exceeds the number on board.
	 * Then it should throw TrainException
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testTooManyAlighting() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50

		passengerCar.board(SEATS_CAPACITY);
		passengerCar.alight(NUMBER_OF_PASSENGER_TO_DEPART);
	}

	/**
	 * Create Locomotive instance and call power().Then check the return 
	 * value.
	 * 
	 */
	@Test 
	public void testPowerCalculatedCorreclty() 
			throws TrainException {
		Locomotive locomotive = new Locomotive(VALID_GROSS_WEIGHT, VALID_CLASSIFICATION);	

		assertTrue("Calculation is Invalid", locomotive.power() == 400);
	}

	/**
	 * Uses reflection to certify each rolling stock has exactly one constructor.
	 * 
	 */
	@Test 
	public void testNumberOfConstructors() 
			throws TrainException {
		assertTrue("rolling stock has more than one constructor", Locomotive.class.getConstructors().length == 1);
		assertTrue("rolling stock has more than one constructor", PassengerCar.class.getConstructors().length == 1);
		assertTrue("rolling stock has more than one constructor", FreightCar.class.getConstructors().length == 1);
	}
	
	/**
	 * Create passenger car instance and with valid number of seats.
	 * Then board the same number of passengers as the number of seats.
	 * And then call alight to unboard the same number board.
	 * Expected the number of passengers on board to be zero.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testBoardandAlightAll() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.board(SEATS_CAPACITY);
		passengerCar.alight(SEATS_CAPACITY);
		assertTrue("number on board should be zero", passengerCar.numberOnBoard() == 0);
	}

	/**
	 * Create passenger car instance and with valid number of seats.
	 * Then board the same number of passengers as the number of seats.
	 * And then call alight to unboard the same number board plus 1.
	 * Expected train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testTooManyAlight() 
			throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, SEATS_CAPACITY);	//seats capacity 50
		passengerCar.board(SEATS_CAPACITY);
		passengerCar.alight(SEATS_CAPACITY + 1);
	}
}
