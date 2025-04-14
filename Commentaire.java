package Modele;

public class Commentaire {
    private int ID_commentaire;
    private String contenu;
    private int ID_logement; // FK
    private int ID;          // FK vers users

    public Commentaire() {
    }

    public Commentaire(int ID_commentaire, String contenu, int ID_logement, int ID) {
        this.ID_commentaire = ID_commentaire;
        this.contenu = contenu;
        this.ID_logement = ID_logement;
        this.ID = ID;
    }

    public int getID_commentaire() {
        return ID_commentaire;
    }
    public void setID_commentaire(int ID_commentaire) {
        this.ID_commentaire = ID_commentaire;
    }

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getID_logement() {
        return ID_logement;
    }
    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "ID_commentaire=" + ID_commentaire +
                ", contenu='" + contenu + '\'' +
                ", ID_logement=" + ID_logement +
                ", ID=" + ID +
                '}';
    }
}
