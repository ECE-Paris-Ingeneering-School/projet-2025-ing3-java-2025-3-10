package Vue;

import Modele.Users;
import javax.swing.*;
import java.awt.*;

public class AdminMenuVue extends JFrame {

    private Users adminUser;  // Variable pour stocker l'administrateur
    private JButton btnGererUtilisateurs;
    private JButton btnGererLogements;
    private JButton btnVoirReservations;
    private JButton btnStats;
    private JButton btnDeconnexion;

    // Le constructeur prend adminUser comme paramètre
    public AdminMenuVue(Users adminUser) {
        this.adminUser = adminUser;  // Initialiser adminUser avec l'utilisateur connecté

        setTitle("Menu Admin - Booking");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Bande bleue (haut)
        JPanel bandeBleu = new JPanel(new BorderLayout());
        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 60));

        // Label "Booking"
        JLabel labelBooking = new JLabel("Booking");
        labelBooking.setForeground(Color.WHITE);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bandeBleu.add(labelBooking, BorderLayout.WEST);

        // Titre
        JLabel titre = new JLabel("Menu Admin");
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Création des boutons
        btnGererUtilisateurs = new JButton("Gérer les Utilisateurs");
        btnGererLogements    = new JButton("Gérer les Logements");
        btnVoirReservations  = new JButton("Voir les Réservations");
        btnStats             = new JButton("Statistiques");
        btnDeconnexion       = new JButton("Déconnexion");

        // Alignement pour BoxLayout
        btnGererUtilisateurs.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGererLogements.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoirReservations.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel vertical (BoxLayout)
        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));
        monPanel.setBackground(Color.WHITE);

        // Ajout du titre
        monPanel.add(titre);
        monPanel.add(Box.createVerticalStrut(30));

        // Ajout des boutons
        monPanel.add(btnGererUtilisateurs);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnGererLogements);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnVoirReservations);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnStats);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnDeconnexion);

        // Wrapper pour placer monPanel en haut
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(monPanel, BorderLayout.NORTH);

        // Layout principal
        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);

        // Couleur de fond du JFrame
        getContentPane().setBackground(Color.WHITE);

        // Affichage
        setVisible(true);
    }

    // Getter pour btnGererUtilisateurs
    public JButton getBtnGererUtilisateurs() {
        return btnGererUtilisateurs;
    }

    // Getter pour btnGererLogements
    public JButton getBtnGererLogements() {
        return btnGererLogements;
    }

    // Getter pour btnVoirReservations
    public JButton getBtnVoirReservations() {
        return btnVoirReservations;
    }

    // Getter pour btnStats
    public JButton getBtnStats() {
        return btnStats;
    }

    // Getter pour btnDeconnexion
    public JButton getBtnDeconnexion() {
        return btnDeconnexion;
    }

    // Getter pour adminUser (si nécessaire)
    public Users getAdminUser() {
        return adminUser;  // Retourne l'utilisateur administrateur
    }
}
