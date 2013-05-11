package asgn2Tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import asgn2Train.DepartingTrain;

public class DepartingTrainTest {
	/**
	 * Create a new departing train and do not add a locomotive, then
	 * test if train can move without locomotive. Expected false.
	 */
	@Test
	public void testTrainCannotMoveWithoutLocomotive(){
		DepartingTrain departingTrain = new DepartingTrain();
		Assert.assertFalse("train cannot move without locomotive", departingTrain.trainCanMove());
	}
	
	@Test
	public void testTrainConstructorNotNull(){
		DepartingTrain departingTrain = new DepartingTrain();
		Assert.assertTrue("train instance is null", departingTrain != null);
	}
}
