package DAO;

import Modele.ImageLogement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageLogementDAOImpl implements ImageLogementDAO {

    @Override
    public List<ImageLogement> findByLogementId(int idLogement) {
        List<ImageLogement> images = new ArrayList<>();
        String sql = "SELECT * FROM image WHERE ID_logement = ? ORDER BY ordre, ID_image";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql)) {
            st.setInt(1, idLogement);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ImageLogement img = new ImageLogement();
                    img.setIdImage(     rs.getInt("ID_image"));
                    img.setIdLogement(  rs.getInt("ID_logement"));
                    img.setChemin(      rs.getString("chemin")); // Ensure this returns the image path
                    img.setDescription( rs.getString("description"));
                    img.setOrdre(       rs.getInt("ordre"));
                    images.add(img);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public void create(ImageLogement img) {
        String sql = "INSERT INTO image (ID_logement, chemin, description, ordre) VALUES (?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement st = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(   1, img.getIdLogement());
            st.setString(2, img.getChemin());
            st.setString(3, img.getDescription());
            st.setInt(   4, img.getOrdre());
            st.executeUpdate();
            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) img.setIdImage(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
