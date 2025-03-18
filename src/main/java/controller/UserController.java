package controller;

import model.User;
import service.UserService;

import java.sql.SQLException;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User login(String username, String password) throws SQLException {
        User user = userService.authenticateUser(username, password);
        if (user == null) {
            throw new SQLException("Invalid username or password.");
        }
        return user;
    }

    public String register(User user) {
        try {
            userService.registerUser(user);
            return "Registration successful! You can now log in.";
        } catch (SQLException e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    public User getUserProfile(Long id) {
        try {
            return userService.getUserById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching user profile: " + e.getMessage());
            return null;
        }
    }

    public String updateUserProfile(Long id, String username, String email) {
        try {
            userService.updateUser(id, username, email);
            return "Profile updated successfully!";
        } catch (SQLException e) {
            return "Error updating profile: " + e.getMessage();
        }
    }

    public String changePassword(Long id, String oldPassword, String newPassword) {
        try {
            userService.changePassword(id, oldPassword, newPassword);
            return "Password changed successfully!";
        } catch (SQLException e) {
            return "Error changing password: " + e.getMessage();
        }
    }
}