package asgn2GUI;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

/**
 * Graphical user interface represents a locomotive.
 * Uses a typical canvas drawing to add and resize images providing
 * a good train representation.
 * 
 * @author Charleston Telles
 * 
 */
public class AssembledTrainPanel extends JPanel {

	/**
	 * Mandatory class ID to be used by serialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Images to represent a departing train
	 */
	private java.awt.Image locomotiveImage;
	private java.awt.Image freightImage;
	private java.awt.Image passengerImage;
	/**
	 * A reference object representing a departing train
	 */
	private DepartingTrain departingTrain;

	/**
	 * 
	 * @param departingTrain a reference object representing a departing train
	 * 
	 * @param actionListener a external listener added to local buttons as observer
	 */
	public AssembledTrainPanel(DepartingTrain departingTrain,
			ActionListener actionListener) {

		this.departingTrain = departingTrain;
		initComponents();
		
	}

	/**
	 * Draw a departing train using carriages images.
	 * 
	 * The images positioning has to be calculate in detail to guarantee
	 * a proper visualisation.
	 * 
	 */
	protected void paintComponent(java.awt.Graphics g) {
		int imagesPerLine = 6;
		int i = 0;
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
		java.awt.Font font = new java.awt.Font("Arial", 1, 11);
		g2.setFont(font);
		RollingStock nextCarriage = departingTrain != null ? departingTrain
				.firstCarriage() : null;
		while (nextCarriage != null) {
			if (nextCarriage instanceof Locomotive) {
				g.drawImage(this.locomotiveImage, (i % imagesPerLine)
						* locomotiveImage.getWidth(null), (i / imagesPerLine)
						* locomotiveImage.getHeight(null), null);
				g.drawString(
						nextCarriage.toString(),
						((i % imagesPerLine) * locomotiveImage.getWidth(null)) + 75,
						((i / imagesPerLine) * locomotiveImage.getHeight(null)) + 40);
			} else if (nextCarriage instanceof FreightCar) {
				g.drawImage(this.freightImage, (i % imagesPerLine)
						* freightImage.getWidth(null), (i / imagesPerLine)
						* freightImage.getHeight(null), null);
				g.drawString(
						nextCarriage.toString(),
						((i % imagesPerLine) * freightImage.getWidth(null)) + 72,
						((i / imagesPerLine) * freightImage.getHeight(null)) + 30);
			} else if (nextCarriage instanceof PassengerCar) {
				g.drawImage(this.passengerImage, (i % imagesPerLine)
						* passengerImage.getWidth(null), (i / imagesPerLine)
						* passengerImage.getHeight(null), null);
				g.drawString(
						nextCarriage.toString(),
						((i % imagesPerLine) * passengerImage.getWidth(null)) + 42,
						((i / imagesPerLine) * passengerImage.getHeight(null)) + 28);
			}
			nextCarriage = departingTrain.nextCarriage();
			i++;
			
		}
	}

	/**
	 * Initiliases components to be used by this panel.
	 */
	public void initComponents() {
		java.net.URL url = ClassLoader.getSystemResource("./asgn2GUI/Images/locomotive.png");
		this.locomotiveImage = new javax.swing.ImageIcon(url).getImage();

		url = ClassLoader.getSystemResource("./asgn2GUI/Images/freightcar.png");
		this.freightImage = new javax.swing.ImageIcon(url).getImage();

		url = ClassLoader.getSystemResource("./asgn2GUI/Images/passengercar.png");
		this.passengerImage = new javax.swing.ImageIcon(url).getImage();
	}
}
