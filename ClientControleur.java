package Controleur;

import Modele.Users;
import Vue.ClientMenuVue;


public class ClientControleur {

    private Users userConnecte;
    private ClientMenuVue clientMenuVue;

    public ClientControleur(Users userConnecte) {
        this.userConnecte = userConnecte;
        this.clientMenuVue = new ClientMenuVue();
    }

    public void afficherMenuClient() {
        clientMenuVue.setVisible(true);

        clientMenuVue.getBtnVoirLogements().addActionListener(e -> ouvrirLogementControleur());
        clientMenuVue.getBtnMesReservations().addActionListener(e -> ouvrirReservationControleur());
        clientMenuVue.getBtnCommenter().addActionListener(e -> ouvrirCommentaireControleur());
        clientMenuVue.getBtnDeconnexion().addActionListener(e -> seDeconnecter());    }


    private void ouvrirLogementControleur() {
        LogementControleur lc = new LogementControleur();
        lc.afficherListeLogements();
    }

    private void ouvrirReservationControleur() {
        // Accéder aux réservations du client
        ReservationControleur rc = new ReservationControleur(userConnecte);
        rc.afficherReservationsClient();
    }

    private void ouvrirCommentaireControleur() {
        // Accéder à l'interface commentaire
        CommentaireControleur cc = new CommentaireControleur(userConnecte);
        cc.afficherFormulaireCommentaire();
    }

    private void seDeconnecter() {
        clientMenuVue.dispose();
        // Option : revenir à la vue de connexion
    }
}
