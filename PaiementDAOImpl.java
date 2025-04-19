package DAO;

import Modele.Paiement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAOImpl implements PaiementDAO {

    /* ---------- CREATE ---------- */
    @Override
    public void create(Paiement p) {
        if (p.getIdPaiement() == 0)            // sécurité
            p.setIdPaiement(getNextId());

        final String sql =
                "INSERT INTO paiement(ID_paiement, montant, statut_paiement) VALUES (?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, p.getIdPaiement());
            st.setInt(2, p.getMontant());
            st.setInt(3, p.getStatutPaiement());
            st.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    /* ---------- READ ---------- */
    @Override
    public Paiement findById(int id) {
        final String sql = "SELECT * FROM paiement WHERE ID_paiement=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            return rs.next() ? map(rs) : null;

        } catch (SQLException ex) { ex.printStackTrace(); return null; }
    }

    @Override
    public List<Paiement> findAll() {
        List<Paiement> list = new ArrayList<>();
        final String sql = "SELECT * FROM paiement";
        try (Connection c = DatabaseConnection.getConnection();
             Statement st  = c.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {

            while (rs.next()) list.add(map(rs));

        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    /* ---------- UPDATE ---------- */
    @Override
    public void update(Paiement p) {
        final String sql =
                "UPDATE paiement SET montant=?, statut_paiement=? WHERE ID_paiement=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, p.getMontant());
            st.setInt(2, p.getStatutPaiement());
            st.setInt(3, p.getIdPaiement());
            st.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    /* ---------- DELETE ---------- */
    @Override public void delete(int id) {
        final String sql = "DELETE FROM paiement WHERE ID_paiement=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    /* ---------- OUTILS ---------- */
    @Override public int getNextId() { return getMaxId() + 1; }

    @Override
    public boolean existsForReservation(int idReservation) {
        /*  L’ID d’une réservation EST l’ID du paiement  */
        final String sql =
                "SELECT 1 FROM paiement WHERE ID_paiement=? LIMIT 1";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, idReservation);
            try (ResultSet rs = st.executeQuery()) { return rs.next(); }

        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }

    /* ========== interne ========== */
    private int getMaxId() {
        try (Connection c = DatabaseConnection.getConnection();
             Statement st  = c.createStatement();
             ResultSet rs  = st.executeQuery(
                     "SELECT MAX(ID_paiement) AS m FROM paiement")) {

            return rs.next() ? rs.getInt("m") : 0;

        } catch (SQLException ex) { ex.printStackTrace(); return 0; }
    }

    private Paiement map(ResultSet rs) throws SQLException {
        Paiement p = new Paiement();
        p.setIdPaiement    (rs.getInt("ID_paiement"));
        p.setMontant       (rs.getInt("montant"));
        p.setStatutPaiement(rs.getInt("statut_paiement"));
        return p;
    }
}
