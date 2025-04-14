package Controleur;

import Modele.Users;
import Vue.AdminMenuVue;

public class AdminControleur {

    private Users adminUser;
    private AdminMenuVue adminMenuVue;

    public AdminControleur(Users adminUser) {
        this.adminUser = adminUser;
        this.adminMenuVue = new AdminMenuVue();
    }

    public void afficherMenuAdmin() {
        adminMenuVue.setVisible(true);

        adminMenuVue.getBtnGererUtilisateurs().addActionListener(e -> ouvrirGestionUtilisateurs());
        adminMenuVue.getBtnGererLogements().addActionListener(e -> ouvrirGestionPrestations());
        adminMenuVue.getBtnVoirReservations().addActionListener(e -> ouvrirGestionReservations());
        adminMenuVue.getBtnStats().addActionListener(e -> ouvrirStats());
        adminMenuVue.getBtnDeconnexion().addActionListener(e -> seDeconnecter());
    }

    private void ouvrirGestionUtilisateurs() {
        // Par ex. : nouvelle vue, ou direct un UsersControleur
    }

    private void ouvrirGestionPrestations() {
        LogementControleur pc = new LogementControleur();
        pc.gererLogementsAdmin();
    }

    private void ouvrirGestionReservations() {
        ReservationControleur rc = new ReservationControleur(adminUser);
        rc.gererReservationsAdmin();
    }

    private void ouvrirStats() {
        StatistiquesControleur sc = new StatistiquesControleur();
        sc.afficherStats();
    }

    private void seDeconnecter() {
        adminMenuVue.dispose();
    }
}
