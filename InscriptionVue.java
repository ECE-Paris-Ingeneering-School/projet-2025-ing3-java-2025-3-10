package Vue;

import javax.swing.*;
import java.awt.*;

public class InscriptionVue extends JFrame {

    private JTextField txtNom, txtPrenom, txtEmail;
    private JPasswordField txtMotDePasse;
    private JButton btnInscription;

    public InscriptionVue() {
        setTitle("Inscription - Booking");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        panel.add(txtNom);

        panel.add(new JLabel("Prénom:"));
        txtPrenom = new JTextField();
        panel.add(txtPrenom);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Mot de passe:"));
        txtMotDePasse = new JPasswordField();
        panel.add(txtMotDePasse);

        btnInscription = new JButton("S'inscrire");
        panel.add(new JLabel(""));
        panel.add(btnInscription);

        add(panel);
    }

    // Getters pour le contrôleur
    public String getNom() {
        return txtNom.getText();
    }

    public String getPrenom() {
        return txtPrenom.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getMotDePasse() {
        return new String(txtMotDePasse.getPassword());
    }

    public JButton getBtnInscription() {
        return btnInscription;
    }

    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void fermerFenetre() {
        dispose();
    }
}
