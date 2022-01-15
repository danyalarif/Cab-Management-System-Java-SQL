package cabapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
	private int locationid;
	private String city;
	private String province;
	public Location(int locationid, String city, String province)
	{
		this.locationid = locationid;
		this.city = city;
		this.province = province;
	}
	public Location(Location other)
	{
		this.locationid = other.locationid;
		this.city = other.city;
		this.province = other.province;
	}
	public Location(String city)
	{
		this.city = city;
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select * from Location where city = ?"; 
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, city);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				locationid = rs.getInt("locationid");
				province = rs.getString("province");
			}
			s.executeUpdate();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public int getLocationID()
	{
		return locationid;
	}
	public String getCity()
	{
		return city;
	}
	public String getProvince()
	{
		return province;
	}
}
