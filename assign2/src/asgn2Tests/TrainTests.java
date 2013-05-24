package asgn2Tests;

import static org.junit.Assert.assertFalse;
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
	private static final int NUMBER_OF_PASSENGER_TO_BOARD = 10;
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
	public void CanIterateThroughtCarriages() throws TrainException{
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
	public void FreightBeforePassengers()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT, VALID_GOODS_TYPE));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));

	}

	/**
	 * Create a new departing train and do not add a locomotive, then test if
	 * train can move without locomotive. Expected true. In the degenerate case
	 * of a "train" which doesn't have any rolling stock at all yet, the method
	 * returns true.
	 */
	@Test
	public void TrainCannotMoveWithoutLocomotive() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue(
				"Can move should return true when there is no rolling stock yet",
				departingTrain.trainCanMove());
	}

	/**
	 * Create a new departing train and test that null train can move. Expect true.
	 */
	@Test
	public void TrainConstructorNotNull() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("Train instance is null", departingTrain.trainCanMove());
	}

	/**
	 * Create instance of departingTrain and check the initial seats capacity of the car to
	 * to return zero.
	 * 
	 */
	@Test
	public void PassengerCarNumberOfSeatsIsZero() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("PassengerCar seats can be zero",
				departingTrain.numberOfSeats() == 0);

	}

	/**
	 * Create instance of departingTrain and add number of seats on the
	 * PassengerCar carriage. Then check expected number of seats installed is
	 * return
	 */
	@Test
	public void NumberOfSeatsCountedCorrectly() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		assertTrue("Returns invald number of seats installed, expected "
				+ PASSENGERCAR_SEAT_CAPACITY,
				departingTrain.numberOfSeats() == PASSENGERCAR_SEAT_CAPACITY);

	}


	/**
	 * Test by boarding exact number of passenger to seats capacity. then it is
	 * expected to return true, otherwise false
	 * 
	 */
	@Test
	public void TrainExactlyFull() throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT, 
				PASSENGERCAR_SEAT_CAPACITY);
		passengerCar.board(PASSENGERCAR_SEAT_CAPACITY);
		assertTrue("Passenger on board is not full ",
				passengerCar.numberOfSeats() == passengerCar.numberOnBoard());
	}

	/**
	 * Added the following valid train configuration. It is expected
	 * to return false. In other words Train is overloaded and cannot
	 * move.
	 */
	@Test
	public void TrainOverLoaded() throws TrainException {
	DepartingTrain departingTrain = new DepartingTrain();
	departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
			VALID_CLASSIFICATION));
	departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
			PASSENGERCAR_SEAT_CAPACITY));
	departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
			PASSENGERCAR_SEAT_CAPACITY));
	departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
			PASSENGERCAR_SEAT_CAPACITY));
	departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
			PASSENGERCAR_SEAT_CAPACITY));
	departingTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT,
			VALID_GOODS_TYPE));
	
	assertFalse("Train is over loaded to move ", departingTrain.trainCanMove());
	}

	/**
	 * Passenger on board strictly greater than or equal to 
	 * seat capacity will return true, otherwise false
	 * 
	 */
	@Test 
	public void PassengerCanBoard() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);
		assertTrue("Too many passenger on board", NUMBER_OF_PASSENGER_TO_BOARD <=
				PASSENGERCAR_SEAT_CAPACITY);
	}

	/**
	 * Adding two locomotive is expected to throw TrainException as shown below
	 * 
	 * @throws TrainException
	 */

	@Test (expected = TrainException.class)
	public void MoreLocomotive() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

	}

	
	/**
	 * Check with negative number of people who wish to board the carriage
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void NumberOnBoardWithNegativePassenger() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
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
	public void AddingPassengerOnBoardWhichAlreadyHasPassenger()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
	}

	/**
	 * Test to add new Locomotive carriage to already existing. It is expected
	 * to throw TrainException message
	 * 
	 * @throws TrainException
	 */

	@Test(expected = TrainException.class)
	public void AddingLocomotiveToExistingLocomotiveIsDisallowed() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
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
	public void DepartingTraingCannotMoveIfTotalWeightExceedPullingPower()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(4 * VALID_GROSS_WEIGHT,
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
	public void NextCarriageWhenTrainHasNoCarriage() throws TrainException {
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
	public void NextCarriageWhenTrainHasOnlyLocomotive()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
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
	public void NextCarriageWhenTrainHasLocomotiveAndCarriages()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
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
	public void TraverseTrainUsingNextCarriage() throws TrainException {
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
	 * Test case for the Carriage which cannot be removed if there is already
	 * passenger on it. If attempted to remove then it is expected to throws
	 * TrainException
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void CannotRemoveNotNullCarriage()
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
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
	public void CannotRemoveNullCarriage() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		RollingStock firstCarriage = departingTrain.firstCarriage();
		assertTrue("There is no Rolling Stock in the train",
				firstCarriage == null);
	}

}
