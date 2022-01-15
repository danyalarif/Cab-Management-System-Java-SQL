package cabapp;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Profile extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel label1, label2, label3, label4, ans1, ans2, ans3, ans4, ans5;
	private Customer customer;
	private Driver driver;
	private JLabel label5;
	private JLabel ans6;
	private JLabel label6;
	private JLabel ans7;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel ans8;
	private JLabel ans9;
	private JLabel ans10;
	private JLabel ans11;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem i1, i2, i3, i4, i5, i6, i7;
	private JLabel type;
	private JLabel citylabel;
	private JLabel cityans;


	public Profile(Person p) {
		setTitle("Cab App");
		boolean b = false;		//true if buyer is sent
		if (p instanceof Customer)		
		{
			customer = (Customer)p;
			b = true;
		}
		else
		{
			driver = (Driver)p;
			b = false;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
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
		
		label4 = new JLabel("Balance:");
		label4.setForeground(new Color(255, 255, 255));
		label4.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		contentPane.add(label4, "cell 41 5");
		
		ans4 = new JLabel("New label");
		ans4.setBackground(new Color(250, 218, 221));
		ans4.setForeground(new Color(255, 0, 0));
		ans4.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ans4.setOpaque(true);
		contentPane.add(ans4, "cell 47 5");
		
		type = new JLabel("New label");
		type.setBackground(new Color(255, 0, 0));
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setForeground(new Color(255, 255, 255));
		type.setOpaque(true);
		contentPane.add(type, "cell 15 14,alignx left");
		if (driver == null)
		{
			type.setText("Customer Account");
		}
		else
		{
			type.setText("Driver account");
		}
		
		ans5 = new JLabel("New label");
		ans5.setBackground(new Color(255, 0, 0));
		ans5.setForeground(new Color(255, 255, 255));
		ans5.setFont(new Font("Monospaced", Font.PLAIN, 24));
		ans5.setOpaque(true);
		contentPane.add(ans5, "cell 21 14,alignx left,aligny top");
		
		label1 = new JLabel("Name:");
		label1.setBackground(new Color(255, 0, 0));
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label1.setOpaque(true);
		contentPane.add(label1, "cell 15 25,aligny center");
		
		ans1 = new JLabel("New label");
		ans1.setForeground(new Color(255, 255, 255));
		ans1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans1, "cell 21 25,aligny center");
		
		label2 = new JLabel("Languages:");
		label2.setBackground(new Color(255, 0, 0));
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label2.setOpaque(true);
		contentPane.add(label2, "cell 15 31,aligny center");
		
		ans2 = new JLabel("New label");
		ans2.setForeground(new Color(255, 255, 255));
		ans2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans2, "cell 21 31,aligny center");
		
		label3 = new JLabel("Joined On:");
		label3.setBackground(new Color(255, 0, 0));
		label3.setForeground(new Color(255, 255, 255));
		label3.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label3.setOpaque(true);
		contentPane.add(label3, "cell 15 37,aligny center");
		
		ans3 = new JLabel("New label");
		ans3.setForeground(new Color(255, 255, 255));
		ans3.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans3, "cell 21 37");
		
		citylabel = new JLabel("City:");
		citylabel.setOpaque(true);
		citylabel.setForeground(Color.WHITE);
		citylabel.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		citylabel.setBackground(Color.RED);
		contentPane.add(citylabel, "cell 15 43");
		
		cityans = new JLabel("New label");
		cityans.setForeground(Color.WHITE);
		cityans.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(cityans, "cell 21 43");
		
		label5 = new JLabel("Account Type:");
		label5.setBackground(new Color(255, 0, 0));
		label5.setForeground(new Color(255, 255, 255));
		label5.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label5.setOpaque(true);
		contentPane.add(label5, "cell 15 52");
		
		ans6 = new JLabel("New label");
		ans6.setForeground(new Color(255, 255, 255));
		ans6.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans6, "cell 21 52");
		
		label6 = new JLabel("Description:");
		label6.setBackground(new Color(255, 0, 0));
		label6.setForeground(new Color(255, 255, 255));
		label6.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label6.setOpaque(true);
		contentPane.add(label6, "cell 15 63");
		
		ans7 = new JLabel("New label");
		ans7.setForeground(new Color(255, 255, 255));
		ans7.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans7, "cell 21 63");
		
		label7 = new JLabel("Skills:");
		label7.setBackground(new Color(255, 0, 0));
		label7.setForeground(new Color(255, 255, 255));
		label7.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label7.setOpaque(true);
		contentPane.add(label7, "cell 15 70");
		
		ans8 = new JLabel("New label");
		ans8.setForeground(new Color(255, 255, 255));
		ans8.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans8, "cell 21 70");
		
		label8 = new JLabel("Experience:");
		label8.setBackground(new Color(255, 0, 0));
		label8.setForeground(new Color(255, 255, 255));
		label8.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label8.setOpaque(true);
		contentPane.add(label8, "cell 15 77");
		
		ans9 = new JLabel("New label");
		ans9.setForeground(new Color(255, 255, 255));
		ans9.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans9, "cell 21 77");
		
		label9 = new JLabel("Salary:");
		label9.setBackground(new Color(255, 0, 0));
		label9.setForeground(new Color(255, 255, 255));
		label9.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label9.setOpaque(true);
		contentPane.add(label9, "cell 15 84");
		
		ans10 = new JLabel("New label");
		ans10.setForeground(new Color(255, 255, 255));
		ans10.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans10, "cell 21 84");
		
		label10 = new JLabel("Licensed:");
		label10.setBackground(new Color(255, 0, 0));
		label10.setForeground(new Color(255, 255, 255));
		label10.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label10.setOpaque(true);
		contentPane.add(label10, "cell 15 91");
		
		ans11 = new JLabel("New label");
		ans11.setForeground(new Color(255, 255, 255));
		ans11.setFont(new Font("Monospaced", Font.PLAIN, 18));
		contentPane.add(ans11, "cell 21 91");
		//customer design ends
		if (b)
		{
			label8.setVisible(false);
			label9.setVisible(false);
			label10.setVisible(false);
			ans9.setVisible(false);
			ans10.setVisible(false);
			ans11.setVisible(false);
			
			label6.setText("Weight:");
			label7.setText("Height:");
			setCustomerData();
		}
		else
		{
			setDriverData();
		}
	}
	public void setCustomerData()
	{
		ans1.setText(customer.getFirstName() + " " + customer.getLastName());
		String langs[] = new String[3];
		String lang = "";
		langs = customer.getLanguages().clone();
		for (int i = 0;i < langs.length;i++)
		{
			if (langs[i].equalsIgnoreCase("none"))
				break;
			if (i != 0)
				lang += ", ";
			lang += langs[i];
		}
		ans2.setText(lang);
		ans3.setText(customer.getDate().toString());
		ans4.setText(Double.toString(customer.getBalance()) + " dollars");
		ans5.setText(customer.getUserName());
		ans6.setText("Customer");
		cityans.setText(customer.getCity());
		ans7.setText(customer.getWeight() + " kg");
		ans8.setText(customer.getHeight() + " m");
	}
	public void setDriverData()
	{
		ans1.setText(driver.getFirstName() + " " + driver.getLastName());
		String langs[] = new String[3];
		String lang = "";
		langs = driver.getLanguages().clone();
		for (int i = 0;i < langs.length;i++)
		{
			if (langs[i].equalsIgnoreCase("none"))
				break;
			if (i != 0)
				lang += ", ";
			lang += langs[i];
		}
		ans2.setText(lang);
		ans3.setText(driver.getDate().toString());
		ans4.setText(Double.toString(driver.getBalance()) + " dollars");
		ans5.setText(driver.getUserName());
		ans6.setText("Driver");
		cityans.setText(driver.getCity());
		ans7.setText(driver.getDescription());
		String[] skills = new String[3];
		skills = driver.getSkills().clone();
		String skill = "";
		for (int i = 0;i < skills.length;i++)
		{
			if (skills[i].equalsIgnoreCase("none"))
				break;
			if (i != 0)
				skill += ", ";
			skill += skills[i];
		}
		ans8.setText(skill);
		int exp = driver.getExperience();
		if (exp == 0)
			ans9.setText("None");
		else if (exp == 1)
			ans9.setText("1 year");
		else
			ans9.setText(exp + " years");
		ans10.setText(Integer.toString(driver.getSalary()));
		boolean l = driver.getLicense();
		if (l)
			ans11.setText("Yes");
		else
			ans11.setText("No");
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
	}
}
