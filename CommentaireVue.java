package Vue;

import javax.swing.*;
import java.awt.*;

public class CommentaireVue extends JFrame {

    private JTextField txtLogementId;
    private JTextArea txtContenu;
    private JButton btnEnvoyer;

    public CommentaireVue() {
        setTitle("Nouveau Commentaire");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("ID Logement :"));
        txtLogementId = new JTextField(5);
        top.add(txtLogementId);

        txtContenu = new JTextArea(5, 30);
        JScrollPane scroll = new JScrollPane(txtContenu);

        btnEnvoyer = new JButton("Envoyer");

        panel.add(top, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnEnvoyer, BorderLayout.SOUTH);

        add(panel);
    }

    public int getIdLogement() {
        return Integer.parseInt(txtLogementId.getText());
    }

    public String getContenu() {
        return txtContenu.getText();
    }

    public JButton getBtnEnvoyer() {
        return btnEnvoyer;
    }

    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
