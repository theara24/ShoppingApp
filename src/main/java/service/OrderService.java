package service;

import dao.OrderDAO;
import model.Order;
import java.sql.Connection;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService(Connection connection) {
        this.orderDAO = new OrderDAO(connection);
    }

    // Calculate the total amount for the user's cart
    public double calculateTotalAmount(int userId) {
        // Implement the logic to calculate the total amount
        return orderDAO.calculateTotalAmount(userId);
    }

    // Create an order for the user
    public boolean createOrder(int userId, double totalAmount) {
        // Implement the logic to create an order
        return orderDAO.createOrder(userId, totalAmount);
    }
}