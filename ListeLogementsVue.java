package Vue;

import Modele.Logement;
import Modele.Users;
import DAO.LogementDAO;
import DAO.LogementDAOImpl;
import Controleur.ClientControleur;
import Controleur.AnnonceControleur;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ListeLogementsVue extends JFrame {

    private JButton btnRechercher;  // Bouton rond pour la recherche avec l'icône loupe
    private PreRemplissage txtTitre;     // Champ pré-rempli pour le Titre
    private PreRemplissage txtPrix;      // Champ pré-rempli pour le Prix
    private PreRemplissage txtCategorie; // Champ pré-rempli pour la Catégorie
    public JPanel panelLogements;  // Panel qui contiendra les cartes de logements
    private Users loggedInUser;

    private List<Logement> logements;  // Liste des logements pour récupérer à partir de l'index des cartes

    public ListeLogementsVue(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
        setTitle("Acceuil");
        setSize(1200, 1000);  // Augmenter la taille de la fenêtre pour une meilleure disposition
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* -------- bandeau bleu haut --------------------------------- */
        JPanel bandeBleu = new JPanel(new BorderLayout());
        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 80));

        // Label "Accueil" centré en haut
        JLabel labelBooking = new JLabel("Accueil", SwingConstants.CENTER);
        labelBooking.setForeground(Color.BLACK);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 28));
        bandeBleu.add(labelBooking, BorderLayout.CENTER);

        // Icône de compte à droite
        ImageIcon icone_compte = new ImageIcon(getClass().getResource("icon_compte.png"));
        if (icone_compte.getImage() != null) {
            Image image = icone_compte.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            icone_compte = new ImageIcon(image);
            BoutonRond boutonCompte = new BoutonRond(icone_compte);
            boutonCompte.setPreferredSize(new Dimension(40, 40));
            bandeBleu.add(boutonCompte, BorderLayout.EAST);
            boutonCompte.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openClientMenu();
                }
            });
        }

        // Panel de recherche centré
        JPanel panelRecherche = new JPanel();
        panelRecherche.setBackground(Color.WHITE);
        panelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Centrer la barre de recherche

        // Champs de recherche avec des PreRemplissage (champ avec placeholder)
        txtTitre = new PreRemplissage("Titre");
        panelRecherche.add(txtTitre);

        txtPrix = new PreRemplissage("Prix");
        panelRecherche.add(txtPrix);

        txtCategorie = new PreRemplissage("Catégorie");
        panelRecherche.add(txtCategorie);

        // Bouton de recherche avec l'icône loupe et nouvelle taille d'icône
        ImageIcon icone_loupe = new ImageIcon(getClass().getResource("icone_loupe.png"));
        if (icone_loupe.getImage() != null) {
            Image image2 = icone_loupe.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);  // Icône plus petite
            icone_loupe = new ImageIcon(image2);
            btnRechercher = new BoutonRond(icone_loupe);  // Bouton rond pour la loupe
            btnRechercher.setBackground(new Color(0, 102, 204));  // Bleu pour le bouton de recherche
            btnRechercher.setForeground(Color.WHITE);
            btnRechercher.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Bordure du bouton
            btnRechercher.setFocusPainted(false);  // Désactive le focus
            panelRecherche.add(btnRechercher);
        }

        // Action listener pour le bouton de recherche
        btnRechercher.addActionListener(e -> rechercherParCritere());

        // Panel principal de contenu avec meilleure organisation
        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBackground(Color.WHITE);
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));

        monPanel.add(labelBooking);
        monPanel.add(Box.createVerticalStrut(30));
        monPanel.add(panelRecherche);
        monPanel.add(Box.createVerticalStrut(20));

        // Panel Logements
        panelLogements = new JPanel();
        panelLogements.setLayout(new BoxLayout(panelLogements, BoxLayout.Y_AXIS));
        panelLogements.setBackground(Color.WHITE);

        // Ajouter des logements dans le panel
        JScrollPane scrollPanel = new JScrollPane(panelLogements);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setPreferredSize(new Dimension(1000, 500));  // Définir une taille maximale

        monPanel.add(scrollPanel);  // Assurez-vous que le panel est ajouté ici

        // Wrapper avec un fond plus clair
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(monPanel, BorderLayout.NORTH);

        // Layout principal
        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    // Méthodes pour récupérer les saisies dans les champs de recherche
    public String getTitreSaisie() {
        return txtTitre.getText().trim();
    }

    public String getPrixSaisie() {
        return txtPrix.getText().trim();
    }

    public String getCategorieSaisie() {
        return txtCategorie.getText().trim();
    }

    public JButton getBtnRechercher() {
        return btnRechercher;
    }
    // Méthodes pour ajouter les logements à la vue
    public void afficherLogements(List<Logement> logements) {
        this.logements = logements;  // Sauvegarder les logements pour une utilisation ultérieure
        panelLogements.removeAll(); // Vider les logements précédents

        // Créer un map pour lier les boutons "Voir l'annonce" aux logements
        for (Logement logement : logements) {
            JPanel carteLogement = creerCarteLogement(logement);  // Créer une carte pour chaque logement

            // Créer le bouton "Voir l'annonce" avec le même style que "Envoyer"
            JButton btnVoirAnnonce = new JButton("Voir l'annonce");
            styleBookingButton(btnVoirAnnonce); // Applique le même style que les autres boutons

            // Ajouter l'action au bouton "Voir l'annonce" basé sur l'ID du logement
            btnVoirAnnonce.addActionListener(e -> {
                new AnnonceControleur(loggedInUser, logement).afficher();  // Ouvrir la page d'annonce basée sur l'ID
            });

            // Ajouter le bouton au bas de la carte de logement
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(btnVoirAnnonce);
            carteLogement.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le bouton en bas de la carte

            // Ajouter la carte au panel
            panelLogements.add(carteLogement);
            panelLogements.add(Box.createVerticalStrut(20)); // Ajouter un espacement entre les cartes
        }

        // Mettre à jour l'interface utilisateur
        revalidate();  // Mettre à jour le layout
        repaint();     // Redessiner la fenêtre
    }

    // Créer une carte (JPanel) pour un logement
    private JPanel creerCarteLogement(Logement logement) {
        JPanel carteLogement = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(211, 211, 211)); // Gris clair pour le fond de la carte
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Coins arrondis
            }
        };

        carteLogement.setOpaque(false);
        carteLogement.setMaximumSize(new Dimension(800, 250)); // Taille des cartes plus grandes
        carteLogement.setAlignmentX(Component.CENTER_ALIGNMENT);
        carteLogement.setLayout(new BorderLayout());
        carteLogement.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Informations du logement
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 2, 10, 5));  // Changer la disposition
        infoPanel.add(new JLabel("Titre : " + logement.getTitre()));
        infoPanel.add(new JLabel("Prix : " + logement.getPrix() + "€"));
        infoPanel.add(new JLabel("Catégorie : " + logement.getCategorie()));

        carteLogement.add(infoPanel, BorderLayout.CENTER);

        // Ajouter la première image du logement
        if (!logement.getImagePath().isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(logement.getImagePath());
            // Assurer que l'image n'est pas déformée, on redimensionne
            Image img = imageIcon.getImage();
            Image resizedImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Redimensionner l'image
            imageIcon = new ImageIcon(resizedImg);
            JLabel imageLabel = new JLabel(imageIcon);
            carteLogement.add(imageLabel, BorderLayout.WEST);
        }

        return carteLogement; // Retourner la carte créée
    }

    /** Méthode utilitaire pour styliser les boutons avec le même style utilisé dans CommentaireVue. */
    private void styleBookingButton(JButton b) {
        Color normal = new Color(0x0071C2);
        Color hover  = normal.brighter();
        b.setBackground(normal);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                b.setBackground(hover);
            }
            @Override public void mouseExited(MouseEvent e) {
                b.setBackground(normal);
            }
        });
    }

    public JButton getBtnVoirAnnonce(int index) {
        JButton btnVoirAnnonce = new JButton("Voir l'annonce");
        styleBookingButton(btnVoirAnnonce); // Applique le même style que les autres boutons
        btnVoirAnnonce.setBackground(new Color(0, 123, 255));
        btnVoirAnnonce.setForeground(Color.WHITE);
        btnVoirAnnonce.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVoirAnnonce.setFocusPainted(false);
        btnVoirAnnonce.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoirAnnonce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Ajouter l'action au bouton "Voir l'annonce"
        btnVoirAnnonce.addActionListener(e -> {
            if (index >= 0 && index < logements.size()) {
                Logement selectedLogement = logements.get(index);
                new AnnonceControleur(loggedInUser, selectedLogement).afficher();  // Ouvrir la page d'annonce
            }
        });

        return btnVoirAnnonce;
    }

    // Méthode pour ouvrir le menu client
    private void openClientMenu() {
        ClientControleur clientControleur = new ClientControleur(loggedInUser);
        clientControleur.afficherMenuClient();  // Ouvre le menu client
    }

    // Recherche des logements par critères
    private void rechercherParCritere() {
        // Récupère les valeurs saisies dans les champs de recherche
        String titre = txtTitre.getText().trim();
        String prix = txtPrix.getText().trim();
        String categorie = txtCategorie.getText().trim();

        // Ignorer les valeurs de texte placeholder (par exemple "Prix", "Catégorie", "Titre")
        if (titre.equals("Titre") || titre.isEmpty()) {
            titre = "";
        }
        if (prix.equals("Prix") || prix.isEmpty()) {
            prix = "";
        }
        if (categorie.equals("Catégorie") || categorie.isEmpty()) {
            categorie = "";
        }

        // Utiliser le DAO pour rechercher les logements en fonction des critères
        LogementDAO logementDAO = new LogementDAOImpl();
        List<Logement> resultats = logementDAO.rechercher(titre, "", prix, categorie);

        // Afficher les résultats dans la vue
        afficherLogements(resultats);
    }

    // Méthode pour obtenir un logement à partir de l'index des cartes
    public Logement getLogementAt(int index) {
        if (index >= 0 && index < logements.size()) {
            return logements.get(index);
        }
        return null;  // Retourner null si l'index est invalide
    }
}
