package controller;

import service.UserService;
import service.ProductService;
import model.User;
import java.util.Scanner;
import java.sql.Connection;

public class UserController {
    private UserService userService;
    private Connection connection;

    public UserController(UserService userService, Connection connection) {
        this.userService = userService;
        this.connection = connection;
    }

    public void handleUserActions(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.login(username, password)) {
            System.out.println("Login successful!");
            // Proceed with user actions
            showUserMenu(scanner, username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void showUserMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. View Products");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewProfile(username);
                    break;
                case 2:
                    ProductService productService = new ProductService(connection);
                    ProductController productController = new ProductController(productService);
                    productController.viewProducts(scanner);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;  // Exit the user menu
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void viewProfile(String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            System.out.println("User Profile:");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Phone Number: " + user.getPhoneNumber());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("User not found.");
        }
    }
}