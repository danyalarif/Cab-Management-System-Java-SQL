package cabapp;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Home extends JFrame implements ActionListener{
	private Customer customer;
	private Driver driver;
	private JPanel contentPane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JLabel lblNewLabel;
	private JLabel type;
	private JButton button7;
	private JButton button8;
	private JComboBox comboBox;
	private Driver[] drivers;
	private Customer[] customers;
	private JButton button9;
	private JTextField textField;
	private JLabel Msghead;
	public Home(Person p) 
	{
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		button1 = new JButton("Home");
		button1.setForeground(new Color(255, 0, 0));
		button1.setBackground(new Color(250, 218, 221));
		button1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button1.addActionListener(this);
		contentPane.add(button1, "cell 0 0,alignx right");
		
		button2 = new JButton("Profile");
		button2.setForeground(Color.RED);
		button2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button2.setBackground(new Color(250, 218, 221));
		button2.addActionListener(this);
		contentPane.add(button2, "cell 1 0,alignx left");
		
		button3 = new JButton("Bookings");
		button3.setForeground(Color.RED);
		button3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button3.setBackground(new Color(250, 218, 221));
		button3.addActionListener(this);
		contentPane.add(button3, "cell 2 0,alignx left");
		
		button4 = new JButton("Payments");
		button4.setForeground(Color.RED);
		button4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button4.setBackground(new Color(250, 218, 221));
		button4.addActionListener(this);
		contentPane.add(button4, "cell 3 0,alignx right");
		
		button5 = new JButton("Logout");
		button5.setForeground(Color.RED);
		button5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button5.setBackground(new Color(250, 218, 221));
		button5.addActionListener(this);
		contentPane.add(button5, "cell 4 0,alignx right");
		
		button6 = new JButton("Requests");
		button6.setForeground(Color.RED);
		button6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button6.setBackground(new Color(250, 218, 221));
		button6.addActionListener(this);
		contentPane.add(button6, "cell 5 0,alignx right");
		
		button7 = new JButton("Messages");
		button7.setForeground(Color.RED);
		button7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button7.setBackground(new Color(250, 218, 221));
		button7.addActionListener(this);
		contentPane.add(button7, "cell 6 0,alignx right");
		
		lblNewLabel = new JLabel("CAB APP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(lblNewLabel, "cell 0 8,alignx left");
		
		type = new JLabel("New label");
		type.setBackground(new Color(255, 0, 0));
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setForeground(new Color(255, 255, 255));
		type.setOpaque(true);
		contentPane.add(type, "cell 0 14,alignx left,aligny center");
		
		if (customer == null)
		{
			type.setText("Driver Account");
		}
		else
		{
			type.setText("Customer Account");
		}
		label1 = new JLabel("User name");
		label1.setBackground(new Color(255, 0, 0));
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(label1, "cell 0 19,alignx center,aligny center");
		label1.setOpaque(true);
		label1.setText(p.getUserName());
		
		label2 = new JLabel("New label");
		label2.setForeground(new Color(255, 255, 255));
		label2.setBackground(new Color(255, 0, 0));
		label2.setOpaque(true);
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(label2, "cell 0 53,alignx center,aligny center");
		
		String s = "";
		if (customer != null)
		{
			label2.setText("Available Drivers");
			drivers = Driver.getAllDrivers();
			for (int i = 0;i < drivers.length;i++)
			{
				if (i != 0)
					s += ", ";
				s += drivers[i].getUserName();
			}
		}
		else
		{
			label2.setText("Available Customers");
			customers = Customer.getAllCustomers();
			for (int i = 0;i < customers.length;i++)
			{
				if (i != 0)
					s += ", ";
				s += customers[i].getUserName();
			}
		}
		label3 = new JLabel("New label");
		label3.setForeground(new Color(255, 255, 255));
		label3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		contentPane.add(label3, "cell 0 56");
		label3.setText(s);
		
		button8 = new JButton("Contact");
		button8.setForeground(Color.RED);
		button8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button8.setBackground(new Color(250, 218, 221));
		button8.addActionListener(this);
		contentPane.add(button8, "cell 0 59,alignx center,aligny center");
		
		Msghead = new JLabel("Your Message:");
		Msghead.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Msghead.setForeground(Color.WHITE);
		contentPane.add(Msghead, "flowx,cell 0 79");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBackground(new Color(250, 218, 221));
		contentPane.add(textField, "cell 0 79,alignx center,aligny center");
		
		button9 = new JButton("Send");
		button9.setForeground(Color.RED);
		button9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button9.setBackground(new Color(250, 218, 221));
		button9.addActionListener(this);
		contentPane.add(button9, "cell 0 90,alignx center,aligny top");
		if (driver == null)
			button6.setVisible(false);
		if (customer != null)
			button8.setText("Contact Driver");
		else
			button8.setText("Contact Customer");
		Msghead.setVisible(false);
		textField.setVisible(false);
		button9.setVisible(false);
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
		else if (title.equals("Messages"))
		{
			setVisible(false);
			Chatting ch = new Chatting(p);
			ch.setVisible(true);
		}
		else if (title.equals("Logout"))
		{
			setVisible(false);
			Main mn = new Main(false);
			mn.setVisible(true);
		}
		else if (title.equals("Contact Customer"))
		{
			Msghead.setVisible(true);
			textField.setVisible(true);
			button9.setVisible(true);
			String usernames[] = new String[customers.length];
			for (int i = 0;i < usernames.length;i++)
			{
				usernames[i] = customers[i].getUserName();
			}
			comboBox = new JComboBox(usernames);
			comboBox.setBackground(new Color(255, 140, 0));
			comboBox.setFont(new Font("Tahoma", Font.ITALIC, 20));
			contentPane.add(comboBox, "cell 0 69,alignx center,aligny center");
			comboBox.revalidate();
		}
		else if (title.equals("Contact Driver"))
		{
			Msghead.setVisible(true);
			textField.setVisible(true);
			button9.setVisible(true);
			String usernames[] = new String[drivers.length];
			for (int i = 0;i < usernames.length;i++)
			{
				usernames[i] = drivers[i].getUserName();
			}
			comboBox = new JComboBox(usernames);
			comboBox.setBackground(new Color(255, 140, 0));
			comboBox.setFont(new Font("Tahoma", Font.ITALIC, 20));
			contentPane.add(comboBox, "cell 0 69,alignx center,aligny center");
			comboBox.revalidate();
		}
		else if (title.equals("Send"))
		{
			String sender = p.getUserName();
			String reciever;
			if (customer != null)
				reciever = drivers[comboBox.getSelectedIndex()].getUserName();
			else
				reciever = customers[comboBox.getSelectedIndex()].getUserName();
			String txt = textField.getText().toString();
			if (txt.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Message cannot be empty!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Message m = new Message(sender, reciever, txt, p.getType());
			m.saveMessage();
			JOptionPane.showMessageDialog(this, "Message sent successfully!","Success", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			Chatting chat = new Chatting(p);
			chat.setVisible(true);
			chat.selectUser(reciever);
		}
	}
}
