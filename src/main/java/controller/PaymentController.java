package controller;

import model.Payment;
import service.PaymentService;

import java.sql.SQLException;

public class PaymentController {
    private PaymentService paymentService = new PaymentService();

    public String processPayment(Long orderId, String paymentMethod) {
        try {
            return paymentService.processPayment(orderId, paymentMethod);
        } catch (SQLException e) {
            return "Error processing payment: " + e.getMessage();
        }
    }

    public Payment getPayment(Long orderId) {
        try {
            return paymentService.getPayment(orderId);
        } catch (SQLException e) {
            System.err.println("Error fetching payment: " + e.getMessage());
            return null;
        }
    }
}