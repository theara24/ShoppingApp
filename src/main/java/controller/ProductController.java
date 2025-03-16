package controller;

import service.ProductService;
import model.Product;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductService productService;
    private Scanner scanner;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    // Add a new product
    public void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        boolean success = productService.addProduct(name, description, price, stockQuantity);
        if (success) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    // Update an existing product
    public void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        boolean success = productService.updateProduct(productId, name, description, price, stockQuantity);
        if (success) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    // Delete a product
    public void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();
        boolean success = productService.deleteProduct(productId);
        if (success) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    // Display all products
    public void listProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
