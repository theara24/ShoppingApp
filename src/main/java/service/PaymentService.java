package service;

import dao.PaymentDAO;
import model.Payment;
import org.json.JSONObject;

import java.sql.SQLException;

public class PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAO();

    public String processPayment(Long orderId, String paymentMethod) throws SQLException {
        Payment payment = new Payment(null, orderId, 100.0, "PENDING", paymentMethod); // Example amount
        paymentDAO.save(payment);
        JSONObject json = new JSONObject();
        json.put("orderId", orderId);
        json.put("amount", 100.0);
        json.put("status", "PENDING");
        json.put("method", paymentMethod);
        return "Scan QR code to pay: " + json.toString();
    }

    public void handleBakongCallback(String paymentId, String status) throws SQLException {
        Long id = Long.parseLong(paymentId);
        paymentDAO.updateStatus(id, status);
    }

    public Payment getPayment(Long orderId) throws SQLException {
        return paymentDAO.findByOrderId(orderId); // Now this should resolve
    }
}