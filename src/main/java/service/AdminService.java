package service;

import dao.AdminDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Admin;
import model.Order;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private AdminDAO adminDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
    }

    public void registerAdmin(Admin admin) throws SQLException {
        adminDAO.save(admin);
    }

    public Admin getAdminById(Long id) throws SQLException {
        return adminDAO.findById(id);
    }

    public Admin authenticateAdmin(String username, String password) throws SQLException {
        return adminDAO.findByUsernameAndPassword(username, password);
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.save(product);
    }

    public Order getOrderById(Long orderId) throws SQLException {
        return orderDAO.findById(orderId);
    }

    public void updateOrderStatus(Long orderId, String newStatus) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) throw new SQLException("Order not found.");
        orderDAO.updateStatus(orderId, newStatus);
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(rs.getLong("id"), rs.getLong("user_id"), rs.getLong("product_id"),
                        rs.getInt("quantity"), rs.getDouble("total_price"), rs.getString("status")));
            }
        }
        return orders;
    }
}