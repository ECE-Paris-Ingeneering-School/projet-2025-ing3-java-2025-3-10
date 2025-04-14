package DAO;

import Modele.Logement;
import DAO.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogementDAOImpl implements LogementDAO {

    @Override
    public Logement findById(int ID_logement) {
        Logement log = null;
        String sql = "SELECT * FROM logement WHERE ID_logement = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_logement);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                log = mapResultSetToLogement(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return log;
    }

    @Override
    public List<Logement> findAll() {
        List<Logement> liste = new ArrayList<>();
        String sql = "SELECT * FROM logement";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Logement l = mapResultSetToLogement(rs);
                liste.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Logement> findByCategorie(int categorie) {
        List<Logement> liste = new ArrayList<>();
        String sql = "SELECT * FROM logement WHERE categorie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categorie);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Logement l = mapResultSetToLogement(rs);
                liste.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void create(Logement logement) {
        // S’il n’y a pas d’AUTO_INCREMENT, vous devez fournir un ID_logement unique
        // Sinon, omettez ID_logement et laissez la BDD l’auto-incrémenter
        String sql = "INSERT INTO logement (ID_logement, titre, description, prix, categorie, ID) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, logement.getID_logement());
            stmt.setString(2, logement.getTitre());
            stmt.setString(3, logement.getDescription());
            stmt.setInt(4, logement.getPrix());
            stmt.setInt(5, logement.getCategorie());
            stmt.setInt(6, logement.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Logement logement) {
        String sql = "UPDATE logement SET titre=?, description=?, prix=?, categorie=?, ID=? WHERE ID_logement=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, logement.getTitre());
            stmt.setString(2, logement.getDescription());
            stmt.setInt(3, logement.getPrix());
            stmt.setInt(4, logement.getCategorie());
            stmt.setInt(5, logement.getID());
            stmt.setInt(6, logement.getID_logement());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID_logement) {
        String sql = "DELETE FROM logement WHERE ID_logement=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_logement);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Logement mapResultSetToLogement(ResultSet rs) throws SQLException {
        Logement log = new Logement();
        log.setID_logement(rs.getInt("ID_logement"));
        log.setTitre(rs.getString("titre"));
        log.setDescription(rs.getString("description"));
        log.setPrix(rs.getInt("prix"));
        log.setCategorie(rs.getInt("categorie"));
        log.setID(rs.getInt("ID"));
        return log;
    }
}
