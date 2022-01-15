package cabapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Driver extends Person{
	private int experience;
	private int salary;
	private boolean hasLicense;
	private String skills[];
	private String description;
	public Driver()
	{
		super();
	}
	public Driver(int id, String username, String password, String first, String last, String languages[], String city, int experience, int salary, String desc, boolean license, String skills[])
	{
		super(id, username, password, first, last, languages, city, "driver");
		this.experience = experience;
		this.salary = salary;
		this.hasLicense = license;
		this.description = desc;
		this.skills = new String[skills.length];
		for (int i = 0;i < this.skills.length;i++)
		{
			if (skills[i] == null)
				break;
			else
				this.skills[i] = skills[i];
		}
	}
	public Driver(int id, String username, String password, String first, String last, String languages[], Date join, double balance, String city, int experience, int salary, String desc, boolean license, String skills[])
	{
		super(id, username, password, first, last, languages,join,balance, city, "driver");
		this.experience = experience;
		this.salary = salary;
		this.hasLicense = license;
		this.description = desc;
		this.skills = new String[skills.length];
		for (int i = 0;i < this.skills.length;i++)
		{
			if (skills[i] == null)
				break;
			else
				this.skills[i] = skills[i];
		}
	}
	public Driver(String username, String password)
	{
		super(username, password);
	}
	public Driver(Driver other)
	{
		super(other);
		this.skills = new String[3];
		this.experience = other.experience;
		this.salary = other.salary;
		this.hasLicense = other.hasLicense;
		this.description = other.description;
		for (int i = 0;i < this.skills.length;i++)
		{
			this.skills[i] = other.skills[i];
		}
	}
	public String[] getSkills()
	{
		return skills;
	}
	public String getDescription()
	{
		return description;
	}
	public int getExperience()
	{
		return experience;
	}
	public int getSalary()
	{
		return salary;
	}
	public boolean getLicense()
	{
		return hasLicense;
	}
	private void saveSkills()
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into skill(driverid, skillname) values (?,?)";
		    for (int i = 0; i < skills.length;i++)
		    {
		    	if (skills[i].equalsIgnoreCase("none"))
		    		continue;
		    	PreparedStatement s = conn.prepareStatement(query);
		    	s.setInt(1, super.getID());
		    	s.setString(2, skills[i]);
		    	s.executeUpdate();
		    }
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
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
			String query = "INSERT into DRIVER(personid, experience, salary, description, hasLicense) values (?,?,?,?,?)";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setInt(1, super.getID());
		    s.setInt(2, experience);
		    s.setInt(3, salary);
		    s.setString(4, description);
		    if (hasLicense)
		    	s.setString(5, "y");
		    else
		    	s.setString(5, "n");
			s.executeUpdate();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return;
		}
		this.saveSkills();
	}
	public static Driver getDriverData(String user, String pass)
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
    	boolean found = false;
    	//Variables required for loading data
    	int personid, experience, salary;
    	personid = experience = salary = 0;
    	String username, password, firstName, lastName, description, city;
    	boolean license = false;
    	username = password = firstName = lastName = description = city = null;
    	String[] languages = new String[3];
    	String[] skills = new String[3];
        double balance = 0;
    	Date joining = null;
        try {
        	int i = 0;
        	int j = 0;		//counter for language
        	int k = 0;		//counter for skill
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "SELECT * from PERSON NATURAL JOIN DRIVER NATURAL JOIN LANGUAGE NATURAL JOIN SKILL where username = ? and password = ?";
		    PreparedStatement s = conn.prepareStatement(query);
		    s.setString(1, user);
		    s.setString(2, pass);
			ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {
            	if (i == 0)
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
                    experience = rs.getInt("experience");
                    salary = rs.getInt("salary");
                    license = rs.getBoolean("hasLicense");
                    description = rs.getString("description");
            	}
            	String l = rs.getString("language");
            	String sk = rs.getString("skillName");
            	if (j == 3 && k == 3)
            		break;
            	if (!(Person.exists(languages, l)))
            	{
            		if (j == 3)
            			break;
            		languages[j] = l;
            		j++;
            	}
            	if (!(Person.exists(skills, sk)))
            	{
            		if (k == 3)
            			break;
            		skills[k] = sk;
            		k++;
            	}
                i++;
            }
	        conn.close();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return null;
		}
        if (!(found))
        	return null;
        Driver driver = new Driver(personid, username, password, firstName, lastName, languages, joining, balance, city, experience, salary, description, license, skills);
        return driver;
	}
	public static Driver[] getAllDrivers()
	{
		ArrayList<Driver>drivers = new ArrayList<Driver>();
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
        	int i = 0;
			conn = DriverManager.getConnection(dbURL, u, p);
			Statement stmt = conn.createStatement();
			String query = "SELECT username, password from PERSON NATURAL JOIN DRIVER ORDER BY PERSONID";
			ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {   
            	String user, pass;
            	user = pass = null;
            	user = rs.getString("username");
            	pass = rs.getString("password");
            	Driver dd = Driver.getDriverData(user, pass);
            	drivers.add(dd);
            }
	        conn.close();
		} catch (SQLException e2) {
			DBTools.ShowErrorMessage();
			return null;
		}
        Driver[] d = new Driver[drivers.size()];
        for (int i = 0;i < drivers.size();i++)
        {
        	d[i] = drivers.get(i);
        }
        return d;
	}
}
