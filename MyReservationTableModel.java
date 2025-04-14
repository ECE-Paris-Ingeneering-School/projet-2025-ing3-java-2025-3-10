package Vue;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import Modele.Reservation;

public class MyReservationTableModel extends AbstractTableModel {

    private List<Reservation> data = new ArrayList<>();
    private String[] columnNames = {"ID_reservation", "Date", "Statut", "ID_user", "ID_logement"};

    public void setData(List<Reservation> newData) {
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
        Reservation r = data.get(rowIndex);
        switch (colIndex) {
            case 0: return r.getID_reservation();
            case 1: return r.getDate();
            case 2: return r.getStatut_reservation();
            case 3: return r.getID();          // id user
            case 4: return r.getID_logement(); // id logement
            default: return "";
        }
    }
}
