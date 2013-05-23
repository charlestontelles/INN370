package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which a train is constructed.
 * This abstract class defines characteristics which they all share, most
 * notably having a known gross weight, measured here in tonnes. (There are, of
 * course many other important shared characteristics of railway carriages, such
 * as identifying codes, a certain number of wheels, the track gauge they're
 * designed for, etc, but we don't need these for this assignment.)
 * 
 * @author Charleston
 * 
 */
public abstract class RollingStock {

	private Integer grossWeight;
	
	/**
	 * Error message to be used by Train Exceptions.
	 */
	protected final static String GROSS_WEIGTH_INVALID = "Gross weight must be positive";


	/**
	 * Constructs a railway carriage with a specific gross weight (i.e., the
	 * carriage's weight when fully laden). We assume that this weight does not
	 * change once shunting operations have begun. (Freight carriages are
	 * assumed to arrive at the marshalling yard already loaded, and we consider
	 * the weight of passengers to be negligible compared to the weight of the
	 * carriage itself.)
	 * 
	 * We require a railway carriage to have at least some weight, but put no
	 * arbitrary upper limit on its weight. (In practice, though, locomotives
	 * normally weigh around 90 to 180 tonnes, passenger carriages weigh around
	 * 50 to 100 tonnes and laden freight cars weigh around 40 to 90 tonnes.)
	 * 
	 * @param grossWeight
	 *            the carriage's gross weight in tonnes
	 * 
	 * @throws TrainException
	 *             - if the gross weight is not positive
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight <= 0)
			throw new TrainException(GROSS_WEIGTH_INVALID);
		this.grossWeight = grossWeight;
	}

	/**
	 * Returns the railway carriage's gross weight.
	 * 
	 * @return the carriage's gross weight, in tonnes
	 */
	public Integer getGrossWeight() {
		return grossWeight;
	}

	/**
	 * Returns a human-readable description of this railway carriage.
	 * 
	 * In the context of the assignment, this method provides you with a textual
	 * description of the carriage which you can use to display the current
	 * train configuration in your user interface.
	 * 
	 * (Note for Javaphiles: The root Object class already provides a default
	 * toString method for all classes, so the purpose of this abstract method
	 * is to override the default one and force us to implement a toString
	 * method in all subclasses of RollingStock.)
	 * 
	 * @return a printable description of the rolling stock
	 */
	public abstract String toString();

}
