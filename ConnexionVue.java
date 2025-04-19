package Vue;

import javax.swing.*;
import java.awt.*;

public class ConnexionVue extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtMotDePasse;
    private JButton btnConnexion;

    // Label cliquable pour inscription
    private JLabel labelInscription;

    public ConnexionVue() {
        setTitle("Page de Connexion");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bande bleue (haut)
        JPanel bandeBleu = new JPanel(new BorderLayout());
        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 60));

        JLabel labelBooking = new JLabel("Booking");
        labelBooking.setForeground(Color.WHITE);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bandeBleu.add(labelBooking, BorderLayout.WEST);

        // Titre
        JLabel titre = new JLabel("Se connecter");
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Champs login
        JLabel labelLogin = new JLabel("Nom d'utilisateur :");
        txtEmail = new JTextField("", 20);

        JLabel labelPassword = new JLabel("Mot de passe :");
        txtMotDePasse = new JPasswordField("", 20);

        // Bouton
        btnConnexion = new JButton("Connexion");
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label pour inscription
        labelInscription = new JLabel("Je n’ai pas encore de compte");
        labelInscription.setForeground(Color.BLUE.darker());
        labelInscription.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelInscription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelInscription.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
        labelInscription.setAlignmentX(Component.CENTER_ALIGNMENT);

        // GridBagLayout pour positionner login / mdp
        JPanel panelObject = new JPanel(new GridBagLayout());
        panelObject.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;

        // Ligne 1 - Nom d'utilisateur
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelObject.add(labelLogin, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(txtEmail, gbc);

        // Ligne 2 - Mot de passe
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelObject.add(labelPassword, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelObject.add(txtMotDePasse, gbc);

        // Panel vertical (BoxLayout)
        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBackground(Color.WHITE);
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));
        monPanel.add(titre);
        monPanel.add(Box.createVerticalStrut(30));
        monPanel.add(panelObject);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnConnexion);

        // Wrapper pour placer monPanel en haut
        JPanel wrapper_1 = new JPanel(new BorderLayout());
        wrapper_1.setBackground(Color.WHITE);
        wrapper_1.add(monPanel, BorderLayout.NORTH);

        // Panel bas
        JPanel panelBas = new JPanel();
        panelBas.setBackground(Color.WHITE);
        panelBas.add(labelInscription);

        // Placement final
        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(wrapper_1, BorderLayout.CENTER);
        add(panelBas, BorderLayout.SOUTH);

        // Couleur du fond principal
        getContentPane().setBackground(Color.WHITE);

        // Affichage final
        setVisible(true);
    }

    // Getters pour le Contrôleur
    public String getEmail() {
        return txtEmail.getText();
    }

    public String getMotDePasse() {
        return new String(txtMotDePasse.getPassword());
    }

    public JButton getBtnConnexion() {
        return btnConnexion;
    }

    // On expose le label pour que le contrôleur y ajoute un MouseListener
    public JLabel getLabelInscription() {
        return labelInscription;
    }

    // Méthodes utilitaires
    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void fermerFenetre() {
        dispose();
    }
}
