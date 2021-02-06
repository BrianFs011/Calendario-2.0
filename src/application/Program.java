package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Calendario;
import entities.CalendarioException;

public class Program {

	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		
		UI.clearScreen();
		while(true) {
			try {
				//Boas vindas
				System.out.println("Calendario (1970-2075)");
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
				Calendario cal = new Calendario(ano, mes);
				UI.printScreen(cal.allDays(), cal.buscaLinhaMatriz(cal.allDays()), cal.getMes(), cal.getAno());							
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

	}
}
