package service;

import dao.OrderDAO;
import dao.PaymentDAO;
import model.Order;
import model.Payment;
import java.sql.Connection;
import java.util.List;

public class AdminService {
    private OrderDAO orderDAO;
    private PaymentDAO paymentDAO;

    public AdminService(Connection connection) {
        this.orderDAO = new OrderDAO(connection);
        this.paymentDAO = new PaymentDAO(connection);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    // View all payments for a particular order
    public List<Payment> getPaymentsForOrder(int orderId) {
        return paymentDAO.getPaymentsForOrder(orderId);
    }
}
