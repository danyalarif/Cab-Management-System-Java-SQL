package cabapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date{
	private int day;
	private int month;
	private int year;
	public Date()
	{
		day = 1;
		month = 1;
		year = 2000;
	}
	public Date(int day, int month, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public Date(Date other)
	{
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String toString()		//newly created toString
	{	
		return String.format("%d-%d-%d", year, month, day);
	}
	public static Date getTodaysDate()
	{
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int current_month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int current_date = Calendar.getInstance().get(Calendar.DATE);
		return new Date(current_date, current_month, current_year);
	}
	public static String getCurrentTime()
	{
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
        java.util.Date date = new java.util.Date();  
        String time = formatter.format(date); 
        return time;
	}
}
