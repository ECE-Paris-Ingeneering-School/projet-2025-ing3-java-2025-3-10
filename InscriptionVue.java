package Vue;

import javax.swing.*;
import java.awt.*;

public class InscriptionVue extends JFrame {

    // Champs texte
    private JTextField nom;
    private JTextField prenom;
    private JTextField email;
    private JTextField password;

    // Bouton d'inscription
    private JButton boutonInscription;

    // Label "J'ai déjà un compte"
    private JLabel labelDejaCompte;

    public InscriptionVue() {

        // Paramètres de base
        setTitle("Page de Connexion");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Création des composants
        JLabel titre = new JLabel("S'inscrire");
        JLabel labelNom = new JLabel("Nom :");
        JLabel labelPrenom = new JLabel("Prénom :");
        JLabel labelEmail = new JLabel("Email :");
        JLabel labelPassword = new JLabel("Mot de passe :");

        nom     = new JTextField("", 20);
        prenom  = new JTextField("", 20);
        email   = new JTextField("", 20);
        password= new JTextField("", 20);

        boutonInscription = new JButton("S'inscrire");

        JLabel labelBooking = new JLabel("Booking");
        // On renomme la variable pour clarifier son but
        labelDejaCompte = new JLabel("J'ai déjà un compte");

        JPanel bandeBleu = new JPanel(new BorderLayout());

        // Mise en forme
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonInscription.setAlignmentX(Component.CENTER_ALIGNMENT);

        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 60));

        labelBooking.setForeground(Color.WHITE);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // Configuration du label "J'ai déjà un compte"
        labelDejaCompte.setForeground(Color.BLUE.darker());
        labelDejaCompte.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelDejaCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelDejaCompte.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
        labelDejaCompte.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Position des champs via GridBagLayout
        JPanel panelObject = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(10, 10, 10, 10);
        gbc.anchor  = GridBagConstraints.LINE_END;

        // Ligne 1 - Nom
        gbc.gridx = 0; gbc.gridy = 0;
        panelObject.add(labelNom, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(nom, gbc);

        // Ligne 2 - Prénom
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panelObject.add(labelPrenom, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(prenom, gbc);

        // Ligne 3 - Email
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panelObject.add(labelEmail, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(email, gbc);

        // Ligne 4 - Mot de passe
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        panelObject.add(labelPassword, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(password, gbc);

        // Panel principal vertical
        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20)); // marge
        monPanel.add(titre);
        monPanel.add(Box.createVerticalStrut(30));
        monPanel.add(panelObject);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(boutonInscription);

        // Bande bleue (en haut)
        bandeBleu.add(labelBooking, BorderLayout.WEST);

        // Wrapper pour placer le formulaire en haut
        JPanel wrapper_1 = new JPanel(new BorderLayout());
        wrapper_1.setBackground(Color.WHITE);
        wrapper_1.add(monPanel, BorderLayout.NORTH);

        // Panel du bas pour "J'ai déjà un compte"
        JPanel panelBas = new JPanel();
        panelBas.setBackground(Color.WHITE);
        panelBas.add(labelDejaCompte);

        // Layout principal
        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(wrapper_1, BorderLayout.CENTER);
        add(panelBas, BorderLayout.SOUTH);

        // Couleurs de fond
        getContentPane().setBackground(Color.WHITE);
        monPanel.setBackground(Color.WHITE);
        panelObject.setBackground(Color.WHITE);

        // Affichage
        setVisible(true);
    }

    // ----------- GETTERS pour le contrôleur -----------

    public String getNom() {
        return nom.getText();
    }

    public String getPrenom() {
        return prenom.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getMotDePasse() {
        return password.getText();
    }

    public JButton getBtnInscription() {
        return boutonInscription;
    }

    // Getter pour le label "J'ai déjà un compte"
    public JLabel getLabelDejaCompte() {
        return labelDejaCompte;
    }

    // Méthodes utilitaires (pour le Contrôleur)
    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void fermerFenetre() {
        dispose();
    }
}
