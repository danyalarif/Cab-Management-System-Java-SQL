package cabapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class Message {
	private int messageid;
	private String sender;		//username of sender
	private String reciever;
	private String text;
	private Date dateSent;
	private String timeSent;
	private String senderAccount;
	public Message(String sender, String reciever, String text, String senderAccount)
	{
		this.sender = sender;
		this.reciever = reciever;
		this.text = text;
		dateSent = Date.getTodaysDate();
		timeSent = Date.getCurrentTime();
		this.senderAccount = senderAccount;
	}
	public Message(int messageid, String sender, String reciever, String text, Date sent, String time, String senderAccount)
	{
		this.messageid = messageid;
		this.sender = sender;
		this.reciever = reciever;
		this.text = text;
		this.dateSent = new Date(sent);
		this.timeSent = time;
		this.senderAccount = senderAccount;
	}
	public void saveMessage()
	{
		int driverid, customerid;
		if (senderAccount.equals("customer"))
		{
			customerid = Person.getPersonID(sender);
			driverid = Person.getPersonID(reciever);
		}
		else
		{
			driverid = Person.getPersonID(sender);
			customerid = Person.getPersonID(reciever);
		}
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into Message(driverid,customerid,text,sentDate, sentTime, sender) values (?,?,?,?,?,?)"; 
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, driverid);
			s.setInt(2, customerid);
			s.setString(3, text);
		    java.sql.Date date = java.sql.Date.valueOf(dateSent.toString());
		    s.setDate(4, date);
		    s.setString(5, timeSent);
		    s.setString(6, senderAccount);
			s.executeUpdate();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public static Hashtable<String, ArrayList<Message>> getMessages(int pid, String username)
	{
		Hashtable<String, ArrayList<Message>> msgs = new Hashtable<String, ArrayList<Message>>();
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select * from Message where driverid = ? or customerid = ? order by sentDate"; 
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, pid);
			s.setInt(2, pid);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				String sender, reciever;
				int messageid = rs.getInt("messageid");
				int driverid = rs.getInt("driverid");
				int customerid = rs.getInt("customerid");
				String text = rs.getString("text");
                String[] date = rs.getDate("sentDate").toString().split("-");
                Date sent = new Date(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
                String sentTime = rs.getString("sentTime");
                String senderAccount = rs.getString("sender");
                if (senderAccount.equals("customer"))
                {
                	sender = Person.getPersonUserName(customerid);
                	reciever = Person.getPersonUserName(driverid);
                }
                else
                {
                	reciever = Person.getPersonUserName(customerid);
                	sender = Person.getPersonUserName(driverid);
                }
                Message m = new Message(messageid, sender, reciever, text, sent, sentTime, senderAccount);
                String name = null;		//target key
                if (sender.equals(username))
                	name = reciever;
                else
                	name = sender;
                ArrayList<Message> temp = new ArrayList<Message>();
                if (msgs.containsKey(name))
                {
                	ArrayList<Message> t = msgs.get(name);
                	for (int i = 0;i < t.size();i++)
                	{
                		temp.add(t.get(i));
                	}
                }
                temp.add(m);
                msgs.put(name, temp);
			}
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return null;
		}
		return msgs;
	}
	
	public static int[] getMessagesCount(int current, String currentType, int pids[])
	{
		int[] total = new int[pids.length];
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = null;
			if (currentType.equals("customer"))
			{
				query = "select driverid, customerid, count(distinct messageid) as totalCount from Message group by driverid, customerid having customerid = ? and driverid = ?";
			}
			else
			{
				query = "select driverid, customerid, count(distinct messageid) as totalCount from Message group by driverid, customerid having driverid = ? and customerid = ?";
			}
			for (int i = 0;i < pids.length; i++)
			{
				PreparedStatement s = conn.prepareStatement(query);
				s.setInt(1, current);
				s.setInt(2, pids[i]);
				ResultSet rs = s.executeQuery();
				while (rs.next())
				{
					total[i] = rs.getInt("totalCount");
				}
			}
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return total;
		}
		return total;
	}
	public String getSender()
	{
		return sender;
	}
	public String getReciever()
	{
		return reciever;
	}
	public String getText()
	{
		return text;
	}
}
