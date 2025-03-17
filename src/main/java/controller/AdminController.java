package controller;

import service.ProductService;
import model.Product;
import java.util.Scanner;

public class AdminController {
    private ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    public void handleAdminActions(Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Products");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    updateProduct(scanner);
                    break;
                case 3:
                    deleteProduct(scanner);
                    break;
                case 4:
                    viewProducts();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;  // Exit the admin menu
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product(name, price, description);
        productService.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();

        Product product = new Product(id, name, price, description);
        productService.updateProduct(product);
        System.out.println("Product updated successfully.");
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        productService.deleteProduct(id);
        System.out.println("Product deleted successfully.");
    }

    private void viewProducts() {
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }
    }
}