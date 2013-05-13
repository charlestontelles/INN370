package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;


public class FreightCarTest {

	private static final int NEGATIVE_GROSS_WEIGHT = -1;
	private static final int VALID_GROSS_WEIGHT = 50;
	private static final String VALID_GOOD_TYPE = "G";
	private static final String INVALID_GOOD_TYPE = "A";
	private static final Integer ZERO_GROSS_WEIGHT = 0;
	
	
	/**
	 * Create a new FreightCar using a negative gross weight.
	 * Expected to throw trainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testFreightCarWithNegativeWeight() throws TrainException{
		new FreightCar(NEGATIVE_GROSS_WEIGHT, VALID_GOOD_TYPE);
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
	public void testFreightCaWithInvalidGoodType() throws TrainException{
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
	public void testToStringHasOverride() throws TrainException {
		FreightCar freightCar = new FreightCar(VALID_GROSS_WEIGHT , VALID_GOOD_TYPE);
		assertTrue("Method toString() has not been overrided ",freightCar.toString().contains(VALID_GOOD_TYPE));
	}
}
