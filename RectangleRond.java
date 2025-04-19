package Vue;
import javax.swing.*;
import java.awt.*;

public class RectangleRond extends JPanel {
    private int arc;

    public RectangleRond(int arc, Color couleur) {
        this.arc = arc;
        setBackground(couleur); // Cette couleur va être utilisée pour l'ombre portée.
        setOpaque(false); // Permet un dessin personnalisé sans fond opaque.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        int strokeWidth = 7;
        int offset = strokeWidth / 2;

// Ombre portée
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(offset + 5, offset + 5, getWidth() - strokeWidth, getHeight() - strokeWidth, arc, arc);

// Contour gris

    }
}

