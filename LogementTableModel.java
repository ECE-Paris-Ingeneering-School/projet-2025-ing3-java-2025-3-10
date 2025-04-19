package Vue;

import Modele.Logement;
import Modele.ImageLogement;
import DAO.ImageLogementDAO;
import DAO.ImageLogementDAOImpl;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class LogementTableModel extends AbstractTableModel {

    private List<Logement> data;
    private final String[] columnNames = { "Image", "ID_logement", "Titre", "Description", "Prix", "Categorie" };

    private ImageLogementDAO imageLogementDAO = new ImageLogementDAOImpl();

    public LogementTableModel() {
        this.data = new ArrayList<>();
    }

    public void setData(List<Logement> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int colIndex) {
        return columnNames[colIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Logement l = data.get(rowIndex);
        switch (colIndex) {
            case 0: // Image column
                // Récupérer la première image du logement
                List<ImageLogement> images = imageLogementDAO.findByLogementId(l.getID_logement());
                if (!images.isEmpty()) {
                    String imagePath = images.get(0).getChemin();
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage();
                    int width = 100;  // Largeur souhaitée
                    int height = 75;  // Hauteur souhaitée
                    // Redimensionner l'image tout en maintenant son ratio
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);  // Retourner l'ImageIcon redimensionnée
                }
                return null;
            case 1: return l.getID_logement();
            case 2: return l.getTitre();
            case 3: return l.getDescription();
            case 4: return l.getPrix();
            case 5: return l.getCategorie();
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return ImageIcon.class; // Assurez-vous que la colonne d'image utilise ImageIcon
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // Empêcher l'édition des cellules
    }

    // Méthode ajoutée pour obtenir l'objet Logement à partir d'une ligne spécifique
    public Logement getLogementAt(int rowIndex) {
        return data.get(rowIndex);  // Retourner l'objet Logement correspondant à l'index de ligne
    }
}
