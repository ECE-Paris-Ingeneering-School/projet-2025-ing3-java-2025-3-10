package Modele;


public class Paiement {

    private int idPaiement;       // PK
    private int montant;
    private int statutPaiement;   // 0 = en attente | 1 = payé
    private int idReservation = -1;

    /* -----------------  C T R S  ----------------- */
    public Paiement() { }                                // vide
    public Paiement(int idPaiement, int montant,
                    int statutPaiement, int idReservation) {
        this.idPaiement      = idPaiement;
        this.montant         = montant;
        this.statutPaiement  = statutPaiement;
        this.idReservation   = idReservation;
    }

    /* ----------------- G / S ----------------- */
    public int  getIdPaiement()            { return idPaiement; }
    public void setIdPaiement(int id)      { this.idPaiement = id; }

    public int  getMontant()               { return montant; }
    public void setMontant(int montant)    { this.montant = montant; }

    public int  getStatutPaiement()               { return statutPaiement; }
    public void setStatutPaiement(int statut)     { this.statutPaiement = statut; }

    public int  getIdReservation()               { return idReservation; }
    public void setIdReservation(int idReserva)  { this.idReservation = idReserva; }

    @Override public String toString() {
        return "Paiement{id="+idPaiement+", montant="+montant+
                ", statut="+statutPaiement+", idReservation="+idReservation+'}';
    }
}
