package Controleur;

import Vue.AccueilVue;
import Vue.ConnexionVue;
import Vue.InscriptionVue;
import DAO.UsersDAO;
import DAO.UsersDAOImpl;


public class AccueilControleur {

    private AccueilVue accueilVue;
    private UsersDAO usersDAO;

    public AccueilControleur() {
        // Crée la vue
        this.accueilVue = new AccueilVue();
        // Prépare le DAO pour "Users" (pour connexion/inscription)
        this.usersDAO = new UsersDAOImpl();

        initController();
    }

    private void initController() {
        // Écoute du bouton "Connexion"
        accueilVue.getBtnConnexion().addActionListener(e -> ouvrirConnexion());

        // Écoute du bouton "Inscription"
        accueilVue.getBtnInscription().addActionListener(e -> ouvrirInscription());
    }

    public void afficherAccueil() {
        accueilVue.setVisible(true);
    }

    private void ouvrirConnexion() {
        // On ferme la fenêtre d'accueil (optionnel)
        accueilVue.dispose();

        // Ouvrir la vue de connexion
        ConnexionVue connexionVue = new ConnexionVue();
        new ConnexionControleur(connexionVue, usersDAO);
        connexionVue.setVisible(true);
    }

    private void ouvrirInscription() {
        // On ferme la fenêtre d'accueil (optionnel)
        accueilVue.dispose();

        // Ouvrir la vue d'inscription
        InscriptionVue inscriptionVue = new InscriptionVue();
        new InscriptionControleur(inscriptionVue, usersDAO);
        inscriptionVue.setVisible(true);
    }
}
