package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entities.Agenda;
import entities.Calendario;
import entities.CalendarioException;
import entities.Dates;

public class Program {

	public static void main(String[]args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc        = new Scanner(System.in);
		Date todayDate    = new Date();
		Dates date = new Dates();
		
		SimpleDateFormat todayYear  = new SimpleDateFormat("yyyy");
		SimpleDateFormat todayMonth = new SimpleDateFormat("MM")  ;
		SimpleDateFormat todayDay   = new SimpleDateFormat("dd")  ;
		
		int year  = Integer.parseInt (todayYear.format(todayDate));
		int month = Integer.parseInt(todayMonth.format(todayDate));
								
		boolean programa =  true; 
		while(programa   == true) {
	
				Calendario calendario = new Calendario(year, month, 1);
				Agenda agend = new Agenda(year, month, 1);
				
				//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
				UI.clearScreen();
				UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
				UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());
				UI.printAgenda(agend.pathYearMonth());
				// }
				
				System.out.print ("NOTE: READ/WRITE|TODAY/DATE ");
				char responseAgenda = sc.next().charAt(0);
				while (responseAgenda != 'r' && responseAgenda != 'w' && responseAgenda != 'd' && responseAgenda != 'e' && responseAgenda != 't') {
					//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
					UI.clearScreen();
					UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
					UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());
					UI.printAgenda(agend.pathYearMonth());
					// }
					System.out.print("WRITE: (R/W/T/D/E) ");
					responseAgenda = sc.next().charAt(0);
				}
				
				//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
				UI.clearScreen();
				UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
				UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());
				UI.printAgenda(agend.pathYearMonth());
				// }

				if(responseAgenda == 'r') {
					char responseRead = 0;
					boolean read = true;
					while(read == true) {						
						try {
								System.out.print("WRITE DAY  : ");
								int day = sc.nextInt();

								Agenda agenda = new Agenda(year, month, day);
								
								//UI.clearScreen();
								UI.clearScreen();
								UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
								UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());								UI.printAgenda(agend.pathYearMonth());
								UI.printAgenda(year, month, day, agenda.pathFile());
								
								System.out.print("READ OTHER DAY? (YES/NO) ");
								responseRead = sc.next().charAt(0);
								while(responseRead != 'y' && responseRead != 'n') {
									System.out.print("READ OTHER DAY? (y/n) ");
									responseRead = sc.next().charAt(0);
								}
								if(responseRead == 'n') {
									read = false;
								}
								
								//UI.clearScreen();
								UI.clearScreen();
								UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
								UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());								UI.printAgenda(agend.pathYearMonth());	
							}
						catch(CalendarioException e) {
							System.out.println("WRITE DAY (1/"+date.toString(month)+") ");
						}
					}
				}
				
				else if(responseAgenda == 'w' || responseAgenda == 't') {
					
					boolean otherDay = true;
					while  (otherDay == true) {
						try {
							
							int day = 0;
							if (responseAgenda == 'w') {
								
								System.out.print("WRITE DAY  : ");
								day = sc.nextInt();
							}
							else {
								
								day = Integer.parseInt(todayDay.format(todayDate));
							}
							
							boolean newNote = true;
							while(newNote == true) {
								//Bloco de impreção (Limpa a tela, imprime calendario e day, imprime valor total){
								UI.clearScreen();
								UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
								UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());								UI.printAgenda(agend.pathYearMonth());
								// }
								
								//quebra de linha
								sc.nextLine();
								System.out.print("WRITE NOTE : ");
								String note   = sc.nextLine();
								System.out.print("WHITE PRICE: ");
								double value  = sc.nextDouble(); 
								Agenda agenda  = new Agenda(year, month, day, note, value);
								
								//newFolder
								agenda.newFolder();
								//escriver
								agenda.write();
								//ler
								UI.printAgenda(year, month, day, agenda.pathFile());
								
								//repetir?
								System.out.print("ADD NEW NOTE? (CURRENT/OTHER/NOT) ");
								char responseNewNote = sc.next().charAt(0); 
								
								while(responseNewNote != 'c' && responseNewNote != 'o' && responseNewNote != 'n') {
									System.out.print("WRITE: (C/O/N) ");
									responseNewNote = sc.next().charAt(0); 
								}
								
								if(responseNewNote == 'c') {
									newNote  = true;
								}
								else if (responseNewNote == 'o') {
									newNote  = false;
									responseAgenda = 'w';
									otherDay = true;
								}
								else {
									newNote  = false;
									otherDay = false;
								}
								
								//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
								UI.clearScreen();
								UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
								UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());								UI.printAgenda(agend.pathYearMonth());
								// }	
								
							}
						}
						catch(CalendarioException e) {
							System.out.println("WRITE DAY (1/"+date.toString(month)+") ");
						}
						catch(InputMismatchException e) {
							System.out.println("WRITE VALUE WITH(.) ");
							sc.next();
						}								
					}
				}
				
				else if (responseAgenda == 'd') {			
					//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
					UI.clearScreen();
					UI.printTop(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year);
					UI.printScreen(calendario.allDays(), calendario.buscaLinhaMatriz(calendario.allDays()), year, month, todayDate, agend.pathYearMonth(), agend.getFile());
					UI.printAgenda(agend.pathYearMonth());
					// }
					boolean excepition = true;
					while (excepition == true) {
						try {
						System.out.print("WRITE YEAR : ");
						year  = sc.nextInt();
						
						new Calendario(year, month, 1);
						
						excepition = false;
						}
						catch(CalendarioException e) {
							System.out.println("WRITE YEAR : (1970/2075) ");
						}
						catch(InputMismatchException e) {
							System.out.println("WRITE YEAR : (1970/2075) ");
							sc.next();
						}
					}
					excepition = true;
					while (excepition == true) {
						try {
							System.out.print("WHITE MONTH: ");
							month = sc.nextInt();
							
							new Calendario(year, month, 1);
							
							excepition = false;
						}
						catch(CalendarioException e) {
							System.out.println("WRITE MONTH: (1/12) ");
						}
						catch(InputMismatchException e) {
							System.out.println("WRITE MONTH: (1/12) ");
							sc.next();
						}
					}

				}
				else {
					programa = false;
					UI.clearScreen();
					System.out.println("EXIT");								
				}
		}//programa	

		sc.close();
	}
}
