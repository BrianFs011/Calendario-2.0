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
		
		int year  = Integer.parseInt (todayYear.format(todayDate));
		int month = Integer.parseInt(todayMonth.format(todayDate));
								
		boolean programa =  true; 
		while(programa   == true) {
			try {		
			Calendario calendarioToday = new Calendario(year, month);
			Agenda agendaSomaPrice = new Agenda(year, month, 1);
			
			//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
			UI.clearScreen();
			UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate);
			UI.printAgenda(agendaSomaPrice.getSave());
			// }
			
			System.out.print("New Agenda? (y/n) ");
			char responseAgenda = sc.next().charAt(0);
			while (responseAgenda != 'y' && responseAgenda != 'n') {
				System.out.print("Digite: (y/n) ");
				responseAgenda = sc.next().charAt(0);
			}
			
			//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
			UI.clearScreen();
			UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate);
			UI.printAgenda(agendaSomaPrice.getSave());
			// }
			
			int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.MONTH, (month - 1));
			calendar.set (Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
			int lastDay  = Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()));
			
			if(responseAgenda == 'y') {
				
				boolean newNote = true;
				while(newNote == true) {
					System.out.print("Digite um dia  : ");
					int day = sc.nextInt();
					while(day < firstDay || day > lastDay) {
						System.out.print("Digite um dia  : ");
						day = sc.nextInt();
					}
					
					//Bloco de impreção (Limpa a tela, imprime calendario e day, imprime valor total){
					UI.clearScreen();
					UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, day, todayDate);
					UI.printAgenda(agendaSomaPrice.getSave());
					// }
					
					//quebra de linha
					sc.nextLine();
					System.out.print("Digite um note : ");
					String note  = sc.nextLine();
					System.out.print("Digite um valor: ");
					double value = sc.nextDouble(); 
					Agenda agenda = new Agenda(year, month, day, note, value);
					
					//escriver
					agenda.write();
					//ler
					UI.printAgenda(year, month, day, agenda.path());
					
					//repetir?
					System.out.print("Add new note? (y/n) ");
					responseAgenda = sc.next().charAt(0); 
					while(responseAgenda != 'n' && responseAgenda != 'y') {
						System.out.print("Digite: (y/n) ");
						responseAgenda = sc.next().charAt(0); 
					}
					if(responseAgenda == 'n') {
						newNote = false;
					}
					
					//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
					UI.clearScreen();
					UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year,month, day, todayDate);
					UI.printAgenda(agendaSomaPrice.getSave());
					// }	
				
					}//renponseDay
				}//responseAgenda
			
				System.out.print   ("New Date? (y/n) ")   ;
				char newDate = sc.next().charAt(0);
				
				while(newDate != 'y' && newDate != 'n') {
					System.out.println("Digite:   (y/n) ");
					newDate  = sc.next().charAt(0);
				}	
				
				//Bloco de impreção (Limpa a tela, imprime calendario, imprime valor total){
				UI.clearScreen();
				UI.printScreen(calendarioToday.allDays(), calendarioToday.buscaLinhaMatriz(calendarioToday.allDays()), year, month, todayDate);
				UI.printAgenda(agendaSomaPrice.getSave());
				// }
				
				if (newDate == 'y') {
					System.out.print("Digite um ano : ");
					year  = sc.nextInt();
					System.out.print("Digite um mes : ");
					month = sc.nextInt();					
				}
				else if(newDate == 'n'){
					programa = false;
					UI.clearScreen();
					System.out.println("Exit");
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
