package DAO;

import Modele.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public void create(Reservation reservation) {
        // Si ID_reservation n'est pas auto-incrément, on l'insère manuellement.
        // Sinon, on peut omettre ID_reservation dans l'INSERT et récupérer la clé générée.
        String sql = "INSERT INTO reservation(ID_reservation, date, statut_reservation, ID, ID_logement) "
                + "VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservation.getID_reservation());
            // Conversion java.util.Date -> java.sql.Date
            stmt.setDate(2, new java.sql.Date(reservation.getDate().getTime()));
            stmt.setInt(3, reservation.getStatut_reservation());
            stmt.setInt(4, reservation.getID()); // user ID
            stmt.setInt(5, reservation.getID_logement());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation findById(int ID_reservation) {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservation WHERE ID_reservation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_reservation);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reservation = mapResultSetToReservation(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> liste = new ArrayList<>();
        String sql = "SELECT * FROM reservation";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = mapResultSetToReservation(rs);
                liste.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void update(Reservation reservation) {
        String sql = "UPDATE reservation SET date=?, statut_reservation=?, ID=?, ID_logement=? "
                + "WHERE ID_reservation=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(reservation.getDate().getTime()));
            stmt.setInt(2, reservation.getStatut_reservation());
            stmt.setInt(3, reservation.getID());         // user ID
            stmt.setInt(4, reservation.getID_logement());
            stmt.setInt(5, reservation.getID_reservation());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID_reservation) {
        String sql = "DELETE FROM reservation WHERE ID_reservation=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_reservation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> findByUserId(int userID) {
        List<Reservation> liste = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE ID=?"; // "ID" = la FK vers la table `users`

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation r = mapResultSetToReservation(rs);
                liste.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    /**
     * Méthode utilitaire pour mapper un ResultSet → objet Reservation.
     */
    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setID_reservation(rs.getInt("ID_reservation"));
        r.setDate(rs.getDate("date"));
        r.setStatut_reservation(rs.getInt("statut_reservation"));
        r.setID(rs.getInt("ID"));            // user
        r.setID_logement(rs.getInt("ID_logement"));
        return r;
    }
}
