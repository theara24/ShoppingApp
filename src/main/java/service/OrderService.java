package service;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;

import java.sql.SQLException;

public class OrderService {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }

    public void placeOrder(Long userId, Long productId, int quantity) throws SQLException {
        Product product = productDAO.findById(productId);
        if (product == null || product.getStock() < quantity) {
            throw new SQLException("Product not available or insufficient stock.");
        }

        double totalPrice = product.getPrice() * quantity;
        Order order = new Order(null, userId, productId, quantity, totalPrice, "PENDING");
        orderDAO.save(order);

        // Update stock in the database
        int newStock = product.getStock() - quantity;
        productDAO.updateStock(productId, newStock);
    }

    public Order getOrderById(Long id) throws SQLException {
        return orderDAO.findById(id);
    }

    public void updateOrderStatus(Long orderId, String newStatus) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) {
            throw new SQLException("Order not found.");
        }
        orderDAO.updateStatus(orderId, newStatus);
    }
}