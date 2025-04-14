package Controleur;

import DAO.LogementDAO;
import DAO.LogementDAOImpl;
import Modele.Logement;
import Vue.ListeLogementsVue;

import javax.swing.JOptionPane;  // Import nécessaire pour JOptionPane
import java.util.List;

/**
 * Contrôleur pour la gestion/affichage des logements (Logement).
 */
public class LogementControleur {

    private ListeLogementsVue listeLogementsVue;
    private LogementDAO logementDAO;

    /**
     * Constructeur sans paramètre.
     * Il crée la vue ListeLogementsVue et un DAO LogementDAOImpl.
     * Puis appelle initController() pour enregistrer les écouteurs.
     */
    public LogementControleur() {
        this.listeLogementsVue = new ListeLogementsVue();
        this.logementDAO = new LogementDAOImpl();
        initController();
    }


    private void initController() {
        //listeLoggingsVue.getBtnRechercher().addActionListener(e -> rechercherParCategorie());
    }


    public void afficherListeLogements() {
        listeLogementsVue.setVisible(true);

        // Charger tous les logements
        List<Logement> all = logementDAO.findAll();
        listeLogementsVue.afficherLogements(all);
    }

    public void gererLogementsAdmin() {
        // Par exemple, on ouvre la vue, on affiche tous les logements
        listeLogementsVue.setVisible(true);
        // ... potentiellement, on fait du code spécial admin
    }

    private void rechercherParCategorie() {
        try {
            String catStr = listeLogementsVue.getCategorieSaisie();
            int cat = Integer.parseInt(catStr);

            // Récupère tous les logements de la catégorie cat
            List<Logement> results = logementDAO.findByCategorie(cat);

            // Affiche le résultat dans la vue
            listeLogementsVue.afficherLogements(results);

        } catch (NumberFormatException e) {
            // L'utilisateur a saisi autre chose qu'un entier
            JOptionPane.showMessageDialog(listeLogementsVue,
                    "Veuillez saisir un entier (catégorie) !");
        }
    }
}
