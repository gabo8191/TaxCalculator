package co.edu.uptc.view;

public enum Texts {
    BRAND_LABEL("Marca:"),
    MODEL_LABEL("Modelo:"),
    LINE_LABEL("Línea:"),
    SEARCH_BUTTON("Buscar"),
    CLEAR_BUTTON("Limpiar"),

    PRICE_CAR_LABEL("Precio del carro:"),
    EARLY_PAYMENT_LABEL("Pronto pago:"),
    REGISTERED_IN_BOYACA_LABEL("Matriculado en Boyaca:"),
    HYBRID_CARD_LABEL("Carro hibrido o electrico:"),
    CALCULATE_BUTTON("Calcular"),

    DISCOUNT_LABEL("Descuento:"),
    TOTAL_TAX_LABEL("Impuesto total:"),
    TOTAL_LABEL("Total a pagar:"),

    FONT("Arial");

    private final String text;

    Texts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
