package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Calendario;

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
	//Class
	private static Calendario cal;
	//SDF
	private static SimpleDateFormat day = new SimpleDateFormat("dd");
	private static SimpleDateFormat month = new SimpleDateFormat("MMM");
	//ClearScreen
	public static void clearScreen() { 
		System.out.println();
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}
	//PrintScrean
	public static void printScreen(Date[][] corpo, int linhaI, int mes, int ano) {
		System.out.println();
		System.out.println("----------"+month.format(corpo[linhaI][6])+"/"+ano+"---------");
		System.out.print  ("dom seg ter qua qui sex sab");
		
		for(int i=linhaI; i<=linhaI+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				
				//imprimi
				if(corpo[i][j].getMonth() + 1== mes) {
					System.out.print(" "+ day.format(corpo[i][j])+" ");														
				}
				else {
					System.out.print("    ");
				}
			}
		}
	}
}