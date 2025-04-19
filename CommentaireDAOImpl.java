package DAO;

import DAO.CommentaireDAO;
import DAO.DatabaseConnection;
import Modele.Commentaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDAOImpl implements CommentaireDAO {

    @Override
    public void create(Commentaire c) {
        // Si aucun ID n'a été fourni, on génère max+1
        if (c.getID_commentaire() == 0) {
            c.setID_commentaire(getNextId());
        }

        String sql = "INSERT INTO commentaire(ID_commentaire, contenu, ID_logement, ID) "
                + "VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt   (1, c.getID_commentaire());
            stmt.setString(2, c.getContenu());
            stmt.setInt   (3, c.getID_logement());
            stmt.setInt   (4, c.getID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Commentaire findById(int ID_commentaire) {
        Commentaire c = null;
        String sql = "SELECT * FROM commentaire WHERE ID_commentaire=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_commentaire);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                c = mapResultSetToCommentaire(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Commentaire> findAll() {
        List<Commentaire> liste = new ArrayList<>();
        String sql = "SELECT * FROM commentaire";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(mapResultSetToCommentaire(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void update(Commentaire c) {
        String sql = "UPDATE commentaire SET contenu=?, ID_logement=?, ID=? "
                + "WHERE ID_commentaire=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getContenu());
            stmt.setInt   (2, c.getID_logement());
            stmt.setInt   (3, c.getID());
            stmt.setInt   (4, c.getID_commentaire());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID_commentaire) {
        String sql = "DELETE FROM commentaire WHERE ID_commentaire=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_commentaire);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commentaire> findByLogementId(int ID_logement) {
        List<Commentaire> liste = new ArrayList<>();
        String sql = "SELECT * FROM commentaire WHERE ID_logement=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_logement);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                liste.add(mapResultSetToCommentaire(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    // ----- méthodes utilitaires -----

    /** Transforme un ResultSet en objet Commentaire */
    private Commentaire mapResultSetToCommentaire(ResultSet rs) throws SQLException {
        Commentaire c = new Commentaire();
        c.setID_commentaire(rs.getInt("ID_commentaire"));
        c.setContenu       (rs.getString("contenu"));
        c.setID_logement   (rs.getInt("ID_logement"));
        c.setID            (rs.getInt("ID"));
        return c;
    }

    /** Retourne le plus grand ID_commentaire présent en base */
    private int getMaxId() {
        String sql = "SELECT MAX(ID_commentaire) AS max_id FROM commentaire";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** Prochain ID à utiliser : max+1 */
    private int getNextId() {
        return getMaxId() + 1;
    }
}
