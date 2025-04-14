package Modele;


import java.util.Date;

public class Reservation {
    private int ID_reservation;
    private Date date;
    private int statut_reservation;
    private int ID;          // FK vers users
    private int ID_logement; // FK vers logement

    public Reservation() {
    }

    public Reservation(int ID_reservation, Date date, int statut_reservation, int ID, int ID_logement) {
        this.ID_reservation = ID_reservation;
        this.date = date;
        this.statut_reservation = statut_reservation;
        this.ID = ID;
        this.ID_logement = ID_logement;
    }

    public int getID_reservation() {
        return ID_reservation;
    }
    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatut_reservation() {
        return statut_reservation;
    }
    public void setStatut_reservation(int statut_reservation) {
        this.statut_reservation = statut_reservation;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_logement() {
        return ID_logement;
    }
    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID_reservation=" + ID_reservation +
                ", date=" + date +
                ", statut_reservation=" + statut_reservation +
                ", ID=" + ID +
                ", ID_logement=" + ID_logement +
                '}';
    }
}
