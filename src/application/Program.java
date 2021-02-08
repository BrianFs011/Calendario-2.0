package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Agenda;
import entities.Calendario;
import entities.CalendarioException;

public class Program {

	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		List<Agenda>agenda = new ArrayList<>();
		Date today = new Date();
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat year  = new SimpleDateFormat("yyyy");
		
		UI.clearScreen();
		
		int todayMonth = Integer.parseInt(month.format(today));
		int todayYear = Integer.parseInt(year.format(today));
		
		Calendario calT = new Calendario(todayYear, todayMonth);
		UI.printScreen(calT.allDays(), calT.buscaLinhaMatriz(calT.allDays()), todayMonth, todayYear, calT.getToday());	
		//agenda
		System.out.print("New Agenda? (y/n) ");
		char responseAgenda = sc.next().charAt(0);
		while (responseAgenda != 'y' && responseAgenda != 'n') {
			System.out.print("Digite: (y/n) ");
			responseAgenda = sc.next().charAt(0);
		}
		
		if(responseAgenda == 'y') {
			System.out.print("Digite um dia: ");
			int day = sc.nextInt();
			while(day < 1 || day >= 31) {
				System.out.print("Digite um dia: ");
				day = sc.nextInt();
			}
			new Agenda(day, todayMonth, todayYear);
			
			sc.nextLine();
			System.out.print("Digite uma anotacao: ");
			String note  = sc.nextLine();
			System.out.print("Digite um valor: ");
			double value = sc.nextDouble(); 
			
			agenda.add(new Agenda(day, todayMonth, todayYear, note, value));
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
