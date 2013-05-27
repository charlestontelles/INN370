package asgn2Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

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
 * @author  Phurpa Wangchuk(n8448060) (tester),
 * 			Charleston Telles(n8388342) (tests reviewer)
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
	public void testCanIterateThroughtCarriages() throws TrainException {
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
	 * Creates a departing train and adds a valid locomotive and a valid freight
	 * car. Then try to add a passenger after the freight car. Expected train
	 * exception.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testFreightBeforePassengers() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT,
				VALID_GOODS_TYPE));

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
	public void testTrainCannotMoveWithoutLocomotive() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue(
				"Can move should return true when there is no rolling stock yet",
				departingTrain.trainCanMove());
	}

	/**
	 * Create a new departing train and test that null train can move. Expect
	 * true.
	 */
	@Test
	public void testTrainConstructorNotNull() {
		DepartingTrain departingTrain = new DepartingTrain();
		assertTrue("Train instance is null", departingTrain.trainCanMove());
	}

	/**
	 * Create instance of departingTrain and check the initial seats capacity of
	 * the car to to return zero.
	 * 
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
	 */
	@Test
	public void testNumberOfSeatsCountedCorrectly() throws TrainException {
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
	public void testTrainExactlyFull() throws TrainException {
		PassengerCar passengerCar = new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY);
		passengerCar.board(PASSENGERCAR_SEAT_CAPACITY);
		assertTrue("Passenger on board is not full ",
				passengerCar.numberOfSeats() == passengerCar.numberOnBoard());
	}

	/**
	 * Added the following valid train configuration. It is expected to return
	 * false. In other words Train is overloaded and cannot move.
	 */
	@Test
	public void testTrainOverLoaded() throws TrainException {
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

		assertFalse("Train is over loaded to move ",
				departingTrain.trainCanMove());
	}

	/**
	 * Passenger on board strictly greater than or equal to seat capacity will
	 * return true, otherwise false
	 * 
	 */
	@Test
	public void testPassengerCanBoard() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);
		assertTrue("Too many passenger on board",
				NUMBER_OF_PASSENGER_TO_BOARD <= PASSENGERCAR_SEAT_CAPACITY);
	}

	/**
	 * Adding two locomotive is expected to throw TrainException as shown below
	 * 
	 * @throws TrainException
	 */

	@Test(expected = TrainException.class)
	public void testMoreLocomotive() throws TrainException {
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
	@Test(expected = TrainException.class)
	public void testNumberOnBoardWithNegativePassenger() throws TrainException {
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
	public void testAddingPassengerOnBoardWhichAlreadyHasPassenger()
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
	public void testAddingLocomotiveToExistingLocomotiveIsDisallowed()
			throws TrainException {
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
	public void testDepartingTraingCannotMoveIfTotalWeightExceedPullingPower()
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
	public void testNextCarriageWhenTrainHasLocomotiveAndCarriages()
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
	public void testTraverseTrainUsingNextCarriage() throws TrainException {
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
	public void testCannotRemoveNotNullCarriage() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD);

		departingTrain.removeCarriage();
	}

	/**
	 * Creates a departing train and try to add a passenger car before adding a
	 * locomotive. Expected train exception
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testNotAddingLocomotiveFirst() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
	}

	/**
	 * Creates a departing train and adds 2 passenger cars. Then try to remove 4
	 * carriages Expected train exception
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testRemovingMoreCarriagesThanExistents() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				PASSENGERCAR_SEAT_CAPACITY));
		departingTrain.removeCarriage();
		departingTrain.removeCarriage();
		departingTrain.removeCarriage();
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
	public void testCannotRemoveNullCarriage() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		RollingStock firstCarriage = departingTrain.firstCarriage();
		assertTrue("There is no Rolling Stock in the train",
				firstCarriage == null);
	}

	/**
	 * Uses class reflection to test the number of constructors implemented.
	 * Expected exactly one constructor
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfConstructors() throws TrainException {
		Constructor<?>[] constructors = DepartingTrain.class.getConstructors();
		assertTrue("more than one constructor found", constructors.length == 1);
	}

	/**
	 * Creates a new departing train, add a valid locomotive, three valid
	 * passenger car and one valid freight car. Then board 20 passengers.
	 * Traverse the train unboarding all passengers, then traverse the train
	 * again removing all carriage. In the end the departing train must be
	 * empty.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testResetDepartingTrain() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		int countCarriage = 0;

		departingTrain.addCarriage(new Locomotive(VALID_GROSS_WEIGHT,
				VALID_CLASSIFICATION));

		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				NUMBER_OF_PASSENGER_TO_BOARD));
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				NUMBER_OF_PASSENGER_TO_BOARD));
		departingTrain.addCarriage(new PassengerCar(VALID_GROSS_WEIGHT,
				NUMBER_OF_PASSENGER_TO_BOARD));

		departingTrain.addCarriage(new FreightCar(VALID_GROSS_WEIGHT,
				VALID_GOODS_TYPE));

		departingTrain.board(NUMBER_OF_PASSENGER_TO_BOARD * 2);

		RollingStock rs = departingTrain.firstCarriage();
		while (rs != null) {
			countCarriage++;
			if (rs instanceof PassengerCar)
				((PassengerCar) rs).alight(((PassengerCar) rs).numberOnBoard());
			rs = departingTrain.nextCarriage();
		}
		while (countCarriage-- > 0) {
			departingTrain.removeCarriage();
		}

		assertTrue("should not have a locomotive",
				departingTrain.firstCarriage() == null);
	}

}
