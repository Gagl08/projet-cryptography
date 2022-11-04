package ui;

import javax.swing.*;

public class App extends JFrame {
    private JPanel mainPanel;
    private JButton desButton, tripleDesButton, quitterButton;

    public App() {
        super("Projet Cryptographie");

        this.add(mainPanel);

        // Actions
        desButton.addActionListener(e -> new FenetreDes());
        tripleDesButton.addActionListener(e -> new FenetreTripleDes());
        quitterButton.addActionListener(e -> System.exit(0));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
