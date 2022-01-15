package cabapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public final class DBTools {
	private static String dbUrl = "jdbc:oracle:thin:@localhost:1521:localDB";
	private static String username = "c##danyal";
	private static String password = "admin810";
	public static String getDBUrl()
	{
		return dbUrl;
	}
	public static String getUserName()
	{
		return username;
	}
	public static String getPassword()
	{
		return password;
	}
	public static void styleTable(JTable table)
	{
		table.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		table.setBackground(new Color(0, 0, 0));
		//table.setForeground(new Color(153, 50, 204));
		table.setForeground(new Color(255, 255, 255));
	    table.setFocusable(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(232, 57, 95));
        table.setShowVerticalLines(false);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0,0,0));
        header.setForeground(new Color(232, 57, 95));
        header.setReorderingAllowed(false);
        header.setFont(new Font("Georgia", Font.BOLD, 18));
	}
	public static void fillTable(JTable table, String query){
		int columnCount = 0;
		Connection conn = null;
		try {
			int rows = table.getRowCount();
			conn = DriverManager.getConnection(dbUrl,username,password);
			Statement stat = conn.createStatement();
		    ResultSet rs = stat.executeQuery(query);
	        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	        ResultSetMetaData metaData = rs.getMetaData();
	        columnCount = metaData.getColumnCount();
	        if (rows == 0)			//only add columns if table is empty
	        {
	        	for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
	        	{
	        		tableModel.addColumn(metaData.getColumnLabel(columnIndex));
	        	}
	        }
	        Object[] row = new Object[columnCount];
	        while (rs.next()){
	            for (int i = 0; i < columnCount; i++){
	                row[i] = rs.getObject(i+1);
	            }
	            tableModel.addRow(row);
	        }
	        table.setModel(tableModel);
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
		//center enteries
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 for(int i = 0; i < columnCount;i++)
		 {
	         table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	     }
    }
	public static void fillTable(JTable table, ResultSet rs){
		 int columnCount = 0;
		try {
			int rows = table.getRowCount();
	        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	        ResultSetMetaData metaData = rs.getMetaData();
	        columnCount = metaData.getColumnCount();
	        if (rows == 0)			//only add columns if table is empty
	        {
	        	for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
	        	{
	        		tableModel.addColumn(metaData.getColumnLabel(columnIndex));
	        	}
	        }
	        Object[] row = new Object[columnCount];
	        while (rs.next())
	        {
	            for (int i = 0; i < columnCount; i++){
	                row[i] = rs.getObject(i+1);
	            }
	            tableModel.addRow(row);
	        }
	        table.setModel(tableModel);
		} catch (SQLException e) {
			DBTools.ShowErrorMessage();
			return;
		}
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 for(int i = 0; i < columnCount;i++)
		 {
	         table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	     }
    }
	public static void ShowErrorMessage()
	{
		JOptionPane.showMessageDialog(null, "Invalid Username or Password!","Error", JOptionPane.ERROR_MESSAGE);
	}
}
