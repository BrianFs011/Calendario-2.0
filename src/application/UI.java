package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Calendario;
import entities.Note;
import service.Agenda;

public class UI {
	//colors
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_CINZA_BACKGROUND = "\u001B[47m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND =   "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND ="\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND =  "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND ="\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND =  "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//SDF
	private static final SimpleDateFormat day = new SimpleDateFormat("dd");
	private static final SimpleDateFormat month = new SimpleDateFormat("MMM");
	//ClearScreen
	
	public static void clearScreen() { 
		System.out.println();
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}
	//PrintCabeçalho
	
	public static void printTop(Date[][] corpo, Calendario calendario) {
		//Boas vindas
				System.out.println("Calendario (1970-2075)");
				System.out.println();
				System.out.println("----------"+month.format(corpo[calendario.buscaLinhaMatriz(corpo)][6])+"/"+calendario.getYear()+"---------");
				System.out.print  ("dom seg ter qua qui sex sab");
	}
	//PrintToday
	@SuppressWarnings("deprecation")
	public static void printScreen(Date[][] corpo, Calendario cal, Agenda agd, Date today) {
	
		for(int i=cal.buscaLinhaMatriz(corpo); i<=cal.buscaLinhaMatriz(corpo)+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				String dia = String.valueOf(corpo[i][j].getDate());
				String convert = agd.pathYearMonth()+"\\"+dia+ agd.getFile();
				File path = new File(convert);
				
				double som   = 0;

				if (path.exists() && !path.isDirectory()) {
					try(BufferedReader br = new BufferedReader(new FileReader(path))){
						String line = br.readLine();
						while (line != null) {
							String[] vect = line.split(",");	
							
							som += Double.parseDouble(vect[1]);	
							
							line = br.readLine();
							
						}
					}
					catch(IOException e) {
						System.out.println("!Fogo no parquinho leitura Agenda!");
						e.printStackTrace();
					}
				}
				
				//imprimi
				if(corpo[i][j].getMonth() + 1== cal.getMonth()) {
					if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					
					else  if(path.exists()) {
						if(som < 0) {
							System.out.print(" "+ANSI_RED_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");														
						}
						else if (som == 0) {
							System.out.print(" "+ANSI_CINZA_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");	
						}
						else {
							System.out.print(" "+ANSI_GREEN_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");	
						}
					}
					
					else{
						System.out.print(" "+ day.format(corpo[i][j])+" ");																				
					}
				}
				else {
					System.out.print("    ");
				}
			}
		}
		System.out.println();
	}
	//PrintToday&Day
	@SuppressWarnings("deprecation")
	public static void printScreen(Date[][] corpo, Calendario cal, Agenda agd, Date today, String sob) {
		
		for(int i=cal.buscaLinhaMatriz(corpo); i<=cal.buscaLinhaMatriz(corpo)+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				String dia = String.valueOf(corpo[i][j].getDate());
				String convert = agd.pathYearMonth()+ "\\"+ dia+ agd.getFile();
				File testPath = new File(convert);
				
				double som   = 0;
				
				if (testPath.exists() && !testPath.isDirectory()) {
					try(BufferedReader br = new BufferedReader(new FileReader(testPath))){
						String line = br.readLine();
						while (line != null) {
							String[] vect = line.split(",");	
							
							som += Double.parseDouble(vect[1]);	
							
							line = br.readLine();
							
						}
					}
					catch(IOException e) {
						System.out.println("!Fogo no parquinho leitura Agenda!");
						e.printStackTrace();
					}
				}
				//imprimi
				if(corpo[i][j].getMonth() + 1== cal.getMonth()) {
					if(corpo[i][j].getDate() == agd.getDay()) {							
						System.out.print(" "+ANSI_YELLOW_BACKGROUND+ ANSI_BLACK+day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					else if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					
					else  if(testPath.exists()) {
						if (som < 0) {
							System.out.print(" "+ANSI_RED_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");
						}
						else if (som == 0) {
							System.out.print(" "+ANSI_CINZA_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");	
						}
						else {
							System.out.print(" "+ANSI_GREEN_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");
						}
					}
					
					else{
						System.out.print(" "+ day.format(corpo[i][j])+" ");																				
					}
				}
				else {
					System.out.print("    ");
				}
			}
		}
		System.out.println();
	}
	
	//imprimir agenda
	public static void printAgenda(Agenda agenda) {
		
		agenda.readSomaDay(agenda.pathFile());
		
		for(Note not : agenda.getList()) {
			if(not.getPrice() < 0) {
				System.out.println(ANSI_RED+   not.getNote()+ String.format("%.2f", not.getPrice())+ ANSI_RESET);								
			}
			else {
				System.out.println(ANSI_GREEN+ not.getNote()+ String.format("%.2f", not.getPrice())+ ANSI_RESET);								
			}
		}
		
		if(agenda.getList().size() > 1) {
			
			System.out.println(ANSI_WHITE+"___________________________"+ANSI_RESET);
			
			double soma = 0;
			for(Note not : agenda.getList()) {
				soma += not.getPrice();
			}
			
			if(soma < 0) {
				System.out.println("TOTAL: "+ ANSI_RED+   String.format("%.2f", soma)+ ANSI_RESET);	
				System.out.println();
			}
			else if (soma == 0) {
				System.out.println("TOTAL: "+ ANSI_WHITE+ String.format("%.2f", soma)+ ANSI_RESET);	
				System.out.println();
			}
			else {
				System.out.println("TOTAL: "+ ANSI_GREEN+ String.format("%.2f", soma)+ ANSI_RESET);	
				System.out.println();
			}
		}
		else {
			System.out.println();
		}
	}
	
	//imprime a soma
	public static void printTotalMonth(Agenda agenda) {		
		if (agenda.readSomaMonth() < 0) {
			System.out.println("TOTAL SPEND: "+ ANSI_RED  + "R$"+String.format("%.2f", agenda.readSomaMonth())+ANSI_RESET);								
		}
		else if (agenda.readSomaMonth() == 0) {
			System.out.println("TOTAL SPEND: "+ ANSI_WHITE+ "R$"+String.format("%.2f", agenda.readSomaMonth())+ANSI_RESET);
		}
		else {
			System.out.println("TOTAL SPEND: "+ ANSI_GREEN+ "R$"+String.format("%.2f", agenda.readSomaMonth())+ANSI_RESET);				
		}
	}
}
