package co.edu.uptc.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;

public class SimulatorFrame extends JFrame {
    private SearchPanel searchPanel;
    private DiscountsPanel discountsPanel;
    private ResultPanel resultPanel;

    public SimulatorFrame(ActionListener actionListener) {
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Simulador de descuentos");
        this.setLocationRelativeTo(null);
        initComponents(actionListener);

        this.setVisible(true);
    }

    private void initComponents(ActionListener actionListener) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        searchPanel = new SearchPanel(actionListener);
        container.add(searchPanel);

        discountsPanel = new DiscountsPanel(actionListener);
        container.add(discountsPanel);

        resultPanel = new ResultPanel(actionListener);
        container.add(resultPanel);

        this.setContentPane(container);
    }

    public String getBrand() {
        return searchPanel.getTxtBrand().getText();
    }

    public String getModel() {
        return searchPanel.getTxtModel().getText();
    }

    public String getLine() {
        return searchPanel.getTxtLine().getText();
    }

    public void setPriceCarValue(String priceCarValue) {
        resultPanel.getPriceCarValue().setText(priceCarValue);
        discountsPanel.getPriceCarValue().setText(priceCarValue);
    }

    public String getPriceCarValue() {
        return resultPanel.getPriceCarValue().getText();
    }

    public boolean getEarlyPayment() {
        return discountsPanel.getChkEarlyPayment().isSelected();
    }

    public boolean getRegisteredInBoyaca() {
        return discountsPanel.getChkRegisteredInBoyaca().isSelected();
    }

    public boolean getHybridCard() {
        return discountsPanel.getChkHybridCard().isSelected();
    }

    public void setTotalTaxValue(String totalTaxValue) {
        resultPanel.getTotalTaxValue().setText(totalTaxValue);

    }

    public String getTotalTaxValue() {
        return resultPanel.getTotalTaxValue().getText();
    }

    public void setDiscountValue(String discountValue) {
        resultPanel.getDiscountValue().setText(discountValue);
    }

    public String getDiscountValue() {
        return resultPanel.getDiscountValue().getText();
    }

    public void setTotalValue(String totalValue) {
        resultPanel.getTotalValue().setText(totalValue);
    }

    public String getTotalValue() {
        return resultPanel.getTotalValue().getText();
    }

    public void clearSearch() {
        searchPanel.getTxtBrand().setText("");
        searchPanel.getTxtLine().setText("");
        searchPanel.getTxtModel().setText("");
    }

    public void clearDiscounts() {
        discountsPanel.getChkEarlyPayment().setSelected(false);
        discountsPanel.getChkHybridCard().setSelected(false);
        discountsPanel.getChkRegisteredInBoyaca().setSelected(false);
    }

}
