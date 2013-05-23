package asgn2Tests;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;


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
	private static final String INVALID_LOCOMOTIVE_POWER = "0D";
	private static final String INVALID_LOCOMOTIVE_TYPE = "4Z";
	
	/**
	 * Try to create a new Locomotive with negative gross weight.
	 * Expected Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testNegativeGrossWeightNotAllowed() throws TrainException{
		Locomotive locomotive = new Locomotive(NEGATIVE_GROSS_WEIGHT, VALID_CLASSIFICATION);
	}
	
	/**
	 * Try to create a new Locomotive with zero gross weight.
	 * Expected Train exception because gross weight must be positive.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testZeroGrossWeightNotAllowed() throws TrainException{
		Locomotive locomotive = new Locomotive(ZERO_GROSS_WEIGHT, VALID_CLASSIFICATION);
	}
	
	/**
	 * Try to create a new Locomotive with power equal zero (allowed range is [1-9]).
	 * Expected Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidLocomotivePower() throws TrainException{
		Locomotive locomotive = new Locomotive(ZERO_GROSS_WEIGHT, INVALID_LOCOMOTIVE_POWER);
	}
	
	/**
	 * Try to create a new Locomotive with a type different of D/E/S.
	 * Expected Train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidLocomotiveType() throws TrainException{
		Locomotive locomotive = new Locomotive(ZERO_GROSS_WEIGHT, INVALID_LOCOMOTIVE_TYPE);
	}
	
	/**
	 * Creates a new passenger car with valid gross weight and valid number of passenger.
	 * Then boards a valid number of passenger. And then try to aligh a negative number of
	 * passengers. Expected train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAlighInvalidNumberOfPassengers() throws TrainException{
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, VALID_NUMBER_PASSENGERS);
		passengerCar.board(VALID_NUMBER_PASSENGERS);
		passengerCar.alight(NEGATIVE_NUMBER_PASSENGERS);
	}
}
