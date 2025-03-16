package service;

import dao.CartDAO;
import model.Cart;
import java.sql.Connection;
import java.util.List;

public class CartService {
    private CartDAO cartDAO;

    public CartService(Connection connection) {
        this.cartDAO = new CartDAO(connection);
    }

    // Add product to the cart
    public boolean addToCart(int userId, int productId, int quantity) {
        return cartDAO.addToCart(userId, productId, quantity);
    }

    // Remove product from the cart
    public boolean removeFromCart(int userId, int productId) {
        return cartDAO.removeFromCart(userId, productId);
    }

    // Get all products in the user's cart
    public List<Cart> getCartByUserId(int userId) {
        return cartDAO.getCartByUserId(userId);
    }
}
