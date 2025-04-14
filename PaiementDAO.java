package DAO;

import Modele.Paiement;
import java.util.List;

public interface PaiementDAO {

    void create(Paiement paiement);

    Paiement findById(int ID_paiement);

    List<Paiement> findAll();

    void update(Paiement paiement);

    void delete(int ID_paiement);
}
