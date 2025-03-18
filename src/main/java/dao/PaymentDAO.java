package dao;

import config.DatabaseConfig;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    public void save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (order_id, amount, status, payment_method) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, payment.getOrderId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getStatus());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }
        }
    }

    public void updateStatus(Long paymentId, String status) throws SQLException {
        String sql = "UPDATE payments SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setLong(2, paymentId);
            stmt.executeUpdate();
        }
    }

    // Add this method to fix the error
    public Payment findByOrderId(Long orderId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE order_id = ? ORDER BY id DESC LIMIT 1";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getLong("id"),
                        rs.getLong("order_id"),
                        rs.getDouble("amount"),
                        rs.getString("status"),
                        rs.getString("payment_method")
                );
            }
            return null; // No payment found for this orderId
        }
    }
}