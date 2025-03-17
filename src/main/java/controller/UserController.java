package controller;

import model.User;
import service.UserService;

import java.sql.SQLException;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public String register(User user) {
        try {
            userService.registerUser(user);
            return "User registered successfully!";
        } catch (SQLException e) {
            return "Error registering user: " + e.getMessage();
        }
    }

    public User getUser(Long id) {
        try {
            return userService.getUserById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            return null;
        }
    }
}