package Controleur;

import DAO.UsersDAO;
import Modele.Users;
import Vue.InscriptionVue;
import javax.swing.JOptionPane;

public class InscriptionControleur {

    private InscriptionVue inscriptionVue;
    private UsersDAO usersDAO;

    public InscriptionControleur(InscriptionVue inscriptionVue, UsersDAO usersDAO) {
        this.inscriptionVue = inscriptionVue;
        this.usersDAO = usersDAO;
        initController();
    }

    private void initController() {
        inscriptionVue.getBtnInscription().addActionListener(e -> creerCompte());
    }

    private void creerCompte() {
        // Récupérer les champs de la vue
        String email = inscriptionVue.getEmail();
        String mdp   = inscriptionVue.getMotDePasse();
        String nom   = inscriptionVue.getNom();
        String prenom= inscriptionVue.getPrenom();

        // Vérifier si l'email n'existe pas déjà
        if (usersDAO.findByEmail(email) != null) {
            inscriptionVue.afficherMessage("Cette adresse email est déjà utilisée !");
            return;
        }

        // Par défaut, on suppose qu'un nouvel inscrit est un client (role=0).
        // Si vous gérez un champ "role" différent, adaptez.
        Users newUser = new Users(
                0,            // ID (si vous avez un auto-increment, vous pouvez mettre un placeholder)
                0,            // role=0 => client, 1 => admin
                email,
                mdp,
                nom,
                prenom
        );
        usersDAO.create(newUser);

        // Après la création, on peut récupérer l'ID généré si auto-increment,
        // par ex. newUser.setID(<ID retourné>); selon votre DAO.

        inscriptionVue.afficherMessage("Compte créé avec succès !\nConnexion automatique...");

        // Fermer la fenêtre d'inscription
        inscriptionVue.dispose();

        // Connexion automatique : rôle=0 => client, rôle=1 => admin, etc.
        if (newUser.getRole() == 1) {
            // Admin
            AdminControleur adminCtrl = new AdminControleur(newUser);
            adminCtrl.afficherMenuAdmin();
        } else {
            // Client
            ClientControleur clientCtrl = new ClientControleur(newUser);
            clientCtrl.afficherMenuClient();
        }
    }
}
