package dao;

import model.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a product to the cart
    public boolean addToCart(int userId, int productId, int quantity) {
        String query = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove a product from the cart
    public boolean removeFromCart(int userId, int productId) {
        String query = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all products in the user's cart
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartItems = new ArrayList<>();
        String query = "SELECT c.user_id, c.product_id, c.quantity, p.name, p.price FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cart cartItem = new Cart(
                        userId,
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
