package co.edu.uptc.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DiscountsPanel extends JPanel {

    private JLabel priceCar;
    private JLabel priceCarValue;

    private JLabel earlyPayment;
    private JCheckBox chkEarlyPayment;

    private JLabel registeredInBoyaca;
    private JCheckBox chkRegisteredInBoyaca;

    private JLabel hybridCard;
    private JCheckBox chkHybridCard;

    private JButton btnCalculate;
    private JButton btnClear;

    private Font fontLabel;
    private Font fontLabelValue;

    public DiscountsPanel(ActionListener actionListener) {
        this.setBackground(new java.awt.Color(234, 234, 234));
        fontLabel = new Font(Texts.FONT.getText(), Font.BOLD, 30);
        fontLabelValue = new Font(Texts.FONT.getText(), Font.PLAIN, 30);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        this.setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        priceCar = new JLabel(Texts.PRICE_CAR_LABEL.getText());
        priceCar.setFont(fontLabel);
        priceCar.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(priceCar);

        priceCarValue = new JLabel("");
        priceCarValue.setFont(fontLabelValue);
        this.add(priceCarValue);

        earlyPayment = new JLabel(Texts.EARLY_PAYMENT_LABEL.getText());
        earlyPayment.setHorizontalAlignment(SwingConstants.CENTER);
        earlyPayment.setFont(fontLabel);
        this.add(earlyPayment);

        chkEarlyPayment = new JCheckBox();
        chkEarlyPayment.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(chkEarlyPayment);

        registeredInBoyaca = new JLabel(Texts.REGISTERED_IN_BOYACA_LABEL.getText());
        registeredInBoyaca.setHorizontalAlignment(SwingConstants.CENTER);
        registeredInBoyaca.setFont(fontLabel);
        this.add(registeredInBoyaca);

        chkRegisteredInBoyaca = new JCheckBox();
        chkRegisteredInBoyaca.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(chkRegisteredInBoyaca);

        hybridCard = new JLabel(Texts.HYBRID_CARD_LABEL.getText());
        hybridCard.setHorizontalAlignment(SwingConstants.CENTER);
        hybridCard.setFont(fontLabel);
        this.add(hybridCard);

        chkHybridCard = new JCheckBox();
        chkHybridCard.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(chkHybridCard);

        btnCalculate = new JButton(Texts.CALCULATE_BUTTON.getText());
        btnCalculate.addActionListener(actionListener);
        btnCalculate.setActionCommand("calculate");
        this.add(btnCalculate);

        btnClear = new JButton(Texts.CLEAR_BUTTON.getText());
        btnClear.addActionListener(actionListener);
        btnClear.setActionCommand("clearDiscount");
        this.add(btnClear);

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

    public JLabel getEarlyPayment() {
        return earlyPayment;
    }

    public void setEarlyPayment(JLabel earlyPayment) {
        this.earlyPayment = earlyPayment;
    }

    public JCheckBox getChkEarlyPayment() {
        return chkEarlyPayment;
    }

    public void setChkEarlyPayment(JCheckBox chkEarlyPayment) {
        this.chkEarlyPayment = chkEarlyPayment;
    }

    public JLabel getRegisteredInBoyaca() {
        return registeredInBoyaca;
    }

    public void setRegisteredInBoyaca(JLabel registeredInBoyaca) {
        this.registeredInBoyaca = registeredInBoyaca;
    }

    public JCheckBox getChkRegisteredInBoyaca() {
        return chkRegisteredInBoyaca;
    }

    public void setChkRegisteredInBoyaca(JCheckBox chkRegisteredInBoyaca) {
        this.chkRegisteredInBoyaca = chkRegisteredInBoyaca;
    }

    public JLabel getHybridCard() {
        return hybridCard;
    }

    public void setHybridCard(JLabel hybridCard) {
        this.hybridCard = hybridCard;
    }

    public JCheckBox getChkHybridCard() {
        return chkHybridCard;
    }

    public void setChkHybridCard(JCheckBox chkHybridCard) {
        this.chkHybridCard = chkHybridCard;
    }

    public JButton getBtnCalculate() {
        return btnCalculate;
    }

    public void setBtnCalculate(JButton btnCalculate) {
        this.btnCalculate = btnCalculate;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(JButton btnClear) {
        this.btnClear = btnClear;
    }

    public Font getFontLabel() {
        return fontLabel;
    }

    public void setFontLabel(Font fontLabel) {
        this.fontLabel = fontLabel;
    }

    public Font getFontLabelValue() {
        return fontLabelValue;
    }

    public void setFontLabelValue(Font fontLabelValue) {
        this.fontLabelValue = fontLabelValue;
    }

}
