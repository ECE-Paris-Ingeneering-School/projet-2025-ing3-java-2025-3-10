
import Controleur.AccueilControleur;      // Contrôleur de la page d'accueil
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable !");
            e.printStackTrace();
            System.exit(1);
        }


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // pas bloquant si échec
            e.printStackTrace();
        }

        AccueilControleur accueilControleur = new AccueilControleur();
        accueilControleur.afficherAccueil();
    }
}
