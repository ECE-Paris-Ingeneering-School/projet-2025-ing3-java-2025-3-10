package DAO;

import Modele.ImageLogement;
import java.util.List;

public interface ImageLogementDAO {
    /** Retourne la liste triée d’images pour ce logement */
    List<ImageLogement> findByLogementId(int idLogement);

    /** Persiste une nouvelle image (l’ID est auto‑incrementé en base) */
    void create(ImageLogement img);
}
