package controller;

import model.Order;
import service.OrderService;

import java.sql.SQLException;

public class OrderController {
    private OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    public String placeOrder(Long userId, Long productId, int quantity) {
        try {
            orderService.placeOrder(userId, productId, quantity);
            return "Order placed successfully!";
        } catch (SQLException e) {
            return "Error placing order: " + e.getMessage();
        }
    }

    public Order getOrder(Long id) {
        try {
            return orderService.getOrderById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching order: " + e.getMessage());
            return null;
        }
    }

    public String updateOrderStatus(Long orderId, String newStatus) {
        try {
            orderService.updateOrderStatus(orderId, newStatus);
            return "Order status updated to " + newStatus + " successfully!";
        } catch (SQLException e) {
            return "Error updating order status: " + e.getMessage();
        }
    }
}