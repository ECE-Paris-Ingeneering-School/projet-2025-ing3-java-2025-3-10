package Vue;

import javax.swing.*;
import java.awt.*;


public class AccueilVue extends JFrame {

    private JButton btnConnexion;
    private JButton btnInscription;

    public AccueilVue() {
        setTitle("Accueil - Booking");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Deux boutons dans un GridLayout 1x2
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));

        btnConnexion = new JButton("Connexion");
        btnInscription = new JButton("Inscription");

        panel.add(btnConnexion);
        panel.add(btnInscription);

        add(panel, BorderLayout.CENTER);
    }

    public JButton getBtnConnexion() {
        return btnConnexion;
    }

    public JButton getBtnInscription() {
        return btnInscription;
    }
}
