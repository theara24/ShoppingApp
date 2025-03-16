package dao;

import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Save payment information to the database
    public boolean createPayment(Payment payment) {
        String query = "INSERT INTO payments (order_id, amount, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payment.getOrderId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getStatus());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get payments for a particular order
    public List<Payment> getPaymentsForOrder(int orderId) {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("payment_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("payment_date").toLocalDateTime()
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("payment_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("payment_date").toLocalDateTime()
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}