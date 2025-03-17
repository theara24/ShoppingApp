// File: src/main/java/dao/PaymentDAO.java
package dao;

import model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createPayment(Payment payment) {
        String sql = "INSERT INTO payments (order_id, amount, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getOrderId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getStatus());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}