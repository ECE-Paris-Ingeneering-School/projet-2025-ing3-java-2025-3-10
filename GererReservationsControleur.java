package Controleur;

import DAO.ReservationDAO;
import DAO.ReservationDAOImpl;
import Modele.Reservation;
import Vue.GererReservationsVue;

import javax.swing.*;
import java.util.List;
import java.sql.Date;

public class GererReservationsControleur {

    private GererReservationsVue vue;
    private ReservationDAO reservationDAO;

    public GererReservationsControleur() {
        this.vue = new GererReservationsVue();
        this.reservationDAO = new ReservationDAOImpl();
        afficherReservations();
        initController();
    }

    private void initController() {
        vue.getBtnAjouter().addActionListener(e -> ouvrirFormulaireAjoutReservation());
        vue.getBtnModifier().addActionListener(e -> modifierReservation());
        vue.getBtnSupprimer().addActionListener(e -> supprimerReservation());
    }

    private void afficherReservations() {
        // Charger et afficher toutes les réservations
        List<Reservation> reservations = reservationDAO.findAll();
        vue.afficherReservations(reservations);
    }

    private void ouvrirFormulaireAjoutReservation() {
        // Créer un formulaire d'ajout de réservation
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfDate = new JTextField(20);
        JTextField tfStatut = new JTextField(20);
        JTextField tfIDUser = new JTextField(20);
        JTextField tfIDLogement = new JTextField(20);

        panel.add(new JLabel("Date (yyyy-mm-dd)"));
        panel.add(tfDate);
        panel.add(new JLabel("Statut"));
        panel.add(tfStatut);
        panel.add(new JLabel("ID Utilisateur"));
        panel.add(tfIDUser);
        panel.add(new JLabel("ID Logement"));
        panel.add(tfIDLogement);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Ajouter une Réservation", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Récupérer les valeurs
            String dateStr = tfDate.getText();  // Date au format "yyyy-mm-dd"
            int statut = Integer.parseInt(tfStatut.getText());
            int idUser = Integer.parseInt(tfIDUser.getText());
            int idLogement = Integer.parseInt(tfIDLogement.getText());

            // Convertir la chaîne de date en Date
            Date date = Date.valueOf(dateStr);  // Utiliser Date.valueOf pour convertir la chaîne en java.sql.Date

            // Créer une nouvelle réservation
            Reservation nouvelleReservation = new Reservation();
            nouvelleReservation.setID_reservation(0);  // ID = 0 pour l'auto-incrémentation
            nouvelleReservation.setDate(date);
            nouvelleReservation.setStatut_reservation(statut);
            nouvelleReservation.setID(idUser);
            nouvelleReservation.setID_logement(idLogement);
            nouvelleReservation.setPaye(false);  // Par défaut, on suppose que la réservation n'est pas payée

            reservationDAO.create(nouvelleReservation);

            // Mettre à jour la liste des réservations
            afficherReservations();
        }
    }


    private void modifierReservation() {
        int idReservation = vue.getSelectedReservationId();
        if (idReservation == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner une réservation à modifier.");
            return;
        }

        Reservation reservation = reservationDAO.findById(idReservation);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfDate = new JTextField(reservation.getDate().toString(), 20);
        JTextField tfStatut = new JTextField(String.valueOf(reservation.getStatut_reservation()), 20);
        JTextField tfIDUser = new JTextField(String.valueOf(reservation.getID()), 20);
        JTextField tfIDLogement = new JTextField(String.valueOf(reservation.getID_logement()), 20);

        panel.add(new JLabel("Date"));
        panel.add(tfDate);
        panel.add(new JLabel("Statut"));
        panel.add(tfStatut);
        panel.add(new JLabel("ID Utilisateur"));
        panel.add(tfIDUser);
        panel.add(new JLabel("ID Logement"));
        panel.add(tfIDLogement);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Modifier la Réservation", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            reservation.setDate(Date.valueOf(tfDate.getText()));
            reservation.setStatut_reservation(Integer.parseInt(tfStatut.getText()));
            reservation.setID(Integer.parseInt(tfIDUser.getText()));
            reservation.setID_logement(Integer.parseInt(tfIDLogement.getText()));

            reservationDAO.update(reservation);
            afficherReservations();  // Mettre à jour la liste des réservations
        }
    }

    private void supprimerReservation() {
        int idReservation = vue.getSelectedReservationId();
        if (idReservation == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner une réservation à supprimer.");
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(vue, "Êtes-vous sûr de vouloir supprimer cette réservation ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            reservationDAO.delete(idReservation);
            afficherReservations();  // Mettre à jour la liste des réservations
        }
    }
}
