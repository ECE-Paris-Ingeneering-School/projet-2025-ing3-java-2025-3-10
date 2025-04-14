package Controleur;

import DAO.UsersDAO;
import Modele.Users;
import Vue.ConnexionVue;

/**
 * Gère la logique de connexion (login).
 */
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
    }

    private void verifierIdentifiants() {
        String email = connexionVue.getEmail();
        String mdp = connexionVue.getMotDePasse();

        Users user = usersDAO.findByEmail(email);
        if (user != null && user.getMdp().equals(mdp)) {
            connexionVue.afficherMessage("Connexion réussie !");
            // Rediriger selon le rôle
            if (user.getRole() == 1) {
                AdminControleur adminCtrl = new AdminControleur(user);
                adminCtrl.afficherMenuAdmin();
            } else {
                ClientControleur clientCtrl = new ClientControleur(user);
                clientCtrl.afficherMenuClient();
            }
            connexionVue.fermerFenetre();
        } else {
            connexionVue.afficherMessage("Identifiants invalides !");
        }
    }
}
