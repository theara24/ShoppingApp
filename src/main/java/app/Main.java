package app;

import controller.*;
import model.*;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserController userController = new UserController();
    private static AdminController adminController = new AdminController();
    private static ProductController productController = new ProductController();
    private static CartController cartController = new CartController();
    private static OrderController orderController = new OrderController();
    private static PaymentController paymentController = new PaymentController();
    private static QRCodeController qrCodeController = new QRCodeController();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Shopping App ===");
            System.out.println("1. User Menu");
            System.out.println("2. Admin Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    adminMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Register User");
            System.out.println("2. View Product");
            System.out.println("3. Add to Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Process Payment");
            System.out.println("6. Generate QR Code");
            System.out.println("7. Back");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    viewProduct();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    processPayment();
                    break;
                case 6:
                    generateQRCode();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Register Admin");
            System.out.println("2. Add Product");
            System.out.println("3. View Order");
            System.out.println("4. Update Order Status");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    registerAdmin();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    viewOrder();
                    break;
                case 4:
                    updateOrderStatus();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(null, username, email, password);
        System.out.println(userController.register(user));
    }

    private static void viewProduct() {
        System.out.print("Enter product ID: ");
        Long id = getLongInput();
        Product product = productController.getProduct(id);
        if (product != null) {
            System.out.println("Product: " + product.getName() + ", $" + product.getPrice() +
                    ", Stock: " + product.getStock());
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addToCart() {
        System.out.print("Enter user ID: ");
        Long userId = getLongInput();
        System.out.print("Enter product ID: ");
        Long productId = getLongInput();
        System.out.print("Enter quantity: ");
        int quantity = getIntInput();
        Cart cart = new Cart(null, userId, productId, quantity);
        System.out.println(cartController.addToCart(cart));
    }

    private static void placeOrder() {
        System.out.print("Enter user ID: ");
        Long userId = getLongInput();
        System.out.print("Enter product ID: ");
        Long productId = getLongInput();
        System.out.print("Enter quantity: ");
        int quantity = getIntInput();
        System.out.println(orderController.placeOrder(userId, productId, quantity));
    }

    private static void processPayment() {
        System.out.print("Enter order ID: ");
        Long orderId = getLongInput();
        System.out.print("Enter payment method (e.g., CREDIT_CARD): ");
        String method = scanner.nextLine();
        System.out.println(paymentController.processPayment(orderId, method));
    }

    private static void generateQRCode() {
        System.out.print("Enter payment ID: ");
        Long paymentId = getLongInput();
        System.out.println(qrCodeController.generateQRCode(paymentId));
    }

    private static void registerAdmin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Admin admin = new Admin(null, username, email, password);
        System.out.println(adminController.registerAdmin(admin));
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = getDoubleInput();
        System.out.print("Enter stock: ");
        int stock = getIntInput();
        Product product = new Product(null, name, description, price, stock);
        System.out.println(adminController.addProduct(product));
    }

    private static void viewOrder() {
        System.out.print("Enter order ID: ");
        Long orderId = getLongInput();
        Order order = adminController.getOrder(orderId);
        if (order != null) {
            System.out.println("Order: User " + order.getUserId() + ", Product " + order.getProductId() +
                    ", Total $" + order.getTotalPrice() + ", Status " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void updateOrderStatus() {
        System.out.print("Enter order ID: ");
        Long orderId = getLongInput();
        System.out.print("Enter new status (e.g., SHIPPED): ");
        String status = scanner.nextLine();
        System.out.println(adminController.updateOrderStatus(orderId, status));
    }

    private static int getIntInput() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value 0.");
            return 0;
        }
    }

    private static long getLongInput() {
        try {
            String input = scanner.nextLine();
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value 0.");
            return 0L;
        }
    }

    private static double getDoubleInput() {
        try {
            String input = scanner.nextLine();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value 0.0.");
            return 0.0;
        }
    }
}