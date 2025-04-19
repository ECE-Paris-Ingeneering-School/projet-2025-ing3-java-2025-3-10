package Vue;

import Modele.Logement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PageAnnonceVue extends JFrame {

    /* ---- widgets principaux ---- */
    private final JLabel      titreLabel      = new JLabel("", SwingConstants.CENTER);
    private final JLabel      lieuLabel       = new JLabel("", SwingConstants.CENTER);
    private final JLabel      prixLabel       = new JLabel("", SwingConstants.CENTER);
    private final JTextPane   descriptionPane = new JTextPane();

    // Carousel
    private final JPanel      carouselPane  = new JPanel(new CardLayout());
    private final CardLayout  imageLayout   = (CardLayout) carouselPane.getLayout();
    private final JButton     btnPrev       = createRoundNavButton("‹");
    private final JButton     btnNext       = createRoundNavButton("›");
    private final JPanel      thumbnailPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));

    // Bouton Réserver
    private final JButton     btnReserver   = new JButton("Réserver");

    /* ---- panneau commentaires (injection) ---- */
    private final CommentaireVue commentaireVue;

    /* ---- stockage des images ---- */
    private List<ImageIcon> photos = new ArrayList<>();

    public PageAnnonceVue(CommentaireVue commentaireVue) {
        super("Détails du Logement");
        this.commentaireVue = commentaireVue;
        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // applique le style ancien pour Réserver
        styleReserveButton(btnReserver);

        add(buildHeader(),  BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 51, 102));
        header.setPreferredSize(new Dimension(0, 60));
        JLabel labelBooking = new JLabel("Booking");
        labelBooking.setForeground(Color.WHITE);
        labelBooking.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelBooking.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        header.add(labelBooking, BorderLayout.WEST);
        return header;
    }

    private JPanel buildContent() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        content.setBackground(Color.WHITE);

        // Titre
        titreLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        content.add(titreLabel);
        content.add(Box.createVerticalStrut(20));

        // Carousel (400×200)
        JPanel carouselWrapper = new JPanel(new BorderLayout());
        carouselWrapper.setBackground(Color.WHITE);
        carouselPane.setPreferredSize(new Dimension(400, 200));

        // nav gauche
        JPanel leftNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        leftNav.setBackground(Color.WHITE);
        leftNav.add(btnPrev);
        carouselWrapper.add(leftNav, BorderLayout.WEST);

        // main
        carouselWrapper.add(carouselPane, BorderLayout.CENTER);

        // nav droite
        JPanel rightNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightNav.setBackground(Color.WHITE);
        rightNav.add(btnNext);
        carouselWrapper.add(rightNav, BorderLayout.EAST);

        btnPrev.addActionListener(e -> imageLayout.previous(carouselPane));
        btnNext.addActionListener(e -> imageLayout.next(carouselPane));

        content.add(carouselWrapper);
        content.add(Box.createVerticalStrut(8));

        // Vignettes
        thumbnailPane.setBackground(Color.WHITE);
        content.add(thumbnailPane);
        content.add(Box.createVerticalStrut(20));

        // Lieu / Prix
        lieuLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        prixLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        content.add(lieuLabel);
        content.add(Box.createVerticalStrut(5));
        content.add(prixLabel);
        content.add(Box.createVerticalStrut(20));

        // Description
        descriptionPane.setEditable(false);
        descriptionPane.setBackground(Color.WHITE);
        descriptionPane.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descriptionPane.setMaximumSize(new Dimension(800, 100));
        centerText(descriptionPane);
        content.add(descriptionPane);
        content.add(Box.createVerticalStrut(20));

        // Bouton Réserver (taille et style d’avant)
        btnReserver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReserver.setPreferredSize(new Dimension(160, 40));
        btnReserver.setFocusable(false);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnReserver);
        content.add(btnPanel);
        content.add(Box.createVerticalStrut(20));

        // Commentaires (agrandi)
        commentaireVue.setAlignmentX(Component.CENTER_ALIGNMENT);
        commentaireVue.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        JScrollPane commentScroll = new JScrollPane(commentaireVue,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        commentScroll.setPreferredSize(new Dimension(800, 200));
        content.add(commentScroll);

        return content;
    }

    private void centerText(JTextPane pane) {
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    /**
     * Remplit la vue avec l’objet Logement et toutes les photos.
     */
    public void setAnnonce(Logement log, List<ImageIcon> photos) {
        this.photos = photos != null ? photos : new ArrayList<>();
        titreLabel.setText(log.getTitre());
        lieuLabel .setText("Localisation : " + log.getDescription());
        prixLabel .setText("Prix : " + log.getPrix() + " € / nuit");

        carouselPane.removeAll();
        thumbnailPane.removeAll();
        Dimension sz = carouselPane.getPreferredSize();

        for (int i = 0; i < this.photos.size(); i++) {
            ImageIcon ico = this.photos.get(i);
            Image bigImg = ico.getImage().getScaledInstance(sz.width, sz.height, Image.SCALE_SMOOTH);
            JLabel big = new JLabel(new ImageIcon(bigImg), SwingConstants.CENTER);
            carouselPane.add(big, String.valueOf(i));

            Image thumbImg = ico.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH);
            JLabel small = new JLabel(new ImageIcon(thumbImg));
            small.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            String key = String.valueOf(i);
            small.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    imageLayout.show(carouselPane, key);
                }
            });
            thumbnailPane.add(small);
        }
        if (!this.photos.isEmpty()) {
            imageLayout.show(carouselPane, "0");
        }
        carouselPane.revalidate();
        thumbnailPane.revalidate();
    }

    public JButton getBtnReserver() {
        return btnReserver;
    }

    /** Crée un bouton nav rond, 24×24 px, hover, flat */
    private JButton createRoundNavButton(String arrow) {
        JButton b = new JButton(arrow);
        b.setPreferredSize(new Dimension(24, 24));
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(0x0071C2));
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setUI(new BasicButtonUI() {
            @Override public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D)g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(b.getBackground());
                g2.fillOval(0, 0, c.getWidth(), c.getHeight());
                FontMetrics fm = g2.getFontMetrics();
                int x = (c.getWidth() - fm.stringWidth(arrow)) / 2;
                int y = (c.getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.setColor(b.getForeground());
                g2.drawString(arrow, x, y);
                g2.dispose();
            }
        });
        b.addMouseListener(new MouseAdapter() {
            Color norm = b.getBackground(), hov = norm.brighter();
            @Override public void mouseEntered(MouseEvent e) { b.setBackground(hov); }
            @Override public void mouseExited(MouseEvent e)  { b.setBackground(norm); }
        });
        return b;
    }

    /** Style d’origine pour le bouton “Réserver” */
    private void styleReserveButton(JButton b) {
        Color normal = new Color(0x0071C2), hover = normal.brighter();
        b.setBackground(normal);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { b.setBackground(hover); }
            @Override public void mouseExited(MouseEvent e)  { b.setBackground(normal); }
        });
    }
}
