package Controleur;

import DAO.CommentaireDAO;
import DAO.CommentaireDAOImpl;
import Modele.Commentaire;
import Modele.Users;
import Vue.CommentaireVue;

public class CommentaireControleur {

    private Users userConnecte;
    private CommentaireDAO commentaireDAO;

    public CommentaireControleur(Users userConnecte) {
        this.userConnecte = userConnecte;
        this.commentaireDAO = new CommentaireDAOImpl(); // ou injection
    }

    public void afficherFormulaireCommentaire() {
        CommentaireVue cv = new CommentaireVue();
        cv.setVisible(true);

        cv.getBtnEnvoyer().addActionListener(e -> {
            int idCommentaire = 10; // ex
            int idLogement = cv.getIdLogement();
            String texte = cv.getContenu();

            Commentaire c = new Commentaire();
            c.setID_commentaire(idCommentaire);
            c.setContenu(texte);
            c.setID_logement(idLogement);
            c.setID(userConnecte.getID());

            commentaireDAO.create(c);
            cv.afficherMessage("Commentaire envoyé !");
        });
    }
}
