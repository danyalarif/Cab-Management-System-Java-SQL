package cabapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RemoveAccount extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSearch;
	private JButton btnDelete;
	private JLabel result;
	private int pid;
	private int type;		//0 customer 1 driver
	private JButton btnGoBack;

	public RemoveAccount() {
		pid = -1;
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][pref!,grow,center][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		
		JLabel lblEnterName = new JLabel("Enter username:");
		lblEnterName.setForeground(Color.WHITE);
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblEnterName, "cell 6 22");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBackground(new Color(250, 218, 221));
		contentPane.add(textField, "cell 9 22,growx");
		
		btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearch.setBackground(Color.RED);
		contentPane.add(btnSearch, "cell 8 37");
		
		result = new JLabel("res");
		result.setForeground(Color.WHITE);
		result.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(result, "cell 8 49,alignx center,aligny center");
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBackground(Color.RED);
		contentPane.add(btnDelete, "cell 8 59");
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBackground(Color.RED);
		btnGoBack.addActionListener(this);
		contentPane.add(btnGoBack, "cell 8 75");
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		
		btnDelete.setVisible(false);
		result.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt = e.getActionCommand();
		if (txt.equals("Search"))
		{
			String user = textField.getText().toString();
			Connection conn = null;
	        String dbURL = DBTools.getDBUrl();
	        String u = DBTools.getUserName();
	        String p = DBTools.getPassword();
	        try {
				conn = DriverManager.getConnection(dbURL, u, p);
				String query = "select personid from person where username = ?"; 
				PreparedStatement s = conn.prepareStatement(query);
				s.setString(1, user);
				ResultSet rs = s.executeQuery();
				while (rs.next())
				{
					pid = rs.getInt("personid");
				}
				conn.close();
			} catch (SQLException e1) {
				DBTools.ShowErrorMessage();
				return;
			}
        	result.setVisible(true);
	        if (pid != -1)
	        {
	        	result.setText("User " + user + " found successfully!");
	        	btnDelete.setVisible(true);
	        }
	        else
	        {
	        	result.setText("User " + user + " not found!");
	        }
		}
		else if (txt.equals("Delete"))
		{
			Connection conn = null;
	        String dbURL = DBTools.getDBUrl();
	        String u = DBTools.getUserName();
	        String p = DBTools.getPassword();
	        try {
				conn = DriverManager.getConnection(dbURL, u, p);
				String query = "delete from Person where personid = ?"; 
				PreparedStatement s = conn.prepareStatement(query);
				s.setInt(1, pid);
				s.executeUpdate();
				conn.close();
			} catch (SQLException e1) {
				DBTools.ShowErrorMessage();
				return;
			}
	        JOptionPane.showMessageDialog(this, "Account Deleted!","Success", JOptionPane.INFORMATION_MESSAGE);
	        result.setVisible(false);
	        btnDelete.setVisible(false);
		}
		else if (txt.equals("Go Back"))
		{
			setVisible(false);
			AdminHome r = new AdminHome();
			r.setVisible(true);
		}
	}

}
