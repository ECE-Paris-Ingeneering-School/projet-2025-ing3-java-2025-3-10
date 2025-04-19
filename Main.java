import Controleur.ConnexionControleur;
import DAO.UsersDAO;
import DAO.UsersDAOImpl;
import Vue.ConnexionVue;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Charger le driver MySQL (optionnel si vous le faites ailleurs)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable !");
            e.printStackTrace();
            System.exit(1);
        }

        // Optionnel : configurer le look & feel natif
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Créer la vue de connexion
        ConnexionVue connexionVue = new ConnexionVue();

        // Créer le DAO (pour la table Users)
        UsersDAO usersDAO = new UsersDAOImpl();

        // Créer le contrôleur de connexion en passant la vue + le DAO
        new ConnexionControleur(connexionVue, usersDAO);

        // Afficher la fenêtre de connexion
        connexionVue.setVisible(true);
    }
}
