package service;

import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Cart;
import model.Order;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.cartDAO = new CartDAO();
        this.productDAO = new ProductDAO();
    }

    public double calculateTotalAmount(Long userId) throws SQLException {
        List<Cart> cartItems = cartDAO.findByUserId(userId);
        if (cartItems.isEmpty()) return 0.0;
        double total = 0.0;
        for (Cart item : cartItems) {
            Product product = productDAO.findById(item.getProductId());
            if (product == null) throw new SQLException("Product not found for cart item: " + item.getProductId());
            total += product.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void createOrderFromCart(Long userId) throws SQLException {
        List<Cart> cartItems = cartDAO.findByUserId(userId);
        if (cartItems.isEmpty()) throw new SQLException("Cart is empty.");
        for (Cart item : cartItems) {
            Product product = productDAO.findById(item.getProductId());
            if (product == null) throw new SQLException("Product not found: " + item.getProductId());
            double totalPrice = product.getPrice() * item.getQuantity();
            Order order = new Order(null, userId, item.getProductId(), product.getName(), item.getQuantity(), totalPrice, "PENDING");
            orderDAO.save(order);
            if (product.getStock() < item.getQuantity()) throw new SQLException("Insufficient stock for product: " + product.getName());
            productDAO.updateStock(item.getProductId(), product.getStock() - item.getQuantity());
        }
        cartDAO.deleteByUserId(userId); // Clear cart after order creation
    }

    public List<Order> getOrdersByUserId(Long userId) throws SQLException {
        return orderDAO.findByUserId(userId);
    }

    public void cancelOrder(Long orderId, Long userId) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) throw new SQLException("Order not found.");
        if (!order.getUserId().equals(userId)) throw new SQLException("Order does not belong to this user.");
        if (!"PENDING".equals(order.getStatus())) throw new SQLException("Only PENDING orders can be cancelled.");
        orderDAO.updateStatus(orderId, "CANCELLED");
        Product product = productDAO.findById(order.getProductId());
        if (product != null) {
            productDAO.updateStock(order.getProductId(), product.getStock() + order.getQuantity());
        }
    }

    public void updateOrderStatus(Long orderId, String status) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) throw new SQLException("Order not found.");
        orderDAO.updateStatus(orderId, status);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.findAll();
    }
}