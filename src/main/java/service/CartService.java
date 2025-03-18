package service;

import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class CartService {
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
        this.productDAO = new ProductDAO();
    }

    public void addToCart(Cart cart) throws SQLException {
        if (cart.getUserId() == null || cart.getUserId() <= 0) throw new IllegalArgumentException("Invalid user ID.");
        if (cart.getProductId() == null || cart.getProductId() <= 0) throw new IllegalArgumentException("Invalid product ID.");
        if (cart.getQuantity() <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
        Product product = productDAO.findById(cart.getProductId());
        if (product == null) throw new SQLException("Product not found.");
        if (product.getStock() < cart.getQuantity()) throw new SQLException("Insufficient stock for product: " + product.getName());
        Cart existingCart = cartDAO.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        if (existingCart != null) {
            cartDAO.updateQuantity(existingCart.getId(), existingCart.getQuantity() + cart.getQuantity());
        } else {
            cartDAO.save(cart);
        }
    }

    public List<Cart> getCart(Long userId) throws SQLException {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("Invalid user ID.");
        List<Cart> cartItems = cartDAO.findByUserId(userId);
        for (Cart item : cartItems) {
            Product product = productDAO.findById(item.getProductId());
            if (product != null) {
                item.setProductName(product.getName());
            }
        }
        return cartItems;
    }

    public void updateQuantity(Long cartId, int newQuantity) throws SQLException {
        if (cartId == null || cartId <= 0) throw new IllegalArgumentException("Invalid cart ID.");
        if (newQuantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
        Cart cart = cartDAO.findById(cartId);
        if (cart == null) throw new SQLException("Cart item not found.");
        Product product = productDAO.findById(cart.getProductId());
        if (product == null) throw new SQLException("Product not found.");
        if (product.getStock() < newQuantity) throw new SQLException("Insufficient stock for product: " + product.getName());
        cartDAO.updateQuantity(cartId, newQuantity);
    }

    public void removeFromCart(Long cartId) throws SQLException {
        if (cartId == null || cartId <= 0) throw new IllegalArgumentException("Invalid cart ID.");
        Cart cart = cartDAO.findById(cartId);
        if (cart == null) throw new SQLException("Cart item not found.");
        cartDAO.delete(cartId);
    }

    public void clearCart(Long userId) throws SQLException {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("Invalid user ID.");
        cartDAO.deleteByUserId(userId);
    }

    public double calculateCartTotal(Long userId) throws SQLException {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("Invalid user ID.");
        List<Cart> cartItems = cartDAO.findByUserId(userId);
        if (cartItems.isEmpty()) return 0.0;
        double total = 0.0;
        for (Cart item : cartItems) {
            Product product = productDAO.findById(item.getProductId());
            if (product == null) throw new SQLException("Product not found for cart item: " + item.getProductId());
            total += product.getPrice() * item.getQuantity();
        }
        return total;
    }
}