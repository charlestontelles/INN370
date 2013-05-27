package asgn2Exceptions;

/**
 * A simple class for exceptions thrown by railway shunting and boarding
 * operations.
 * 
 * @author  Phurpa Wangchuk(n8448060) (initial version), 
 * 			Charleston Telles(n8388342) (reviewer)
 * 
 */
public class TrainException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8463596790935842790L;

	/**
	 * Constructs a new TrainException object
	 * 
	 * @param message
	 *            an informative message describing the cause of the problem
	 */
	public TrainException(String message) {
		super(message);
	}
}
