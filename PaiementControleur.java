package Controleur;

import DAO.PaiementDAO;
import DAO.PaiementDAOImpl;
import Modele.Paiement;
import Modele.Users;
import Vue.FormPaiementVue;

import java.util.List;

public class PaiementControleur {

    private Users userConnecte; // éventuellement
    private PaiementDAO paiementDAO;

    public PaiementControleur(Users userConnecte) {
        this.userConnecte = userConnecte;
        this.paiementDAO = new PaiementDAOImpl(); // ou injection
    }

    public void afficherFormPaiement() {
        FormPaiementVue pv = new FormPaiementVue();
        pv.setVisible(true);

        // Écoute d'un bouton "Payer"
        pv.getBtnPayer().addActionListener(e -> {
            // Récupérer montant, statut, etc.
            int idPaiement = 10; // ex
            int montant = pv.getMontant();
            int statut = 1; // 1 => payé

            Paiement p = new Paiement();
            p.setID_paiement(idPaiement);
            p.setMontant(montant);
            p.setStatut_paiement(statut);

            paiementDAO.create(p);
            pv.afficherMessage("Paiement effectué !");
        });
    }

    public void afficherTousLesPaiements() {
        List<Paiement> liste = paiementDAO.findAll();
        // Ouvrir une vue listant ces paiements, par ex.
    }
}
