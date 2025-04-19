package Controleur;

import DAO.UsersDAO;
import DAO.UsersDAOImpl;
import Modele.Users;
import Vue.GererUtilisateursVue;

import javax.swing.*;
import java.util.List;

public class UsersControleur {

    private Users adminUser;
    private UsersDAO usersDAO;
    private GererUtilisateursVue vue;

    // Le constructeur de UsersControleur accepte l'utilisateur administrateur
    public UsersControleur(Users adminUser) {
        this.adminUser = adminUser;
        this.usersDAO = new UsersDAOImpl();
        this.vue = new GererUtilisateursVue();
        afficherUtilisateurs();
        initController();
    }

    private void initController() {
        // Ajout des actions pour les boutons
        vue.getBtnAjouter().addActionListener(e -> ouvrirFormulaireAjoutUtilisateur());
        vue.getBtnModifier().addActionListener(e -> modifierUtilisateur());
        vue.getBtnSupprimer().addActionListener(e -> supprimerUtilisateur());
    }

    private void afficherUtilisateurs() {
        // Charger et afficher tous les utilisateurs
        List<Users> utilisateurs = usersDAO.findAll();
        vue.afficherUtilisateurs(utilisateurs);
    }

    private void ouvrirFormulaireAjoutUtilisateur() {
        // Créer un formulaire d'ajout utilisateur
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfNom = new JTextField(20);
        JTextField tfPrenom = new JTextField(20);
        JTextField tfEmail = new JTextField(20);
        JPasswordField tfMdp = new JPasswordField(20);
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"Client", "Administrateur"});

        panel.add(new JLabel("Nom"));
        panel.add(tfNom);
        panel.add(new JLabel("Prénom"));
        panel.add(tfPrenom);
        panel.add(new JLabel("Email"));
        panel.add(tfEmail);
        panel.add(new JLabel("Mot de passe"));
        panel.add(tfMdp);
        panel.add(new JLabel("Rôle"));
        panel.add(cbRole);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Ajouter un utilisateur", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            String email = tfEmail.getText();
            String mdp = new String(tfMdp.getPassword());
            String roleStr = (String) cbRole.getSelectedItem();
            int role = "Administrateur".equals(roleStr) ? 1 : 0;

            // Créer un nouvel utilisateur
            Users newUser = new Users(0, role, email, mdp, nom, prenom);
            usersDAO.create(newUser);

            // Mettre à jour la liste des utilisateurs
            afficherUtilisateurs();
        }
    }

    private void modifierUtilisateur() {
        int idUtilisateur = vue.getSelectedUserId();
        if (idUtilisateur == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un utilisateur à modifier.");
            return;
        }

        // Récupérer l'utilisateur sélectionné
        Users user = usersDAO.findById(idUtilisateur);

        // Créer un formulaire de modification avec les données de l'utilisateur
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfNom = new JTextField(user.getNom(), 20);
        JTextField tfPrenom = new JTextField(user.getPrenom(), 20);
        JTextField tfEmail = new JTextField(user.getEmail(), 20);
        JPasswordField tfMdp = new JPasswordField(user.getMdp(), 20);
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"Client", "Administrateur"});
        cbRole.setSelectedIndex(user.getRole());

        panel.add(new JLabel("Nom"));
        panel.add(tfNom);
        panel.add(new JLabel("Prénom"));
        panel.add(tfPrenom);
        panel.add(new JLabel("Email"));
        panel.add(tfEmail);
        panel.add(new JLabel("Mot de passe"));
        panel.add(tfMdp);
        panel.add(new JLabel("Rôle"));
        panel.add(cbRole);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Modifier l'utilisateur", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            String email = tfEmail.getText();
            String mdp = new String(tfMdp.getPassword());
            String roleStr = (String) cbRole.getSelectedItem();
            int role = "Administrateur".equals(roleStr) ? 1 : 0;

            // Mettre à jour les informations de l'utilisateur
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setMdp(mdp);
            user.setRole(role);

            usersDAO.update(user);

            // Mettre à jour la liste des utilisateurs
            afficherUtilisateurs();
        }
    }

    private void supprimerUtilisateur() {
        int idUtilisateur = vue.getSelectedUserId();
        if (idUtilisateur == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un utilisateur à supprimer.");
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(vue, "Êtes-vous sûr de vouloir supprimer cet utilisateur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            usersDAO.delete(idUtilisateur);
            afficherUtilisateurs();  // Rafraîchir la liste des utilisateurs
        }
    }
}
