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

	public static void main(String[]args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Agenda agenda = new Agenda();
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat year  = new SimpleDateFormat("yyyy");
				
		int todayMonth   = Integer.parseInt(month.format(today));
		int todayYear    = Integer.parseInt(year.format(today ));
		int first        = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDayMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		Calendario calT = new Calendario(todayYear, todayMonth);
		//clearScreen
		UI.clearScreen();
		//printCalendarioToday
		UI.printScreen(calT.allDays(), calT.buscaLinhaMatriz(calT.allDays()), todayMonth, todayYear, calT.getToday());	
		//valor total
		UI.printAgenda(agenda.getSave());
		
		//add agenda?
		System.out.print("New Agenda? (y/n) ");
		char responseAgenda = sc.next().charAt(0);
		//clearScreen
		UI.clearScreen();
		//printCalendarioToday
		UI.printScreen(calT.allDays(), calT.buscaLinhaMatriz(calT.allDays()), todayMonth, todayYear, calT.getToday());	
		//valor total
		UI.printAgenda(agenda.getSave());
		while (responseAgenda != 'y' && responseAgenda != 'n') {
			System.out.print("Digite: (y/n) ");
			responseAgenda = sc.next().charAt(0);
		}
		//add nota
		if(responseAgenda == 'y') {
			//add nova nota?
			boolean renponseDay = true;
			while(renponseDay == true) {
				System.out.print("Digite um dia  : ");
				int day = sc.nextInt();
				while(day < first || day > lastDayMonth) {
					System.out.print("Digite um dia: ");
					day = sc.nextInt();
				}
				//clearScreen
				UI.clearScreen();
				//printCalendarioToday&day
				UI.printScreen(calT.allDays(), calT.buscaLinhaMatriz(calT.allDays()), todayMonth, todayYear, calT.getToday(), day);
				//valor total
				UI.printAgenda(agenda.getSave());
				//quebra de linha
				sc.nextLine();
				System.out.print("Digite um note : ");
				String note  = sc.nextLine();
				System.out.print("Digite um valor: ");
				double value = sc.nextDouble(); 
				Agenda agd = new Agenda(day, todayMonth, todayYear, note, value);
				//escriver
				agd.write();
				//ler
				UI.printAgenda(day, todayMonth, todayYear, agd.path());
				
				//repetir?
				System.out.print("Add new note? (y/n) ");
				char rd = sc.next().charAt(0); 
				while(rd != 'n' && rd != 'y') {
					System.out.print("Digite: (y/n) ");
					rd = sc.next().charAt(0); 
				}
				//clearScreen
				UI.clearScreen();
				//printCalendarioToday&day
				UI.printScreen(calT.allDays(), calT.buscaLinhaMatriz(calT.allDays()), todayMonth, todayYear, calT.getToday());
				//valor total
				UI.printAgenda(agenda.getSave());
				if(rd == 'n') {
					renponseDay = false;				
				}
			}
		}
		
		if(responseAgenda == 'n') {
		}
		
		//calendario
		boolean play = true;					

		System.out.print("New Date? (y/n) ");
		char responsePlay = sc.next().charAt(0);
		
		while (responsePlay != 'y' && responsePlay != 'n') {
			System.out.print("Digite: (y/n) ");
			responsePlay = sc.next().charAt(0);
		}
		
		if(responsePlay == 'n') {
			play = false;
		}
		
		while(play == true) {
			try {
				UI.clearScreen();
				System.out.print("Digite um ano: ");
				int ano = sc.nextInt();
				//exception ano
				while(ano < 1970 || ano > 2075) {
					System.out.print("Por favor digite um ano entre (1970-2075): ");
					ano = sc.nextInt();
				}
				System.out.print("Digite um mes: ");
				int mes = sc.nextInt();
				//exception mes
				while(mes < 1 || mes > 12) {
					System.out.print("Por favor digite um mes entre (1-12): ");
					mes = sc.nextInt();
				}
				
				UI.clearScreen();
				
				Calendario cal = new Calendario(ano, mes);
				UI.printScreen(cal.allDays(), cal.buscaLinhaMatriz(cal.allDays()), cal.getMes(), cal.getAno(), cal.getToday(), "sobreCarga");		
				
				System.out.print("New Date? (y/n) ");
				responsePlay = sc.next().charAt(0);
				
				while (responsePlay != 'y' && responsePlay != 'n') {
					System.out.print("Digite: (y/n) ");
					responsePlay = sc.next().charAt(0);
				}

				if(responsePlay == 'n') {
					play = false;
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
		}
		sc.close();

	}
}
