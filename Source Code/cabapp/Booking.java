package cabapp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Booking{
	private int bookingid;
	private String details;
	private Customer customer;
	private Driver driver;
	private Location source;
	private Location destination;
	private boolean accepted;
	private boolean completed;
	private Date created;
	private String time;
	private ArrayList<Booking> bookings;
	public Booking()
	{
		bookings = new ArrayList<Booking>();
	}
	public Booking(Customer customer, String details, Location source, Location destination)
	{
		bookings = new ArrayList<Booking>();
		this.customer = new Customer(customer);
		this.details = details;
		this.source = new Location(source);
		this.destination = new Location(destination);
		accepted = completed = false;
		created = Date.getTodaysDate();
		time = Date.getCurrentTime();
		this.driver = null;
	}
	public Booking(int bookingid, Customer customer, Driver driver, String details, Location source, Location destination, Date date, String time, boolean accepted, boolean completed)
	{
		bookings = new ArrayList<Booking>();
		this.bookingid = bookingid;
		this.customer = customer;
		this.driver = driver;
		this.details = details;
		this.source = new Location(source);
		this.destination = new Location(destination);
		this.created = new Date(date);
		this.time = time;
		this.accepted = accepted;
		this.completed = completed;
	}
	public static ArrayList<Booking> getBookings(int id, String type, boolean requests)
	{
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = null;
			if (type.equals("customer"))
			{
				query = "SELECT * from Booking join \r\n" + 
						"(select locationid as sid, city as sourceCity, province as sourceProvince from Location) Location1 \r\n" + 
						"on \r\n" + 
						"Location1.sid = Booking.sourceid \r\n" + 
						"join (select locationid as desid, city as destinationCity, province as destinationProvince from Location) Location2\r\n" + 
						"on\r\n" + 
						"location2.desid = booking.destinationid\r\n" + 
						"join (select personid as cusid, username as customer_username, password as customer_password from Person \r\n" + 
						"where type = 'customer') Customer\r\n" + 
						"on Customer.cusid = booking.customerid\r\n" + 
						"join (select personid as did, username as driver_username, password as driver_password from Person \r\n" + 
						"where type = 'driver') Driver\r\n" + 
						"on Driver.did = booking.driverid\r\n" + 
						"where booking.customerid = ?";
			}
			else
			{
				query = "SELECT * from Booking join \r\n" + 
						"(select locationid as sid, city as sourceCity, province as sourceProvince from Location) Location1 \r\n" + 
						"on \r\n" + 
						"Location1.sid = Booking.sourceid \r\n" + 
						"join (select locationid as desid, city as destinationCity, province as destinationProvince from Location) Location2\r\n" + 
						"on\r\n" + 
						"location2.desid = booking.destinationid\r\n" + 
						"join (select personid as cusid, username as customer_username, password as customer_password from Person \r\n" + 
						"where type = 'customer') Customer\r\n" + 
						"on Customer.cusid = booking.customerid\r\n" + 
						"join (select personid as did, username as driver_username, password as driver_password from Person \r\n" + 
						"where type = 'driver') Driver\r\n" + 
						"on Driver.did = booking.driverid\r\n" + 
						"where booking.driverid = ?";
			}
			if (requests)
			{
				query = "SELECT * from Booking join \r\n" + 
						"(select locationid as sid, city as sourceCity, province as sourceProvince from Location) Location1 \r\n" + 
						"on\r\n" + 
						"Location1.sid = Booking.sourceid \r\n" + 
						"join (select locationid as desid, city as destinationCity, province as destinationProvince from Location) Location2 \r\n" + 
						"on \r\n" + 
						"location2.desid = booking.destinationid \r\n" + 
						"join (select personid as cusid, username as customer_username, password as customer_password from Person\r\n" + 
						"where type = 'customer') Customer \r\n" + 
						"on Customer.cusid = booking.customerid";
			}
			PreparedStatement s = conn.prepareStatement(query);
			if (!(requests))
				s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				int bookingid = rs.getInt("bookingid");
				if (bookingExists(bookings, bookingid))
					continue;
				String details = rs.getString("details");
                String[] date = rs.getDate("createdDate").toString().split("-");
                Date createdDate = new Date(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
                String time = rs.getString("createdTime");
                String t = rs.getString("accepted");
                boolean accepted = false;
                if (t.equals("y"))
                	accepted = true;
                t = rs.getString("completed");
                boolean completed = false;
                if (t.equals("y"))
                	completed = true;
                Location source = new Location(rs.getInt("sid"), rs.getString("sourcecity"), rs.getString("sourceprovince"));
                Location destination = new Location(rs.getInt("desid"), rs.getString("destinationcity"), rs.getString("destinationprovince"));
                Customer c = Customer.getCustomerData(rs.getString("customer_username"), rs.getString("customer_password"));
                Driver d = null;
                if (!(requests))
                	d = Driver.getDriverData(rs.getString("driver_username"), rs.getString("driver_password"));
                Booking b = new Booking(bookingid, c, d, details, source, destination, createdDate, time, accepted, completed);
                bookings.add(b);
			}
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return null;
		}
        return bookings;
	}
	private static boolean bookingExists(ArrayList<Booking> bookings, int b)
	{
		for (int i = 0; i < bookings.size(); i++)
		{
			if (bookings.get(i).bookingid == b)
				return true;
		}
		return false;
	}
	public void saveBooking()
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into Booking(details, createdDate, createdTime, accepted, customerid, driverid, sourceid, destinationid, completed) values (?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, details);
		    java.sql.Date date = java.sql.Date.valueOf(created.toString());
		    s.setDate(2, date);
		    s.setString(3, time);
		    if (accepted)
		    	s.setString(4, "y");
		    else
		    	s.setString(4, "n");
		    s.setInt(5, customer.getID());
			if (driver == null)
				s.setNull(6,Types.INTEGER);
			else
				s.setInt(6, driver.getID());
			s.setInt(7, source.getLocationID());
			s.setInt(8, destination.getLocationID());
		    if (completed)
		    	s.setString(9, "y");
		    else
		    	s.setString(9, "n");
			s.executeUpdate();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static ArrayList<Booking> searchActiveCustomerBookings(ArrayList<Booking> bookings, int customerid) 	//to get all active orders
	{
		ArrayList<Booking> bk = new ArrayList<Booking>();
		for (int i = 0;i < bookings.size();i++)
		{
			if (bookings.get(i).customer.getID() == customerid && bookings.get(i).accepted && !(bookings.get(i).completed))
			{
				bk.add(bookings.get(i));
			}
		}
		return bk;
	}
	public static ArrayList<Booking> searchActiveDriverBookings(ArrayList<Booking> bookings, int driverid) 	//to get all active orders
	{
		ArrayList<Booking> bk = new ArrayList<Booking>();
		for (int i = 0;i < bookings.size();i++)
		{
			if (bookings.get(i).driver.getID() == driverid && bookings.get(i).accepted && !(bookings.get(i).completed))
			{
				bk.add(bookings.get(i));
			}
		}
		return bk;
	}
	public static ArrayList<Booking> searchActiveRequests(ArrayList<Booking> bookings)
	{
		ArrayList<Booking> bk = new ArrayList<Booking>();
		for (int i = 0;i < bookings.size();i++)
		{
			if (!(bookings.get(i).accepted))
			{
				bk.add(bookings.get(i));
			}
		}
		return bk;
	}
	public static void acceptRequest(int bookingid, int driverid)
	{
		String a = "y";
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "UPDATE Booking SET driverid = ?,accepted = ? where bookingid = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, driverid);
			s.setString(2, a);
			s.setInt(3, bookingid);
			s.executeUpdate();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static void completeBooking(int bookingid)
	{
		String a = "y";
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "UPDATE Booking SET completed = ? where bookingid = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, a);
			s.setInt(2, bookingid);
			s.executeUpdate();
	        conn.close();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static void insertRide(String details, Date completedDate, String completedTime, int paymentid)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into Ride(details, completedDate, completedTime, paymentid) values (?,?,?,?)";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setString(1, details);
		    java.sql.Date date = java.sql.Date.valueOf(completedDate.toString());
		    s.setDate(2, date);
		    s.setString(3, completedTime);
		    s.setInt(4, paymentid);
			s.executeUpdate();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public int getBookingID()
	{
		return bookingid;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public Driver getDriver()
	{
		return driver;
	}
	public boolean getCompleteStatus()
	{
		return completed;
	}
	public Date getDateCreated()
	{
		return created;
	}
	public String getTimeCreated()
	{
		return time;
	}
	public Location getSource()
	{
		return source;
	}
	public Location getDestination()
	{
		return destination;
	}
	public String getDetails()
	{
		return details;
	}
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

}
