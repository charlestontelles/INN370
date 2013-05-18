package asgn2Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 * @author 
 *
 */
public class PanelsAndWidgets extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 250;


	private JPanel pnlDisplay;
	private JPanel pnlTop;
	private JPanel pnlBottom;
	private JPanel pnlRight;
	private JPanel pnlLeft;

	private JButton btnLoad;
	private JButton btnUnload;
	private JButton btnFind;
	private JButton btnSwitch;

	private JRadioButton locomotive;
	private JRadioButton passengerCar;
	private JRadioButton freidgtCar;
	
	private JTextArea areDisplay; 
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public PanelsAndWidgets(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		pnlDisplay 	= createPanel(Color.WHITE);
		pnlTop 		= createPanel(Color.LIGHT_GRAY);
		pnlBottom 	= createPanel(Color.LIGHT_GRAY);
		pnlRight 	= createPanel(Color.LIGHT_GRAY);
		pnlLeft 	= createPanel(Color.LIGHT_GRAY);

		btnLoad 	= createButton("Load");
		btnUnload 	= createButton("Unload");
		btnFind 	= createButton("Find ");
		btnSwitch 	= createButton("Switch");
		
		locomotive 	= createCarriageOption("Locomotive");
		passengerCar= createCarriageOption("Passenger Car");
		freidgtCar = createCarriageOption("FreidgtCar Car");

		layoutButtonPanel(); 
		layoutRadioButtonPanel();
		areDisplay = createTextArea();

		pnlDisplay.setLayout(new BorderLayout());
		pnlDisplay.add(areDisplay,BorderLayout.CENTER);

		this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
		this.getContentPane().add(pnlTop,BorderLayout.NORTH);
		this.getContentPane().add(pnlBottom,BorderLayout.SOUTH);
		this.getContentPane().add(pnlRight,BorderLayout.EAST);
		this.getContentPane().add(pnlLeft,BorderLayout.WEST);

		
		repaint(); 
	}

	private void layoutRadioButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlTop.setLayout(layout);
		
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints(); 

		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;

		// x and y position of grid
		// w and h of the grid
		addToPanel(pnlTop, locomotive,constraints,  0,0,2,1); 
		addToPanel(pnlTop, passengerCar,constraints,3,0,2,1);
		addToPanel(pnlTop, freidgtCar,constraints,6,0,2,1);
		
				
	}

	private JRadioButton createCarriageOption(String string) {
		JRadioButton jrb = new JRadioButton(string);
		jrb.addActionListener(this);		
		return jrb;
	}

	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c); 
		return jp;
	}

	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}

	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,24));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}

	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlBottom.setLayout(layout);

		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints(); 

		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;

		// x and y position of grid
		// w and h of the grid
		addToPanel(pnlBottom, btnLoad,constraints,  0,0,2,1); 
		addToPanel(pnlBottom, btnUnload,constraints,3,0,2,1); 
		addToPanel(pnlBottom, btnFind,constraints,  0,2,2,1); 
		addToPanel(pnlBottom, btnSwitch,constraints,3,2,2,1); 	
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		//Get event source 
		Object src=event.getSource(); 
		 

		//Consider the alternatives - not all active at once. 
		if (src == btnLoad) {
			JButton btn = (JButton) src;
			areDisplay.setText(btn.getText().trim());
			areDisplay.setBackground(Color.BLUE);
		} else if (src==btnUnload) {
			JButton btn = (JButton) src;
			areDisplay.setText(btn.getText().trim());
			areDisplay.setBackground(Color.RED);
		} else if (src==btnSwitch) {
			JButton btn = (JButton) src;
			areDisplay.setText(btn.getText().trim());						
			areDisplay.setBackground(Color.GREEN);
		} else if (src==btnFind) {
			JButton btn = (JButton) src;
			areDisplay.setText(btn.getText().trim());						
			areDisplay.setBackground(Color.GRAY);
		}
		else if (src==locomotive) {
			JRadioButton jrb = (JRadioButton) src;
			areDisplay.setText(jrb.getText().trim());						
			areDisplay.setBackground(Color.ORANGE);
		}
		else if (src==passengerCar) {
			JRadioButton jrb = (JRadioButton) src;
			areDisplay.setText(jrb.getText().trim());						
			areDisplay.setBackground(Color.CYAN);
		}else if (src==freidgtCar) {
			JRadioButton jrb = (JRadioButton) src;
			areDisplay.setText(jrb.getText().trim());						
			areDisplay.setBackground(Color.MAGENTA);
		}else{
			areDisplay.setText("Error event");
		}
		
	}

	/**
	 * 
	 * A convenience method to add a component to given grid bag
	 * layout locations. 
	 *
	 * @param c the component to add
	 * @param constraints the grid bag constraints to use
	 * @param x the x grid position
	 * @param y the y grid position
	 * @param w the grid width
	 * @param h the grid height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PanelsAndWidgets gui = new PanelsAndWidgets("PanelsAndWidgets");
		gui.setVisible(true);
	}

}

