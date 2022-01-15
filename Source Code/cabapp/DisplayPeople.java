package cabapp;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class DisplayPeople extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JLabel head;
	private JScrollPane pane;
	private JButton button;

	public DisplayPeople() {
		setTitle("Cab App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		head = new JLabel();
		head.setHorizontalAlignment(SwingConstants.CENTER);
		head.setFont(new Font("SansSerif", Font.PLAIN, 24));
		head.setForeground(Color.WHITE);
		contentPane.add(head, BorderLayout.NORTH);
		button = new JButton("Go back");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBackground(Color.RED);
		button.addActionListener(this);
		contentPane.add(button, BorderLayout.SOUTH);
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
	    pane = new JScrollPane(table);
		contentPane.add(pane);
        pane.setViewportView(table);
        pane.setViewportView(table);
        pane.getHorizontalScrollBar().setBackground(new Color(0,0,0));
        pane.getVerticalScrollBar().setBackground(new Color(0,0,0));
        DBTools.styleTable(table);
	}
	public void insertIntoTable(String query, String head)
	{
		this.head.setText(head);
        DBTools.fillTable(table,query);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String t = e.getActionCommand();
		if (t.equals("Go back"))
		{
			setVisible(false);
			AdminHome r = new AdminHome();
			r.setVisible(true);
		}
		
	}
}
