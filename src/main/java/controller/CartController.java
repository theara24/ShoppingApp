package controller;

import model.Cart;
import service.CartService;

import java.sql.SQLException;

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

    public Cart getCartItem(Long id) {
        try {
            return cartService.getCartItemById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching cart item: " + e.getMessage());
            return null;
        }
    }
}