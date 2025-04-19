package Controleur;

import DAO.LogementDAO;
import DAO.LogementDAOImpl;
import DAO.PaiementDAO;
import DAO.PaiementDAOImpl;
import DAO.ReservationDAO;
import DAO.ReservationDAOImpl;

import Modele.Logement;
import Modele.Paiement;
import Modele.Reservation;
import Modele.Users;
import Vue.FormPaiementVue;

import javax.swing.*;

public class PaiementControleur {

    private final Users          user;         // (facultatif pour d’autres features)
    private final Reservation    reservation;  // réservation à solder
    private final ReservationDAO resaDAO;
    private final PaiementDAO    payDAO = new PaiementDAOImpl();
    private final LogementDAO    logDAO = new LogementDAOImpl();

    public PaiementControleur(Users u, Reservation r, ReservationDAO resaDAO) {
        this.user        = u;
        this.reservation = r;
        this.resaDAO     = resaDAO != null ? resaDAO : new ReservationDAOImpl(); // S'assurer que le DAO est bien initialisé
    }

    /* =============================================================== */
    public void afficherFormPaiement() {

        // 1) Récupérer le prix et le titre du logement
        Logement logement = logDAO.findById(reservation.getID_logement());
        if (logement == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Logement introuvable (id=" + reservation.getID_logement() + ")",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        int prix = logement.getPrix();
        String titreLogement = logement.getTitre();

        // 2) Instancier la vue et pré‑remplir montant + récap
        FormPaiementVue vue = new FormPaiementVue();
        vue.setMontant(prix);
        vue.setRecap(titreLogement);
        vue.setVisible(true);

        // 3) Handler du bouton "Payer"
        vue.getBtnPayer().addActionListener(evt -> {
            try {
                int montant = vue.getMontant();

                // Créer et persister le paiement
                Paiement p = new Paiement();
                p.setIdPaiement(payDAO.getNextId());               // max+1
                p.setMontant(montant);
                p.setStatutPaiement(1);                              // 1 = payé
                p.setIdReservation(reservation.getID_reservation());
                payDAO.create(p);

                // Marquer la réservation comme payée
                reservation.setPaye(true);
                resaDAO.update(reservation);

                vue.afficherMessage("Paiement effectué avec succès !");
                vue.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        vue,
                        "Erreur paiement : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
