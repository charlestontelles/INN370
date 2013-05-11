package asgn2Tests;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;


public class FreightCarTest {

	private static final int NEGATIVE_WEIGHT = -1;
	
	/**
	 * Try to create a new FreightCar using a negative gross weight.
	 * Expected a trainException.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testNewFreightCarWithNegativeWeight() throws TrainException{
		FreightCar freightCar = new FreightCar(NEGATIVE_WEIGHT, "A");
	}

}
