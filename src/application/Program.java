package application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entities.Agenda;
import entities.Calendario;
import entities.CalendarioException;

public class Program {

	@SuppressWarnings("static-access")
	public static void main(String[]args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc        = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();
		Date todayDate    = new Date();
		
		SimpleDateFormat todayYear  = new SimpleDateFormat("yyyy");
		SimpleDateFormat todayMonth = new SimpleDateFormat("MM")  ;
		SimpleDateFormat todayDay   = new SimpleDateFormat("dd")  ;
		
		int year  = Integer.parseInt (todayYear.format(todayDate));
		int month = Integer.parseInt(todayMonth.format(todayDate));
								
		boolean programa =  true; 
		while(programa   == true) {
			try {		
				Calendario calendarioToday = new Calendario(year, month);
				Agenda agendaSomaPrice = new Agenda(year, month, 1);
				
				//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
				UI.clearScreen();
				UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate, agendaSomaPrice.direct());
				UI.printAgenda(agendaSomaPrice.getSave());
				// }
				
				System.out.print ("NOTE: READ/WRITE|TODAY/DATE ");
				char responseAgenda = sc.next().charAt(0);
				while (responseAgenda != 'r' && responseAgenda != 'w' && responseAgenda != 'd' && responseAgenda != 'e' && responseAgenda != 't') {
					System.out.print("WRITE: (R/W//T/D/E) ");
					responseAgenda = sc.next().charAt(0);
				}
				
				//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
				UI.clearScreen();
				UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate, agendaSomaPrice.direct());
				UI.printAgenda(agendaSomaPrice.getSave());
				// }

				int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.MONTH, (month - 1));
				calendar.set (Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
				int lastDay  = Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()));
				
				if(responseAgenda == 'r') {
					char responseRead = 0;
					boolean read = true;
					while(read == true) {						
						
						calendar.set(Calendar.MONTH, (month - 1));
						calendar.set (Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
						int testLastDay  = Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()));
						
						//ler
						System.out.print("WRITE DAY  : ");
						int day = sc.nextInt();
						while(day < firstDay || day > testLastDay) {
							System.out.print("WRITE DAY  : (1/"+testLastDay+") ");
							day = sc.nextInt();
						}
						Agenda agenda = new Agenda(year, month, day);
						
						UI.clearScreen();
						UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, day, todayDate, agendaSomaPrice.direct());
						UI.printAgenda(agendaSomaPrice.getSave());
						UI.printAgenda(year, month, day, agenda.path());
						
						System.out.print("READ OTHER DAY? (YES/NO) ");
						responseRead = sc.next().charAt(0);
						while(responseRead != 'y' && responseRead != 'n') {
							System.out.print("READ OTHER DAY? (y/n) ");
							responseRead = sc.next().charAt(0);
						}
						if(responseRead == 'n') {
							read = false;
						}
						
						UI.clearScreen();
						UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, day, todayDate, agendaSomaPrice.direct());
						UI.printAgenda(agendaSomaPrice.getSave());
						
					}
				}
				else if(responseAgenda == 'w') {
					
					boolean otherDay = true;
					while  (otherDay == true) {
						
						System.out.print("WRITE DAY  : ");
						int day = sc.nextInt();
						
						while(day < firstDay || day > lastDay) {
							System.out.print("WRITE DAY  : (1/"+lastDay+") ");
							day = sc.nextInt();
						}
						
						boolean newNote = true;
						while(newNote == true) {
							//Bloco de impreção (Limpa a tela, imprime calendario e day, imprime valor total){
							UI.clearScreen();
							UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, day, todayDate, agendaSomaPrice.direct());
							UI.printAgenda(agendaSomaPrice.getSave());
							// }
							
							//quebra de linha
							sc.nextLine();
							System.out.print("WRITE NOTE : ");
							String note  = sc.nextLine();
							System.out.print("WHITE PRICE: ");
							double value = sc.nextDouble(); 
							Agenda agenda = new Agenda(year, month, day, note, value);
							
							//escriver
							agenda.write();
							//ler
							UI.printAgenda(year, month, day, agenda.path());
							
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
								otherDay = true;
							}
							else {
								newNote  = false;
								otherDay = false;
							}
							
							//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
							UI.clearScreen();
							UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year,month, day, todayDate, agendaSomaPrice.direct());
							UI.printAgenda(agendaSomaPrice.getSave());
							// }	
					
							}
						}
					}
				
				else if(responseAgenda == 't') {
						
						int day = Integer.parseInt(todayDay.format(todayDate));
						
						boolean newNote = true;
						while(newNote == true) {
							//Bloco de impreção (Limpa a tela, imprime calendario e day, imprime valor total){
							UI.clearScreen();
							UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, day, todayDate, agendaSomaPrice.direct());
							UI.printAgenda(agendaSomaPrice.getSave());
							// }
							
							//quebra de linha
							sc.nextLine();
							System.out.print("WRITE NOTE : ");
							String note  = sc.nextLine();
							System.out.print("WHITE PRICE: ");
							double value = sc.nextDouble(); 
							Agenda agenda = new Agenda(year, month, day, note, value);
							
							//escriver
							agenda.write();
							//ler
							UI.printAgenda(year, month, day, agenda.path());
							
							//repetir?
							System.out.print("ADD NEW NOTE? (YES/NO) ");
							char responseNewNote = sc.next().charAt(0); 
							
							while(responseNewNote != 'y' && responseNewNote != 'n') {
								System.out.print("WRITE: (Y/N) ");
								responseNewNote = sc.next().charAt(0); 
							}
							
							if(responseNewNote == 'y') {
								newNote  = true;
							}
							else {
								newNote  = false;
							}
							
							//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
							UI.clearScreen();
							UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year,month, day, todayDate, agendaSomaPrice.direct());
							UI.printAgenda(agendaSomaPrice.getSave());
							// }	
					
							}//renponseDay
						}

				else if (responseAgenda == 'd') {			
					//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
					UI.clearScreen();
					UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate, agendaSomaPrice.direct());
					UI.printAgenda(agendaSomaPrice.getSave());
					// }
					
					System.out.print("WRITE YEAR : ");
					year  = sc.nextInt();
					
					while(year < 1970 || year > 2075) {
						System.out.print("WRITE YEAR : (1970/2075) ");
						year  = sc.nextInt();
					}
					
					System.out.print("WHITE MONTH: ");
					month = sc.nextInt();
					
					while(month < 1 || month > 12) {
						System.out.print("WRITE MONTH: (1/12) ");
						month  = sc.nextInt();
					}
				}
				else {
					programa = false;
					UI.clearScreen();
					System.out.println("EXIT");								
				}
			}
			catch(CalendarioException e) {
				System.out.println("!Fogo no parquinho!");
			}
			catch(InputMismatchException e) {
				System.out.println("Digite numeros inteiros");
				sc.nextLine();
			}	
			catch(IllegalStateException e) {
				System.out.print("Digite numeros inteiros");
			}
		}//programa	

		sc.close();
	}
}
