package Modele;

public class Paiement {
    private int ID_paiement;
    private int montant;
    private int statut_paiement;

    public Paiement() {
    }

    public Paiement(int ID_paiement, int montant, int statut_paiement) {
        this.ID_paiement = ID_paiement;
        this.montant = montant;
        this.statut_paiement = statut_paiement;
    }

    public int getID_paiement() {
        return ID_paiement;
    }
    public void setID_paiement(int ID_paiement) {
        this.ID_paiement = ID_paiement;
    }

    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getStatut_paiement() {
        return statut_paiement;
    }
    public void setStatut_paiement(int statut_paiement) {
        this.statut_paiement = statut_paiement;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "ID_paiement=" + ID_paiement +
                ", montant=" + montant +
                ", statut_paiement=" + statut_paiement +
                '}';
    }
}
