package dao;

import config.DatabaseConfig;
import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    // Other methods...

    public Cart findByUserIdAndProductId(Long userId, Long productId) throws SQLException {
        String sql = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                );
            }
            return null;
        }
    }

    public void updateQuantity(Long cartId, int quantity) throws SQLException {
        String sql = "UPDATE cart SET quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setLong(2, cartId);
            stmt.executeUpdate();
        }
    }

    public void save(Cart cart) throws SQLException {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, cart.getUserId());
            stmt.setLong(2, cart.getProductId());
            stmt.setInt(3, cart.getQuantity());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cart.setId(rs.getLong(1));
            }
        }
    }

    public Cart findById(Long cartId) throws SQLException {
        String sql = "SELECT * FROM cart WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cartId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                );
            }
            return null;
        }
    }

    public void delete(Long cartId) throws SQLException {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cartId);
            stmt.executeUpdate();
        }
    }
}