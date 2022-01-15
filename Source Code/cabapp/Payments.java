package cabapp;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextArea;

public class Payments extends JFrame implements ActionListener{
	private Customer customer;
	private Driver driver;
	private JPanel contentPane, contentPane1, contentPane2;
	private JTextField textField, textField2;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem i1, i2, i3, i4, i5, i6, i7;
	private JButton button1, button2;
	private JButton button3;
	private JComboBox comboBox;
	private JLabel label2;
	private JLabel label4;
	private JLabel label5;
	private String[] descriptions;
	private double[] prices;
	private JLabel labelHead;
	private JLabel ans;
	private ArrayList<Booking> bookings;
	private JLabel type;
	private JButton buttondone;
	private JLabel lblPaymentMethod;
	private JComboBox comboBox2;
	private JLabel lblCharges;
	private JLabel label6;
	private JLabel lblDrivingRating;
	private JComboBox comboBox3;
	private JLabel lblDrivingRating_1;
	private JTextArea textArea;

	public Payments(Person p) {
		if (p instanceof Customer)
		{
			customer = (Customer)p;
			driver = null;
		}
		else
		{
			driver = (Driver)p;
			customer = null;
		}
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		i1 = new JMenuItem("Home");
		i2 = new JMenuItem("Profile");
		i3 = new JMenuItem("Bookings");
		i4 = new JMenuItem("Payments");
		i5 = new JMenuItem("Requests");
		i6 = new JMenuItem("Logout");
		i7 = new JMenuItem("Messages");
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.add(i4);
		menu.add(i5);
		menu.add(i6);
		menu.add(i7);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		i4.addActionListener(this);
		i5.addActionListener(this);
		i6.addActionListener(this);
		i7.addActionListener(this);
		if (driver == null)
			i5.setVisible(false);
		screen0();
	}
	public void screen0()
	{
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		type = new JLabel("New label");
		type.setOpaque(true);
		type.setForeground(Color.WHITE);
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setBackground(Color.RED);
		contentPane.add(type, "cell 3 8");
		if (customer == null)
		{
			type.setText("Driver Account");
		}
		else
		{
			type.setText("Customer Account");
		}
		labelHead = new JLabel("Balance:");
		labelHead.setForeground(Color.WHITE);
		labelHead.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		contentPane.add(labelHead, "cell 65 8,alignx center,aligny center");
		
		ans = new JLabel("New label");
		ans.setOpaque(true);
		ans.setForeground(Color.RED);
		ans.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ans.setBackground(new Color(250, 218, 221));
		contentPane.add(ans, "cell 69 8,alignx center,aligny center");
		if (customer == null)
		{
			ans.setText(Double.toString(driver.getBalance()) + " dollars");
		}
		else
		{
			ans.setText(Double.toString(customer.getBalance()) + " dollars");
		}
		button1 = new JButton("Deposit");
		button1.setForeground(new Color(255, 255, 255));
		button1.setBackground(new Color(255, 0, 0));
		button1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(button1, "cell 43 34,alignx center,aligny center");
		button1.addActionListener(this);
		
		button2 = new JButton("Pay Driver");
		button2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button2.setBackground(new Color(255, 0, 0));
		button2.setForeground(new Color(255, 255, 255));
		contentPane.add(button2, "cell 43 41,alignx center,aligny center");
		button2.addActionListener(this);
		
		button3 = new JButton("Withdraw");
		button3.setBackground(new Color(255, 0, 0));
		button3.setForeground(new Color(255, 255, 255));
		button3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(button3, "cell 43 48,alignx center,aligny center");
		button3.addActionListener(this);
		if (customer == null)
		{
			button1.setVisible(false);
			button2.setVisible(false);
		}
	}
	public void screen1()		//Deposit and withdraw
	{
		contentPane.setVisible(false);
		contentPane1 = new JPanel();
		contentPane1.setBackground(new Color(0, 0, 0));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		JLabel label1 = new JLabel("Amount:");
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		contentPane1.add(label1, "cell 19 28,aligny center");
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 218, 221));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane1.add(textField, "cell 22 28,aligny center");
		textField.setColumns(10);
		
	    buttondone = new JButton("Done");
		buttondone.setBackground(new Color(255, 0, 0));
		buttondone.setForeground(new Color(255, 255, 255));
		buttondone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane1.add(buttondone, "cell 22 38,alignx center,aligny center");
		buttondone.addActionListener(this);
		
		labelHead = new JLabel("Balance:");
		labelHead.setForeground(Color.WHITE);
		labelHead.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		contentPane1.add(labelHead, "cell 64 8,alignx center,aligny center");
		
		ans = new JLabel("New label");
		ans.setOpaque(true);
		ans.setForeground(Color.RED);
		ans.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ans.setBackground(new Color(250, 218, 221));
		contentPane1.add(ans, "cell 68 8,alignx center,aligny center");
		if (customer == null)
		{
			ans.setText(Double.toString(driver.getBalance()) + " dollars");
		}
		else
		{
			ans.setText(Double.toString(customer.getBalance()) + " dollars");
		}
		
		type = new JLabel("New label");
		type.setOpaque(true);
		type.setForeground(Color.WHITE);
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setBackground(Color.RED);
		contentPane1.add(type, "cell 3 8");
		if (customer == null)
		{
			type.setText("Driver Account");
		}
		else
		{
			type.setText("Customer Account");
		}
	}
	public void screen2()		
	{
		contentPane.setVisible(false);
		contentPane2 = new JPanel();
		contentPane2.setBackground(new Color(0, 0, 0));
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		label5 = new JLabel("None");
		label5.setForeground(new Color(255, 255, 255));
		label5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane2.add(label5, "cell 24 43,alignx center,aligny center");
		
		label2 = new JLabel("Select Driver:");
		label2.setBackground(new Color(255, 0, 0));
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label2.setOpaque(true);
		contentPane2.add(label2, "cell 13 33");
		
		label4 = new JLabel("Booking Description:");
		label4.setBackground(new Color(255, 0, 0));
		label4.setForeground(new Color(255, 255, 255));
		label4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label4.setOpaque(true);
		contentPane2.add(label4, "cell 13 43");
		
		lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setOpaque(true);
		lblPaymentMethod.setForeground(Color.WHITE);
		lblPaymentMethod.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPaymentMethod.setBackground(Color.RED);
		contentPane2.add(lblPaymentMethod, "cell 13 53");
		
		String[] methods = {"Credit Card", "Cash", "Bank Transfer"};
		
		comboBox2 = new JComboBox(methods);
		comboBox2.setOpaque(true);
		comboBox2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		comboBox2.setBackground(new Color(255, 140, 0));
		contentPane2.add(comboBox2, "cell 24 53,alignx center,aligny center");
		
		labelHead = new JLabel("Balance:");
		labelHead.setForeground(Color.WHITE);
		labelHead.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		contentPane2.add(labelHead, "cell 64 8,alignx center,aligny center");
		
		ans = new JLabel("New label");
		ans.setOpaque(true);
		ans.setForeground(Color.RED);
		ans.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ans.setBackground(new Color(250, 218, 221));
		contentPane2.add(ans, "cell 68 8,alignx center,aligny center");
		if (customer == null)
		{
			ans.setText(Double.toString(driver.getBalance()) + " dollars");
		}
		else
		{
			ans.setText(Double.toString(customer.getBalance()) + " dollars");
		}
		
		bookings = Booking.getBookings(customer.getID(), customer.getType(), false);
		bookings = Booking.searchActiveCustomerBookings(bookings, customer.getID());
		Driver[] drivers = new Driver[bookings.size()];
		descriptions = new String[bookings.size()];
		prices = new double[bookings.size()];
		for (int i = 0;i < drivers.length;i++)
		{
			drivers[i] = bookings.get(i).getDriver();
			descriptions[i] = bookings.get(i).getDetails();
			prices[i] = 10 + (Math.random() * ((50 - 10) + 1));
		}
		String names[] = null;
		if (drivers.length > 0)
		{
			names = new String[drivers.length];
			for (int i = 0;i < drivers.length;i++)
			{
				names[i] = drivers[i].getUserName();
			}
			comboBox = new JComboBox(names);
			label5.setText(descriptions[0]);
		}
		else
		{
			String n[] = {"None"};
			comboBox = new JComboBox(n);
		}
		contentPane2.add(comboBox, "cell 24 33,aligny center");
		comboBox.addActionListener(this);
		type = new JLabel("New label");
		type.setOpaque(true);
		type.setForeground(Color.WHITE);
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setBackground(Color.RED);
		contentPane2.add(type, "cell 3 8");
				
				lblCharges = new JLabel("Charges:");
				lblCharges.setOpaque(true);
				lblCharges.setForeground(Color.WHITE);
				lblCharges.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				lblCharges.setBackground(Color.RED);
				contentPane2.add(lblCharges, "cell 13 63");
				
				label6 = new JLabel("None");
				label6.setForeground(Color.WHITE);
				label6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				contentPane2.add(label6, "cell 24 63,alignx center,aligny center");
				
				JLabel label3 = new JLabel("Amount+Tip:");
				label3.setBackground(new Color(255, 0, 0));
				label3.setForeground(new Color(255, 255, 255));
				label3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				label3.setOpaque(true);
				contentPane2.add(label3, "cell 13 73,aligny center");
				
				textField2 = new JTextField();
				textField2.setBackground(new Color(250, 218, 221));
				textField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				contentPane2.add(textField2, "cell 24 73,alignx center,aligny center");
				textField2.setColumns(10);
				
				lblDrivingRating = new JLabel("Rating:");
				lblDrivingRating.setOpaque(true);
				lblDrivingRating.setForeground(Color.WHITE);
				lblDrivingRating.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				lblDrivingRating.setBackground(Color.RED);
				contentPane2.add(lblDrivingRating, "cell 13 83");
				
				String[] stars = {"1 star", "2 stars", "3 stars", "4 stars", "5 stars"};
				
				comboBox3 = new JComboBox(stars);
				comboBox3.setOpaque(true);
				comboBox3.setFont(new Font("Tahoma", Font.ITALIC, 20));
				comboBox3.setBackground(new Color(255, 140, 0));
				contentPane2.add(comboBox3, "cell 24 83,alignx center,aligny center");
				
				lblDrivingRating_1 = new JLabel("Comment:");
				lblDrivingRating_1.setOpaque(true);
				lblDrivingRating_1.setForeground(Color.WHITE);
				lblDrivingRating_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				lblDrivingRating_1.setBackground(Color.RED);
				contentPane2.add(lblDrivingRating_1, "cell 13 93");
						
						textArea = new JTextArea();
						textArea.setText("");
						textArea.setRows(10);
						textArea.setForeground(Color.BLACK);
						textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
						textArea.setColumns(10);
						textArea.setBackground(new Color(255, 182, 193));
						contentPane2.add(textArea, "cell 24 93,alignx center,aligny center");
				
						JButton buttonsend = new JButton("Send");
						buttonsend.setFont(new Font("Tahoma", Font.PLAIN, 14));
						buttonsend.setForeground(new Color(255, 255, 255));
						buttonsend.setBackground(new Color(255, 0, 0));
						contentPane2.add(buttonsend, "cell 24 108,alignx center,aligny center");
				buttonsend.addActionListener(this);
		if (customer == null)
		{
			type.setText("Driver Account");
		}
		else
		{
			type.setText("Customer Account");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		Person p = null;
		if (customer != null)
		{
			p = customer;
		}
		else
		{
			p = driver;
		}
		String title = e.getActionCommand();
		if (title.equals("Home"))
		{
			setVisible(false);
			Home home = new Home(p);
			home.setVisible(true);
		}
		else if (title.equals("Profile"))
		{
			setVisible(false);
			Profile pf = new Profile(p);
			pf.setVisible(true);
		}
		else if (title.equals("Bookings"))
		{
			setVisible(false);
			ViewBookings o = new ViewBookings(p);
			o.setVisible(true);
		}
		else if (title.equals("Payments"))
		{
			setVisible(false);
			Payments pay = new Payments(p);
			pay.setVisible(true);
		}
		else if (title.equals("Requests"))
		{
			setVisible(false);
			ViewRequests view = new ViewRequests(driver);
			view.setVisible(true);
		}
		else if (title.equals("Logout"))
		{
			setVisible(false);
			Main mn = new Main(false);
			mn.setVisible(true);
		}
		else if (title.equals("Messages"))
		{
			setVisible(false);
			Chatting mn = new Chatting(p);
			mn.setVisible(true);
		}
		else if (title.equals("Deposit"))
		{
			screen1();
		}
		else if (title.equals("Withdraw"))
		{
			screen1();
			buttondone.setText("Get");
		}
		else if (title.equals("Pay Driver"))
		{
			screen2();
		}
		else if (title.equals("Done"))
		{
			String am = textField.getText().toString();
			boolean valid = validateAmount(am);
			if (!(valid))
			{
				JOptionPane.showMessageDialog(null, "Invalid amount!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double amount = Double.parseDouble(am);
			if (customer != null)
			{
				customer.setBalance(customer.getBalance() + amount);
			}
			else
			{
				driver.setBalance(driver.getBalance() + amount);
			}
			JOptionPane.showMessageDialog(null, "Deposit successfull!","Success", JOptionPane.INFORMATION_MESSAGE);
			screen0();
		}
		else if (title.equals("Get"))
		{
			String am = textField.getText().toString();
			boolean valid = validateAmount(am);
			if (!(valid))
			{
				JOptionPane.showMessageDialog(null, "Invalid amount!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double amount = Double.parseDouble(am);
			if (customer != null)
			{
				if (amount <= customer.getBalance())
					customer.setBalance(customer.getBalance() - amount);
				else
				{
					JOptionPane.showMessageDialog(null, "Insufficient balance!","Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else
			{
				if (amount <= driver.getBalance())
					driver.setBalance(driver.getBalance() - amount);
				else
				{
					JOptionPane.showMessageDialog(null, "Insufficient balance!","Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Withdraw successfull!","Success", JOptionPane.INFORMATION_MESSAGE);
			screen0();
		}
		else if (title.equals("Send"))
		{
			if (bookings.size() == 0)
			{ 
				JOptionPane.showMessageDialog(null, "No drivers found!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String am = textField2.getText().toString();
			boolean valid = validateAmount(am);
			if (!(valid))
			{
				JOptionPane.showMessageDialog(null, "Invalid amount!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double amount = Double.parseDouble(am);
			Driver d = bookings.get(comboBox.getSelectedIndex()).getDriver();
			double charges = prices[comboBox.getSelectedIndex()];
			String method = comboBox2.getSelectedItem().toString();
			int stars = comboBox3.getSelectedIndex() + 1;
			double tip = 0;
			if (customer.getBalance() >= amount)
			{
				if (amount < charges)
				{
					JOptionPane.showMessageDialog(null, "Insufficient balance!","Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (amount > charges)
				{
					tip = amount - charges;
					amount = amount - tip;
					this.insertTip(tip, d);
				}
				customer.setBalance(customer.getBalance() - amount - tip);
				d.setBalance(d.getBalance() + amount + tip);
				Booking.completeBooking(bookings.get(comboBox.getSelectedIndex()).getBookingID());
				int payid = this.insertPayment(amount);
				Booking b = bookings.get(comboBox.getSelectedIndex());
				Booking.insertRide(b.getDetails(), Date.getTodaysDate(), Date.getCurrentTime(), payid);
				this.insertReview();
				int total = this.getCustomerRides(customer);
				if (total >= 5)
				{
					double percentage = total;
					double amount2 = (percentage / 100) * charges;
					this.insertDiscount(amount2, percentage);
					customer.setBalance(customer.getBalance() + amount2);
				}
				JOptionPane.showMessageDialog(null, "Booking completed!","Success",JOptionPane.INFORMATION_MESSAGE);
				screen0();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insufficient balance!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		JComboBox temp = null;
		if (e.getSource() instanceof JComboBox)
		{
			temp = (JComboBox)e.getSource();
			label5.setText(descriptions[temp.getSelectedIndex()]);
			label6.setText(Double.toString(prices[temp.getSelectedIndex()]));
		}
	}
	public void addBalanceToAdmin(double amount)
	{
		Connection conn = null;
		try {
			int adminid = -1;
			double balance = 0;
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			Statement stmt = conn.createStatement();
			String query = "select * from admin";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				adminid = rs.getInt("adminid");
				balance = rs.getDouble("balance");
			}
			balance += amount;
			query = "update admin set balance = ? where adminid = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, balance);
			s.setInt(2, adminid);
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	private boolean validateAmount(String amount)
	{
		for (int i = 0;i < amount.length();i++)
		{
			if (!(amount.charAt(i) >= '0' && amount.charAt(i) <= '9'))
				return false;
		}
		return true;
	}
	public int insertPayment(double amount)
	{
		int payid = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "insert into Payment(amount, PaymentDate, paymentTime, Customer_personid, method) values (?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, amount);
		    java.sql.Date date = java.sql.Date.valueOf(Date.getTodaysDate().toString());
		    s.setDate(2, date);
		    s.setString(3, Date.getCurrentTime());
		    s.setInt(4, customer.getID());
		    s.setString(5, comboBox2.getSelectedItem().toString());
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return 0;
		}
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "select paymentid from payment where paymentid = (select max(paymentid) from payment)";
			PreparedStatement s = conn.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				payid = rs.getInt("paymentid");
			}
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return 0;
		}
		return payid;
	}
	public void insertTip(double amount, Driver d)
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "insert into Tip(driverid, customerid, tipDate, amount) values (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, d.getID());
			s.setInt(2, customer.getID());
		    java.sql.Date date = java.sql.Date.valueOf(Date.getTodaysDate().toString());
		    s.setDate(3, date);
		    s.setDouble(4, amount);
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public void insertReview()
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "insert into Review(stars, comments, reviewDate, customerid) values (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, comboBox3.getSelectedIndex() + 1);
			s.setString(2, textArea.getText().toString());
		    java.sql.Date date = java.sql.Date.valueOf(Date.getTodaysDate().toString());
		    s.setDate(3, date);
		    s.setInt(4, customer.getID());
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public void insertDiscount(double amount, double percentage)
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "insert into Discount(amount, givenDate, customerid, percentage) values (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, amount);
		    java.sql.Date date = java.sql.Date.valueOf(Date.getTodaysDate().toString());
		    s.setDate(2, date);
		    s.setInt(3, customer.getID());
		    s.setDouble(4, percentage);
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public int getCustomerRides(Customer c)
	{
		int count = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBTools.getDBUrl(), DBTools.getUserName(), DBTools.getPassword());
			String query = "SELECT customerid, count(distinct bookingid) as total From booking join person on personid = customerid group by customerid having customerid = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, c.getID());
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				count = rs.getInt("total");
			}
			s.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return count;
		}
		return count;
	}
}
