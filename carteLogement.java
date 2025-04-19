package Vue;

import javax.swing.*;
import java.awt.*;

public class carteLogement extends JPanel {

    public carteLogement(String imagePath, String titreText, String descriptionText, String prixText) {
        // Config du panel
        setPreferredSize(new Dimension(800, 150));
        setMaximumSize(new Dimension(800, 150));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setLayout(new BorderLayout());

        // Partie image
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(130, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setPreferredSize(new Dimension(140, 140));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(150, 150));
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        add(imagePanel, BorderLayout.WEST);

        // Partie texte
        JPanel textePanel = new JPanel();
        textePanel.setLayout(new BoxLayout(textePanel, BoxLayout.Y_AXIS));
        textePanel.setOpaque(false);
        textePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titre = new JLabel(titreText);
        titre.setFont(new Font("SansSerif", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JLabel description = new JLabel(descriptionText);
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));
        description.setForeground(Color.DARK_GRAY);

        textePanel.add(titre);
        textePanel.add(Box.createVerticalStrut(5));
        textePanel.add(description);

        add(textePanel, BorderLayout.CENTER);

        // Partie prix
        JLabel prix = new JLabel(prixText);
        prix.setFont(new Font("SansSerif", Font.BOLD, 16));
        prix.setForeground(new Color(0, 0, 0));
        prix.setHorizontalAlignment(SwingConstants.CENTER);
        prix.setVerticalAlignment(SwingConstants.CENTER);

        JPanel prixPanel = new JPanel(new BorderLayout());
        prixPanel.setPreferredSize(new Dimension(120, 150));
        prixPanel.setOpaque(false);
        prixPanel.add(prix, BorderLayout.CENTER);

        add(prixPanel, BorderLayout.EAST);
    }
}
