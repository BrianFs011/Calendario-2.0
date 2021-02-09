package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
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
	//PrintToday
	@SuppressWarnings("deprecation")
	public static void printScreen(Date[][] corpo, int linhaI, int todayMonth, int todayYear, Date today) {
		//Boas vindas
		System.out.println("Calendario (1970-2075)");
		System.out.println();
		System.out.println("----------"+month.format(corpo[linhaI][6])+"/"+todayYear+"---------");
		System.out.print  ("dom seg ter qua qui sex sab");
		
		for(int i=linhaI; i<=linhaI+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				
				//imprimi
				if(corpo[i][j].getMonth() + 1== todayMonth) {
					if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
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
	//PrintScrean
	@SuppressWarnings("deprecation")
	public static void printScreen(Date[][] corpo, int linhaI, int mes, int ano, Date today, String sobrecarga) {
		//Boas vindas
		System.out.println("Calendario (1970-2075)");
		System.out.println();
		System.out.println("----------"+month.format(corpo[linhaI][6])+"/"+ano+"---------");
		System.out.print  ("dom seg ter qua qui sex sab");
		
		for(int i=linhaI; i<=linhaI+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				
				//imprimi
				if(corpo[i][j].getMonth() + 1== mes) {
					if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
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
	
	public static void printAgenda(int day, int month, int year, String save) {
		String convert ="\\"+String.valueOf(day)+ String.valueOf(month)+ String.valueOf(year);
		String path = save + convert + "\\note.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while (line != null) {
 				System.out.println(ANSI_RED+line+" "+ANSI_RESET);
				line = br.readLine();
			}
		}
		catch(IOException e) {
			System.out.println("!Fogo no parquinho leitura Agenda!");
			e.printStackTrace();
		}
	}
}
