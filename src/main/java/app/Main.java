package app;

import controller.UserController;
import controller.AdminController;
import service.UserService;
import service.ProductService;
import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Database connection
        String dbUrl = "jdbc:postgresql://202.178.125.77:1234/shopping";
        String dbUsername = "postgres";
        String dbPassword = "postgres";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            // Initialize services
            UserService userService = new UserService(connection);
            ProductService productService = new ProductService(connection);

            // Initialize controllers
            UserController userController = new UserController(userService, connection);
            AdminController adminController = new AdminController(productService);

            // Application menu
            Scanner scanner = new Scanner(System.in);
            User loggedInUser = null;

            while (loggedInUser == null) {
                System.out.println("Welcome to the Shopping App!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        loggedInUser = userController.register(scanner);
                        break;
                    case 2:
                        loggedInUser = userController.login(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;  // Exit the application
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }

            while (true) {
                System.out.println("Welcome to the Shopping App!");
                System.out.println("1. User Actions");
                System.out.println("2. Admin Actions");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        userController.handleUserActions(scanner);  // Call user actions
                        break;
                    case 2:
                        if ("admin".equals(loggedInUser.getRole())) {
                            adminController.handleAdminActions(scanner);  // Call admin actions
                        } else {
                            System.out.println("Access denied. Admins only.");
                        }
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        loggedInUser = null;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }

                if (loggedInUser == null) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}