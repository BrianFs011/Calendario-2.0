package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.UI;

public class Agenda {

	private int    day;
	private int    month;
	private int    year;
	private String note;
	private double value;
	private String save = "E:\\ws-eclipse\\Calendario 2.0\\save";
	
	public Agenda() {

	}
	
	public Agenda(int day, int month, int year, String note, double value) {
		this.day   = day;
		this.month = month;
		this.year  = year;
		
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		@SuppressWarnings("unused")
		boolean success = new File(save+ convert).mkdir();		
			
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
	
	public String getSave() {
		return save;
	}

	public String path() {
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		String path = save + convert + "\\note.txt";
		return path;	
	}	
	
	public void newFolder(){
		
	}
	
	public void write() {
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		String path = save + convert + "\\note.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			bw.write(note+" R$-"+String.format("%.2f", value));
			bw.newLine();
			System.out.println(UI.ANSI_GREEN+"SAVE"+UI.ANSI_RESET);
		}
		catch(IOException e) {
			System.out.println("!Fogo no parquinho escrita Agenda!");
			e.printStackTrace();
		}
	}
}
