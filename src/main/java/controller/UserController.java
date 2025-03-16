package controller;

import service.UserService;
import model.User;
import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    // Register a new user
    public void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = userService.registerUser(username, email, password);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Try again.");
        }
    }

    // Login user
    public User loginUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful!");
            return user;  // Return logged-in user
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return null;
        }
    }

    // Handle user actions
    public void handleUserActions() {
        while (true) {
            System.out.println("User Actions:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting user actions...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}