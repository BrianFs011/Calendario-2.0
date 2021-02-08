package entities;

import java.io.File;

public class Agenda {

	private int    day;
	private int    month;
	private int    year;
	private String note;
	private double value;
	
	public Agenda(int day, int month, int year) {
		this.day   = day;
		this.month = month;
		this.year  = year;
	}
		
	public Agenda(int day, int month, int year, String note, double value) {
		this.day   = day;
		this.month = month;
		this.year  = year;
		this.note  = note;
		this.value = value;
		
		String save = "E:\\ws-eclipse\\Calendario 2.0\\save";
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		boolean success = new File(save+ convert).mkdir();
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

	public String getNote() {
		return note;
	}

	public double getValue() {
		return value;
	}
}
