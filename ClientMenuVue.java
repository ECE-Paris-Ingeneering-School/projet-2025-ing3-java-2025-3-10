package Vue;

import javax.swing.*;
import java.awt.*;

public class ClientMenuVue extends JFrame {

    private JButton btnVoirLogements;
    private JButton btnMesReservations;
    private JButton btnCommenter;
    private JButton btnDeconnexion;

    public ClientMenuVue() {
        setTitle("Menu Client - Booking");
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
        JLabel titre = new JLabel("Menu Client");
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Création des boutons
        btnVoirLogements = new JButton("Rechercher Logements");
        btnMesReservations = new JButton("Mes Réservations");
        btnCommenter = new JButton("Ajouter un Commentaire");
        btnDeconnexion = new JButton("Déconnexion");

        // Alignement pour BoxLayout
        btnVoirLogements.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMesReservations.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCommenter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel vertical (BoxLayout)
        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20)); // marge
        monPanel.setBackground(Color.WHITE);

        // On ajoute le titre
        monPanel.add(titre);
        monPanel.add(Box.createVerticalStrut(30)); // espace

        // On ajoute les boutons avec espace entre
        monPanel.add(btnVoirLogements);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnMesReservations);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnCommenter);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnDeconnexion);

        // Wrapper pour placer le panel en haut
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(monPanel, BorderLayout.NORTH);

        // Layout principal
        setLayout(new BorderLayout());

        // Ajout de la bande bleue en haut
        add(bandeBleu, BorderLayout.NORTH);

        // Ajout du wrapper (contenant le titre + boutons)
        add(wrapper, BorderLayout.CENTER);

        // Couleur de fond du frame
        getContentPane().setBackground(Color.WHITE);

        // Affichage
        setVisible(true);
    }

    public JButton getBtnVoirLogements() {
        return btnVoirLogements;
    }

    public JButton getBtnMesReservations() {
        return btnMesReservations;
    }

    public JButton getBtnCommenter() {
        return btnCommenter;
    }

    public JButton getBtnDeconnexion() {
        return btnDeconnexion;
    }
}
