package Vue;

import Modele.Reservation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GererReservationsVue extends JFrame {

    private JTable tableReservations;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSupprimer;

    public GererReservationsVue() {
        setTitle("Gestion des Réservations");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Création des boutons
        btnAjouter = new JButton("Ajouter Réservation");
        btnModifier = new JButton("Modifier Réservation");
        btnSupprimer = new JButton("Supprimer Réservation");

        // Tableau pour afficher les réservations
        tableReservations = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableReservations);

        // Panel des boutons
        JPanel panelButtons = new JPanel();
        panelButtons.add(btnAjouter);
        panelButtons.add(btnModifier);
        panelButtons.add(btnSupprimer);

        // Ajouter les composants à la fenêtre
        panel.add(panelButtons, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    public void afficherReservations(List<Reservation> reservations) {
        String[] columns = {"ID", "Date", "Statut", "Utilisateur", "Logement", "Payée"};
        Object[][] data = new Object[reservations.size()][columns.length];

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            data[i][0] = r.getID_reservation();
            data[i][1] = r.getDate();
            data[i][2] = r.getStatut_reservation();
            data[i][3] = r.getID();  // L'ID utilisateur
            data[i][4] = r.getID_logement();  // L'ID du logement
            data[i][5] = r.isPaye() ? "Oui" : "Non";
        }

        tableReservations.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public int getSelectedReservationId() {
        int row = tableReservations.getSelectedRow();
        if (row == -1) {
            return -1;
        }
        return (int) tableReservations.getValueAt(row, 0);
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnModifier() {
        return btnModifier;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }
}
