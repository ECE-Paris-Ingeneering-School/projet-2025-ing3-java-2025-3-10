package Vue;

import javax.swing.*;
import java.awt.*;

public class FormPaiementVue extends JFrame {

    private JTextField txtMontant;
    private JButton btnPayer;

    public FormPaiementVue() {
        setTitle("Paiement");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Montant:"));
        txtMontant = new JTextField();
        panel.add(txtMontant);

        btnPayer = new JButton("Payer");
        panel.add(new JLabel(""));
        panel.add(btnPayer);

        add(panel);
    }

    public int getMontant() {
        return Integer.parseInt(txtMontant.getText());
    }

    public JButton getBtnPayer() {
        return btnPayer;
    }

    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
