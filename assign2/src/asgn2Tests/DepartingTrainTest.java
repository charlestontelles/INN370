package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

/**
 * Test Cases for DepartingTrain.
 * 
 * @author Phurpa and Charleston
 * 
 */
public class DepartingTrainTest {
	private static final int PASSENGERCAR_SEAT_CAPACITY = 50;
	private static final int NEGATIVE_NUMBER = -5;
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 5;
	private static final int GROSS_WEIGHT = 100;
	private static final String VALID_CLASSIFICATION = "4D";

	/**
	 * Create a new departing train and do not add a locomotive, then test if
	 * train can move without locomotive. Expected true. In the degenerate case
	 * of a "train" which doesn't have any rolling stock at all yet, the method
	 * returns true.
	 */
	@Test
	public void testTrainCannotMoveWithoutLocomotive() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue(
				"Can move should return true when there is no rolling stock yet",
				departingTrain.trainCanMove());
	}

	/**
	 * Create a new departing train and test with not null. Expect true.
	 */
	@Test
	public void testTrainConstructorNotNull() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("Train instance is null", departingTrain != null);
	}

	/**
	 * Create instance of departingTrain and check the initial seats capacity to
	 * return zero.
	 */
	/**
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeatsIsZero() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("PassengerCar seats can be zero",
				departingTrain.numberOfSeats() == 0);

	}

	/**
	 * Create instance of departingTrain and add number of seats on the
	 * PassengerCar carriage. Then check expected number of seats installed is
	 * return
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeats() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		assertTrue("Returns invald number of seats installed, expected "
				+ PASSENGERCAR_SEAT_CAPACITY,
				departingTrain.numberOfSeats() == PASSENGERCAR_SEAT_CAPACITY);

	}

	/**
	 * Check with negative number of people who wish to board the carriage
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testNumberOnBoardWithNegativePassenger() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NEGATIVE_NUMBER);
	}

	/**
	 * Try to add new passenger to train which has passenger on board. Expected
	 * to return TrainException
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testAddNewPassengerOnBoardWhichAlreadyHasPassenger()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
	}

	/**
	 * Test to add new Locomotive carriage to already existing. It is expected
	 * to throw TrainException message
	 * 
	 * @throws TrainException
	 */

	@Test(expected = TrainException.class)
	public void testAddLocomotiveToExistingLocomotive() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

	}

	/**
	 * Test perform to check Train can not move if its locomotive's pulling
	 * power is less than the train's total weight (including the locomotive
	 * itself).
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testDepartingTraingCannotMoveIfTotalWeightExceedPullingPower()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(4 * GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		assertFalse("Train overloaded", departingTrain.trainCanMove());
	}

	/**
	 * Creates a train with no carriage and calls getNext method. Expected null
	 * as result.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNextCarriageWhenTrainHasNoCarriage() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		RollingStock carriage = departingTrain.nextCarriage();

		assertTrue("Carriage is not null", carriage == null);
	}

	/**
	 * Creates a trains and adds a locomotive only. Then calls nextCarriage and
	 * expect the result to be the locomotive
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNextCarriageWhenTrainHasOnlyLocomotive()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		RollingStock carriage = departingTrain.nextCarriage();

		assertTrue("Carriage is not null", carriage instanceof Locomotive);
	}

	/**
	 * Creates a train and adds a locomotive and a carriage (PassengerCar). Then
	 * calls firstCarriage to return the locomotive and calls nextCarriage
	 * immediatelly after. Expect PassengerCar to be returned.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNextCarriageWhenTrainHasLocomotiveAndCarriages()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		departingTrain.firstCarriage();

		RollingStock carriage = departingTrain.nextCarriage();

		assertTrue("Carriage is not null", carriage instanceof PassengerCar);
	}

	/**
	 * Creates a train and adds 3 passenger cars, then adds a locomotive and
	 * starts traversing using nextCarriage method. Expected to return 4 rolling
	 * stocks starting with locomotive.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testTraverseTrainUsingNextCarriage() throws TrainException {
		int carriageCount = 0;

		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
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
	 * Test case for the Carriage which cannot be removed if there is already
	 * passenger on it. If attempted to remove then it is expected to throws
	 * TrainException
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testCannotRemoveCarriageIfPassengerExist()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);

		departingTrain.removeCarriage();
	}

	/**
	 * Cannot remove empty carriage.In other words if there is no rolling stock
	 * on the "train" and attempt to remove it. Then it is expected to throw
	 * exception message
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testCannotRemoveCarriageIfNull() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		RollingStock firstCarriage = departingTrain.firstCarriage();
		assertTrue("There is no Rolling Stock in the train",
				firstCarriage == null);
	}

}
