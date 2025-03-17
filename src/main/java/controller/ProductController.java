package controller;

import service.ProductService;
import model.Product;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // View products with pagination
    public void viewProducts(Scanner scanner) {
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();
        System.out.print("Enter page size: ");
        int pageSize = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        List<Product> products = productService.getProducts(page, pageSize);
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Description: " + product.getDescription());
                System.out.println("-------------");
            }
        }
    }
}