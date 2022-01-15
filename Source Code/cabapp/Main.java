package cabapp;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Main extends JFrame implements ActionListener{
	private Person p;
	private JPanel contentPane;
	private JPanel contentPane2;
	private JTextField textField1;
	private JTextField textField2;
	private JButton button1;
	private JButton loginButton;
	private JButton signupButton;
	private Customer customer;
	private Driver driver;
	private JPasswordField passwordField;
	private boolean b;
	private int globalid;
	private JLabel lblNewLabel;
	private JLabel type;
	private JButton forgotButton, button2;
	private JLabel label3, label2, label1;
	private String globalUser;
	private JButton button3;
	private JButton btnGoBack;
	public static void main(String[] args) 
	{
		Main frame = new Main(false);
		frame.setVisible(true);
	}

	public Main(boolean b) {
		this.b = b;
		globalUser = null;
		globalid = 0;
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		setContentPane(contentPane);
		
		button1 = new JButton("Login as Customer");
		button1.setForeground(new Color(255, 255, 255));
		button1.setBackground(new Color(255, 0, 0));
		button1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		button1.addActionListener(this);
		
		lblNewLabel = new JLabel("Cab App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel, "cell 22 13,alignx center,aligny center");
		button1.setFocusable(false);
		contentPane.add(button1, "cell 22 45,alignx center,aligny center");
		button2 = new JButton("Login as Driver");
		button2.setForeground(new Color(255, 255, 255));
		button2.setBackground(new Color(255, 0, 0));
		button2.setFont(new Font("Tahoma", Font.PLAIN, 29));
		button2.addActionListener(this);
		button2.setFocusable(false);
		contentPane.add(button2, "cell 22 56,alignx center,aligny center");
		
		button3 = new JButton("Login as Admin");
		button3.setForeground(Color.WHITE);
		button3.setFont(new Font("Tahoma", Font.PLAIN, 29));
		button3.setFocusable(false);
		button3.setBackground(Color.RED);
		button3.addActionListener(this);
		contentPane.add(button3, "cell 22 67,alignx center,aligny center");
		screen2();
	}
	public void actionPerformed(ActionEvent e)
	{
		String title = e.getActionCommand();
		if (title.equals("Login as Customer"))
		{
			b = true;
			contentPane.setVisible(false);
			contentPane2.setVisible(true);
			setContentPane(contentPane2);
			type.setText("Customer Login");
		}
		else if (title.equals("Login as Driver"))
		{
			contentPane.setVisible(false);
			contentPane2.setVisible(true);
			setContentPane(contentPane2);
			type.setText("Driver Login");
		}
		else if (title.equals("Login as Admin"))
		{
			contentPane.setVisible(false);
			screen3();
			type.setText("Admin Login");
			int count = this.getAdmins();
			if (count == 0)
			{	
				textField2 = new JTextField();
				textField2.setBackground(new Color(250, 218, 221));
				textField2.setFont(new Font("Tahoma", Font.PLAIN, 20));
				textField2.setColumns(10);
				contentPane2.add(textField2, "cell 17 21,alignx center,aligny center");
				JLabel labeltemp = new JLabel("ID:");
				labeltemp.setForeground(new Color(255, 255, 255));
				labeltemp.setFont(new Font("Tahoma", Font.PLAIN, 20));
				contentPane2.add(labeltemp, "cell 13 21,alignx left");
				loginButton.setText("Create");
				JOptionPane.showMessageDialog(this, "No login Found!Please signup now!","Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Login found!Please login now!","Info", JOptionPane.INFORMATION_MESSAGE);
				loginButton.setText("Go");
			}
			contentPane2.revalidate();
			contentPane2.repaint();
		}
		else if (title.equals("Sign Up"))
		{
			contentPane2.setVisible(false);
			setVisible(false);				//set frame false
			Registration reg = new Registration(b);
			reg.setVisible(true);
		}
		else if (title.equals("Login"))
		{
			boolean res;
			String user, pass;
			user = textField1.getText().toString();
			pass = new String(passwordField.getPassword());
			if (b)
			{
	        	customer = Customer.getCustomerData(user, pass);
	        	if (customer != null)
				{
					customer.LoadPerson();
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid Username or Password!","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				driver = Driver.getDriverData(user, pass);
				if (driver != null)
				{
					setVisible(false);
					driver.LoadPerson();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid Username or Password!","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (title.equals("Click here"))
		{
			textField1.setText("");
			label2.setVisible(false);
			label3.setVisible(false);
			passwordField.setVisible(false);
			signupButton.setVisible(false);
			loginButton.setText("Search");
			forgotButton.setText("Go back");
			JOptionPane.showMessageDialog(this, "Enter your username!","Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (title.equals("Search"))
		{
			boolean found = false;
			String user = textField1.getText().toString();
			Connection conn = null;
	        String dbURL = DBTools.getDBUrl();
	        String u = DBTools.getUserName();
	        String p = DBTools.getPassword();
	        int pid = 0;
	        try {
				conn = DriverManager.getConnection(dbURL, u, p);
				String query = null;
				query = "SELECT personid from PERSON where username = ?";
			    PreparedStatement s = conn.prepareStatement(query);
			    s.setString(1, user);
				ResultSet rs = s.executeQuery();
                while (rs.next()) 
                {
                	pid = rs.getInt("personid");
                	found = true;
                }
                if (!(found))
    			{
    				JOptionPane.showMessageDialog(this, "Username not found!","Error", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    	        conn.close();
			} catch (SQLException e2) {
				DBTools.ShowErrorMessage();
				return;
			}
	        globalUser = new String(user);			//storing the current searched username globally
	        globalid = pid;
			textField1.setText("");
			JOptionPane.showMessageDialog(this, "Enter your new password!","Success", JOptionPane.INFORMATION_MESSAGE);
			label1.setText("Password");
			loginButton.setText("Save");
		}
		else if (title.equals("Go back"))
		{
			setVisible(false);
			Main m = new Main(b);
			m.setVisible(true);
			m.setScreen2();
		}
		else if (title.equals("Save"))
		{
			String pass = textField1.getText().toString();
			Person temp = new Customer("None",pass);
			boolean res = temp.validatePassword();
			if (!(res))
			{
				JOptionPane.showMessageDialog(this, "Invalid Password!Create a strong password!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Connection conn = null;
	        String dbURL = DBTools.getDBUrl();
	        String u = DBTools.getUserName();
	        String p = DBTools.getPassword();
	        try {
				conn = DriverManager.getConnection(dbURL, u, p);
				String query = null;
				query = "UPDATE PERSON set password = ? where personid = ?";
			    PreparedStatement s = conn.prepareStatement(query);
			    s.setString(1, pass);
			    s.setInt(2, globalid);
			    s.executeUpdate();
			} catch (SQLException e2) {
				DBTools.ShowErrorMessage();
				return;
			}
	        globalUser = null;
	        globalid = 0;
			setVisible(false);
			Main m = new Main(b);
			m.setVisible(true);
			m.setScreen2();
			JOptionPane.showMessageDialog(this, "Password updated successfully!","Success", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (title.equals("Create"))
		{
			this.saveAdmin();
			JOptionPane.showMessageDialog(this, "Admin Created!You can login now","Success", JOptionPane.INFORMATION_MESSAGE);
			button3.doClick();
		}
		else if (title.equals("Go"))
		{
			if (!(this.validateAdminLogin()))
			{
				JOptionPane.showMessageDialog(this, "Invalid username or password!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			setVisible(false);
			AdminHome admin = new AdminHome();
			admin.setVisible(true);
		}
		else if (title.equals("Go Back"))
		{
			setVisible(false);
			Main m = new Main(false);
			m.setVisible(true);
		}
	}

	public void screen2()
	{
		contentPane2 = new JPanel();
		contentPane2.setBackground(new Color(0, 0, 0));
		contentPane2.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][grow][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		type = new JLabel("New label");
		type.setBackground(new Color(255, 0, 0));
		type.setForeground(new Color(255, 255, 255));
		type.setFont(new Font("Tahoma", Font.PLAIN, 20));
		type.setOpaque(true);
		contentPane2.add(type, "cell 16 15,alignx center,aligny center");
		label1 = new JLabel("Username:");
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane2.add(label1, "cell 13 31,alignx right");
		
		textField1 = new JTextField();
		textField1.setBackground(new Color(250, 218, 221));
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField1.setColumns(10);
		contentPane2.add(textField1, "cell 17 31,alignx center,aligny center");
		
		label2 = new JLabel("Password:");
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane2.add(label2, "cell 13 41,alignx right");
		
		loginButton = new JButton("Login");
		loginButton.setBackground(new Color(255, 0, 0));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(250, 218, 221));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		contentPane2.add(passwordField, "cell 17 41,alignx center,aligny center");
		contentPane2.add(loginButton, "cell 16 54,alignx center,aligny center");
		
		signupButton = new JButton("Sign Up");
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setBackground(new Color(255, 0, 0));
		signupButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		signupButton.addActionListener(this);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBackground(Color.RED);
		btnGoBack.addActionListener(this);
		contentPane2.add(btnGoBack, "cell 13 64,alignx center,aligny center");
		contentPane2.add(signupButton, "cell 16 64,alignx center,aligny center");
		
		label3 = new JLabel("Forgot Password?");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane2.add(label3, "cell 13 74");
		
		forgotButton = new JButton("Click here");
		forgotButton.setForeground(Color.WHITE);
		forgotButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		forgotButton.setBackground(Color.RED);
		contentPane2.add(forgotButton, "cell 13 80,alignx center,aligny center");
		forgotButton.addActionListener(this);
		if (b)
		{
			type.setText("Customer Login");
		}
		else
		{
			type.setText("Driver Login");
		}
	}
	public void setScreen2()
	{
		screen2();
		setContentPane(contentPane2);
	}
	public void screen3()
	{
		contentPane2 = new JPanel();
		contentPane2.setBackground(new Color(0, 0, 0));
		contentPane2.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][grow][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		type = new JLabel("New label");
		type.setBackground(new Color(255, 0, 0));
		type.setForeground(new Color(255, 255, 255));
		type.setFont(new Font("Tahoma", Font.PLAIN, 20));
		type.setOpaque(true);
		contentPane2.add(type, "cell 16 15,alignx center,aligny center");
		label1 = new JLabel("Username:");
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane2.add(label1, "cell 13 31,alignx right");
		
		textField1 = new JTextField();
		textField1.setBackground(new Color(250, 218, 221));
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField1.setColumns(10);
		contentPane2.add(textField1, "cell 17 31,alignx center,aligny center");
		
		label2 = new JLabel("Password:");
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane2.add(label2, "cell 13 41,alignx right");
		
		loginButton = new JButton("Login");
		loginButton.setBackground(new Color(255, 0, 0));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(250, 218, 221));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		contentPane2.add(passwordField, "cell 17 41,alignx center,aligny center");
		contentPane2.add(loginButton, "cell 16 54,alignx center,aligny center");
		setContentPane(contentPane2);
	}
	public int getAdmins()
	{
		int count = -1;
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
		try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "SELECT COUNT(*) as TOTAL from ADMIN";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			if (rs.next())
				count = rs.getInt("total");
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return 0;
		}
		return count;
	}
	public void saveAdmin()
	{
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
		int id = Integer.parseInt(textField2.getText().toString());
		String username = textField1.getText().toString();
		String password = new String(passwordField.getPassword());
        try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "insert into admin(username,password) values (?,?)"; 
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, username);
			s.setString(2, password);
			s.executeUpdate();
			conn.close();
		} catch (SQLException e1) {
			DBTools.ShowErrorMessage();
			return;
		}
	}
	public boolean validateAdminLogin()
	{
		String user = textField1.getText().toString();
		String pass = new String(passwordField.getPassword());
		String username, password;
		username = password = "none";
		Connection conn = null;
        String dbURL = DBTools.getDBUrl();
        String u = DBTools.getUserName();
        String p = DBTools.getPassword();
		try {
			conn = DriverManager.getConnection(dbURL, u, p);
			String query = "select * from admin where username = ? and password = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, user);
			s.setString(2, pass);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				username = rs.getString("username");
				password = rs.getString("password");
			}
			conn.close();
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return false;
		}
		if (username.equals(user) && password.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
