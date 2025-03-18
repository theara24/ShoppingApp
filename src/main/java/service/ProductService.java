package service;

import dao.ProductDAO;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("stock")));
            }
        }
        return products;
    }
}