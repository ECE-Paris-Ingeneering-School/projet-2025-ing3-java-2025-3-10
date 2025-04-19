package Controleur;

import DAO.CommentaireDAO;
import DAO.CommentaireDAOImpl;
import Modele.Commentaire;
import Modele.Users;
import Vue.CommentaireVue;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CommentaireControleur {

    private final Users           userConnecte;
    private final int             idLogement;
    private final CommentaireDAO  commentaireDAO;

    public CommentaireControleur(Users userConnecte, int idLogement) {
        this.userConnecte   = userConnecte;
        this.idLogement     = idLogement;
        this.commentaireDAO = new CommentaireDAOImpl();
    }

    /**
     * Initialise et retourne le panel de commentaires préchargé.
     * Tu pourras l'injecter dans ta PageAnnonceVue via add(...).
     */
    public CommentaireVue creerCommentairePanel() {
        CommentaireVue vue = new CommentaireVue();

        // 1) Charger les commentaires existants
        List<String> textes = commentaireDAO.findByLogementId(idLogement)
                .stream()
                .map(Commentaire::getContenu)
                .collect(Collectors.toList());
        vue.setCommentaires(textes);

        // 2) Attacher le handler “Envoyer”
        vue.addEnvoyerListener(e -> {
            String texte = vue.getChampCommentaire();
            if (texte.isEmpty()) {
                JOptionPane.showMessageDialog(vue,
                        "Le commentaire ne peut pas être vide.",
                        "Attention",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Créer et persister
            Commentaire c = new Commentaire();
            c.setID_commentaire(0);       // 0 → DAO générera max+1
            c.setContenu(texte);
            c.setID_logement(idLogement);
            c.setID(userConnecte.getID());
            commentaireDAO.create(c);

            // Rafraîchir la vue
            vue.ajouterCommentaire(texte);
            vue.clearChampCommentaire();
        });

        return vue;
    }
}
