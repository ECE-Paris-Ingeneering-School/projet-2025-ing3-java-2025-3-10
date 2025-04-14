package DAO;

import DAO.PaiementDAO;
import DAO.DatabaseConnection;
import Modele.Paiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PaiementDAOImpl implements PaiementDAO {

    @Override
    public void create(Paiement paiement) {
        // S'il n'y a pas d'auto-incrément, on insère l'ID_paiement nous-mêmes
        String sql = "INSERT INTO paiement(ID_paiement, montant, statut_paiement) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, paiement.getID_paiement());
            stmt.setInt(2, paiement.getMontant());
            stmt.setInt(3, paiement.getStatut_paiement());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paiement findById(int ID_paiement) {
        Paiement p = null;
        String sql = "SELECT * FROM paiement WHERE ID_paiement = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_paiement);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = mapResultSetToPaiement(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Paiement> findAll() {
        List<Paiement> liste = new ArrayList<>();
        String sql = "SELECT * FROM paiement";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paiement p = mapResultSetToPaiement(rs);
                liste.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void update(Paiement paiement) {
        String sql = "UPDATE paiement SET montant=?, statut_paiement=? WHERE ID_paiement=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, paiement.getMontant());
            stmt.setInt(2, paiement.getStatut_paiement());
            stmt.setInt(3, paiement.getID_paiement());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID_paiement) {
        String sql = "DELETE FROM paiement WHERE ID_paiement=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_paiement);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire
    private Paiement mapResultSetToPaiement(ResultSet rs) throws SQLException {
        Paiement p = new Paiement();
        p.setID_paiement(rs.getInt("ID_paiement"));
        p.setMontant(rs.getInt("montant"));
        p.setStatut_paiement(rs.getInt("statut_paiement"));
        return p;
    }
}
