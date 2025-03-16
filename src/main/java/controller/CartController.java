package controller;

import service.CartService;
import model.Cart;
import java.util.List;
import java.util.Scanner;

public class CartController {
    private CartService cartService;
    private Scanner scanner;

    public CartController(CartService cartService) {
        this.cartService = cartService;
        this.scanner = new Scanner(System.in);
    }

    // Add a product to the cart
    public void addToCart(int userId) {
        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        boolean success = cartService.addToCart(userId, productId, quantity);
        if (success) {
            System.out.println("Product added to cart successfully!");
        } else {
            System.out.println("Failed to add product to cart.");
        }
    }

    // Remove a product from the cart
    public void removeFromCart(int userId) {
        System.out.print("Enter product ID to remove from cart: ");
        int productId = scanner.nextInt();
        boolean success = cartService.removeFromCart(userId, productId);
        if (success) {
            System.out.println("Product removed from cart successfully!");
        } else {
            System.out.println("Failed to remove product from cart.");
        }
    }

    // View all products in the cart
    public void viewCart(int userId) {
        List<Cart> cartItems = cartService.getCartByUserId(userId);
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            for (Cart item : cartItems) {
                System.out.println(item);
            }
        }
    }
}
