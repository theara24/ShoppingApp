package service;

import dao.PaymentDAO;
import model.Payment;
import java.sql.Connection;
import java.util.List;

public class PaymentService {
    private PaymentDAO paymentDAO;

    public PaymentService(Connection connection) {
        this.paymentDAO = new PaymentDAO(connection);
    }

    // Process the payment
    public boolean processPayment(int orderId, double amount) {
        // In a real scenario, integrate with a payment gateway here
        System.out.println("Processing payment of $" + amount);

        // Simulate payment success
        boolean paymentSuccess = true;

        if (paymentSuccess) {
            // Save the payment information to the database
            Payment payment = new Payment(orderId, amount, "SUCCESS");
            return paymentDAO.createPayment(payment);
        } else {
            return false;
        }
    }

    // Make a payment
    public boolean makePayment(Payment payment) {
        return paymentDAO.createPayment(payment);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }
}