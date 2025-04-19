package Controleur;

import DAO.ImageLogementDAO;
import DAO.ImageLogementDAOImpl;
import DAO.ReservationDAO;
import DAO.ReservationDAOImpl;
import Modele.ImageLogement;
import Modele.Logement;
import Modele.Reservation;
import Modele.Users;
import Vue.CommentaireVue;
import Vue.FormReservationVue;
import Vue.PageAnnonceVue;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnonceControleur {

    private final Users        user;
    private final Logement     logement;
    private final PageAnnonceVue vueAnnonce;

    public AnnonceControleur(Users user, Logement logement) {
        this.user     = user;
        this.logement = logement;

        // 1) Préparer le panneau de commentaires
        CommentaireControleur cc = new CommentaireControleur(user, logement.getID_logement());
        CommentaireVue commentairePanel = cc.creerCommentairePanel();

        // 2) Construire la vue d’annonce
        this.vueAnnonce = new PageAnnonceVue(commentairePanel);

        // 3) Charger toutes les images associées et les transformer en ImageIcon
        ImageLogementDAO imgDAO = new ImageLogementDAOImpl();
        List<ImageLogement> images = imgDAO.findByLogementId(logement.getID_logement());
        List<ImageIcon> photos = images.stream()
                .map(img -> new ImageIcon(img.getChemin()))
                .collect(Collectors.toList());

        // 4) Remplir la vue (carousel + données)
        vueAnnonce.setAnnonce(logement, photos);

        // 5) Brancher le bouton "Réserver"
        initController();
    }

    private void initController() {
        vueAnnonce.getBtnReserver().addActionListener(evt -> {
            vueAnnonce.dispose();

            // Ouvre immédiatement le formulaire de réservation
            FormReservationVue fr = new FormReservationVue();
            fr.setLogementId(logement.getID_logement());
            fr.setVisible(true);

            fr.getBtnConfirmer().addActionListener(ev -> {
                try {
                    Date sqlDate = Date.valueOf(fr.getDate().trim());
                    Reservation r = new Reservation();
                    r.setID_reservation(0);                    // 0 → DAO génèrera max+1
                    r.setDate(sqlDate);
                    r.setStatut_reservation(fr.getStatut());
                    r.setID(user.getID());
                    r.setID_logement(logement.getID_logement());
                    r.setPaye(false);

                    ReservationDAO resaDAO = new ReservationDAOImpl();
                    resaDAO.create(r);

                    // Paiement immédiat si coché
                    if (fr.isPayerMaintenant()) {
                        new PaiementControleur(user, r, resaDAO)
                                .afficherFormPaiement();
                    }

                    JOptionPane.showMessageDialog(
                            fr,
                            "Réservation créée !",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    fr.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            fr,
                            "Erreur : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        });
    }
    public void afficher() {
        vueAnnonce.setVisible(true);
    }
}
