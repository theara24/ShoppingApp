package dao;

import model.Cart;
import config.DatabaseConfig;

import java.sql.*;

public class CartDAO {

    public void save(Cart cart) throws SQLException {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cart.getUserId());
            stmt.setLong(2, cart.getProductId());
            stmt.setInt(3, cart.getQuantity());
            stmt.executeUpdate();
        }
    }

    public Cart findById(Long id) throws SQLException {
        String sql = "SELECT * FROM cart WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
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
}