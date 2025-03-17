package service;

import dao.AdminDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Admin;
import model.Order;
import model.Product;

import java.sql.SQLException;

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

    public void addProduct(Product product) throws SQLException {
        productDAO.save(product);
    }

    public Order getOrderById(Long orderId) throws SQLException {
        return orderDAO.findById(orderId);
    }

    public void updateOrderStatus(Long orderId, String newStatus) throws SQLException {
        Order order = orderDAO.findById(orderId);
        if (order == null) {
            throw new SQLException("Order not found.");
        }
        orderDAO.updateStatus(orderId, newStatus);
    }
}