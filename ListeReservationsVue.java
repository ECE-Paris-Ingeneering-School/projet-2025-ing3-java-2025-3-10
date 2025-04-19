package Vue;

import Modele.Reservation;
import Controleur.PaiementControleur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListeReservationsVue extends JFrame {

    private JPanel panelReservations; // Le panel qui contiendra toutes les cartes de réservation

    public ListeReservationsVue() {
        super("Mes réservations");

        // Configuration de la fenêtre
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /* -------- bandeau bleu haut --------------------------------- */
        JPanel bandeBleu = new JPanel(new BorderLayout());
        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 60));

        JLabel lblBooking = new JLabel("Booking");
        lblBooking.setForeground(Color.WHITE);
        lblBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bandeBleu.add(lblBooking, BorderLayout.WEST);

        /* -------- titre de la page ---------------------------------- */
        JLabel titre = new JLabel("Mes réservations", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 30));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* -------- Panel de cartes de réservations ------------------- */
        panelReservations = new JPanel();
        panelReservations.setLayout(new BoxLayout(panelReservations, BoxLayout.Y_AXIS));
        panelReservations.setBackground(Color.WHITE);

        /* -------- ScrollPane pour permettre le défilement ----------- */
        JScrollPane scrollPane = new JScrollPane(panelReservations);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /* -------- Wrapper avec mise en page racine ------------------- */
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        /* -------- Wrapper pour titre et contenu --------------------- */
        JPanel titleWrapper = new JPanel(new BorderLayout());
        titleWrapper.setBackground(Color.WHITE);
        titleWrapper.add(titre, BorderLayout.NORTH);
        titleWrapper.add(wrapper, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(titleWrapper, BorderLayout.CENTER);
    }

    // Méthode pour afficher les réservations sous forme de cartes
    public void afficherReservations(List<Reservation> liste) {
        panelReservations.removeAll(); // Vider les cartes précédentes

        for (Reservation res : liste) {
            JPanel carteResa = creerCarteReservation(res); // Créer une carte pour chaque réservation
            panelReservations.add(carteResa); // Ajouter la carte au panel
            panelReservations.add(Box.createVerticalStrut(10)); // Ajouter un espacement entre les cartes
        }

        revalidate();  // Mettre à jour le layout
        repaint();     // Redessiner la fenêtre
    }

    // Créer une carte (JPanel) pour une réservation
    private JPanel creerCarteReservation(Reservation res) {
        JPanel carteResa = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(211, 211, 211)); // Gris clair pour le fond de la carte
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Coins arrondis
            }
        };

        carteResa.setOpaque(false);
        carteResa.setMaximumSize(new Dimension(660, 120)); // Taille de la carte
        carteResa.setAlignmentX(Component.CENTER_ALIGNMENT);
        carteResa.setLayout(new GridLayout(1, 4, 10, 5)); // Disposition horizontale des informations
        carteResa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Remplir les informations de la réservation dans la carte
        carteResa.add(new JLabel("ID : " + res.getID_reservation()));
        carteResa.add(new JLabel("Date : " + res.getDate()));
        carteResa.add(new JLabel("Logement : " + res.getID_logement()));
        carteResa.add(new JLabel("Payé ? : " + (res.isPaye() ? "Oui" : "Non")));

        // Ajouter un bouton Payer avec un style moderne
        JButton btnPayer = new JButton("Payer");
        styleBookingButton(btnPayer);

        btnPayer.addActionListener(e -> {
            if (!res.isPaye()) {
                // Afficher le formulaire de paiement si la réservation n'est pas payée
                PaiementControleur pc = new PaiementControleur(null, res, null);
                pc.afficherFormPaiement(); // Afficher le formulaire de paiement
            } else {
                JOptionPane.showMessageDialog(null, "Cette réservation est déjà payée.");
            }
        });

        // Ajouter le bouton au bas de la carte
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnPayer);

        carteResa.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le bouton en bas de la carte

        return carteResa; // Retourner la carte créée
    }

    // Méthode utilitaire pour styliser un bouton Booking (bleu + hover)
    private void styleBookingButton(JButton b) {
        Color normal = new Color(0x0071C2);
        Color hover  = normal.brighter();
        b.setBackground(normal);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                b.setBackground(hover);
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                b.setBackground(normal);
            }
        });
    }
}
