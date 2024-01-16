package co.edu.uptc.model;

import java.util.ArrayList;

public class Simulator {
	private ArrayList<Brand> registeredCars;
	private ArrayList<Range> taxes;
	private ArrayList<Disccount> discounts;

	public Simulator() {
		registeredCars = new ArrayList<Brand>();
		taxes = new ArrayList<Range>();
		discounts = new ArrayList<Disccount>();
	}

	public long calculateCarTax(long price) {
		if (price < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}
		for (int i = 0; i < taxes.size(); i++) {
			if (price >= taxes.get(i).getMinPrice() && price <= taxes.get(i).getMaxPrice()) {
				double taxPercentage = taxes.get(i).getPercentage() / 100.0;
				return (long) (price * taxPercentage);
			}
		}
		return 0;
	}

	public long calculateCarDiscounts(long tax, boolean earlyPayment, boolean registeredInBoyaca, boolean isHybridCar) {
		if (tax < 0) {
			throw new IllegalArgumentException("El impuesto no puede ser negativo");
		}

		long discount = 0;

		if (earlyPayment) {
			discount += tax * (discounts.get(0).getPercentageEarlyPayment() / 100.0);
		}

		if (registeredInBoyaca) {
			discount += tax * (discounts.get(0).getPercentageRegisteredInBoyaca() / 100.0);
		}

		if (isHybridCar) {
			discount += tax * (discounts.get(0).getPercentageIsHybridCar() / 100.0);
		}

		return tax - discount;
	}

	private Brand searchBrand(String brandName) {
		for (Brand brand : registeredCars) {
			if (brand.getName().equals(brandName)) {
				return brand;
			}
		}
		return null;
	}

	public long findPrice(String brandName, String lineName, int year) {
		if (brandName == null || lineName == null || year < 0) {
			throw new IllegalArgumentException(
					"El nombre de la marca y la linea no pueden ser nulos, el año no puede ser negativo");
		}
		Brand brand = searchBrand(brandName);
		if (brand != null) {
			Line line = brand.searchLine(lineName);
			if (line != null) {
				Model model = line.searchModel(year);
				if (model != null) {
					return model.getPrice();
				}
			}
		}
		return 0;
	}

	public boolean changeTax(Range taxToChange, Range newTax) {
		if (taxToChange == null || newTax == null) {
			throw new IllegalArgumentException("El impuesto a cambiar y el nuevo impuesto no pueden ser nulos");
		}
		if (newTax.getMaxPrice() < newTax.getMinPrice()) {
			throw new IllegalArgumentException(
					"El impuesto nuevo no puede tener un valor máximo menor al valor mínimo");
		}

		if (taxToChange.getMinPrice() < 0 || taxToChange.getMaxPrice() < 0 || newTax.getMinPrice() < 0
				|| newTax.getMaxPrice() < 0 || taxToChange.getPercentage() < 0 || newTax.getPercentage() < 0) {
			throw new IllegalArgumentException(
					"Los valores del impuesto a cambiar y del nuevo impuesto no pueden ser negativos");
		}

		for (int i = 0; i < taxes.size(); i++) {
			if (taxes.get(i).getMinPrice() == taxToChange.getMinPrice()
					&& taxes.get(i).getMaxPrice() == taxToChange.getMaxPrice()) {
				taxes.remove(i);
				taxes.add(newTax);
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getTaxRanges() {
		ArrayList<String> taxRangesList = new ArrayList<>();
		for (Range tax : taxes) {
			taxRangesList.add(tax.toString());
		}
		return taxRangesList;
	}

	public ArrayList<String> getDiscountsList() {
		ArrayList<String> discountsList = new ArrayList<>();
		for (Disccount discount : discounts) {
			discountsList.add(discount.toString());
		}
		return discountsList;
	}

	public void changeDiscounts(int option, int newPercentage) {
		if (option < 0 || option > 3 || newPercentage < 0) {
			throw new IllegalArgumentException(
					"La opción no puede ser negativa, ni mayor a 3, el porcentaje no puede ser negativo");
		}
		switch (option) {
			case 1:
				discounts.get(0).setPercentageEarlyPayment(newPercentage);
				break;
			case 2:
				discounts.get(0).setPercentageRegisteredInBoyaca(newPercentage);
				break;
			case 3:
				discounts.get(0).setPercentageIsHybridCar(newPercentage);
				break;

			default:
				break;
		}
	}

	public boolean addBrand(String brandName) {
		if (brandName == null) {
			throw new IllegalArgumentException("El nombre de la marca no puede ser nulo");
		}
		for (Brand brand : registeredCars) {
			if (brand.getName().equals(brandName)) {
				return false;
			}
		}
		registeredCars.add(new Brand(brandName));
		return true;
	}

	public boolean addNewLine(String brandName, String lineName) {
		if (brandName == null || lineName == null) {
			throw new IllegalArgumentException("El nombre de la marca y la linea no pueden ser nulos");
		}

		Brand foundBrand = null;
		for (Brand brand : registeredCars) {
			if (brand.getName().equals(brandName)) {
				foundBrand = brand;
				break;
			}
		}

		if (foundBrand != null) {
			for (Line line : foundBrand.getLines()) {
				if (line.getName().equals(lineName)) {
					return false;
				}
			}
			foundBrand.addLine(new Line(lineName));
			return true;
		}

		return false;
	}

	public boolean addNewModel(String brandName, String lineName, int year, long price) {
		if (brandName == null || lineName == null || year < 0 || price < 0) {
			throw new IllegalArgumentException(
					"El nombre de la marca y la linea no pueden ser nulos, el año y el precio no pueden ser negativos");
		}

		Brand brand = searchBrand(brandName);
		if (brand != null) {
			Line line = brand.searchLine(lineName);
			if (line != null) {
				for (Model model : line.getModels()) {
					if (model.getYear() == year) {
						return false;
					}
				}
				line.addModel(new Model(year, price));
				return true;
			} else {
				throw new IllegalArgumentException("La linea no existe");
			}
		} else {
			throw new IllegalArgumentException("La marca no existe");
		}
	}

	public void addNewRange(long minPrice, long maxPrice, double percentage) {
		if (minPrice < 0 || maxPrice < 0 || percentage < 0) {
			throw new IllegalArgumentException("Los precios y el porcentaje no pueden ser negativos");
		}
		if (maxPrice < minPrice) {
			throw new IllegalArgumentException("El precio máximo no puede ser menor al precio mínimo");
		}

		for (Range range : taxes) {
			if (range.getMinPrice() == minPrice && range.getMaxPrice() == maxPrice
					&& range.getPercentage() == percentage) {
				throw new IllegalArgumentException("Los valores ingresados ya existen en la lista de rangos");
			}
		}

		taxes.add(new Range(minPrice, maxPrice, percentage));
	}

	public void addNewDiscount(int percentageEarlyPayment, int percentageRegisteredInBoyaca,
			int percentageIsHybridCar) {
		if (percentageEarlyPayment < 0 || percentageRegisteredInBoyaca < 0 || percentageIsHybridCar < 0) {
			throw new IllegalArgumentException(
					"Los porcentajes de descuento no pueden ser negativos");
		}

		for (Disccount discount : discounts) {
			if (discount.getPercentageEarlyPayment() == percentageEarlyPayment
					&& discount.getPercentageRegisteredInBoyaca() == percentageRegisteredInBoyaca
					&& discount.getPercentageIsHybridCar() == percentageIsHybridCar) {
				throw new IllegalArgumentException("Los valores ingresados ya existen en la lista de descuentos");
			}
		}

		discounts.add(new Disccount(percentageEarlyPayment, percentageRegisteredInBoyaca, percentageIsHybridCar));
	}

	public ArrayList<Range> getTaxes() {
		return taxes;
	}

	public ArrayList<Disccount> getDiscounts() {
		return discounts;
	}

}
