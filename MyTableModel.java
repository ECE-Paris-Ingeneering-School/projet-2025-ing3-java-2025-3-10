package Vue;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class MyTableModel extends AbstractTableModel {

    // Stockage des données dans une liste
    private List<Object> data = new ArrayList<>();
    // Nom des colonnes
    private String[] columnNames = {"Col 1", "Col 2", "Col 3"};

    public MyTableModel() {
        super();
    }


    public void setData(List<Object> newData) {
        this.data = newData;
        fireTableDataChanged(); // Notifie la table qu'il faut se rafraîchir
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
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Récupérer l'objet correspondant à la ligne
        Object rowObject = data.get(rowIndex);

        // Pour l'exemple, on retourne juste une valeur au hasard
        // Adaptez en fonction de la structure de votre objet
        switch (columnIndex) {
            case 0: return rowObject.toString();
            case 1: return "Valeur col 2";
            case 2: return "Valeur col 3";
            default: return "";
        }
    }
}
