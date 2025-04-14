package Vue;

import javax.swing.*;
import java.awt.*;

public class AdminMenuVue extends JFrame {

    private JButton btnGererUtilisateurs;
    private JButton btnGererLogements;
    private JButton btnVoirReservations;
    private JButton btnStats;
    private JButton btnDeconnexion;

    public AdminMenuVue() {
        setTitle("Menu Admin - Booking");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnGererUtilisateurs = new JButton("Gérer les Utilisateurs");
        btnGererLogements = new JButton("Gérer les Logements");
        btnVoirReservations = new JButton("Voir les Réservations");
        btnStats = new JButton("Statistiques");
        btnDeconnexion = new JButton("Déconnexion");

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.add(btnGererUtilisateurs);
        panel.add(btnGererLogements);
        panel.add(btnVoirReservations);
        panel.add(btnStats);
        panel.add(btnDeconnexion);

        add(panel);
    }

    public JButton getBtnGererUtilisateurs() {
        return btnGererUtilisateurs;
    }

    public JButton getBtnGererLogements() {
        return btnGererLogements;
    }

    public JButton getBtnVoirReservations() {
        return btnVoirReservations;
    }

    public JButton getBtnStats() {
        return btnStats;
    }

    public JButton getBtnDeconnexion() {
        return btnDeconnexion;
    }
}
