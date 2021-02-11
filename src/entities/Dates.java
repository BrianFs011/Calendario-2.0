package entities;

public class Dates {

	protected Integer year ;
	protected Integer month;
	protected Integer day  ;
	
	public Dates() {
	}
	
	public Dates(Integer year, Integer month) {
		this.year  = year ;
		this.month = month;
	}
	
	public Dates(Integer year, Integer month, Integer day) {
		this.year  = year ;
		this.month = month;
		this.day   = day  ;
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
