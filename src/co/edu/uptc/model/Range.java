package co.edu.uptc.model;

public class Range {
	private long minPrice;
	private long maxPrice;
	private double percentage;

	public Range(long minPrice, long maxPrice, double percentage) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.percentage = percentage;
	}

	public long getMinPrice() {
		return minPrice;
	}

	public long getMaxPrice() {
		return maxPrice;
	}

	public double getPercentage() {
		return percentage;
	}

	@Override
	public String toString() {
		return "Rango de impuesto:\n" +
				"Valor mínimo: " + minPrice + "\n" +
				"Valor máximo: " + maxPrice + "\n" +
				"Porcentaje de impuesto: " + percentage + "%";
	}

}