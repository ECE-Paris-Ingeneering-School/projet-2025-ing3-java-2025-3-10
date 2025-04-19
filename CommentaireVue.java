package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CommentaireVue extends JPanel {

    private final DefaultListModel<String> commentListModel = new DefaultListModel<>();
    private final JList<String>            commentList      = new JList<>(commentListModel);
    private final JTextField               champCommentaire = new JTextField();
    private final JButton                  envoyerBtn       = new JButton("Envoyer");

    public CommentaireVue() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Titre
        JLabel titre = new JLabel("Commentaires", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titre);
        add(Box.createVerticalStrut(10));

        // Liste
        commentList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(commentList);
        add(scrollPane);
        add(Box.createVerticalStrut(10));

        // Champ de saisie
        champCommentaire.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        add(champCommentaire);
        add(Box.createVerticalStrut(10));

        // Style du bouton Envoyer
        styleBookingButton(envoyerBtn);

        // Wrapper pour centrer le bouton
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Color.WHITE);
        wrapper.add(envoyerBtn);
        add(wrapper);
    }

    /** Pré-remplit la liste avec les contenus fournis. */
    public void setCommentaires(List<String> commentaires) {
        commentListModel.clear();
        for (String c : commentaires) commentListModel.addElement(c);
    }

    /** Ajoute un commentaire à la volée. */
    public void ajouterCommentaire(String commentaire) {
        commentListModel.addElement(commentaire);
    }

    /** Vide le champ de saisie. */
    public void clearChampCommentaire() {
        champCommentaire.setText("");
    }

    /** Récupère le texte saisi. */
    public String getChampCommentaire() {
        return champCommentaire.getText().trim();
    }

    /** Expose le bouton “Envoyer” pour y attacher un listener. */
    public void addEnvoyerListener(ActionListener l) {
        envoyerBtn.addActionListener(l);
    }

    // --------------------------------------------------------
    // Méthode utilitaire pour styliser un bouton Booking (bleu + hover)
    private void styleBookingButton(JButton b) {
        Color normal = new Color(0x0071C2);
        Color hover  = normal.brighter();
        b.setBackground(normal);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                b.setBackground(hover);
            }
            @Override public void mouseExited(MouseEvent e) {
                b.setBackground(normal);
            }
        });
    }
}
