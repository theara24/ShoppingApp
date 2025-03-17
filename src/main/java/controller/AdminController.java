package controller;

import model.Admin;
import model.Order;
import model.Product;
import service.AdminService;

import java.sql.SQLException;

public class AdminController {
    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    public String registerAdmin(Admin admin) {
        try {
            adminService.registerAdmin(admin);
            return "Admin registered successfully!";
        } catch (SQLException e) {
            return "Error registering admin: " + e.getMessage();
        }
    }

    public Admin getAdmin(Long id) {
        try {
            return adminService.getAdminById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching admin: " + e.getMessage());
            return null;
        }
    }

    public String addProduct(Product product) {
        try {
            adminService.addProduct(product);
            return "Product added successfully by admin!";
        } catch (SQLException e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    public Order getOrder(Long orderId) {
        try {
            return adminService.getOrderById(orderId);
        } catch (SQLException e) {
            System.err.println("Error fetching order: " + e.getMessage());
            return null;
        }
    }

    public String updateOrderStatus(Long orderId, String newStatus) {
        try {
            adminService.updateOrderStatus(orderId, newStatus);
            return "Order status updated to " + newStatus + " by admin successfully!";
        } catch (SQLException e) {
            return "Error updating order status: " + e.getMessage();
        }
    }
}