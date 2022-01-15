package cabapp;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class ViewRequests extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem i1, i2, i3, i4, i5, i6, i7;
	private Driver driver;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JComboBox comboBox;
	private JLabel label4;
	private JLabel label6;
	private JLabel label8;
	private JLabel ans1;
	private JLabel ans2;
	private JLabel ans4;
	private ArrayList<Booking> bookings;
	private JLabel label7;
	private JButton button1;
	private JLabel type;
	private JLabel lblTimeCreated;
	private JLabel lblSourceCity;
	private JLabel lblDestinationCity;
	private JLabel lblDetails;
	private JLabel ans7;
	private JLabel ans8;
	private JLabel ans9;
	private JLabel lblSelectOrder;
	private JLabel ans5;
	private JLabel ans6;
	private JLabel lblAction;
	
	public ViewRequests(Driver d) {
		driver = d;
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		type = new JLabel("Driver Account");
		type.setOpaque(true);
		type.setForeground(Color.WHITE);
		type.setFont(new Font("Tahoma", Font.PLAIN, 18));
		type.setBackground(Color.RED);
		contentPane.add(type, "cell 5 15");
		
		label1 = new JLabel("Active Requests:");
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label1, "cell 5 26,aligny center");
		
		label2 = new JLabel("0");
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label2, "cell 7 26,aligny center");
		
		lblSelectOrder = new JLabel("Select Request:");
		lblSelectOrder.setForeground(new Color(255, 255, 255));
		lblSelectOrder.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblSelectOrder, "cell 5 38");
		
		bookings = null;
		bookings = Booking.getBookings(driver.getID(), driver.getType(), true);
		bookings = Booking.searchActiveRequests(bookings);
		String[] temp = null;
		if (bookings.size() == 0)
		{
			temp = new String[1];
			temp[0] = "None";
		}
		else
		{
			temp = new String[bookings.size()];
			label2.setText(Integer.toString(bookings.size()));
			for (int i = 0;i < temp.length;i++)
			{
				temp[i] = "Request " + (i + 1);
			}
		}
		
		comboBox = new JComboBox(temp);
		comboBox.addActionListener(this);
		contentPane.add(comboBox, "cell 7 38,aligny center");
		
		label3 = new JLabel("Request#");
		label3.setBackground(new Color(255, 0, 0));
		label3.setForeground(new Color(255, 255, 255));
		label3.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label3.setOpaque(true);
		contentPane.add(label3, "cell 7 54,aligny center");
		
		label4 = new JLabel("Sent By");
		label4.setBackground(new Color(255, 0, 0));
		label4.setForeground(new Color(255, 255, 255));
		label4.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label4.setOpaque(true);
		contentPane.add(label4, "cell 11 54,aligny center");
		
		label7 = new JLabel("Status");
		label7.setBackground(new Color(255, 0, 0));
		label7.setForeground(new Color(255, 255, 255));
		label7.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label7.setOpaque(true);
		contentPane.add(label7, "cell 16 54,aligny center");
		
		label8 = new JLabel("Date Created");
		label8.setBackground(new Color(255, 0, 0));
		label8.setForeground(new Color(255, 255, 255));
		label8.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label8.setOpaque(true);
		contentPane.add(label8, "cell 21 54");
		
		lblTimeCreated = new JLabel("Time Created");
		lblTimeCreated.setOpaque(true);
		lblTimeCreated.setForeground(Color.WHITE);
		lblTimeCreated.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblTimeCreated.setBackground(Color.RED);
		contentPane.add(lblTimeCreated, "cell 26 54");
		
		ans1 = new JLabel("none");
		ans1.setForeground(new Color(255, 255, 255));
		ans1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans1, "cell 7 61,aligny center");
		
		ans2 = new JLabel("none");
		ans2.setForeground(new Color(255, 255, 255));
		ans2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans2, "cell 11 61,aligny center");
		
		ans4 = new JLabel("none");
		ans4.setForeground(new Color(255, 255, 255));
		ans4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans4, "cell 16 61,aligny center");
		
		ans5 = new JLabel("none");
		ans5.setForeground(new Color(255, 255, 255));
		ans5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans5, "cell 21 61,aligny center");
		
		ans6 = new JLabel("none");
		ans6.setForeground(new Color(255, 255, 255));
		ans6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans6, "cell 26 61,aligny center");
		
		lblSourceCity = new JLabel("Source City");
		lblSourceCity.setOpaque(true);
		lblSourceCity.setForeground(Color.WHITE);
		lblSourceCity.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblSourceCity.setBackground(Color.RED);
		contentPane.add(lblSourceCity, "cell 7 69");
		
		lblDestinationCity = new JLabel("Destination City");
		lblDestinationCity.setOpaque(true);
		lblDestinationCity.setForeground(Color.WHITE);
		lblDestinationCity.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblDestinationCity.setBackground(Color.RED);
		contentPane.add(lblDestinationCity, "cell 11 69");
		
		lblDetails = new JLabel("Details");
		lblDetails.setOpaque(true);
		lblDetails.setForeground(Color.WHITE);
		lblDetails.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblDetails.setBackground(Color.RED);
		contentPane.add(lblDetails, "cell 16 69");
		
		lblAction = new JLabel("Action");
		lblAction.setOpaque(true);
		lblAction.setForeground(Color.WHITE);
		lblAction.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblAction.setBackground(Color.RED);
		contentPane.add(lblAction, "cell 21 69");
		
		ans7 = new JLabel("none");
		ans7.setForeground(Color.WHITE);
		ans7.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans7, "cell 7 76");
		
		ans8 = new JLabel("none");
		ans8.setForeground(Color.WHITE);
		ans8.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans8, "cell 11 76");
		
		ans9 = new JLabel("none");
		ans9.setForeground(Color.WHITE);
		ans9.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(ans9, "cell 16 76");
		
		button1 = new JButton("Accept Request");
		button1.setForeground(new Color(255, 255, 255));
		button1.setBackground(new Color(255, 0, 0));
		button1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(button1, "cell 21 76,aligny center");
		button1.addActionListener(this);
		
		if (bookings.size() > 0)
		{
			int index = 0;
			ans1.setText(Integer.toString(index + 1));
			ans2.setText(bookings.get(index).getCustomer().getUserName());
			ans4.setText("Waiting");
			ans5.setText(bookings.get(index).getDateCreated().toString());
			ans6.setText(bookings.get(index).getTimeCreated());
			ans7.setText(bookings.get(index).getSource().getCity());
			ans8.setText(bookings.get(index).getDestination().getCity());
			ans9.setText(bookings.get(index).getDetails());
		}
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
	}

	public void actionPerformed(ActionEvent e) {
		Person p = driver;
		String title = e.getActionCommand();
		if (title.equals("Home")) {
			setVisible(false);
			Home home = new Home(p);
			home.setVisible(true);
		} 
		else if (title.equals("Profile")) {
			setVisible(false);
			Profile pf = new Profile(p);
			pf.setVisible(true);
		} 
		else if (title.equals("Bookings")) {
			setVisible(false);
			ViewBookings b = new ViewBookings(p);
			b.setVisible(true);
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
		else if (title.equals("Accept Request")) 
		{
			if (bookings.size() <= 0)
			{
				JOptionPane.showMessageDialog(null, "No request to accept!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Booking current = bookings.get(comboBox.getSelectedIndex());
			current.setDriver(driver);
			Booking.acceptRequest(current.getBookingID(), current.getDriver().getID());
			this.setVisible(false);
			ViewRequests view = new ViewRequests(driver);
			view.setVisible(true);
			JOptionPane.showMessageDialog(null, "Order created!","Success", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JComboBox temp = null;
		if (e.getSource() instanceof JComboBox) {
			temp = (JComboBox) e.getSource();
			int index = temp.getSelectedIndex();
			ans1.setText(Integer.toString(index + 1));
			ans2.setText(bookings.get(index).getCustomer().getUserName());
			ans4.setText("Waiting");
			ans5.setText(bookings.get(index).getDateCreated().toString());
			ans6.setText(bookings.get(index).getTimeCreated());
			ans7.setText(bookings.get(index).getSource().getCity());
			ans8.setText(bookings.get(index).getDestination().getCity());
			ans9.setText(bookings.get(index).getDetails());
		}
	}

}
