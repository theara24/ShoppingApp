package service;

import dao.OrderDAO;
import model.Order;
import dao.CartDAO;
import model.Cart;
import java.sql.Connection;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private CartDAO cartDAO;

    public OrderService(Connection connection) {
        this.orderDAO = new OrderDAO(connection);
        this.cartDAO = new CartDAO(connection);
    }

    // Calculate total amount for the cart
    public double calculateTotalAmount(int userId) {
        List<Cart> cartItems = cartDAO.getCartByUserId(userId);
        double totalAmount = 0.0;
        for (Cart item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        return totalAmount;
    }

    // Create an order
    public boolean createOrder(int userId) {
        double totalAmount = calculateTotalAmount(userId);
        Order order = new Order(userId, totalAmount);
        return orderDAO.createOrder(order);
    }
}
