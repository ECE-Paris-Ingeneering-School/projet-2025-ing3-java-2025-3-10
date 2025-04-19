package Vue;

import javax.swing.*;
import java.awt.*;

public class FormReservationVue extends JFrame {

    private JTextField txtLogementId;
    private JTextField txtDate;
    private JTextField txtStatut;
    private JButton btnConfirmer;
    private JCheckBox chkPayerMaintenant; // Choix de payer immédiatement

    public FormReservationVue() {
        setTitle("Nouvelle Réservation");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Bande bleue
        JPanel bandeBleu = new JPanel(new BorderLayout());
        bandeBleu.setBackground(new Color(0, 51, 102));
        bandeBleu.setPreferredSize(new Dimension(0, 60));

        JLabel labelBooking = new JLabel("Booking");
        labelBooking.setForeground(Color.WHITE);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bandeBleu.add(labelBooking, BorderLayout.WEST);

        JLabel titre = new JLabel("Réserver un Logement");
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelId     = new JLabel("ID Logement :");
        JLabel labelDate   = new JLabel("Date (YYYY-MM-DD) :");
        JLabel labelStatut = new JLabel("Statut (0=Annulé,1=Confirmé) :");

        txtLogementId = new JTextField("", 20);
        txtDate       = new JTextField("", 20);
        txtStatut     = new JTextField("", 20);

        chkPayerMaintenant = new JCheckBox("Payer maintenant ?");
        chkPayerMaintenant.setBackground(Color.WHITE);
        chkPayerMaintenant.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.LINE_END;

        // Ligne 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(labelId, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(txtLogementId, gbc);

        // Ligne 2
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(labelDate, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(txtDate, gbc);

        // Ligne 3
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(labelStatut, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(txtStatut, gbc);

        JPanel monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
        monPanel.setBackground(Color.WHITE);
        monPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));

        monPanel.add(titre);
        monPanel.add(Box.createVerticalStrut(30));
        monPanel.add(formPanel);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(chkPayerMaintenant);
        monPanel.add(Box.createVerticalStrut(20));
        monPanel.add(btnConfirmer);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(monPanel, BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(bandeBleu, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    // Set / get
    public void setLogementId(int logementId) {
        txtLogementId.setText(String.valueOf(logementId));
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
    public boolean isPayerMaintenant() {
        return chkPayerMaintenant.isSelected();
    }
}
