package Vue;

import javax.swing.*;
import java.awt.*;


public class ClientMenuVue extends JFrame {

    private JButton btnVoirLogements;
    private JButton btnMesReservations;
    private JButton btnCommenter;
    private JButton btnDeconnexion;

    public ClientMenuVue() {
        setTitle("Menu Client - Booking");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnVoirLogements = new JButton("Rechercher Logements");
        btnMesReservations = new JButton("Mes Réservations");
        btnCommenter = new JButton("Ajouter un Commentaire");
        btnDeconnexion = new JButton("Déconnexion");

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.add(btnVoirLogements);
        panel.add(btnMesReservations);
        panel.add(btnCommenter);
        panel.add(btnDeconnexion);

        add(panel);
    }

    public JButton getBtnVoirLogements() {
        return btnVoirLogements;
    }

    public JButton getBtnMesReservations() {
        return btnMesReservations;
    }

    public JButton getBtnCommenter() {
        return btnCommenter;
    }

    public JButton getBtnDeconnexion() {
        return btnDeconnexion;
    }
}
