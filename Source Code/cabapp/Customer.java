package cabapp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.miginfocom.layout.CC;

public class Customer extends Person{
	private int weight;
	private int height;
	public Customer()
	{
		super();
	}
	public Customer(int id, String username, String password, String first, String last, String languages[], String city, int weight, int height)
	{
		super(id,username, password,first,last,languages, city, "customer");
		this.weight = weight;
		this.height = height;
	}
	public Customer(int id, String username, String password, String first, String last, String languages[], Date joining, double balance, String city, int weight, int height)
	{
		super(id,username, password,first,last,languages,joining,balance, city, "customer");
		this.height = height;
		this.weight = weight;
	}
	public Customer(String user, String pass)
	{
		super(user, pass);
	}
	public Customer(Customer other)
	{
		super(other);
	}
	public int getWeight()
	{
		return weight;
	}
	public int getHeight()
	{
		return height;
	}
	@Override
	public void saveData()
	{
		super.savePersonData();
		int pid = super.getPersonID(super.getUserName());
		super.setID(pid);
		super.saveLanguages();
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			Statement stmt = conn.createStatement();
			String query = "insert into Customer(personid, weight, height) values (?,?,?)";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setInt(1, getID());
		    s.setInt(2, weight);
		    s.setInt(3, height);
			s.executeUpdate();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static Customer getCustomerData(String user, String pass)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
    	boolean found = false;
    	//Variables required for loading data
    	int personid, height, weight;
    	personid = weight = height = 0;
    	String username, password, firstName, lastName, city;
    	username = password = firstName = lastName = city = null;
    	String[] languages = new String[3];
        double balance = 0;
    	Date joining = null;
	    try {
	        int i = 0;
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "SELECT * from PERSON NATURAL JOIN CUSTOMER NATURAL JOIN LANGUAGE where username = ? and password = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setString(1, user);
		    s.setString(2, pass);
			ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {
               	found = true;
                personid = rs.getInt("personid");
                username = rs.getString("username");
                password = rs.getString("password");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                balance = rs.getDouble("balance");
                String[] date = rs.getDate("joinedDate").toString().split("-");
                joining = new Date(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
                city = rs.getString("city");
                weight = rs.getInt("weight");
                height = rs.getInt("height");
                if (i == 3)
                	break;
                languages[i] = rs.getString("language");
                i++;
            }
		    conn.close();
			} catch (SQLException e2) {
				DBTools.ShowErrorMessage();
				return null;
			}
	    if (!(found))
	    	return null;
	    Customer customer = new Customer(personid, username, password, firstName, lastName, languages,joining,balance, city, weight, height);
	    return customer;
	}
	public static Customer[] getAllCustomers()
	{
		ArrayList<Customer>customers = new ArrayList<Customer>();
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
        	int i = 0;
			conn = DriverManager.getConnection(dbURL, u, p);
			Statement stmt = conn.createStatement();
			String query = "SELECT username, password from PERSON NATURAL JOIN CUSTOMER ORDER BY PERSONID";
			ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {   
            	String user, pass;
            	user = pass = null;
            	user = rs.getString("username");
            	pass = rs.getString("password");
            	Customer cc = Customer.getCustomerData(user, pass);
            	customers.add(cc);
            }
	        conn.close();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return null;
		}
        Customer[] c = new Customer[customers.size()];
        for (int i = 0;i < customers.size();i++)
        {
        	c[i] = customers.get(i);
        }
        return c;
	}
}
