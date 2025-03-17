package service;

import dao.ProductDAO;
import model.Product;

import java.sql.SQLException;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.save(product);
    }

    public Product getProductById(Long id) throws SQLException {
        return productDAO.findById(id);
    }
}