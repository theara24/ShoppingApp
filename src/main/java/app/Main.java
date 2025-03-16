package app;

import controller.UserController;
import controller.ProductController;
import controller.AdminController;
import controller.PaymentController;
import controller.QRCodeController;
import service.UserService;
import service.ProductService;
import service.AdminService;
import service.PaymentService;
import service.QRCodeService;
import dao.UserDAO;
import dao.ProductDAO;
import dao.OrderDAO;
import dao.PaymentDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Database connection
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres"; // Replace with your actual database username
        String dbPassword = "theara24"; // Replace with your actual database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            // Initialize services
            UserService userService = new UserService(connection);
            ProductService productService = new ProductService(connection);
            AdminService adminService = new AdminService(connection);
            PaymentService paymentService = new PaymentService(connection);
            QRCodeService qrCodeService = new QRCodeService(connection);

            // Initialize controllers
            UserController userController = new UserController(userService);
            ProductController productController = new ProductController(productService);
            AdminController adminController = new AdminController(adminService);
            PaymentController paymentController = new PaymentController(paymentService);
            QRCodeController qrCodeController = new QRCodeController(qrCodeService);

            // Application menu
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Welcome to the Shopping App!");
                System.out.println("1. User Actions");
                System.out.println("2. Admin Actions");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userController.handleUserActions();  // Call user actions
                        break;
                    case 2:
                        adminController.viewAllOrders();  // Admin actions
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;  // Exit the application
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}