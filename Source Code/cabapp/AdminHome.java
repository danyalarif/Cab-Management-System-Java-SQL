package cabapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class AdminHome extends JFrame implements MouseListener, ActionListener {

	private JPanel contentPane, contentPane2;

	public AdminHome() {
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane2 = new JPanel();
		contentPane2.setForeground(Color.WHITE);
		contentPane2.setBackground(Color.BLACK);
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		setContentPane(contentPane2);
		JLabel lblNewLabel = new JLabel("ADMIN PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane2.add(lblNewLabel, "cell 1 13,alignx center,aligny center");
		JButton button1 = new JButton("View Reports");
		button1.setForeground(Color.WHITE);
		button1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		button1.setFocusable(false);
		button1.setBackground(Color.RED);
		button1.addActionListener(this);
		contentPane2.add(button1, "cell 22 44,alignx center,aligny center");
		
		JButton button2 = new JButton("Delete Accounts");
		button2.setForeground(Color.WHITE);
		button2.setFont(new Font("Tahoma", Font.PLAIN, 29));
		button2.setFocusable(false);
		button2.setBackground(Color.RED);
		button2.addActionListener(this);
		contentPane2.add(button2, "cell 22 60,alignx center,aligny center");
	}
	public void reportsScreen()
	{
		contentPane2.setVisible(false);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		setContentPane(contentPane);
		
		JLabel label1 = new JLabel("<HTML><U>Customers</U></HTML>");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label1.setForeground(Color.WHITE);
		contentPane.add(label1, "cell 50 8,alignx center,aligny center");
		label1.addMouseListener(this);
		label1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lbldrivers = new JLabel("<HTML><U>Drivers</U></HTML>");
		lbldrivers.setForeground(Color.WHITE);
		lbldrivers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldrivers.addMouseListener(this);
		lbldrivers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lbldrivers, "cell 50 14,alignx center,aligny center");
		
		JLabel lblrides = new JLabel("<HTML><U>Rides</U></HTML>");
		lblrides.setForeground(Color.WHITE);
		lblrides.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblrides.addMouseListener(this);
		lblrides.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblrides, "cell 50 20,alignx center,aligny center");
		
		JLabel lbltips = new JLabel("<HTML><U>Tips</U></HTML>");
		lbltips.setForeground(Color.WHITE);
		lbltips.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltips.addMouseListener(this);
		lbltips.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lbltips, "cell 50 26,alignx center,aligny center");
		
		JLabel lblreviews = new JLabel("<HTML><U>Reviews</U></HTML>");
		lblreviews.setForeground(Color.WHITE);
		lblreviews.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblreviews.addMouseListener(this);
		lblreviews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblreviews, "cell 50 32,alignx center,aligny center");
		
		JLabel lbldiscounts = new JLabel("<HTML><U>Discounts</U></HTML>");
		lbldiscounts.setForeground(Color.WHITE);
		lbldiscounts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldiscounts.addMouseListener(this);
		lbldiscounts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lbldiscounts, "cell 50 38,alignx center,aligny center");
		
		JLabel lblhighestPaymentOf = new JLabel("<HTML><U>Highest Payments By Customers</U></HTML>");
		lblhighestPaymentOf.setForeground(Color.WHITE);
		lblhighestPaymentOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblhighestPaymentOf.addMouseListener(this);
		lblhighestPaymentOf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblhighestPaymentOf, "cell 50 44,alignx center,aligny center");
		
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBackground(Color.RED);
		btnGoBack.addActionListener(this);
		contentPane.add(btnGoBack, "cell 50 76,alignx center,aligny center");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		String title = ((JLabel)e.getSource()).getText().toString();
		String query, head;
		query = head = null;
		if (title.equals("<HTML><U>Drivers</U></HTML>"))
		{
			query = "Select * From Person natural join Driver";
			head = "Drivers";
		}
		else if (title.equals("<HTML><U>Customers</U></HTML>"))
		{
			query = "Select * From Person natural join Customer";
			head = "Customers";
		}
		else if (title.equals("<HTML><U>Rides</U></HTML>"))
		{
			query = "select distinct customerName, driverName, amount, createdDate, completedDate from\r\n" + 
					"(select *\r\n" + 
					"from (select personid as customerid, username as customerName from Person) Person1 \r\n" + 
					"natural join customer join booking on Person1.customerid = Booking.customerid \r\n" + 
					"join \r\n" + 
					"(select personid as driverid, username as driverName from person natural join driver) tempDriver\r\n" + 
					"on tempDriver.driverid = Booking.driverid\r\n" + 
					"natural join Payment natural join Ride\r\n" + 
					"join Location on Location.locationid = sourceid\r\n" + 
					"join (select locationid as lid, city as destinationCity from Location) Location2 on lid = destinationid)";
			head = "Rides";
		}
		else if (title.equals("<HTML><U>Tips</U></HTML>"))
		{
			query = "select customerName as tippedBy, driverName as tippedTo, tipdate, amount from\r\n" + 
					"(select *\r\n" + 
					"from Tip natural join (select personid as customerid, username as customerName from Person) Person1  \r\n" + 
					"natural join\r\n" + 
					"(select personid as driverid, username as driverName from person natural join driver) tempDriver)";
			head = "Tips";
		}
		else if (title.equals("<HTML><U>Reviews</U></HTML>"))
		{
			query = "select customerName as reviewer, stars, comments, reviewdate from\r\n" + 
					"(select * from Review natural join (select personid as customerid, username as customerName from Person))";
			head = "Reviews";
		}
		else if (title.equals("<HTML><U>Discounts</U></HTML>"))
		{
			query = "select customerName, amount, percentage, givendate from\r\n" + 
					"(select * from Discount natural join (select personid as customerid, username as customerName from Person))";
			head = "Discounts";
		}
		else if (title.equals("<HTML><U>Highest Payments By Customers</U></HTML>"))
		{
			query = "select username, max(amount) as highestPayment from person join Payment on Person.personid = payment.customer_personid group by username";
			head = "Highest Payments By Customers";
		}
		if (head != null && query != null)
		{
			setVisible(false);
			DisplayPeople p = new DisplayPeople();
			p.setVisible(true);
			p.insertIntoTable(query, head);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JLabel)
		{
			
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt = e.getActionCommand();
		if (txt.equals("View Reports"))
		{
			reportsScreen();
		}
		if (txt.equals("Delete Accounts"))
		{
			setVisible(false);
			RemoveAccount r = new RemoveAccount();
			r.setVisible(true);
		}
		if (txt.equals("Go Back"))
		{
			setVisible(false);
			AdminHome r = new AdminHome();
			r.setVisible(true);
		}
	}

	
}
