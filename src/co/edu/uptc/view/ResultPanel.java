package co.edu.uptc.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ResultPanel extends JPanel {
    private JLabel priceCar;
    private JLabel priceCarValue;

    private JLabel totalTax;
    private JLabel totalTaxValue;

    private JLabel discount;
    private JLabel discountValue;

    private JLabel total;
    private JLabel totalValue;

    private Font labelFont;
    private Font valueFont;

    public ResultPanel(ActionListener actionListener) {
        setBackground(new java.awt.Color(115, 98, 138));
        labelFont = new Font(Texts.FONT.getText(), Font.BOLD, 30);
        valueFont = new Font(Texts.FONT.getText(), Font.PLAIN, 30);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        this.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        priceCar = new JLabel(Texts.PRICE_CAR_LABEL.getText());
        priceCar.setFont(labelFont);
        priceCar.setForeground(new java.awt.Color(255, 255, 255));
        priceCar.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(priceCar);

        priceCarValue = new JLabel("");
        priceCarValue.setFont(valueFont);
        priceCarValue.setForeground(new java.awt.Color(255, 255, 255));
        this.add(priceCarValue);

        // impuesto total
        totalTax = new JLabel(Texts.TOTAL_TAX_LABEL.getText());
        totalTax.setFont(labelFont);
        totalTax.setForeground(new java.awt.Color(255, 255, 255));
        totalTax.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(totalTax);

        totalTaxValue = new JLabel("");
        totalTaxValue.setFont(valueFont);
        totalTaxValue.setForeground(new java.awt.Color(255, 255, 255));
        this.add(totalTaxValue);

        discount = new JLabel(Texts.DISCOUNT_LABEL.getText());
        discount.setFont(labelFont);
        discount.setForeground(new java.awt.Color(255, 255, 255));
        discount.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(discount);

        discountValue = new JLabel("");
        discountValue.setFont(valueFont);
        discountValue.setForeground(new java.awt.Color(255, 255, 255));
        this.add(discountValue);

        total = new JLabel(Texts.TOTAL_LABEL.getText());
        total.setFont(labelFont);
        total.setForeground(new java.awt.Color(255, 255, 0));
        total.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(total);

        totalValue = new JLabel("");
        totalValue.setFont(valueFont);
        totalValue.setForeground(new java.awt.Color(255, 255, 0));
        this.add(totalValue);
    }

    public JLabel getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(JLabel priceCar) {
        this.priceCar = priceCar;
    }

    public JLabel getPriceCarValue() {
        return priceCarValue;
    }

    public void setPriceCarValue(JLabel priceCarValue) {
        this.priceCarValue = priceCarValue;
    }

    public JLabel getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(JLabel totalTax) {
        this.totalTax = totalTax;
    }

    public JLabel getTotalTaxValue() {
        return totalTaxValue;
    }

    public void setTotalTaxValue(JLabel totalTaxValue) {
        this.totalTaxValue = totalTaxValue;
    }

    public JLabel getDiscount() {
        return discount;
    }

    public void setDiscount(JLabel discount) {
        this.discount = discount;
    }

    public JLabel getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(JLabel discountValue) {
        this.discountValue = discountValue;
    }

    public JLabel getTotal() {
        return total;
    }

    public void setTotal(JLabel total) {
        this.total = total;
    }

    public JLabel getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(JLabel totalValue) {
        this.totalValue = totalValue;
    }

}
