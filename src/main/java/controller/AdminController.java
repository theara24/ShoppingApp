package controller;

import model.Admin;
import model.Order;
import model.Product;
import service.AdminService;
import service.OrderService;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private AdminService adminService;
    private ProductService productService;
    private OrderService orderService;

    public AdminController() {
        this.adminService = new AdminService();
        this.productService = new ProductService();
        this.orderService = new OrderService();
    }

    public Admin login(String username, String password) throws SQLException {
        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin == null) {
            throw new SQLException("Invalid username or password.");
        }
        return admin;
    }

    public String registerAdmin(Admin admin) {
        try {
            adminService.registerAdmin(admin);
            return "Admin registration successful! You can now log in.";
        } catch (SQLException e) {
            return "Admin registration failed: " + e.getMessage();
        }
    }

    public String addProduct(Product product) {
        try {
            productService.addProduct(product);
            return "Product added successfully!";
        } catch (SQLException e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    public List<Order> viewAllOrders() {
        try {
            return orderService.getAllOrders();
        } catch (SQLException e) {
            System.err.println("Error fetching all orders: " + e.getMessage());
            return null;
        }
    }

    public String updateOrderStatus(Long orderId, String status) {
        try {
            orderService.updateOrderStatus(orderId, status);
            return "Order status updated successfully!";
        } catch (SQLException e) {
            return "Error updating order status: " + e.getMessage();
        }
    }
}