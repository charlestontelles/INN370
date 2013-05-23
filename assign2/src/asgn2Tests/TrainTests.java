package asgn2Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;


/**
 * Test Cases for Departing Train.
 * 
 * 
 * @author Phurpa and Charleston
 * 
 */
public class TrainTests {

	private static final int PASSENGERCAR_SEAT_CAPACITY = 50;
	private static final int NEGATIVE_NUMBER = -5;
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 5;
	private static final int VALID_GROSS_WEIGHT = 100;
	private static final String VALID_CLASSIFICATION = "4D";
	private static final String VALID_GOODS_TYPE = "D";
	
	/**
	 * Creates a train and adds 3 passenger cars, then adds a locomotive and
	 * starts traversing using nextCarriage method. Expected to return 4 rolling
	 * stocks starting with locomotive.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testCanIterateThroughtCarriages() throws TrainException{
		int carriageCount = 0;

		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		RollingStock rollingStock = departingTrain.nextCarriage();

		assertTrue("first carriage is not locomotive",
				rollingStock instanceof Locomotive);
		carriageCount++;

		while ((rollingStock = departingTrain.nextCarriage()) != null) {
			assertTrue("carriage is not passenger car",
					rollingStock instanceof PassengerCar);
			carriageCount++;
		}

		assertTrue("Next Carriage has not traverse the whole train",
				carriageCount == 4);
	}
	
	/**
	 * Creates a departing train and adds a valid locomotive and a valid freight car.
	 * Then try to add a passenger after the freight car.
	 * Expected train exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFreightBeforePassengers()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		
		departingTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT, VALID_GOODS_TYPE));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

	}
}
