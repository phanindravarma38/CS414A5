package cs414.a4.phanisag.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.Naming;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cs414.a4.phanisag.bo.intrface.AdminBOInterface;
import cs414.a4.phanisag.bo.intrface.AttendantBOInterface;
import cs414.a4.phanisag.controller.LostTicketActionListener;
import cs414.a4.phanisag.controller.OutRadioButtonActionListener;
import cs414.a4.phanisag.controller.PayButtonActionListener;
import cs414.a4.phanisag.utils.ComponentNames;
import cs414.a4.phanisag.utils.MyRMISecurityManager;

public class OutUserGUI extends JPanel {

	GridBagConstraints gbc = new GridBagConstraints();

	private static JLabel gateNumberLabel;
	
	public static int gateNumber;
	
	public static int numberOfAvailableSpaces;
	// JLabel

	private JLabel ticketNumber;
	private JLabel plateNumberLabel = new JLabel("Enter Plate No");

	public static JLabel numberofAvailableCars;

	private JLabel paymentDetailsLabel = new JLabel("PAYMENT DETAILS");

	JRadioButton outRadioButton = new JRadioButton("OUT");

	// JTextBox

	public static JTextField ticketNumberTextArea = new JTextField(10);
	private JTextField plateNumberTextArea = new JTextField(10);

	// JButton


	private JButton payButton = new JButton("Pay");

	private JButton lostTicketButton = new JButton("Lost Ticket ? ");

	static JFrame f;

	public static AdminBOInterface adminBo;
	public static AttendantBOInterface attendantBo;

	static {

		try {

			System.setSecurityManager(new MyRMISecurityManager());
			adminBo = (AdminBOInterface) Naming.lookup("rmi://localhost/admin");
			attendantBo = (AttendantBOInterface) Naming.lookup("rmi://localhost/attendant");

			numberOfAvailableSpaces = adminBo.getLotCapacity();
			numberofAvailableCars = new JLabel("Number of Available Lots = "
					+ numberOfAvailableSpaces);
			/*numberofAvailableCars = new JLabel("Number of Available Lots = "
					+ 50);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OutUserGUI() {
		/*
		 * AdminBO admin = new AdminBO(); numberOfAvailableCars =
		 * admin.getLotCapacity();
		 */
		initGUI();
		doTheLayout();
		addActionListeners();

		ticketNumber.setName(ComponentNames.TICKET_NUMBER_LABEL);
		plateNumberLabel.setName(ComponentNames.PLATE_NUMBER_LABEL);

		numberofAvailableCars
				.setName(ComponentNames.NUMBER_OF_AVAILABLE_CARS_LABEL);

		paymentDetailsLabel.setName(ComponentNames.PAYMENT_DETAILS_LABEL);


		outRadioButton.setName(ComponentNames.OUT_RADIO_BUTTON);
		lostTicketButton.setName(ComponentNames.LOST_TICKET_BUTTON);
		// JTextBox

		ticketNumberTextArea.setName(ComponentNames.TICKET_NUMBER_TEXT_AREA);
		plateNumberTextArea.setName(ComponentNames.PLATE_NUMBER_TEXT_AREA);

		// JButton


		payButton.setName(ComponentNames.PAY_BUTTON);

	} // end of constructor

	public static void dispose() {

		if (f != null)
			f.dispose();

	}

	private void initGUI() {
		gateNumber++;

		numberofAvailableCars.setFont(new Font("Serif", Font.BOLD, 20));

		outRadioButton.setFont(new Font("Serif", Font.BOLD, 24));

		ticketNumber = new JLabel("Enter Ticket No");

		gateNumberLabel = new JLabel("Gate Number: "+gateNumber);
		
		ButtonGroup group = new ButtonGroup();
		group.add(outRadioButton);


		disableAllFields();

	}// end of creating objects method

	private void disableAllFields() {

		ticketNumberTextArea.setEnabled(false);
		plateNumberTextArea.setEnabled(false);
		payButton.setEnabled(false);
	}

	private void doTheLayout() {

		try {

			GridBagLayout gbl = new GridBagLayout();
			setLayout(gbl);

			gbc.insets = new Insets(10, 10, 10, 10);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(gateNumberLabel, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.anchor = GridBagConstraints.EAST;
			add(numberofAvailableCars, gbc);

			gbc.gridx = 4;
			gbc.gridy = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(outRadioButton, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.anchor = GridBagConstraints.EAST;
			add(ticketNumber, gbc);

			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.anchor = GridBagConstraints.EAST;
			add(ticketNumberTextArea, gbc);

			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.anchor = GridBagConstraints.EAST;
			add(plateNumberLabel, gbc);

			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.anchor = GridBagConstraints.EAST;
			add(plateNumberTextArea, gbc);

			

			gbc.gridx = 1;
			gbc.gridy = 4;
			gbc.anchor = GridBagConstraints.CENTER;
			add(payButton, gbc);

			gbc.gridx = 1;
			gbc.gridy = 5;
			gbc.anchor = GridBagConstraints.CENTER;
			add(lostTicketButton, gbc);

		} catch (Exception e) {

		}

	}// end of Layout method

	public void addActionListeners() {
		payButton.addActionListener(new PayButtonActionListener());
		outRadioButton.addActionListener(new OutRadioButtonActionListener());
		lostTicketButton.addActionListener(new LostTicketActionListener());
	}

	public static void main(String[] args) {

		f = new JFrame("Parking System");
		Container contentPane = f.getContentPane();
		f.setResizable(false);
		contentPane.add(new OutUserGUI());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		f.setResizable(false);
		// f.setSize(400, 200);
		f.setVisible(true);

	}

	public static void updateUserGUI() {
		f.repaint();
		f.revalidate();
		f.getContentPane().repaint();
		f.getContentPane().revalidate();
	}

}
