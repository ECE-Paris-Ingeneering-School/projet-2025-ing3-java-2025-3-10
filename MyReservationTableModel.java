package Vue;

import Modele.Reservation;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class MyReservationTableModel extends AbstractTableModel {

    private final String[] col = { "ID", "Date", "Logement", "Payé ?" };
    private List<Reservation> data = new ArrayList<>();

    public void setData(List<Reservation> l) {
        data = l;
        fireTableDataChanged();
    }

    @Override public int getRowCount()    { return data.size(); }
    @Override public int getColumnCount() { return col.length; }
    @Override public String getColumnName(int i) { return col[i]; }

    @Override
    public Object getValueAt(int r, int c) {
        Reservation res = data.get(r);
        return switch (c) {
            case 0 -> res.getID_reservation();
            case 1 -> res.getDate();
            case 2 -> res.getID_logement();
            case 3 -> res.isPaye() ? "Oui" : "Non";
            default -> "";
        };
    }

    public Reservation getReservationAt(int row) {
        return data.get(row);
    }
}
