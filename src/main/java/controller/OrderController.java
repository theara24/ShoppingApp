package controller;

import model.Order;
import service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderController {
    private OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    public String checkout(Long userId) {
        try {
            double total = orderService.calculateTotalAmount(userId);
            if (total == 0.0) return "Cart is empty.";
            return String.format("Total amount to pay: $%.2f", total);
        } catch (SQLException e) {
            return "Error during checkout: " + e.getMessage();
        }
    }

    public String confirmCheckout(Long userId) {
        try {
            orderService.createOrderFromCart(userId);
            return "Order placed successfully!";
        } catch (SQLException e) {
            return "Error placing order: " + e.getMessage();
        }
    }

    public List<Order> getUserOrders(Long userId) {
        try {
            return orderService.getOrdersByUserId(userId);
        } catch (SQLException e) {
            System.err.println("Error fetching user orders: " + e.getMessage());
            return null;
        }
    }

    public String cancelOrder(Long orderId, Long userId) {
        try {
            orderService.cancelOrder(orderId, userId);
            return "Order cancelled successfully!";
        } catch (SQLException e) {
            return "Error cancelling order: " + e.getMessage();
        }
    }
}