package dao;

import model.QRCode;
import config.DatabaseConfig;

import java.sql.*;

public class QRCodeDAO {

    public void save(QRCode qrCode) throws SQLException {
        String sql = "INSERT INTO qrcodes (payment_id, file_path) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, qrCode.getPaymentId());
            stmt.setString(2, qrCode.getFilePath());
            stmt.executeUpdate();
        }
    }

    public QRCode findById(Long id) throws SQLException {
        String sql = "SELECT * FROM qrcodes WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new QRCode(
                        rs.getLong("id"),
                        rs.getLong("payment_id"),
                        rs.getString("file_path")
                );
            }
            return null;
        }
    }
}