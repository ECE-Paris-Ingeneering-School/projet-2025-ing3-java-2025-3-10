package Controleur;

import DAO.UsersDAO;
import Modele.Users;
import Vue.InscriptionVue;
import Vue.ListeLogementsVue;
import Vue.ConnexionVue;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionControleur {

    private InscriptionVue inscriptionVue;
    private UsersDAO usersDAO;

    public InscriptionControleur(InscriptionVue inscriptionVue, UsersDAO usersDAO) {
        this.inscriptionVue = inscriptionVue;
        this.usersDAO = usersDAO;
        initController();
    }

    private void initController() {
        // Ecoute du bouton "S'inscrire"
        inscriptionVue.getBtnInscription().addActionListener(e -> creerCompte());

        // Ecoute du label "J'ai déjà un compte" (clic)
        inscriptionVue.getLabelDejaCompte().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ouvrirConnexion();
            }
        });
    }

    private void creerCompte() {
        String email  = inscriptionVue.getEmail();
        String mdp    = inscriptionVue.getMotDePasse();
        String nom    = inscriptionVue.getNom();
        String prenom = inscriptionVue.getPrenom();

        if (usersDAO.findByEmail(email) != null) {
            inscriptionVue.afficherMessage("Cette adresse email est déjà utilisée !");
            return;
        }

        // role=0 => client
        Users newUser = new Users(0, 0, email, mdp, nom, prenom);
        usersDAO.create(newUser);

        inscriptionVue.afficherMessage("Compte créé avec succès !\nConnexion automatique...");
        inscriptionVue.dispose();

        // Connexion auto et affichage des logements
        ListeLogementsVue accueilVue = new ListeLogementsVue(newUser);
        accueilVue.setVisible(true);

        // Charger tous les logements après la connexion
        LogementControleur logementControleur = new LogementControleur(newUser);
        logementControleur.afficherListeLogements();  // Charger et afficher tous les logements pour le client
    }

    private void ouvrirConnexion() {
        // Fermer l'inscription
        inscriptionVue.dispose();

        // Ouvrir la connexion
        ConnexionVue connexionVue = new ConnexionVue();
        new ConnexionControleur(connexionVue, this.usersDAO);
        connexionVue.setVisible(true);
    }
}

