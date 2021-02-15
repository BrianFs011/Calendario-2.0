package application;

import java.io.BufferedReader;
import java.io.File;
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
	//PrintCabeçalho
	public static void printTop(Date[][] corpo, int linhaI, int todayYear) {
		//Boas vindas
				System.out.println("Calendario (1970-2075)");
				System.out.println();
				System.out.println("----------"+month.format(corpo[linhaI][6])+"/"+todayYear+"---------");
				System.out.print  ("dom seg ter qua qui sex sab");
	}

	//PrintToday
	@SuppressWarnings("deprecation")
	public static void printScreen(Date[][] corpo, int linhaI, int todayYear, int todayMonth, Date today, String pathYearMonth, String file) {
	
		for(int i=linhaI; i<=linhaI+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				String dia = String.valueOf(corpo[i][j].getDate());
				String convert = pathYearMonth+"\\"+dia+ file;
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
				if(corpo[i][j].getMonth() + 1== todayMonth) {
					if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					
					else  if(testPath.exists()) {
						if(som < 0) {
							System.out.print(" "+ANSI_RED_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");														
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
	public static void printScreen(Date[][] corpo, int linhaI, int Year, int Month, int newDay, Date today, String pathYearMonth, String file) {
		
		for(int i=linhaI; i<=linhaI+5; i++) {
			System.out.println();
			for (int j=0; j<7; j++) {
				String dia = String.valueOf(corpo[i][j].getDate());
				String convert = pathYearMonth+ "\\"+ dia+ file;
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
				if(corpo[i][j].getMonth() + 1== Month) {
					if(corpo[i][j].getDate() == newDay) {							
						System.out.print(" "+ANSI_YELLOW_BACKGROUND+ ANSI_BLACK+day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					else if(corpo[i][j].getDate() == today.getDate() && corpo[i][j].getMonth() == today.getMonth() && corpo[i][j].getYear() == today.getYear()) {							
						System.out.print(" "+ANSI_BLUE_BACKGROUND+ day.format(corpo[i][j])+ANSI_RESET+" ");
					}
					
					else  if(testPath.exists()) {
						if (som < 0) {
							System.out.print(" "+ANSI_RED_BACKGROUND+ ANSI_BLACK+ day.format(corpo[i][j])+ANSI_RESET+" ");
							
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
	public static void printAgenda(int year, int month, int day, String path) {

		File testPath = new File(path);
		double som   = 0;
		int    cont  = 0;
		
		System.out.println();
		System.out.println("DAY SPEND :");
		
		if (testPath.exists() && !testPath.isDirectory()) {
			try(BufferedReader br = new BufferedReader(new FileReader(path))){
				String line = br.readLine();
				while (line != null) {
					String[] vect = line.split(",");	
					String name = vect[0];
					double price = Double.parseDouble(vect[1]);	
					som += Double.parseDouble(vect[1]);	
					
					if (price < 0) {
						System.out.println(ANSI_RED+name+ " R$" + String.format("%.2f", price)+" "+ANSI_RESET);						
					}
					else {
						System.out.println(ANSI_GREEN+name+ " R$"+ String.format("%.2f", price)+" "+ANSI_RESET);		
					}
					line = br.readLine();
					cont ++;
				}
			}
			catch(IOException e) {
				System.out.println("!Fogo no parquinho leitura Agenda!");
				e.printStackTrace();
			}
			if(cont > 1) {
				System.out.println(ANSI_WHITE+"___________________________"+ANSI_RESET);
				
				if(som < 0) {
					
					System.out.println("TOTAL: "+ANSI_RED  +"R$"+String.format("%.2f", som)+ANSI_RESET);
				}
				else {
					System.out.println("TOTAL: "+ANSI_GREEN+"R$"+String.format("%.2f", som)+ANSI_RESET);
				}
				System.out.println();
			}
			else {
				System.out.println();
			}
		}
		else {
			System.out.println(ANSI_YELLOW+"!NOTHING FOUND!"+ANSI_RESET);
			}		
	}
	
	//imprime a soma
	public static double printTotalMonth(String save, String file) {
		File testPath = new File(save);

		File path = new File(save);
		File[] folders = path.listFiles(File::isDirectory);
		
		double price = 0;
		if (testPath.exists()) {
			for(File folder : folders) {	
				try(BufferedReader br = new BufferedReader(new FileReader(folder+ file))){
					String line = br.readLine();
					while (line != null) {
						String[] vect = line.split(",");
						price += Double.parseDouble(vect[1]);	
						
						
						line = br.readLine();
					}
				}
				catch(IOException e) {
					System.out.println("!Fogo no parquinho leitura Agenda!");
					e.printStackTrace();
				}
			}
		}
		if (price < 0) {
			System.out.println("TOTAL SPEND: "+ ANSI_RED  + "R$"+String.format("%.2f", price)+ANSI_RESET);								
		}
		else if (price == 0) {
			System.out.println("TOTAL SPEND: "+ ANSI_WHITE+ "R$"+String.format("%.2f", price)+ANSI_RESET);
		}
		else {
			System.out.println("TOTAL SPEND: "+ ANSI_GREEN+ "R$"+String.format("%.2f", price)+ANSI_RESET);				
		}
		return price;
	}
}
