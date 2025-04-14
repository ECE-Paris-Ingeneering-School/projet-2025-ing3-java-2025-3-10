package Vue;

import Modele.Logement;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ListeLogementsVue extends JFrame {

    private JTextField txtCategorie;
    private JButton btnRechercher;
    private JTable tableLogements;
    private LogementTableModel tableModel;

    public ListeLogementsVue() {
        setTitle("Liste des Logements");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Barre de recherche
        JPanel panelRecherche = new JPanel(new FlowLayout());
        panelRecherche.add(new JLabel("Catégorie:"));
        txtCategorie = new JTextField(10);
        panelRecherche.add(txtCategorie);

        btnRechercher = new JButton("Rechercher");
        panelRecherche.add(btnRechercher);

        // Table
        tableModel = new LogementTableModel();
        tableLogements = new JTable(tableModel);

        add(panelRecherche, BorderLayout.NORTH);
        add(new JScrollPane(tableLogements), BorderLayout.CENTER);
    }

    public JButton getBtnRechercher() {
        return btnRechercher;
    }

    public String getCategorieSaisie() {
        return txtCategorie.getText();
    }

    /**
     * Met à jour le tableau en affichant la liste de logements
     */
    public void afficherLogements(List<Logement> logements) {
        tableModel.setData(logements);
    }
}
