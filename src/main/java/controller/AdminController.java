package controller;

import service.AdminService;
import model.Order;
import model.Payment;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private AdminService adminService;
    private Scanner scanner;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }

    // View all orders
    public void viewAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders to display.");
        } else {
            System.out.println("All orders:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    // View payments for a particular order
    public void viewPaymentsForOrder() {
        System.out.print("Enter Order ID to view payments: ");
        int orderId = scanner.nextInt();
        List<Payment> payments = adminService.getPaymentsForOrder(orderId);
        if (payments.isEmpty()) {
            System.out.println("No payments for this order.");
        } else {
            System.out.println("Payments for Order ID " + orderId + ":");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }
}
