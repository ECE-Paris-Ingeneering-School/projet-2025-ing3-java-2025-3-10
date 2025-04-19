package Modele;

public class ImageLogement {
    private int    idImage;      // PK
    private int    idLogement;   // FK vers logement
    private String chemin;       // chemin / URL du fichier
    private String description;  // légende (facultatif)
    private int    ordre;        // pour trier

    public ImageLogement() {}

    public ImageLogement(int idImage, int idLogement, String chemin, String description, int ordre) {
        this.idImage      = idImage;
        this.idLogement   = idLogement;
        this.chemin       = chemin;
        this.description  = description;
        this.ordre        = ordre;
    }

    public int getIdImage()             { return idImage; }
    public void setIdImage(int id)      { this.idImage = id; }

    public int getIdLogement()          { return idLogement; }
    public void setIdLogement(int id)   { this.idLogement = id; }

    public String getChemin()           { return chemin; }
    public void setChemin(String c)     { this.chemin = c; }

    public String getDescription()      { return description; }
    public void setDescription(String d){ this.description = d; }

    public int getOrdre()               { return ordre; }
    public void setOrdre(int o)         { this.ordre = o; }

    @Override
    public String toString() {
        return "ImageLogement{" +
                "idImage=" + idImage +
                ", idLogement=" + idLogement +
                ", chemin='" + chemin + '\'' +
                ", description='" + description + '\'' +
                ", ordre=" + ordre +
                '}';
    }
}
