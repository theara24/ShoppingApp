package controller;

import model.Payment;
import service.PaymentService;

import java.sql.SQLException;

public class PaymentController {
    private PaymentService paymentService;

    public PaymentController() {
        this.paymentService = new PaymentService();
    }

    public String processPayment(Long orderId, String paymentMethod) {
        try {
            paymentService.processPayment(orderId, paymentMethod);
            return "Payment processed successfully!";
        } catch (SQLException e) {
            return "Error processing payment: " + e.getMessage();
        }
    }

    public Payment getPayment(Long id) {
        try {
            return paymentService.getPaymentById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching payment: " + e.getMessage());
            return null;
        }
    }
}