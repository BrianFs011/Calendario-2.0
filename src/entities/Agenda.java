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
	
	public Agenda(int year, int month, int day) {
		this.year  = year;
		this.month = month;
		this.day   = day;
		
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		
		@SuppressWarnings("unused")
		boolean newFolderYear  = new File(save+ convertYear).mkdir();
		@SuppressWarnings("unused")
		boolean newFolderMonth = new File(save+ convertYear+ convertmonth).mkdir();
		@SuppressWarnings("unused")
		boolean folderException= new File(save+ convertYear+ convertmonth+ "\\exception").mkdir();
		
		String path = save+ convertYear+ convertmonth+ "\\exception"+ "\\note.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			bw.write("-0");
			bw.newLine();
		}
		catch(IOException e) {
			System.out.println("!Fogo no parquinho escrita Agenda!");
			e.printStackTrace();
		}
	}
	
	public Agenda(int year, int month, int day, String note, double value) {
		this.year  = year;
		this.month = month;
		this.day   = day;	
		
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		String convertday   ="\\"+String.valueOf(day)  ;
		
		@SuppressWarnings("unused")
		boolean newFolderYear  = new File(save+ convertYear).mkdir();
		@SuppressWarnings("unused")
		boolean newFolderMonth = new File(save+ convertYear+ convertmonth).mkdir();
		@SuppressWarnings("unused")
		boolean newFolderDay   = new File(save+ convertYear+ convertmonth+ convertday).mkdir();
		
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
		return save+"\\"+year+"\\"+month;
	}

	public String path() {
		/*String convert      ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);*/
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		String convertday   ="\\"+String.valueOf(day)  ;
			
		String path = save+ convertYear+ convertmonth+ convertday+ "\\note.txt";
		return path;	
	}	
	
	public void write() {
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		String convertday   ="\\"+String.valueOf(day)  ;
			
		String path = save+ convertYear+ convertmonth+ convertday+ "\\note.txt";
	
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
