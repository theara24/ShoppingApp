package dao;

import model.Admin;
import config.DatabaseConfig;

import java.sql.*;

public class AdminDAO {

    public void save(Admin admin) throws SQLException {
        String sql = "INSERT INTO admins (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.executeUpdate();
        }
    }

    public Admin findById(Long id) throws SQLException {
        String sql = "SELECT * FROM admins WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
            return null;
        }
    }
}