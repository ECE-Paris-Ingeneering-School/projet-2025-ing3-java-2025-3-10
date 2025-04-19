package Vue;

import javax.swing.*;
import java.awt.*;

public class BoutonRond extends JButton {

    public BoutonRond(ImageIcon icone) {
        super(icone);
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.BLUE.darker()); // Couleur du texte
    }

    //Méthode
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(new Color(9, 52, 106)); // Couleur du fond
        graphics.fillOval(0, 0, getWidth(), getHeight()); // Dessine le rond

        super.paintComponent(graphics); // Dessine le texte du bouton
    }

    public Dimension getPreferredSize() {
        return new Dimension(40, 40); // Taille carrée
    }

}
