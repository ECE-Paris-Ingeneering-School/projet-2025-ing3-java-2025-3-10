package Controleur;

import DAO.UsersDAO;
import Modele.Users;
import Vue.ConnexionVue;
import Vue.InscriptionVue;
import Vue.ListeLogementsVue;
import Vue.AdminMenuVue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnexionControleur {

    private ConnexionVue connexionVue;
    private UsersDAO usersDAO;

    public ConnexionControleur(ConnexionVue connexionVue, UsersDAO usersDAO) {
        this.connexionVue = connexionVue;
        this.usersDAO = usersDAO;
        initController();
    }

    private void initController() {
        // Écoute du bouton de connexion
        connexionVue.getBtnConnexion().addActionListener(e -> verifierIdentifiants());

        // Écoute du label "Je n’ai pas encore de compte"
        connexionVue.getLabelInscription().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ouvrirInscription();
            }
        });
    }

    private void verifierIdentifiants() {
        String email = connexionVue.getEmail();
        String mdp = connexionVue.getMotDePasse();

        Users user = usersDAO.findByEmail(email);
        if (user != null && user.getMdp().equals(mdp)) {
            connexionVue.afficherMessage("Connexion réussie !");

            // Vérifier le rôle de l'utilisateur
            if (user.getRole() == 1) {  // Si l'utilisateur est un admin
                // Instancier AdminControleur pour afficher le menu admin
                AdminControleur adminControleur = new AdminControleur(user);  // Passer adminUser au constructeur
                adminControleur.afficherMenuAdmin();  // Afficher le menu administrateur
            } else {  // Si l'utilisateur est un client
                // Rediriger vers la page d'accueil avec la liste des logements
                ListeLogementsVue accueilVue = new ListeLogementsVue(user);
                accueilVue.setVisible(true);

                // Charger tous les logements après la connexion
                LogementControleur logementControleur = new LogementControleur(user);
                logementControleur.afficherListeLogements();  // Charger et afficher tous les logements pour le client
            }

            // Fermer la fenêtre de connexion après la connexion réussie
            connexionVue.fermerFenetre();
        } else {
            connexionVue.afficherMessage("Identifiants invalides !");
        }
    }

    private void ouvrirInscription() {
        // Fermer la fenêtre de connexion (optionnel)
        connexionVue.dispose();

        // Créer la vue d'inscription
        InscriptionVue inscriptionVue = new InscriptionVue();

        // Créer le contrôleur d'inscription (besoin d'un UsersDAO ?)
        new InscriptionControleur(inscriptionVue, this.usersDAO);

        // Afficher la vue
        inscriptionVue.setVisible(true);
    }
}

