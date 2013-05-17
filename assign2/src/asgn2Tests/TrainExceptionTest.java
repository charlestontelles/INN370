package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;

/**
 * Test cases for the TrainException class.
 * 
 * @author Charleston
 *
 */
public class TrainExceptionTest {
	
	/**
	 * Messages to be used by constructors.
	 */
	private static final String TRAIN_EXCEPTION_MESSAGE = "Train Exception Message";

	/**
	 * Creates a new train exception using a specific message as parameter.
	 * Then checks the message has been set properly after creation.
	 * 
	 */
	@Test
	public void testCreateNewTrainException() {
		TrainException trainException = new TrainException(
				TRAIN_EXCEPTION_MESSAGE);
		assertTrue(
				"TrainException has not been created properly",
				trainException.getMessage().equalsIgnoreCase(
						TRAIN_EXCEPTION_MESSAGE));
	}
	
	/**
	 * Creates a new train exception using a specific message as parameter.
	 * Then check the object is a instance of Exception.
	 * 
	 */
	@Test
	public void testTrainExceptionExtendsException() {
		TrainException trainException = new TrainException(
				TRAIN_EXCEPTION_MESSAGE);
		assertTrue(
				"TrainException has not been created properly",
				trainException instanceof Exception);
	}
}
