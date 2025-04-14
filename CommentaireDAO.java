package DAO;

import Modele.Commentaire;
import java.util.List;

public interface CommentaireDAO {

    void create(Commentaire commentaire);

    Commentaire findById(int ID_commentaire);

    List<Commentaire> findAll();

    void update(Commentaire commentaire);

    void delete(int ID_commentaire);

    // Par exemple, trouver tous les commentaires d'un logement
    List<Commentaire> findByLogementId(int ID_logement);
}
