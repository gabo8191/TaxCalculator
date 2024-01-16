package co.edu.uptc.presenter;

import co.edu.uptc.model.*;
import co.edu.uptc.view.*;
import co.edu.uptc.persistence.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

public class Presenter implements ActionListener {

    private Simulator simulator;
    private View view;
    private Properties file;
    private Persistence persistence;
    private static final String CONFIG_FILE = "src/co/edu/uptc/config/config.properties";
    private static final String DATA_FILE = "data/Guia_CSV_324.csv";

    private SimulatorFrame simulatorFrame;

    public Presenter() {
        simulator = new Simulator();
        view = new View();
        file = new Properties();
        readData();
        loadFile();
        readDiscounts();
        readRanges();
        simulatorFrame = new SimulatorFrame(this);
    }

    public void readData() {

        ArrayList<String> lines;
        persistence = new Persistence(DATA_FILE);
        String[] excludedCars = { "motocarro", "motocicleta", "cuatrimoto" };
        try {
            lines = persistence.loadFile();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.replace("\"", "").split(",");

                String vehicleType = parts[2];
                boolean isExcluded = Arrays.asList(excludedCars).contains(vehicleType.toLowerCase());

                if (!isExcluded) {
                    String brandName = parts[1];
                    simulator.addBrand(brandName);

                    String lineName = parts[5];
                    simulator.addNewLine(brandName, lineName);
                    for (int j = 11; j < 66; j++) {
                        String year = parts[j];
                        if (!year.equals("0")) {
                            int modelName = 1970 + j - 11;
                            Long price = Long.parseLong(parts[j]) * 1000;
                            simulator.addNewModel(brandName, lineName, modelName, price);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            view.showMessage("El archivo no existe");
        } catch (IOException e) {
            view.showMessage("Error al leer el archivo");
        }
    }

    public void loadFile() {
        try {
            file.load(new FileReader(CONFIG_FILE));
        } catch (FileNotFoundException e) {
            view.showMessage("Error: El archivo de configuración no fue encontrado");
        } catch (IOException e) {
            view.showMessage("Error: El archivo de configuración no pudo ser leido");
        }
    }

    public void writeFile(String key, String value) {
        file.setProperty(key, value);
        try {
            file.store(new FileWriter(CONFIG_FILE), "Nuevos valores de configuración");
        } catch (IOException e) {
            view.showMessage("El archivo de configuración no pudo ser escrito");
        }
    }

    public void readDiscounts() {
        int earlyPayment = Integer.parseInt(file.getProperty("percentageEarlyPayment"));
        int registeredInBoyaca = Integer.parseInt(file.getProperty("percentageRegisteredInBoyaca"));
        int hybridCard = Integer.parseInt(file.getProperty("percentageHybridCar"));
        simulator.addNewDiscount(earlyPayment, registeredInBoyaca, hybridCard);
    }

    public void readRanges() {
        for (int i = 1; i <= 3; i++) {
            String rangeKey = "range" + i;
            String range = file.getProperty(rangeKey);
            String[] rangeSplit = range.split(",");
            int min = Integer.parseInt(rangeSplit[0]);
            int max = rangeSplit[1].equals("max") ? Integer.MAX_VALUE : Integer.parseInt(rangeSplit[1]);
            double percentage = Double.parseDouble(rangeSplit[2]);
            simulator.addNewRange(min, max, percentage);
        }
    }

    public void calculateTotalTax() {
        String brand = view.readString("Ingrese la marca del vehiculo").toUpperCase();
        String line = view.readString("Ingrese la linea del vehiculo").toUpperCase();
        int year = view.readInt("Ingrese el año del vehiculo");

        long price = simulator.findPrice(brand, line, year);
        if (price > 0) {
            long tax = simulator.calculateCarTax(price);

            int earlyPaymentChoice = view.readInt("Digite 1 si aplica para descuento por pronto pago, 0 si no");
            int registeredInBoyacaChoice = view
                    .readInt("Digite 1 si aplica para descuento por estar registrado en Boyaca, 0 si no");
            int electricChoice = view
                    .readInt("Digite 1 si aplica para descuento por ser un vehiculo electrico o hibrido, 0 si no");

            boolean earlyPaymentDiscount = earlyPaymentChoice == 1 ? true : false;
            boolean registeredInBoyacaDiscount = registeredInBoyacaChoice == 1 ? true : false;
            boolean electricDiscount = electricChoice == 1 ? true : false;

            long totalDiscount = simulator.calculateCarDiscounts(tax, earlyPaymentDiscount, registeredInBoyacaDiscount,
                    electricDiscount);
            Locale locale = new Locale("es", "CO");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

            view.showMessage("El avaluo del vehiculo es: " + currencyFormatter.format(price)
                    + "\nEl impuesto antes de descuentos es: " + currencyFormatter.format(tax)
                    + "\nEl valor a pagar despues de descuentos es: "
                    + currencyFormatter.format(totalDiscount != 0 ? totalDiscount : tax));
        } else {
            view.showMessage("No se encontró el vehiculo con los datos ingresados.");
        }
    }

    public void searchCar() {
        if (simulatorFrame.getBrand().equals("") || simulatorFrame.getLine().equals("")
                || simulatorFrame.getModel().equals("")) {
            simulatorFrame.setPriceCarValue("No se encontró el vehiculo");
            simulatorFrame.setTotalTaxValue("No se encontró el vehiculo");
            simulatorFrame.setDiscountValue("No se encontró el vehiculo");
            simulatorFrame.setTotalValue("No se encontró el vehiculo");
        }

        String brand = simulatorFrame.getBrand().toUpperCase();
        String line = simulatorFrame.getLine().toUpperCase();
        int year = Integer.parseInt(simulatorFrame.getModel());

        long price = simulator.findPrice(brand, line, year);
        if (price > 0) {
            Locale locale = new Locale("es", "CO");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            simulatorFrame.setPriceCarValue(currencyFormatter.format(price));
        } else {
            simulatorFrame.setPriceCarValue("No se encontró el vehiculo");
        }
    }

    public void addDiscounts() {
        boolean earlyPayment = simulatorFrame.getEarlyPayment();
        boolean registeredInBoyaca = simulatorFrame.getRegisteredInBoyaca();
        boolean hybridCard = simulatorFrame.getHybridCard();

        if (simulatorFrame.getPriceCarValue().equals("No se encontró el vehiculo")) {
            simulatorFrame.setDiscountValue("No se encontró el vehiculo");
            simulatorFrame.setTotalValue("No se encontró el vehiculo");
        } else {
            long price = Long
                    .parseLong(simulatorFrame.getPriceCarValue().replace("$", "").replace(".", "").replace(",00", ""));
            long tax = simulator.calculateCarTax(price);
            long discount = tax - simulator.calculateCarDiscounts(tax, earlyPayment, registeredInBoyaca, hybridCard);
            long total = discount != 0
                    ? simulator.calculateCarDiscounts(tax, earlyPayment, registeredInBoyaca, hybridCard)
                    : tax;
            Locale locale = new Locale("es", "CO");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            simulatorFrame.setTotalTaxValue(currencyFormatter.format(tax));
            simulatorFrame.setDiscountValue(currencyFormatter.format(discount));
            simulatorFrame.setTotalValue(currencyFormatter.format(total));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "search":
                searchCar();
                break;
            case "calculate":
                addDiscounts();
                break;
            case "clearSearch":
                simulatorFrame.clearSearch();
                break;
            case "clearDiscount":
                simulatorFrame.clearDiscounts();
                break;
        }
    }

    public void changeRange() {
        ArrayList<Range> taxRanges = simulator.getTaxes();
        String rangeList = "Listado de Rangos de Impuesto:\n";

        for (int i = 0; i < taxRanges.size(); i++) {
            Range range = taxRanges.get(i);
            rangeList += (i + 1) + ". " + range.toString() + "\n";
        }
        int selection = view.readInt(rangeList + "\nSeleccione el número del rango a cambiar (1, 2 o 3)");

        if (selection < 1 || selection > taxRanges.size()) {
            view.showMessage("Selección inválida. Intente nuevamente.");
            return;
        }

        Range rangeToChange = taxRanges.get(selection - 1);

        int newMin = view.readInt("Ingrese el valor minimo del nuevo rango");
        int newMax = view.readInt("Ingrese el valor maximo del nuevo rango");
        double newPercentage = view.readDouble("Ingrese el porcentaje del nuevo rango");
        writeFile("range" + selection, newMin + "," + newMax + "," + newPercentage);
        Range newRange = new Range(newMin, newMax, newPercentage);
        boolean isChanged = simulator.changeTax(rangeToChange, newRange);

        if (isChanged) {
            view.showMessage("El rango de impuesto ha sido modificado exitosamente. El nuevo rango es: " + newRange);
        } else {
            view.showMessage("El rango de impuesto a modificar no fue encontrado.");
        }
    }

    public void changeDiscounts() {
        int choosenDiscount = view.readInt(
                "Digite 1 para cambiar el descuento por pronto pago\nDigite 2 para cambiar el descuento por estar registrado en Boyaca\nDigite 3 para cambiar el descuento por ser un vehiculo electrico o hibrido");
        switch (choosenDiscount) {
            case 1:
                simulator.changeDiscounts(1, view.readInt("Ingrese el porcentaje de descuento por pronto pago"));
                break;
            case 2:
                simulator.changeDiscounts(2,
                        view.readInt("Ingrese el porcentaje de descuento por estar registrado en Boyaca"));
                break;
            case 3:
                simulator.changeDiscounts(3,
                        view.readInt("Ingrese el porcentaje de descuento por ser electrico o hibrido"));
                break;
            default:
                view.showMessage("Opcion invalida");
                break;
        }

        writeFile("percentageEarlyPayment",
                String.valueOf(simulator.getDiscounts().get(0).getPercentageEarlyPayment()));
        writeFile("percentageRegisteredInBoyaca", String
                .valueOf(simulator.getDiscounts().get(0).getPercentageRegisteredInBoyaca()));
        writeFile("percentageHybridCar", String.valueOf(simulator.getDiscounts().get(0).getPercentageIsHybridCar()));

        view.showMessage("Descuento cambiado exitosamente");
        view.showMessage("Los nuevos descuentos son: " + simulator.getDiscounts());
    }

    public void addNewRange() {
        int min = view.readInt("Ingrese el valor minimo del rango");
        int max = view.readInt("Ingrese el valor maximo del rango");
        int percentage = view.readInt("Ingrese el porcentaje del rango");

        try {
            simulator.addNewRange(min, max, percentage);
            view.showMessage("El rango ha sido agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            view.showMessage("Datos inválidos. Intente nuevamente.");
        }
    }

    public void showTaxRanges() {
        ArrayList<String> taxRanges = simulator.getTaxRanges();
        String list = "Listado de Rangos de Impuesto:\n";

        for (String range : taxRanges) {
            list += "- " + range + "\n";
        }

        view.showMessage(list);
    }

    public void showDiscounts() {
        ArrayList<String> discounts = simulator.getDiscountsList();
        String list = "Listado de Descuentos:\n";

        for (String discount : discounts) {
            list += "- " + discount + "\n";
        }

        view.showMessage(list);
    }

    public void run() {
        int option = 0;
        do {
            try {
                option = view.readInt(
                        "1 - Calcular el impuesto de un vehiculo\n2 - Cambiar un rango\n3 - Cambiar los descuentos\n4 - Mostrar el listado de descuentos\n5 - Mostrar los rangos de impuesto\nDigite 0 para salir");

                switch (option) {
                    case 1:
                        calculateTotalTax();
                        break;
                    case 2:
                        changeRange();
                        break;
                    case 3:
                        changeDiscounts();
                        break;
                    case 4:
                        showDiscounts();
                        break;
                    case 5:
                        showTaxRanges();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        view.showMessage("Opcion invalida");
                        break;
                }
            } catch (NumberFormatException e) {
                view.showMessage("Debe ingresar un número");
            }
        } while (option != 0);
    }

    public static void main(String[] args) {
        // Presenter presenter = new Presenter();
        // presenter.run();
        new Presenter();
    }

}
