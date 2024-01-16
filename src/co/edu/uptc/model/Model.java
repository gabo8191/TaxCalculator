package co.edu.uptc.model;

public class Model {

	private int year;
	private long price;

	public Model(int year, long price) {
		this.year = year;
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public long getPrice() {
		return price;
	}

}
