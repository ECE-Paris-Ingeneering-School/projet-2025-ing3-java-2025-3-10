package Vue;

import javax.swing.*;
import java.awt.*;


public class FormReservationVue extends JFrame {

    private JTextField txtLogementId;
    private JTextField txtDate;
    private JTextField txtStatut;
    private JButton btnConfirmer;

    public FormReservationVue() {
        setTitle("Nouvelle Réservation");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("ID Logement:"));
        txtLogementId = new JTextField();
        panel.add(txtLogementId);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        txtDate = new JTextField();
        panel.add(txtDate);

        panel.add(new JLabel("Statut (0=Annulé,1=Confirmé):"));
        txtStatut = new JTextField();
        panel.add(txtStatut);

        btnConfirmer = new JButton("Confirmer");
        panel.add(new JLabel(""));
        panel.add(btnConfirmer);

        add(panel);
    }

    public int getLogementId() {
        return Integer.parseInt(txtLogementId.getText());
    }

    public String getDate() {
        return txtDate.getText();
    }

    public int getStatut() {
        return Integer.parseInt(txtStatut.getText());
    }

    public JButton getBtnConfirmer() {
        return btnConfirmer;
    }
}
