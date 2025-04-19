package Controleur;

import DAO.*;
import Modele.*;
import Vue.ListeReservationsVue;

import java.sql.Date;
import java.util.List;

public class ReservationControleur {

    private final Users user;
    private final ReservationDAO resaDAO = new ReservationDAOImpl();

    public ReservationControleur(Users u) {
        this.user = u;
    }

    /* Création de la réservation inchangée – on laisse resaDAO gérer l’ID auto */
    public void creerReservation(int idResa, int idLogement, Date date, int statut) {
        Reservation r = new Reservation();
        r.setID_reservation(0);      // 0 → sera MAX+1 dans DAO
        r.setDate(date);
        r.setStatut_reservation(statut);
        r.setID(user.getID());  // L'utilisateur connecté
        r.setID_logement(idLogement);
        r.setPaye(false);

        resaDAO.create(r);
    }

    /* Affiche les réservations du client */
    public void afficherReservationsClient() {
        // Création de la vue pour afficher les réservations
        ListeReservationsVue vue = new ListeReservationsVue();
        List<Reservation> l = resaDAO.findByUserId(user.getID());  // Récupérer uniquement les réservations de l'utilisateur
        vue.afficherReservations(l);  // Passer les réservations à la vue pour affichage

        vue.setVisible(true);
    }

    /* Admin - Gestion des réservations */
    public void gererReservationsAdmin() {
        ListeReservationsVue v = new ListeReservationsVue();
        v.afficherReservations(resaDAO.findAll());  // Afficher toutes les réservations de tous les utilisateurs
        v.setVisible(true);
    }
}
