package DAO;

import Modele.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    /* ---- on réutilise le PaiementDAO pour savoir si la réservation est payée ---- */
    private final PaiementDAO payDAO = new PaiementDAOImpl();

    /* ====================== CREATE ====================== */
    @Override
    public void create(Reservation reservation) {

        /* ID manuel : max + 1 si 0 */
        if (reservation.getID_reservation() == 0)
            reservation.setID_reservation(getMaxIdReservation() + 1);

        final String sql = """
             INSERT INTO reservation
             (ID_reservation, date, statut_reservation, ID, ID_logement)
             VALUES (?,?,?,?,?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservation.getID_reservation());
            stmt.setDate(2, new java.sql.Date(reservation.getDate().getTime()));
            stmt.setInt(3, reservation.getStatut_reservation());
            stmt.setInt(4, reservation.getID());          // FK user
            stmt.setInt(5, reservation.getID_logement()); // FK logement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ====================== READ ====================== */
    @Override
    public Reservation findById(int idResa) {
        final String sql = "SELECT * FROM reservation WHERE ID_reservation = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, idResa);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> list = new ArrayList<>();
        final String sql = "SELECT * FROM reservation";
        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByUserId(int userID) {
        List<Reservation> list = new ArrayList<>();
        final String sql = "SELECT * FROM reservation WHERE ID = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));  // Ajoute les réservations dans la liste
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;  // Retourne la liste des réservations
    }

    /* ====================== UPDATE & DELETE ====================== */
    @Override
    public void update(Reservation r) {
        final String sql = """
            UPDATE reservation
               SET date = ?,
                   statut_reservation = ?,
                   ID = ?,
                   ID_logement = ?
             WHERE ID_reservation = ?
        """;
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setDate(1, new java.sql.Date(r.getDate().getTime()));
            st.setInt(2, r.getStatut_reservation());
            st.setInt(3, r.getID());
            st.setInt(4, r.getID_logement());
            st.setInt(5, r.getID_reservation());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idResa) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement("DELETE FROM reservation WHERE ID_reservation = ?")) {

            st.setInt(1, idResa);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ====================== PRIVATE HELPERS ====================== */
    private int getMaxIdReservation() {
        final String sql = "SELECT MAX(ID_reservation) AS max_id FROM reservation";
        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return rs.next() ? rs.getInt("max_id") : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** Construit un objet Reservation + vérification si elle est payée via la table Paiement. */
    private Reservation map(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setID_reservation(rs.getInt("ID_reservation"));
        r.setDate(rs.getDate("date"));
        r.setStatut_reservation(rs.getInt("statut_reservation"));
        r.setID(rs.getInt("ID"));
        r.setID_logement(rs.getInt("ID_logement"));

        // Vérification du paiement à partir de la table Paiement
        boolean paid = payDAO.existsForReservation(r.getID_reservation());
        r.setPaye(paid);

        return r;
    }
}
