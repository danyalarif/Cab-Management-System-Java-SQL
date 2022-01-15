package cabapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public abstract class Person{
	private int personid;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String languages[];
	private Date join;		//joining date
	private double balance;
	private String city;
	private String type;
	public Person()
	{
		super();
	}
	public Person(int id, String username, String password, String first, String last, String languages[], String city, String type)
	{
		this.personid = id;
		this.languages = new String[3];
		this.username = username;
		this.password = password;
		this.firstName = first;
		this.lastName = last;
		if (languages == null)
		{
			this.languages = null;
		}
		else
		{
		for (int i = 0; i < languages.length;i++)
		{
			if (languages[i] == null)
				this.languages[i] = "none";
			else
				this.languages[i] = languages[i];
		}
		}
		this.balance = 0;
		join = Date.getTodaysDate();
		this.city = city;
		this.type = type;
	}
	public Person(int id, String username, String password, String first, String last, String languages[], Date joining, double balance, String city, String type)
	{
		this.personid = id;
		this.languages = new String[3];
		this.username = username;
		this.password = password;
		this.firstName = first;
		this.lastName = last;
		if (languages == null)
		{
			this.languages = null;
		}
		else
		{
		for (int i = 0; i < languages.length;i++)
		{
			if (languages[i] == null)
				this.languages[i] = "none";
			else
				this.languages[i] = languages[i];
		}
		}
		this.balance = balance;
		join = new Date(joining);
		this.city = city;
		this.type = type;
	}
	public Person(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public Person(Person other)		//copy constructor
	{
		this.personid = other.personid;
		this.languages = new String[3];
		this.username = other.username;
		this.firstName = other.firstName;
		this.lastName = other.lastName;
		this.password = other.password;
		this.balance = other.balance;
		this.join = new Date(other.join);
		for (int i = 0;i < other.languages.length;i++)
		{
			this.languages[i] = other.languages[i];
		}
		this.city = other.city;
		this.type = other.type;
	}
	public String toString()
	{
		return username + " " + password + " " + firstName + " " + lastName + " " + Arrays.toString(languages) + " " + balance;
	}
	//abstract methods
	public abstract void saveData();
	//public abstract void updateData();
	public static boolean userExists(String username)
	{
		boolean found = false;
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "SELECT username from PERSON where username = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, username);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				found = true;
			}
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return found;
		}
        return found;
	}
	public void LoadPerson()
	{
		Home home = new Home(this);
		home.setVisible(true);
	}
	public boolean validateUserName()
	{
		boolean flag1, flag2, flag3;
		flag1 = flag2 = flag3 = false;
		if (username.length() < 7)
			return false;
		for (int i = 0; i < username.length(); i++)
		{
			if ((username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') || (username.charAt(i) >= 'a' && username.charAt(i) <= 'z'))
				flag1 = true;
			else if (username.charAt(i) >= '0' && username.charAt(i) <= '9')
				flag2 = true;
			if (flag1 && flag2)
				return true;
		}
		return false;
	}
	public boolean validatePassword()
	{
		boolean flag1, flag2, flag3;
		flag1 = flag2 = flag3 = false;
		if (password.length() < 7)
			return false;
		for (int i = 0; i < password.length();i++)
		{
			if ((password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') || (password.charAt(i) >= 'a' && password.charAt(i) <= 'z'))
				flag1 = true;
			else if (password.charAt(i) >= '0' && password.charAt(i) <= '9')
				flag2 = true;
			else if ((password.charAt(i) >= '!' && password.charAt(i) <= '/') || (password.charAt(i) >= ':' && password.charAt(i) <= '@') || (password.charAt(i) >= '[' && password.charAt(i) <= '`') || (password.charAt(i) >= '{' && password.charAt(i) <= '~'))
				flag3 = true;
			if (flag1 && flag2 && flag3)
				return true;
		}
		return false;
	}
	public boolean validateFirstName()
	{
		if (firstName.length() <= 0)
		{
			return false;
		}
		for (int i = 0;i < firstName.length();i++)
		{
			if (!((firstName.charAt(i) >= 'A' && firstName.charAt(i) <= 'Z') || (firstName.charAt(i) >= 'a' && firstName.charAt(i) <= 'z')))
				return false;
		}
		return true;
	}
	public boolean validateLastName()
	{
		if (lastName.length() <= 0)
		{
			return false;
		}
		for (int i = 0;i < lastName.length();i++)
		{
			if (!((lastName.charAt(i) >= 'A' && lastName.charAt(i) <= 'Z') || (lastName.charAt(i) >= 'a' && lastName.charAt(i) <= 'z')))
				return false;
		}
		return true;
	}
	protected void saveLanguages()
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into Language(personid, language) values (?,?)";
		    for (int i = 0; i < languages.length;i++)
		    {
		    	if (languages[i].equalsIgnoreCase("none"))
		    		continue;
		    	PreparedStatement s = conn.prepareStatement(query);
		    	s.setInt(1, personid);
		    	s.setString(2, languages[i]);
		    	s.executeUpdate();
		    }
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	protected void savePersonData()
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into Person(username,password,firstName,lastName,joinedDate,balance,city,type) values (?,?,?,?,?,?,?,?)";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setString(1, getUserName());
		    s.setString(2, getPassword());
		    s.setString(3, getFirstName());
		    s.setString(4, getLastName());	
		    java.sql.Date date = java.sql.Date.valueOf(getDate().toString());
		    s.setDate(5, date);
		    s.setDouble(6, getBalance());
		    s.setString(7, city);
		    s.setString(8, type);
			s.executeUpdate();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static int getPersonID(String username)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        int personid = 0;
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select personid from Person where username = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setString(1, username);
		    ResultSet rs = s.executeQuery();
		    while (rs.next())
		    	personid = rs.getInt("personid");
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return 0;
		}
        return personid;
	}
	public static String getPersonUserName(int pid)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        String user = null;
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select username from Person where personid = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setInt(1, pid);
		    ResultSet rs = s.executeQuery();
		    while (rs.next())
		    	user = rs.getString("username");
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return user;
		}
        return user;
	}
	public static String getPersonType(int pid)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        String userType = null;
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select type from Person where personid = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setInt(1, pid);
		    ResultSet rs = s.executeQuery();
		    while (rs.next())
		    	userType = rs.getString("type");
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return userType;
		}
        return userType;
	}
	public String getUserName()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String[] getLanguages()
	{
		return languages;
	}
	public Date getDate()
	{
		return new Date(join);
	}
	public double getBalance()
	{
		return balance;
	}
	public int getID()
	{
		return personid;
	}
	public String getCity()
	{
		return city;
	}
	public String getType()
	{
		return type;
	}
	protected static boolean exists(String arr[], String element)
	{
		for (int i = 0;i < arr.length;i++)
		{
			if (arr[i] != null && arr[i].equals(element))
				return true;
		}
		return false;
	}
	public void setID(int id)
	{
		personid = id;
	}
	public void setBalance(double amount)
	{
		this.balance = amount;
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        String userType = null;
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "UPDATE PERSON set balance = ? where personid = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setDouble(1, balance);
		    s.setInt(2, personid);
		    s.executeUpdate();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
	}

}
