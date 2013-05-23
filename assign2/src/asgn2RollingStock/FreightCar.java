package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Freight cars are designed to handle a variety of goods. For the purposes of
 * this assignment we assume there are three freight car types of interest,
 * characterised by the kinds of goods they are designed to carry:
 * 
 * "G" - General goods "R" - Refrigerated goods "D" - Dangerous materials
 * 
 * 
 * @author Charleston
 * 
 */
public class FreightCar extends RollingStock {

	private String goodsType;
	
	/**
	 * Error message to be used by Train Exceptions.
	 */
	private static final String INVALID_GOODS_TYPE = "Invalid Goods Type, expected G/R/D";
	

	/**
	 * Constructs a freight car object.
	 * 
	 * @param grossWeight
	 *            the freight car's gross weight (fully-laden), in tonnes
	 * 
	 * @param goodsType
	 *            the type of goods the car is designed to carry (either "G",
	 *            "R" or "D")
	 * 
	 * @throws TrainException
	 *             if the gross weight is not positive or if the goods' type is
	 *             invalid
	 */
	public FreightCar(Integer grossWeight, String goodsType)
			throws TrainException {
		super(grossWeight);
		if (!goodsType.equals("G") && !goodsType.equals("R")
				&& !goodsType.equals("D"))
			throw new TrainException(INVALID_GOODS_TYPE);

		this.goodsType = goodsType;
	}

	/**
	 * Returns a human-readable description of the freight car. This has the
	 * form "Freight(x)" where x is a character ("G", "R" or "D") indicating the
	 * type of goods the car is designed to carry.
	 * 
	 * @return a human-readable description of the freight car
	 */
	@Override
	public String toString() {
		return "Freight("+this.goodsType+ ")";
	}

	/**
	 * Returns the type of goods this carriage was designed to carry.
	 * 
	 * @return the goodsType (G", "R" or "D")
	 */
	public String goodsType() {
		return this.goodsType;
	}

}
