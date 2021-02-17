package entities;

public class Note implements Comparable<Note>{

	private String note ;
	private Double price;
	
	public Note() {
	}
	
	public Note(String note, Double price) {
		this.note  = note ;
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return note+"R$,"+price;
	}
	@Override
	public int compareTo(Note other) {
		return price.compareTo(other.getPrice());
	}
}
