package Controleur;

import DAO.LogementDAO;
import DAO.LogementDAOImpl;
import Modele.Logement;
import Vue.GererLogementsVue;

import javax.swing.*;
import java.util.List;

public class GestionLogementsControleur {

    private GererLogementsVue vue;
    private LogementDAO logementDAO;

    public GestionLogementsControleur() {
        this.vue = new GererLogementsVue();
        this.logementDAO = new LogementDAOImpl();
        afficherLogements();
        initController();
    }

    private void initController() {
        vue.getBtnAjouter().addActionListener(e -> ouvrirFormulaireAjoutLogement());
        vue.getBtnModifier().addActionListener(e -> modifierLogement());
        vue.getBtnSupprimer().addActionListener(e -> supprimerLogement());
    }

    private void afficherLogements() {
        // Charger et afficher tous les logements
        List<Logement> logements = logementDAO.findAll();
        vue.afficherLogements(logements);
    }

    private void ouvrirFormulaireAjoutLogement() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfTitre = new JTextField(20);
        JTextField tfDescription = new JTextField(20);
        JTextField tfPrix = new JTextField(20);
        JTextField tfCategorie = new JTextField(20);
        JTextField tfImagePath = new JTextField(20);

        panel.add(new JLabel("Titre"));
        panel.add(tfTitre);
        panel.add(new JLabel("Description"));
        panel.add(tfDescription);
        panel.add(new JLabel("Prix"));
        panel.add(tfPrix);
        panel.add(new JLabel("Catégorie"));
        panel.add(tfCategorie);
        panel.add(new JLabel("Chemin de l'image"));
        panel.add(tfImagePath);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Ajouter un Logement", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String titre = tfTitre.getText();
            String description = tfDescription.getText();
            int prix = Integer.parseInt(tfPrix.getText());
            int categorie = Integer.parseInt(tfCategorie.getText());
            String imagePath = tfImagePath.getText();

            // Créer un nouveau logement
            Logement nouveauLogement = new Logement(0, titre, description, prix, categorie, 1, imagePath); // ID = 0 pour l'auto-incrémentation
            logementDAO.create(nouveauLogement);

            // Mettre à jour la liste des logements
            afficherLogements();
        }
    }

    private void modifierLogement() {
        int idLogement = vue.getSelectedLogementId();
        if (idLogement == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un logement à modifier.");
            return;
        }

        Logement logement = logementDAO.findById(idLogement);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField tfTitre = new JTextField(logement.getTitre(), 20);
        JTextField tfDescription = new JTextField(logement.getDescription(), 20);
        JTextField tfPrix = new JTextField(String.valueOf(logement.getPrix()), 20);
        JTextField tfCategorie = new JTextField(String.valueOf(logement.getCategorie()), 20);
        JTextField tfImagePath = new JTextField(logement.getImagePath(), 20);

        panel.add(new JLabel("Titre"));
        panel.add(tfTitre);
        panel.add(new JLabel("Description"));
        panel.add(tfDescription);
        panel.add(new JLabel("Prix"));
        panel.add(tfPrix);
        panel.add(new JLabel("Catégorie"));
        panel.add(tfCategorie);
        panel.add(new JLabel("Chemin de l'image"));
        panel.add(tfImagePath);

        int option = JOptionPane.showConfirmDialog(vue, panel, "Modifier le Logement", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            logement.setTitre(tfTitre.getText());
            logement.setDescription(tfDescription.getText());
            logement.setPrix(Integer.parseInt(tfPrix.getText()));
            logement.setCategorie(Integer.parseInt(tfCategorie.getText()));
            logement.setImagePath(tfImagePath.getText());

            logementDAO.update(logement);
            afficherLogements();  // Mettre à jour la liste des logements
        }
    }

    private void supprimerLogement() {
        int idLogement = vue.getSelectedLogementId();
        if (idLogement == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un logement à supprimer.");
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(vue, "Êtes-vous sûr de vouloir supprimer ce logement ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            logementDAO.delete(idLogement);
            afficherLogements();  // Mettre à jour la liste des logements
        }
    }
}
