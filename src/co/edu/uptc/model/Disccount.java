package co.edu.uptc.model;

public class Disccount {
	private int percentageEarlyPayment;
	private int percentageRegisteredInBoyaca;
	private int percentageIsHybridCar;

	public Disccount(int percentageEarlyPayment, int percentageRegisteredInBoyaca, int percentageIsHybridCar) {
		this.percentageEarlyPayment = percentageEarlyPayment;
		this.percentageRegisteredInBoyaca = percentageRegisteredInBoyaca;
		this.percentageIsHybridCar = percentageIsHybridCar;
	}

	public int getPercentageEarlyPayment() {
		return percentageEarlyPayment;
	}

	public int getPercentageRegisteredInBoyaca() {
		return percentageRegisteredInBoyaca;
	}

	public int getPercentageIsHybridCar() {
		return percentageIsHybridCar;
	}

	public void setPercentageEarlyPayment(int percentageEarlyPayment) {
		this.percentageEarlyPayment = percentageEarlyPayment;
	}

	public void setPercentageRegisteredInBoyaca(int percentageRegisteredInBoyaca) {
		this.percentageRegisteredInBoyaca = percentageRegisteredInBoyaca;
	}

	public void setPercentageIsHybridCar(int percentageIsHybridCar) {
		this.percentageIsHybridCar = percentageIsHybridCar;
	}

	@Override
	public String toString() {
		return "Descuentos:\n" +
				"Porcentaje de descuento por pronto pago: " + percentageEarlyPayment + "%\n" +
				"Porcentaje de descuento por estar registrado en Boyacá: " + percentageRegisteredInBoyaca + "%\n" +
				"Porcentaje de descuento por ser eléctrico o híbrido: " + percentageIsHybridCar + "%";
	}

}
