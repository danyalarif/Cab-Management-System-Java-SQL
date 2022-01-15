package cabapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Chatting extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JTextArea textArea;
	private Hashtable<String, ArrayList<Message>> msgs;
	private String usernames[];
	private int count[];
	private String userid;
	private String type;
	private JLabel label;
	private JLabel label2;
	private JLabel labelCount;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem i1, i2, i3, i4, i5, i6, i7;
	private Person p;
	
	public Chatting(Person p) {
		this.p = p;
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		userid = p.getUserName();
		type = p.getType();
		msgs = Message.getMessages(p.getID(), userid);
		Set<String> idSet = msgs.keySet();
		int ids[] = new int[msgs.size()];
		int c = 0;
		for (String temp: idSet)
		{
			ids[c] = Person.getPersonID(temp);
			c++;
		}
		count = Message.getMessagesCount(p.getID(), p.getType(), ids);
		usernames = new String[msgs.size()];
		int count = 0;
		for (String temp : idSet)
		{
			usernames[count] = temp;
			count++;
		}
		boolean b = false;
		if (p instanceof Customer)
		{
			b = true;
		}
		comboBox = new JComboBox(usernames);
		comboBox.setBackground(new Color(255, 140, 0));
		comboBox.setFont(new Font("Tahoma", Font.ITALIC, 20));
		comboBox.setOpaque(true);
		comboBox.addActionListener(this);
		
		label = new JLabel("Select Contact:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(label, "cell 9 15");
		contentPane.add(comboBox, "cell 11 15,alignx center,aligny center");
		
		label2 = new JLabel("Message Count:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(label2, "cell 9 27");
		
		labelCount = new JLabel("0");
		labelCount.setForeground(Color.WHITE);
		labelCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(labelCount, "cell 11 27,alignx center,aligny center");
		
		textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBackground(new Color(255, 182, 193));
		textArea.setEditable(false);
		textArea.setColumns(100);
		textArea.setRows(100);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(textArea, "cell 11 33,grow");
		
		textField = new JTextField();
		textField.setBackground(new Color(152, 251, 152));
		contentPane.add(textField, "flowx,cell 11 34,growx");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		
		JButton sendButton = new JButton("Send");
		sendButton.setBackground(Color.MAGENTA);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(sendButton, "cell 11 34,alignx left,aligny center");
		sendButton.addActionListener(this);
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
		if (p.getType().equalsIgnoreCase("customer"))
			i5.setVisible(false);
		if (msgs.size() == 0)
		{
			comboBox.setVisible(false);
			label.setVisible(false);
			sendButton.setVisible(false);
			textField.setVisible(false);
			textArea.setText("No Messages found!");
			label2.setVisible(false);
			labelCount.setVisible(false);
			return;
		}
		loadMessages();
	}
	public void loadMessages()
	{
		String txt = "";
		ArrayList<Message> current = msgs.get(usernames[comboBox.getSelectedIndex()]);
		for (int i = 0;i < current.size();i++)
		{
			Message m = current.get(i);
			String s = m.getSender();
			String r = m.getReciever();
			String t = m.getText();
			if (s.equals(userid))
			{
				txt += "Me: " + t + "\n";
			}
			else
			{
				txt += usernames[comboBox.getSelectedIndex()] + ": " + t + "\n";
				//System.out.println(people[comboBox.getSelectedIndex()].getUserName());
			}
		}
		textArea.setText(txt);
		labelCount.setText(Integer.toString(count[comboBox.getSelectedIndex()]));
	}
	public void actionPerformed(ActionEvent e)
	{
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
			ViewRequests view = new ViewRequests((Driver)p);
			view.setVisible(true);
		}
		else if (title.equals("Logout"))
		{
			setVisible(false);
			Main mn = new Main(false);
			mn.setVisible(true);
		}
		else if (title.equals("Send"))
		{
			String sender = new String(userid);
			String reciever = usernames[comboBox.getSelectedIndex()];
			String txt = textField.getText().toString();
			String tempText = textArea.getText();
			tempText += "Me: " + txt + "\n";
			textArea.setText(tempText);
			Message m = new Message(sender, reciever, txt, type);
			m.saveMessage();
			textField.setText("");
			//Saving message to array
			ArrayList<Message> current = msgs.get(usernames[comboBox.getSelectedIndex()]);
			current.add(m);
			msgs.put(sender, current);
		}
		JComboBox temp = null;
		if (e.getSource() instanceof JComboBox)
		{
			loadMessages();
		}
	}
	public void selectUser(String username)
	{
		int index = 0;
		for (int i = 0;i < usernames.length;i++)
		{
			if (usernames[i].equals(username))
			{
				index = i;
				break;
			}
		}
		comboBox.setSelectedIndex(index);
		loadMessages();
	}
}
