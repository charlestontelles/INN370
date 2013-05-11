package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;


public class FreightCarTest {

	private static final int NEGATIVE_GROSS_WEIGHT = -1;
	private static final int VALID_GROSS_WEIGHT = 50;
	private static final String VALID_GOOD_TYPE = "G";
	private static final String INVALID_GOOD_TYPE = "A";
	private static final Integer ZERO_GROSS_WEIGHT = 0;
	
	
	/**
	 * Create a new FreightCar using a negative gross weight.
	 * Expected a trainException.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testNewFreightCarWithNegativeWeight() throws TrainException{
		FreightCar freightCar = new FreightCar(NEGATIVE_GROSS_WEIGHT, VALID_GOOD_TYPE);
	}

	
	/**
	 * Create a new FreightCar with a zero gross weight.
	 * Expected to return True.
	 * 
	 * @throws TrainException
	 */
	@Test 
	public void testFreightCarWithZeroWeight() throws TrainException {
		FreightCar freightCar = new FreightCar(ZERO_GROSS_WEIGHT, VALID_GOOD_TYPE);
	    assertTrue("Invalid gross Weight", freightCar.getGrossWeight() == ZERO_GROSS_WEIGHT);
	}
	
	/**
	 * Create new FreightCar with valid gross weight and valid 
	 * goodtype
	 * Expected  to return true 
	 * 
	 *@throws TrainException
	 */
	
	@Test
	public void testFreightCarWithInvalidGoodType() throws TrainException{
		FreightCar freightCar = new FreightCar(VALID_GROSS_WEIGHT , VALID_GOOD_TYPE);
	    assertTrue("Invalid good type", freightCar.goodsType() == VALID_GOOD_TYPE);
	}
}
