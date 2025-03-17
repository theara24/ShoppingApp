package service;

import dao.OrderDAO;
import dao.PaymentDAO;
import model.Order;
import model.Payment;

import java.sql.SQLException;

public class PaymentService {
    private PaymentDAO paymentDAO;
    private OrderDAO orderDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
        this.orderDAO = new OrderDAO();
    }

    public void processPayment(Long orderId, String paymentMethod) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) {
            throw new SQLException("Order not found.");
        }

        Payment payment = new Payment(null, orderId, order.getTotalPrice(), paymentMethod, "PENDING");
        paymentDAO.save(payment);

        // Simulate payment processing (in a real app, integrate with a payment gateway)
        // For now, we’ll assume it succeeds and update the status
        payment.setStatus("COMPLETED"); // This change is local; in a real app, update the DB
    }

    public Payment getPaymentById(Long id) throws SQLException {
        return paymentDAO.findById(id);
    }
}