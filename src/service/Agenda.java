package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.UI;
import entities.Dates;
import entities.Note;

public class Agenda extends Dates{

	private String note;
	private double value;
	private String save = "E:\\ws-eclipse\\Calendario 2.0\\save";
	private String file = "\\note.txt";
	private List<Note>list = new ArrayList<>();
	
	public Agenda() {
	}
	
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
	
	public void write(boolean operacao) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile(), true))){
			if (operacao == true) {
				bw.write(note+" R$,+"+String.format("%.2f", value));
				bw.newLine();
				System.out.println(UI.ANSI_GREEN+"SAVE"+UI.ANSI_RESET);
			}
			else {
				
				bw.write(note+" R$,-"+String.format("%.2f", value));
				bw.newLine();
				System.out.println(UI.ANSI_GREEN+"SAVE"+UI.ANSI_RESET);
			}
		}
		catch(IOException e) {
			System.out.println("!Fogo no parquinho escrita Agenda!");
			e.printStackTrace();
		}
	}
	
	public void readSomaDay() {
		
		File testPath = new File(pathFile());
		
		if (testPath.exists() && !testPath.isDirectory()) {
			System.out.println();
			System.out.println("DAY SPEND :");
			
			try(BufferedReader br = new BufferedReader(new FileReader(testPath))){
				String line = br.readLine();
				while (line != null) {
					
					String[] vect = line.split(",");	
					list.add(new Note(vect[0], Double.parseDouble(vect[1])));					
					line = br.readLine();

				}
				Collections.sort(list);
			}
			catch(IOException e) {
				System.out.println("!Fogo no parquinho leitura Agenda!");
				e.printStackTrace();
			}	
		}
		else {
			System.out.println(UI.ANSI_YELLOW+"!NOTHING FOUND!"+UI.ANSI_RESET);
		}
	}
	
	public double readSomaMonth() {
				
		File   testPathFolder = new File(pathYearMonth());
		File[] folders = testPathFolder.listFiles(File::isDirectory);
		
		double total = 0;
		if (testPathFolder.exists()) {
			for(File folder : folders) {
				try (BufferedReader br = new BufferedReader(new FileReader(folder+ file))) {
					String line = br.readLine();
					while (line != null) {
						String[] vect = line.split(",");
						total += Double.parseDouble(vect[1]);
						line = br.readLine();
					}
				}
				catch(IOException e) {
					System.out.println("!Fogo no parquinho leitura Agenda!");
					e.printStackTrace();
				}
			}
		}
		return total;
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

	public List<Note> getList() {
		return list;
	}	
}
