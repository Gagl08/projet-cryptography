package ui;

import javax.swing.*;

public class FenetreErreur {

    public FenetreErreur(String message, JFrame parent) {
        final String TITLE = "Erreur";
        JOptionPane.showMessageDialog(parent, message, TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new FenetreErreur("Message", null);
    }
}
