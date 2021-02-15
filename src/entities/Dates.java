package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dates {

	protected Integer  year ;
	protected Integer  month;
	protected Integer  day  ;
	private   Calendar calendar   = Calendar.getInstance();	
	
	public Dates() {	
	}
	
	@SuppressWarnings("static-access")
	public Dates(Integer year, Integer month, Integer day) {
		
		calendar.set(Calendar.MONTH, (month - 1));
		calendar.set (Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		int lastDay  = Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()));

		
		if (day < 1 || day > lastDay) {
			throw new CalendarioException ("WRITE DAY  : (1/"+lastDay+") ");
		}
		
		if (month < 1 || month > 12 ) {
			throw new CalendarioException ("WRITE MONTH: (1/12) ");
		}
		
		if (year < 1970 || year > 2075) {
			throw new CalendarioException ("WRITE YEAR : (1970/2075) ");
		}
		
		this.year  = year ;
		this.month = month;
		this.day   = day  ;
	}

	@SuppressWarnings("static-access")
	public String toString(int month) {
		
		calendar.set(Calendar.MONTH, (month -1));
		calendar.set (Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		int lastDay  = Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()));
		String convert = String.valueOf(lastDay);
		return convert;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getDay() {
		return day;
	}
}
