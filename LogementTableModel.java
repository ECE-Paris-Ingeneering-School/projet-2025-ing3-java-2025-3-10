package Vue;

import Modele.Logement;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LogementTableModel extends AbstractTableModel {

    private List<Logement> data = new ArrayList<>();

    private final String[] columnNames = { "ID_logement", "Titre", "Description", "Prix", "Catégorie", "ID_user" };

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
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Logement l = data.get(rowIndex);
        switch (colIndex) {
            case 0: return l.getID_logement();
            case 1: return l.getTitre();
            case 2: return l.getDescription();
            case 3: return l.getPrix();
            case 4: return l.getCategorie();
            case 5: return l.getID(); // l'ID user
            default: return null;
        }
    }
}
