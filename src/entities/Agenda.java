package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.UI;

public class Agenda extends Dates{

	private String note;
	private double value;
	private String save = "E:\\ws-eclipse\\Calendario 2.0\\save";
	private String file = "\\note.txt";
	
	public Agenda(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}
	
	public Agenda(Integer year, Integer month, Integer day, String note, double value) {
		super(year, month, day);
		
		this.note  = note;
		this.value = value;
	}
	
	public void newFolder() {
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		String convertday   ="\\"+String.valueOf(day)  ;
		
		@SuppressWarnings("unused")
		boolean newFolderYear  = new File(save+ convertYear).mkdir();
		@SuppressWarnings("unused")
		boolean newFolderMonth = new File(save+ convertYear+ convertmonth).mkdir();
		@SuppressWarnings("unused")
		boolean newFolderDay   = new File(save+ convertYear+ convertmonth+ convertday).mkdir();
	}
	
	public String pathYearMonth() {
		return save+"\\"+year+"\\"+month;
	}

	public String pathFile() {
		
		String convertYear  ="\\"+String.valueOf(year) ;
		String convertmonth ="\\"+String.valueOf(month);
		String convertday   ="\\"+String.valueOf(day)  ;
		
		String path = save+ convertYear+ convertmonth+ convertday+ file;
		return path;	
	}
	
	
	public void write() {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile(), true))){
			bw.write(note+" R$-"+String.format("%.2f", value));
			bw.newLine();
			System.out.println(UI.ANSI_GREEN+"SAVE"+UI.ANSI_RESET);
		}
		catch(IOException e) {
			System.out.println("!Fogo no parquinho escrita Agenda!");
			e.printStackTrace();
		}
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

	public String getFile() {
		return file;
	}	
}
