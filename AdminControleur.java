package Controleur;

import Modele.Users;
import Vue.AdminMenuVue;

public class AdminControleur {

    private Users adminUser;
    private AdminMenuVue adminMenuVue;

    // Le constructeur de AdminControleur accepte maintenant l'utilisateur administrateur
    public AdminControleur(Users adminUser) {
        this.adminUser = adminUser;
        this.adminMenuVue = new AdminMenuVue(adminUser);  // Passer adminUser au constructeur de AdminMenuVue
    }

    public void afficherMenuAdmin() {
        adminMenuVue.setVisible(true);

        // Ajouter des listeners aux boutons dans AdminMenuVue
        adminMenuVue.getBtnGererUtilisateurs().addActionListener(e -> ouvrirGestionUtilisateurs());
        adminMenuVue.getBtnGererLogements().addActionListener(e -> ouvrirGestionLogements());
        adminMenuVue.getBtnVoirReservations().addActionListener(e -> ouvrirGestionReservations());
        adminMenuVue.getBtnStats().addActionListener(e -> ouvrirStats());
        adminMenuVue.getBtnDeconnexion().addActionListener(e -> seDeconnecter());
    }

    private void ouvrirGestionUtilisateurs() {
        // Afficher la gestion des utilisateurs
        System.out.println("Ouvrir la gestion des utilisateurs");
        new UsersControleur(adminUser);  // Appeler UsersControleur pour gérer les utilisateurs
    }

    private void ouvrirGestionLogements() {
        // Appeler GestionLogementsControleur pour la gestion des logements
        new GestionLogementsControleur();  // Appeler le contrôleur des logements
    }

    private void ouvrirGestionReservations() {
        // Appeler GererReservationsControleur pour la gestion des réservations
        new GererReservationsControleur();  // Appeler le contrôleur des réservations
    }

    private void ouvrirStats() {
        // Appeler StatistiquesControleur pour afficher les statistiques
        StatistiquesControleur sc = new StatistiquesControleur();
        sc.afficherStats();
    }

    private void seDeconnecter() {
        adminMenuVue.dispose();  // Fermer le menu admin
    }
}

    private void seDeconnecter() {
        adminMenuVue.dispose();
    }
}
