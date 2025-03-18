package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void registerUser(User user) throws SQLException {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
        if (userDAO.findByUsername(user.getUsername()) != null) {
            throw new SQLException("Username already exists.");
        }
        userDAO.save(user);
    }

    public User getUserById(Long id) throws SQLException {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid user ID.");
        return userDAO.findById(id);
    }

    public void updateUser(Long id, String username, String email) throws SQLException {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid user ID.");
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email address.");
        User existingUser = userDAO.findById(id);
        if (existingUser == null) throw new SQLException("User not found.");
        if (!username.equals(existingUser.getUsername()) && userDAO.findByUsername(username) != null) {
            throw new SQLException("Username already exists.");
        }
        userDAO.update(id, username, email);
    }

    public void changePassword(Long id, String oldPassword, String newPassword) throws SQLException {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid user ID.");
        if (oldPassword == null || newPassword == null) throw new IllegalArgumentException("Passwords cannot be null.");
        if (newPassword.length() < 6) throw new IllegalArgumentException("New password must be at least 6 characters.");
        User user = userDAO.findById(id);
        if (user == null) throw new SQLException("User not found.");
        if (!user.getPassword().equals(oldPassword)) throw new SQLException("Incorrect old password.");
        userDAO.updatePassword(id, newPassword);
    }
}