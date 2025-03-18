package dao;

import model.Admin;
import config.DatabaseConfig;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class AdminDAO {
    public void save(Admin admin) throws SQLException {
        String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO admins (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, hashedPassword);
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
                return new Admin(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
            }
            return null;
        }
    }

    public Admin findByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admins WHERE username = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(password, storedHash)) {
                    return new Admin(rs.getLong("id"), rs.getString("username"), rs.getString("email"), storedHash);
                }
            }
            return null;
        }
    }
}