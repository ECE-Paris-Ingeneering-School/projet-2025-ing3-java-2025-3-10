package Vue;

import Modele.Logement;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GererLogementsVue extends JFrame {

    private JTable tableLogements;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSupprimer;

    public GererLogementsVue() {
        setTitle("Gestion des Logements");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Création des boutons
        btnAjouter = new JButton("Ajouter Logement");
        btnModifier = new JButton("Modifier Logement");
        btnSupprimer = new JButton("Supprimer Logement");

        // Tableau pour afficher les logements
        tableLogements = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLogements);

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

    public void afficherLogements(List<Logement> logements) {
        String[] columns = {"ID", "Titre", "Description", "Prix", "Catégorie", "Rôle"};
        Object[][] data = new Object[logements.size()][columns.length];

        for (int i = 0; i < logements.size(); i++) {
            Logement log = logements.get(i);
            data[i][0] = log.getID_logement();
            data[i][1] = log.getTitre();
            data[i][2] = log.getDescription();
            data[i][3] = log.getPrix();
            data[i][4] = log.getCategorie();
            data[i][5] = log.getImagePath(); // Si vous souhaitez afficher l'image du logement
        }

        tableLogements.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public int getSelectedLogementId() {
        int row = tableLogements.getSelectedRow();
        if (row == -1) {
            return -1;
        }
        return (int) tableLogements.getValueAt(row, 0);
    }

    // Getters pour les boutons
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
