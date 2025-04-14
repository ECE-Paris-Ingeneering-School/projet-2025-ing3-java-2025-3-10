package Controleur;

import Vue.ListeReservationsVue;  // <-- import NECESSAIRE

import DAO.ReservationDAO;
import DAO.ReservationDAOImpl;
import Modele.Reservation;
import Modele.Users;
import java.sql.Date;
import java.util.List;


public class ReservationControleur {

    private Users userConnecte;
    private ReservationDAO reservationDAO;

    public ReservationControleur(Users userConnecte) {
        this.userConnecte = userConnecte;
        this.reservationDAO = new ReservationDAOImpl(); // ou injection
    }

    public void afficherReservationsClient() {
        // Ouvrir une vue affichant les réservations du userConnecte
        ListeReservationsVue listeVue = new ListeReservationsVue();
        listeVue.setVisible(true);

        List<Reservation> liste = reservationDAO.findByUserId(userConnecte.getID());
        listeVue.afficherReservations(liste);

        // Écoute d'un bouton "Annuler" => appeler reservationDAO.delete(...)
    }

    public void gererReservationsAdmin() {
        // Lister toutes les réservations, possibilités de filtrer...
    }

    public void creerReservation(int idReservation, int idLogement, Date date, int statut) {
        // Ex : pour la vue d'un client qui choisit un logement, une date, ...
        Reservation r = new Reservation();
        r.setID_reservation(idReservation);
        r.setDate(date);
        r.setStatut_reservation(statut);
        r.setID(userConnecte.getID());
        r.setID_logement(idLogement);

        reservationDAO.create(r);
        // messages, mise à jour vue, etc.
    }
}
