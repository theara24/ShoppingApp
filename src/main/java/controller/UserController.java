package controller;

import service.UserService;
import model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    private UserService userService;
    private Connection connection;

    public UserController(UserService userService, Connection connection) {
        this.userService = userService;
        this.connection = connection;
    }

    public User register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter role (user/admin): ");
        String role = scanner.nextLine();

        User user = new User(username, email, password, fullName, address, phoneNumber, role);
        try {
            userService.addUser(user);
            System.out.println("Registration successful.");
            return user;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return null;
        }
    }

    public User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.authenticate(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                return user;
            } else {
                System.out.println("Invalid username or password.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    public void handleUserActions(Scanner scanner) {
        // Implement user actions here
    }
}