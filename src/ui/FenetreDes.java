package ui;

import cryptography.Des;

import javax.swing.*;

public class FenetreDes extends JFrame {
    private final Des des;
    private JPanel mainPanel;
    private JTextField inputMsgClair, inputMsgCrypte;
    private JButton crypterButton, decrypterButton;

    public FenetreDes() {
        super("Des");
        des = new Des();

        this.add(mainPanel);

        crypterButton.addActionListener(e -> {
            String messageClair = inputMsgClair.getText();
            try {
                int[] messageCrypte = des.crypte(messageClair);
                inputMsgCrypte.setText(Des.bitsToString(messageCrypte));
            } catch (Exception ignored) {
                new FenetreErreur("Impossible de crypter le message", this);
            }
        });

        decrypterButton.addActionListener(e -> {
            int[] messageCrypte = Des.stringToBits(inputMsgCrypte.getText());
            try {
                String messageClair = des.decrypte(messageCrypte);
                inputMsgClair.setText(messageClair);
            } catch (Exception ignored) {
                new FenetreErreur("Impossible de décrypter le message", this);
            }
        });

        this.setSize(400, 250);
        this.setVisible(true);
    }
}
