package Controleur;

import Modele.Users;
import Vue.ClientMenuVue;
import Vue.CommentaireVue;

import javax.swing.*;

public class ClientControleur {

    private final Users userConnecte;
    private final ClientMenuVue clientMenuVue;

    public ClientControleur(Users userConnecte) {
        this.userConnecte = userConnecte;
        this.clientMenuVue = new ClientMenuVue();
    }

    public void afficherMenuClient() {
        clientMenuVue.setVisible(true);

        clientMenuVue.getBtnVoirLogements()
                .addActionListener(e -> ouvrirLogementControleur());
        clientMenuVue.getBtnMesReservations()
                .addActionListener(e -> ouvrirReservationControleur());
        clientMenuVue.getBtnCommenter()
                .addActionListener(e -> ouvrirCommentaireControleur());
        clientMenuVue.getBtnDeconnexion()
                .addActionListener(e -> seDeconnecter());
    }

    private void ouvrirLogementControleur() {
        new LogementControleur(userConnecte)
                .afficherListeLogements();
    }

    private void ouvrirReservationControleur() {
        new ReservationControleur(userConnecte)
                .afficherReservationsClient();
    }

    private void ouvrirCommentaireControleur() {
        // Demander à l'utilisateur l'ID du logement à commenter
        String saisie = JOptionPane.showInputDialog(
                clientMenuVue,
                "Entrez l'ID du logement à commenter :",
                "Commentaire",
                JOptionPane.QUESTION_MESSAGE
        );
        if (saisie == null) return;  // Annulé

        try {
            int idLogement = Integer.parseInt(saisie.trim());

            // Créer le panel de commentaires
            CommentaireVue commentPanel =
                    new CommentaireControleur(userConnecte, idLogement)
                            .creerCommentairePanel();  // :contentReference[oaicite:0]{index=0}&#8203;:contentReference[oaicite:1]{index=1}

            // L’afficher dans une nouvelle fenêtre
            JFrame frame = new JFrame("Commentaires du logement " + idLogement);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(commentPanel);
            frame.pack();
            frame.setLocationRelativeTo(clientMenuVue);
            frame.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    clientMenuVue,
                    "ID invalide, veuillez saisir un nombre entier.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void seDeconnecter() {
        clientMenuVue.dispose();
    }
}
