package DAO;

import Modele.Paiement;
import java.util.List;

public interface PaiementDAO {
    /* C R U D */
    void create (Paiement p);
    Paiement findById(int idPaiement);
    List<Paiement> findAll();
    void update(Paiement p);
    void delete(int idPaiement);

    /* utilitaires complémentaires */
    int  getNextId();                       // max(id)+1
    boolean existsForReservation(int idReservation);
}
