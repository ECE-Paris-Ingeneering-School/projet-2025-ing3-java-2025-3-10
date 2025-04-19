package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PreRemplissage extends JTextField {
    private String placeholder;
    private Color placeholderColor = Color.GRAY;

    // Constructeur
    public PreRemplissage(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        setForeground(placeholderColor);

        // Assurer une taille fixe pour le champ
        setPreferredSize(new Dimension(150, 30));  // Taille fixe pour le champ

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Effacer le texte grisé lorsque le champ reçoit le focus
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Réafficher le texte grisé si le champ est vide
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(placeholderColor);
                }
            }
        });
    }
}
