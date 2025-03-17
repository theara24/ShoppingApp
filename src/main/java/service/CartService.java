package service;

import dao.CartDAO;
import model.Cart;

import java.sql.SQLException;

public class CartService {
    private CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public void addToCart(Cart cart) throws SQLException {
        cartDAO.save(cart);
    }

    public Cart getCartItemById(Long id) throws SQLException {
        return cartDAO.findById(id);
    }
}