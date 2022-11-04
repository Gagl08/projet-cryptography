package ui;

import cryptography.Des;
import cryptography.TripleDes;

import javax.swing.*;

public class FenetreTripleDes extends JFrame {
    private JPanel mainPanel;
    private JTextField inputMsgClair, inputMsgCrypte;
    private JButton crypterButton, decrypterButton;

    private final TripleDes tripleDes;

    public FenetreTripleDes() {
        super("Triple Des");
        tripleDes = new TripleDes();

        this.add(mainPanel);

        crypterButton.addActionListener(e -> {
            String messageClair = inputMsgClair.getText();
            try {
                int[] messageCrypte = tripleDes.crypte(messageClair);
                inputMsgCrypte.setText(Des.bitsToString(messageCrypte));
            } catch (Exception ignored) {
                new FenetreErreur("Impossible de crypter le message", this);
            }
        });

        decrypterButton.addActionListener(e -> {
            int[] messageCrypte = Des.stringToBits(inputMsgCrypte.getText());
            try {
                String messageClair = tripleDes.decrypte(messageCrypte);
                inputMsgClair.setText(messageClair);
            } catch (Exception ignored) {
                new FenetreErreur("Impossible de décrypter le message", this);
            }
        });

        this.setSize(400, 250);
        this.setVisible(true);
    }
}
