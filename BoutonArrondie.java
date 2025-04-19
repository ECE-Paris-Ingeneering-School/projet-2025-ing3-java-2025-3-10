package Vue;

import javax.swing.*;
import java.awt.*;

public class BoutonArrondie extends JButton {

    private int arrondie = 40;

    public BoutonArrondie(String texte) {
        super(texte);
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(140, 40));
    }

    protected void paintComponent(Graphics graph) {
        graph.setColor(getBackground());
        graph.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        super.paintComponent(graph);
    }
}
