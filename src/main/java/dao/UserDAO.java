package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO users (username, email, password, full_name, address, phone_number, role, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getRole());
            statement.setTimestamp(8, user.getCreatedAt());
            statement.setTimestamp(9, user.getUpdatedAt());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name"),
                            resultSet.getString("address"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("role"),
                            resultSet.getTimestamp("created_at"),
                            resultSet.getTimestamp("updated_at")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}