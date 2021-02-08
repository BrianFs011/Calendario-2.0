package entities;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Calendario {
	private Integer mes;
	private Integer ano;
	private Date today = new Date();
	private Calendar cal = Calendar.getInstance();

	public Calendario(Integer ano, Integer mes) {
		this.ano = ano;
		this.mes = mes;
	}

	public Integer getMes() {
		return mes;
	}

	public Integer getAno() {
		return ano;
	}
	
	public Date getToday() {
		return today;
	}

	//matriz 
	public Date[][] allDays() {
		Date[][] corpo = new Date[5533][7];
		Date dia = Date.from(Instant.parse("1969-12-28T04:00:00Z"));
		
		for(int i = 0; i<corpo.length; i++) {
			for(int j = 0; j<7; j++) {
				
				corpo[i][j] = dia;
				
				cal.setTime(dia);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				dia = cal.getTime();
				
			}
		}
		return corpo;
	}
	//busca na matriz
	@SuppressWarnings("deprecation")
	public int buscaLinhaMatriz(Date[][] corpo) {
		int linhaI = 0;
		int subAno = ano - 1900;
		for(int i = 0; i<corpo.length; i++) {
			for(int j = 0; j<7; j++) {
				if(corpo[i][j].getMonth() +1 == mes && corpo[i][j].getYear() == subAno) {
					linhaI = i;
					
					j += 8;
					i += 6000;
				}
			}
		}
		return linhaI;
	}	
}
