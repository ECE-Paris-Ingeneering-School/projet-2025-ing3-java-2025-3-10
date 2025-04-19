package Vue;

import javax.swing.*;
import java.awt.*;

public class FondArrondi extends JPanel {

    public FondArrondi() {
        setOpaque(false);
        setLayout(new GridBagLayout());
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        int arc = 50;

        // Dessiner le fond blanc arrondi
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(10, 10, width - 20, height - 20, arc, arc);

        // Bordure dégradée vers l'extérieur
        for (int i = 0; i < 10; i++) {
            float a = (10 - i) / 100f;
            g2d.setColor(new Color(150, 150, 150, (int)(a * 255)));
            g2d.drawRoundRect(10 - i, 10 - i, width - 1 - (20 - 2 * i), height - 1 - (20 - 2 * i), arc + i, arc + i);
        }

        g2d.dispose();
        super.paintComponent(g);
    }
}
