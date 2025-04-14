package DAO;

import DAO.UsersDAO;
import Modele.Users;
import DAO.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {

    @Override
    public Users findById(int ID) {
        Users user = null;
        String sql = "SELECT * FROM users WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = mapResultSetToUsers(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Users> findAll() {
        List<Users> liste = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Users user = mapResultSetToUsers(rs);
                liste.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void create(Users user) {
        // Selon votre script, ID n'est pas AUTO_INCREMENT
        // donc vous devez insérer la valeur ID vous-même.
        String sql = "INSERT INTO users(ID, role, email, mdp, nom, prenom) "
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getID());
            stmt.setInt(2, user.getRole());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMdp());
            stmt.setString(5, user.getNom());
            stmt.setString(6, user.getPrenom());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users user) {
        String sql = "UPDATE users SET role=?, email=?, mdp=?, nom=?, prenom=? WHERE ID=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getRole());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMdp());
            stmt.setString(4, user.getNom());
            stmt.setString(5, user.getPrenom());
            stmt.setInt(6, user.getID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID) {
        String sql = "DELETE FROM users WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users findByEmail(String email) {
        Users user = null;
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = mapResultSetToUsers(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private Users mapResultSetToUsers(ResultSet rs) throws SQLException {
        Users u = new Users();
        u.setID(rs.getInt("ID"));
        u.setRole(rs.getInt("role"));
        u.setEmail(rs.getString("email"));
        u.setMdp(rs.getString("mdp"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        return u;
    }
}
