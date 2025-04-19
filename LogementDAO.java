package DAO;

import Modele.Logement;
import java.util.List;

public interface LogementDAO {
    Logement findById(int ID_logement);
    List<Logement> findAll();
    List<Logement> findByCategorie(int categorie);

    // Nouvelle méthode pour la recherche
    List<Logement> rechercher(String titre, String description, String prix, String categorie);

    void create(Logement logement);
    void update(Logement logement);
    void delete(int ID_logement);
}
