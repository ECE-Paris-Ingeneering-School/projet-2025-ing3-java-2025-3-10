package Vue;

import Modele.Users;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GererUtilisateursVue extends JFrame {

    private JTable tableUtilisateurs;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSupprimer;

    public GererUtilisateursVue() {
        setTitle("Gestion des Utilisateurs");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Création des boutons
        btnAjouter = new JButton("Ajouter Utilisateur");
        btnModifier = new JButton("Modifier Utilisateur");
        btnSupprimer = new JButton("Supprimer Utilisateur");

        // Tableau pour afficher les utilisateurs
        tableUtilisateurs = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableUtilisateurs);

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

    public void afficherUtilisateurs(List<Users> utilisateurs) {
        String[] columns = {"ID", "Nom", "Prénom", "Email", "Rôle"};
        Object[][] data = new Object[utilisateurs.size()][columns.length];

        for (int i = 0; i < utilisateurs.size(); i++) {
            Users u = utilisateurs.get(i);
            data[i][0] = u.getID();
            data[i][1] = u.getNom();
            data[i][2] = u.getPrenom();
            data[i][3] = u.getEmail();
            data[i][4] = u.getRole() == 1 ? "Administrateur" : "Client";
        }

        tableUtilisateurs.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public int getSelectedUserId() {
        int row = tableUtilisateurs.getSelectedRow();
        if (row == -1) {
            return -1;
        }
        return (int) tableUtilisateurs.getValueAt(row, 0);
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
