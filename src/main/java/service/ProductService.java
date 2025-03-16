package service;

import dao.ProductDAO;
import model.Product;
import java.sql.Connection;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    // Add new product
    public boolean addProduct(String name, String description, double price, int stockQuantity) {
        Product product = new Product(name, description, price, stockQuantity);
        return productDAO.addProduct(product);
    }

    // Update product details
    public boolean updateProduct(int id, String name, String description, double price, int stockQuantity) {
        Product product = new Product(id, name, description, price, stockQuantity);
        return productDAO.updateProduct(product);
    }

    // Delete a product
    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }

    // List all products
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
