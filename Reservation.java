package Modele;

import java.sql.Date;

public class Reservation {

    private int    idReservation;
    private Date   date;
    private int    statutReservation;
    private int    idUser;
    private int    idLogement;
    private boolean paye;      // <‑‑ NOUVEL attribut

    public Reservation() {}

    /* --- getters / setters --- */
    public int getID_reservation()       { return idReservation; }
    public void setID_reservation(int v) { this.idReservation = v; }

    public Date getDate()                { return date; }
    public void setDate(Date v)          { this.date = v; }

    public int getStatut_reservation()   { return statutReservation; }
    public void setStatut_reservation(int v){ this.statutReservation = v; }

    public int getID()                   { return idUser; }
    public void setID(int v)             { this.idUser = v; }

    public int getID_logement()          { return idLogement; }
    public void setID_logement(int v)    { this.idLogement = v; }

    public boolean isPaye()              { return paye; }
    public void setPaye(boolean v)       { this.paye = v; }
}
