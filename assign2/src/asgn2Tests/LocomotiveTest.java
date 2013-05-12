/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;

/**
 * @author Phurpa
 *
 */
public class LocomotiveTest {

	private static final String VALID_CLASSIFICATION = "4D";
	private static final String INVALID_CLASSIFICATION = "ABC";
	private static final Integer NEGATIVE_GROSSWEIGHT = -1;
	private static final Integer ZERO_GROSSWEIGHT = 0;


	/**
	 * Create a new Locomotive instance with negative gross weight.
	 * Expected to throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorWithNegativeGrossWeight() throws TrainException{
		new Locomotive(NEGATIVE_GROSSWEIGHT, VALID_CLASSIFICATION);
	}

	/**
	 * Create a new Locomotive instance with zero gross weight.
	 * Expected to throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveConstructorWithZeroGrossWeight() throws TrainException {
		new Locomotive(ZERO_GROSSWEIGHT, VALID_CLASSIFICATION);
	}
	
	/**
	 * Create valid Locomotive instance with valid power engine, 4D. Then
	 * expected to return power pulling of 400.
	 * 
	 * @throws TrainException
	 */	
	@Test
	public void testLocomotivePowerEngine() throws TrainException {
		Locomotive locomotive = new Locomotive(100, VALID_CLASSIFICATION);
		assertTrue("Invalid pulling power",locomotive.power() == 400);
	}
	
	
	/** Create instance of Locomotive with invalid classification with
	 * three character, For eg. "ABC".It is expected to throw TrainException
	 * 
	 * @throws TrainException 
	 * 
	 */
	@Test(expected = TrainException.class)
	public void testLocomotiveWithInvalidClassification() throws TrainException{
        new Locomotive(100, INVALID_CLASSIFICATION);
	}
}
