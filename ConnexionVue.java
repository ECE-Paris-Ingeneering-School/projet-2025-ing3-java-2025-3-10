package Vue;

import javax.swing.*;
import java.awt.*;


public class ConnexionVue extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtMotDePasse;
    private JButton btnConnexion;

    public ConnexionVue() {
        setTitle("Connexion - Booking");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Mot de passe:"));
        txtMotDePasse = new JPasswordField();
        panel.add(txtMotDePasse);

        // Espace pour alignement
        panel.add(new JLabel(""));
        btnConnexion = new JButton("Se connecter");
        panel.add(btnConnexion);

        add(panel);
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

    // Méthodes utilitaires
    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void fermerFenetre() {
        dispose();
    }
}
