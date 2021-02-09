package entities;

import java.io.File;

public class Agenda {

	private int    day;
	private int    month;
	private int    year;
	private String note;
	private double value;
	private String save = "E:\\ws-eclipse\\Calendario 2.0\\save";
	
	public Agenda(int day, int month, int year) {
		this.day   = day;
		this.month = month;
		this.year  = year;
		
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		
		boolean success = new File(save+ convert).mkdir();
			for(int i=0; i>0; i++) {
			System.out.println("Nova pasta criada "+ success);
		}		
	}
		
	public Agenda(String note, double value) {
		this.note  = note;
		this.value = value;

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
	
	public String save() {
		return save;
	}

	public String path() {
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		String path = save + convert + "\\note.txt";
		return path;	
	}	
}
