package co.edu.uptc.view;

import javax.swing.JOptionPane;

public class View {

    public String readString(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public int readInt(String message) {
        return Integer.parseInt(JOptionPane.showInputDialog(message));
    }

    public long readLong(String message) {
        return Long.parseLong(JOptionPane.showInputDialog(message));
    }

    public boolean readBoolean(String message) {
        return Boolean.parseBoolean(JOptionPane.showInputDialog(message));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public double readDouble(String message) {
        return Double.parseDouble(JOptionPane.showInputDialog(message));
    }
}
