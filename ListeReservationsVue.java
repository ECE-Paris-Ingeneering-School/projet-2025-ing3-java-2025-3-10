package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Modele.Reservation;


public class ListeReservationsVue extends JFrame {

    private JTable tableReservations;
    private MyReservationTableModel tableModel;
    // MyReservationTableModel = classe similaire à MyTableModel,
    // mais spécialisée pour Reservation

    public ListeReservationsVue() {
        setTitle("Liste des Réservations");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new MyReservationTableModel();
        tableReservations = new JTable(tableModel);

        add(new JScrollPane(tableReservations), BorderLayout.CENTER);
    }

    /**
     * Méthode pour mettre à jour la table avec une liste de réservations
     */
    public void afficherReservations(List<Reservation> reservations) {
        tableModel.setData(reservations);
    }
}
