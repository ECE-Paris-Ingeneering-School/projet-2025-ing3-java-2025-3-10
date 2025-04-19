package Vue;

import javax.swing.*;
import java.awt.*;

/* Dépendances :
 *  – RectangleRond  : JPanel custom (fond arrondi)
 *  – BoutonRond     : JButton rond pour l’icône utilisateur
 *  – PreRemplissage : JTextField pré‑rempli
 */
public class FormPaiementVue extends JFrame {

    /* -------------------- champs -------------------- */
    private final JTextField txtMontant    = new JTextField(10);
    private final JTextField txtCardHolder = new PreRemplissage("DUPOND");
    private final JTextField txtCardNumber = new PreRemplissage("1234 1234 1234 1234");
    private final JTextField txtCVV        = new PreRemplissage("123");
    private final JTextField txtExpiry     = new PreRemplissage("MM/AA");
    private final JTextField txtRecap      = new JTextField(20);
    private final JTextField txtCodePromo  = new JTextField(12);

    private final JButton    btnPayer      = new JButton("Payer");

    public FormPaiementVue() {
        setTitle("Page de Paiement");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        add(buildBandeau(), BorderLayout.NORTH);

        JPanel centre = new JPanel();
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        centre.setBackground(Color.WHITE);
        centre.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));

        JLabel titre = new JLabel("Paiement");
        titre.setFont(new Font("SansSerif", Font.BOLD, 25));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        centre.add(titre);
        centre.add(Box.createVerticalStrut(30));

        centre.add(buildFormLayer());
        centre.add(Box.createVerticalStrut(80));
        centre.add(buildBottomLine());

        add(centre, BorderLayout.CENTER);
        setVisible(true);
    }

    /* ============ API publique ============ */
    public void setMontant(int m) {
        txtMontant.setText(String.valueOf(m));
        txtMontant.setEditable(false);
    }
    public int getMontant() {
        return Integer.parseInt(txtMontant.getText());
    }
    public void setRecap(String recap) {
        txtRecap.setText(recap);
        txtRecap.setEditable(false);
    }
    public JButton getBtnPayer() {
        return btnPayer;
    }
    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /* ============ construction UI ============ */
    private JPanel buildBandeau() {
        JPanel bande = new JPanel(new BorderLayout());
        bande.setPreferredSize(new Dimension(0, 60));
        bande.setBackground(new Color(0, 51, 102));

        JLabel lbl = new JLabel("Booking");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 20));
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bande.add(lbl, BorderLayout.WEST);

        ImageIcon ic = loadIcon("icon_compte.png", 40, 40);
        BoutonRond btnUser = new BoutonRond(ic);
        btnUser.setPreferredSize(new Dimension(40, 40));
        btnUser.setBackground(Color.WHITE);
        bande.add(btnUser, BorderLayout.EAST);

        bande.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        return bande;
    }

    private JLayeredPane buildFormLayer() {
        RectangleRond bg = new RectangleRond(30, new Color(230, 240, 255));
        bg.setBounds(50, 0, 420, 300);

        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.anchor = GridBagConstraints.LINE_END;

        int row = 0;
        JLabel logo = new JLabel(loadIcon("iconCB.jpg", 150, 35));
        g.gridx = 1; g.gridy = row++; g.anchor = GridBagConstraints.LINE_START;
        grid.add(logo, g);

        addRow(grid, g, row++, "Nom du titulaire :",    txtCardHolder);
        addRow(grid, g, row++, "Numéro de carte :",     txtCardNumber);
        addRow(grid, g, row++, "CVC :",                 txtCVV);
        addRow(grid, g, row,   "Date expiration :",     txtExpiry);

        grid.setBounds(0, 0, 500, 330);

        JPanel recap = new JPanel(new GridBagLayout());
        recap.setOpaque(false);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(10, 10, 10, 10);
        recap.add(new JLabel("Récapitulatif :"), r);
        r.gridx = 1;
        recap.add(txtRecap, r);
        recap.setBounds(530, 40, 300, 60);

        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(900, 330));
        layer.add(bg,    Integer.valueOf(0));
        layer.add(grid,  Integer.valueOf(1));
        layer.add(recap, Integer.valueOf(1));
        return layer;
    }

    private JPanel buildBottomLine() {
        JPanel line = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        line.setOpaque(false);

        line.add(new JLabel("Entrez un code promo :"));
        line.add(txtCodePromo);
        line.add(new JLabel("Montant total à payer :"));
        line.add(txtMontant);
        line.add(btnPayer);
        return line;
    }

    /* ============ utilitaires ============ */
    private static void addRow(JPanel p, GridBagConstraints g, int row,
                               String label, JComponent comp) {
        g.gridx = 0; g.gridy = row; g.anchor = GridBagConstraints.LINE_END;
        p.add(new JLabel(label), g);
        g.gridx = 1; g.anchor = GridBagConstraints.LINE_START;
        p.add(comp, g);
    }

    private static ImageIcon loadIcon(String path, int w, int h) {
        java.net.URL url = FormPaiementVue.class.getResource(path);
        if (url == null) return null;
        Image img = new ImageIcon(url).getImage()
                .getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
