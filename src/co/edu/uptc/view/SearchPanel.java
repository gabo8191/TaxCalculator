package co.edu.uptc.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchPanel extends JPanel {

    private JLabel lblBrand;
    private JTextField txtBrand;

    private JLabel lblModel;
    private JTextField txtModel;

    private JLabel lblLine;
    private JTextField txtLine;

    private JButton btnSearch;
    private JButton btnClear;
    private Font fontLabel;
    private Font fontTextField;

    public SearchPanel(ActionListener actionListener) {
        setBackground(new java.awt.Color(203, 197, 234));
        fontLabel = new Font(Texts.FONT.getText(), Font.BOLD, 30);
        fontTextField = new Font(Texts.FONT.getText(), Font.PLAIN, 30);

        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        this.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        lblBrand = new JLabel(Texts.BRAND_LABEL.getText());
        lblBrand.setFont(fontLabel);
        lblBrand.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblBrand);

        txtBrand = new JTextField(10);
        txtBrand.setFont(fontTextField);
        this.add(txtBrand);

        lblLine = new JLabel(Texts.LINE_LABEL.getText());
        lblLine.setFont(fontLabel);
        lblLine.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblLine);

        txtLine = new JTextField(10);
        txtLine.setFont(fontTextField);
        this.add(txtLine);

        lblModel = new JLabel(Texts.MODEL_LABEL.getText());
        lblModel.setFont(fontLabel);
        lblModel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblModel);

        txtModel = new JTextField(10);
        txtModel.setFont(fontTextField);
        this.add(txtModel);

        btnSearch = new JButton(Texts.SEARCH_BUTTON.getText());
        btnSearch.addActionListener(actionListener);
        btnSearch.setActionCommand("search");
        this.add(btnSearch);

        btnClear = new JButton(Texts.CLEAR_BUTTON.getText());
        btnClear.addActionListener(actionListener);
        btnClear.setActionCommand("clearSearch");
        this.add(btnClear);
    }

    public JLabel getLblBrand() {
        return lblBrand;
    }

    public void setLblBrand(JLabel lblBrand) {
        this.lblBrand = lblBrand;
    }

    public JTextField getTxtBrand() {
        return txtBrand;
    }

    public void setTxtBrand(JTextField txtBrand) {
        this.txtBrand = txtBrand;
    }

    public JLabel getLblModel() {
        return lblModel;
    }

    public void setLblModel(JLabel lblModel) {
        this.lblModel = lblModel;
    }

    public JTextField getTxtModel() {
        return txtModel;
    }

    public void setTxtModel(JTextField txtModel) {
        this.txtModel = txtModel;
    }

    public JLabel getLblLine() {
        return lblLine;
    }

    public void setLblLine(JLabel lblLine) {
        this.lblLine = lblLine;
    }

    public JTextField getTxtLine() {
        return txtLine;
    }

    public void setTxtLine(JTextField txtLine) {
        this.txtLine = txtLine;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
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

    public Font getFontTextField() {
        return fontTextField;
    }

    public void setFontTextField(Font fontTextField) {
        this.fontTextField = fontTextField;
    }

}
