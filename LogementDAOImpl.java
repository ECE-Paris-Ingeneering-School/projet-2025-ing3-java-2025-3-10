package DAO;

import Modele.Logement;
import Modele.ImageLogement;
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
                Logement log = mapResultSetToLogement(rs);
                liste.add(log);
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
                Logement log = mapResultSetToLogement(rs);
                liste.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Logement> rechercher(String titre, String description, String prix, String categorie) {
        List<Logement> logements = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM logement WHERE 1=1");

        if (titre != null && !titre.isEmpty()) {
            query.append(" AND titre LIKE ?");
        }
        if (description != null && !description.isEmpty()) {
            query.append(" AND description LIKE ?");
        }
        if (prix != null && !prix.isEmpty()) {
            query.append(" AND prix = ?");
        }
        if (categorie != null && !categorie.isEmpty()) {
            query.append(" AND categorie = ?");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            int index = 1;
            if (titre != null && !titre.isEmpty()) {
                stmt.setString(index++, "%" + titre + "%");
            }
            if (description != null && !description.isEmpty()) {
                stmt.setString(index++, "%" + description + "%");
            }
            if (prix != null && !prix.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(prix));
            }
            if (categorie != null && !categorie.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(categorie));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Logement log = mapResultSetToLogement(rs);
                logements.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    // Méthode utilitaire pour mapper le ResultSet vers un objet Logement
    private Logement mapResultSetToLogement(ResultSet rs) throws SQLException {
        Logement log = new Logement();
        log.setID_logement(rs.getInt("ID_logement"));
        log.setTitre(rs.getString("titre"));
        log.setDescription(rs.getString("description"));
        log.setPrix(rs.getInt("prix"));
        log.setCategorie(rs.getInt("categorie"));
        log.setID(rs.getInt("ID"));

        // Récupérer l'image associée au logement
        ImageLogementDAOImpl imageDAO = new ImageLogementDAOImpl();
        List<ImageLogement> images = imageDAO.findByLogementId(log.getID_logement());
        if (!images.isEmpty()) {
            log.setImagePath(images.get(0).getChemin()); // Récupérer le chemin de la première image
        }

        return log;
    }

    @Override
    public void create(Logement logement) {
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
}
