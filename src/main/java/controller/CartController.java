package controller;

import model.Cart;
import service.CartService;

import java.sql.SQLException;
import java.util.List;

public class CartController {
    private CartService cartService;

    public CartController() {
        this.cartService = new CartService();
    }

    public String addToCart(Cart cart) {
        try {
            cartService.addToCart(cart);
            return "Item added to cart successfully!";
        } catch (SQLException e) {
            return "Error adding to cart: " + e.getMessage();
        }
    }

    public List<Cart> viewCart(Long userId) {
        try {
            return cartService.getCart(userId);
        } catch (SQLException e) {
            System.err.println("Error fetching cart: " + e.getMessage());
            return null;
        }
    }

    public String updateCartQuantity(Long cartId, int newQuantity) {
        try {
            cartService.updateQuantity(cartId, newQuantity);
            return "Cart quantity updated successfully!";
        } catch (SQLException e) {
            return "Error updating cart quantity: " + e.getMessage();
        }
    }

    public String removeFromCart(Long cartId) {
        try {
            cartService.removeFromCart(cartId);
            return "Item removed from cart successfully!";
        } catch (SQLException e) {
            return "Error removing item from cart: " + e.getMessage();
        }
    }

    public String clearCart(Long userId) {
        try {
            cartService.clearCart(userId);
            return "Cart cleared successfully!";
        } catch (SQLException e) {
            return "Error clearing cart: " + e.getMessage();
        }
    }

    public double getCartTotal(Long userId) {
        try {
            return cartService.calculateCartTotal(userId);
        } catch (SQLException e) {
            System.err.println("Error calculating cart total: " + e.getMessage());
            return 0.0;
        }
    }
}