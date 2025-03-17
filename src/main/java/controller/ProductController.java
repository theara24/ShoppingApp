package controller;

import model.Product;
import service.ProductService;

import java.sql.SQLException;

public class ProductController {
    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public String addProduct(Product product) {
        try {
            productService.addProduct(product);
            return "Product added successfully!";
        } catch (SQLException e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    public Product getProduct(Long id) {
        try {
            return productService.getProductById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching product: " + e.getMessage());
            return null;
        }
    }
}