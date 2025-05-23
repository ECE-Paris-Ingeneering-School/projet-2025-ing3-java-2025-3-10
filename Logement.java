package Modele;

public class Logement {
    private int ID_logement;
    private String titre;
    private String description;
    private int prix;
    private int categorie;
    private int ID; // FK vers la table users
    private String imagePath; // Chemin de l'image du logement

    // Constructeurs
    public Logement() {
    }

    public Logement(int ID_logement, String titre, String description, int prix, int categorie, int ID, String imagePath) {
        this.ID_logement = ID_logement;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.ID = ID;
        this.imagePath = imagePath; // Initialise le chemin de l'image
    }

    // Getters et setters
    public int getID_logement() {
        return ID_logement;
    }

    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImagePath() {
        return imagePath; // Retourne le chemin de l'image
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath; // Permet de définir le chemin de l'image
    }

    @Override
    public String toString() {
        return "Logement{" +
                "ID_logement=" + ID_logement +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", categorie=" + categorie +
                ", ID=" + ID +
                ", imagePath='" + imagePath + '\'' + // Affiche également le chemin de l'image
                '}';
    }
}
