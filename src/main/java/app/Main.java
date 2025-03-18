package app;

import controller.*;
import model.*;
import config.LoggingConfig;
import service.PaymentService; // Add this import

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private static Scanner scanner = new Scanner(System.in);
    private static UserController userController = new UserController();
    private static AdminController adminController = new AdminController();
    private static ProductController productController = new ProductController();
    private static CartController cartController = new CartController();
    private static OrderController orderController = new OrderController();
    private static PaymentController paymentController = new PaymentController();
    private static QRCodeController qrCodeController = new QRCodeController();
    private static User loggedInUser = null;
    private static Admin loggedInAdmin = null;

    public static void main(String[] args) {
        while (true) {
            try {
                displayMainMenu();
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        userLoginMenu();
                        if (loggedInUser != null) userMenu();
                        break;
                    case 2:
                        adminLoginMenu();
                        if (loggedInAdmin != null) adminMenu();
                        break;
                    case 3:
                        System.out.println("Exiting Shopping App...");
                        LOGGER.info("Application shutdown initiated.");
                        scanner.close();
                        return;
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                LOGGER.severe("Unexpected error in main loop: " + e.getMessage());
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n==========================================");
        System.out.println("          Welcome to Shopping App         ");
        System.out.println("==========================================");
        System.out.println("1. User Login/Register");
        System.out.println("2. Admin Login/Register");
        System.out.println("3. Exit");
        System.out.println("==========================================");
    }

    private static void userLoginMenu() {
        System.out.println("\n==========================================");
        System.out.println("          User Login/Register             ");
        System.out.println("==========================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("==========================================");
        int choice = getIntInput("Choose an option: ");
        if (choice < 1 || choice > 2) {
            System.out.println("Invalid option. Please select 1 or 2.");
            return;
        }

        try {
            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine().trim();
                if (username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
                System.out.print("Enter password: ");
                String password = scanner.nextLine().trim();
                if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty.");
                loggedInUser = userController.login(username, password);
                LOGGER.info("User login attempt: " + username + " - Success");
            } else {
                registerUser();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
            LOGGER.warning("User login/register failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during login/register: " + e.getMessage());
            LOGGER.severe("Error during user login/register: " + e.getMessage());
        }
    }

    private static void adminLoginMenu() {
        System.out.println("\n==========================================");
        System.out.println("          Admin Login/Register            ");
        System.out.println("==========================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("==========================================");
        int choice = getIntInput("Choose an option: ");
        if (choice < 1 || choice > 2) {
            System.out.println("Invalid option. Please select 1 or 2.");
            return;
        }

        try {
            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine().trim();
                if (username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
                System.out.print("Enter password: ");
                String password = scanner.nextLine().trim();
                if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty.");
                loggedInAdmin = adminController.login(username, password);
                LOGGER.info("Admin login attempt: " + username + " - Success");
            } else {
                registerAdmin();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
            LOGGER.warning("Admin login/register failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during login/register: " + e.getMessage());
            LOGGER.severe("Error during admin login/register: " + e.getMessage());
        }
    }

    private static void userMenu() {
        while (true) {
            try {
                System.out.println("\n==========================================");
                System.out.println("          User Menu - " + loggedInUser.getUsername() + "          ");
                System.out.println("==========================================");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. Manage Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Make Payment");
                System.out.println("6. Manage Orders");
                System.out.println("7. Manage Profile");
                System.out.println("8. Logout");
                System.out.println("==========================================");
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 8) {
                    System.out.println("Invalid option. Please select 1-8.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        addToCart();
                        break;
                    case 3:
                        manageCart();
                        break;
                    case 4:
                        checkout();
                        break;
                    case 5:
                        makePayment();
                        break;
                    case 6:
                        manageOrders();
                        break;
                    case 7:
                        manageProfile();
                        break;
                    case 8:
                        LOGGER.info("User logout: " + loggedInUser.getUsername());
                        System.out.println("Logging out " + loggedInUser.getUsername() + "...");
                        loggedInUser = null;
                        return;
                }
            } catch (Exception e) {
                System.out.println("An error occurred in user menu: " + e.getMessage());
                LOGGER.severe("Error in user menu for " + loggedInUser.getUsername() + ": " + e.getMessage());
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            try {
                System.out.println("\n==========================================");
                System.out.println("          Admin Menu - " + loggedInAdmin.getUsername() + "         ");
                System.out.println("==========================================");
                System.out.println("1. Add Product");
                System.out.println("2. View Orders");
                System.out.println("3. Update Order Status");
                System.out.println("4. Logout");
                System.out.println("==========================================");
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid option. Please select 1-4.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewOrders();
                        break;
                    case 3:
                        updateOrderStatus();
                        break;
                    case 4:
                        LOGGER.info("Admin logout: " + loggedInAdmin.getUsername());
                        System.out.println("Logging out " + loggedInAdmin.getUsername() + "...");
                        loggedInAdmin = null;
                        return;
                }
            } catch (Exception e) {
                System.out.println("An error occurred in admin menu: " + e.getMessage());
                LOGGER.severe("Error in admin menu for " + loggedInAdmin.getUsername() + ": " + e.getMessage());
            }
        }
    }

    private static void registerUser() {
        System.out.println("\n--- User Registration ---");
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            if (username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty() || !email.contains("@")) throw new IllegalArgumentException("Invalid email address.");
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            if (password.length() < 6) throw new IllegalArgumentException("Password must be at least 6 characters.");
            User user = new User(null, username, email, password);
            String result = userController.register(user);
            System.out.println(result);
            LOGGER.info("User registered: " + username);
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
            LOGGER.warning("User registration failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            LOGGER.severe("Error during user registration: " + e.getMessage());
        }
    }

    private static void registerAdmin() {
        System.out.println("\n--- Admin Registration ---");
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            if (username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty() || !email.contains("@")) throw new IllegalArgumentException("Invalid email address.");
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            if (password.length() < 6) throw new IllegalArgumentException("Password must be at least 6 characters.");
            Admin admin = new Admin(null, username, email, password);
            String result = adminController.registerAdmin(admin);
            System.out.println(result);
            LOGGER.info("Admin registered: " + username);
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
            LOGGER.warning("Admin registration failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            LOGGER.severe("Error during admin registration: " + e.getMessage());
        }
    }

    private static void viewProducts() {
        try {
            List<Product> products = productController.displayAllProducts();
            if (products != null && !products.isEmpty()) {
                System.out.println("\n==========================================");
                System.out.println("            Available Products            ");
                System.out.println("==========================================");
                System.out.printf("| %-5s | %-20s | %-10s | %-5s |\n", "ID", "Name", "Price", "Stock");
                System.out.println("------------------------------------------");
                for (Product p : products) {
                    System.out.printf("| %-5d | %-20s | %-10.2f | %-5d |\n", p.getId(), p.getName(), p.getPrice(), p.getStock());
                }
                System.out.println("==========================================");
            } else {
                System.out.println("No products available at this time.");
            }
            LOGGER.info("User " + loggedInUser.getUsername() + " viewed products.");
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e.getMessage());
            LOGGER.severe("Error fetching products for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void addToCart() {
        try {
            viewProducts();
            Long productId = getLongInput("Enter product ID to add to cart: ");
            if (productId <= 0) throw new IllegalArgumentException("Product ID must be a positive number.");
            int quantity = getIntInput("Enter quantity: ");
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
            Cart cart = new Cart(null, loggedInUser.getId(), productId, quantity);
            String result = cartController.addToCart(cart);
            System.out.println(result);
            LOGGER.info("User " + loggedInUser.getUsername() + " added product ID " + productId + " (qty: " + quantity + ") to cart.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add to cart: " + e.getMessage());
            LOGGER.warning("Failed to add to cart for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e.getMessage());
            LOGGER.severe("Error adding to cart for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void manageCart() {
        while (true) {
            try {
                List<Cart> cartItems = cartController.viewCart(loggedInUser.getId());
                if (cartItems != null && !cartItems.isEmpty()) {
                    System.out.println("\n==========================================");
                    System.out.println("            Your Cart                    ");
                    System.out.println("==========================================");
                    System.out.printf("| %-5s | %-20s | %-10s |\n", "ID", "Product", "Quantity");
                    System.out.println("------------------------------------------");
                    for (Cart item : cartItems) {
                        System.out.printf("| %-5d | %-20s | %-10d |\n", item.getId(), item.getProductName(), item.getQuantity());
                    }
                    System.out.println("------------------------------------------");
                    double total = cartController.getCartTotal(loggedInUser.getId());
                    System.out.printf("Total: $%.2f\n", total);
                    System.out.println("==========================================");
                } else {
                    System.out.println("Your cart is empty.");
                    return;
                }

                System.out.println("\nCart Management Options:");
                System.out.println("1. Update Quantity");
                System.out.println("2. Remove Item");
                System.out.println("3. Clear Cart");
                System.out.println("4. Back to User Menu");
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid option. Please select 1-4.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        updateCartQuantity();
                        break;
                    case 2:
                        removeFromCart();
                        break;
                    case 3:
                        clearCart();
                        break;
                    case 4:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error managing cart: " + e.getMessage());
                LOGGER.severe("Error managing cart for " + loggedInUser.getUsername() + ": " + e.getMessage());
            }
        }
    }

    private static void updateCartQuantity() {
        try {
            Long cartId = getLongInput("Enter cart item ID to update: ");
            if (cartId <= 0) throw new IllegalArgumentException("Cart ID must be a positive number.");
            int newQuantity = getIntInput("Enter new quantity: ");
            if (newQuantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
            String result = cartController.updateCartQuantity(cartId, newQuantity);
            System.out.println(result);
            LOGGER.info("User " + loggedInUser.getUsername() + " updated cart item ID " + cartId + " to quantity " + newQuantity);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to update quantity: " + e.getMessage());
            LOGGER.warning("Failed to update cart quantity for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error updating quantity: " + e.getMessage());
            LOGGER.severe("Error updating cart quantity for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void removeFromCart() {
        try {
            Long cartId = getLongInput("Enter cart item ID to remove: ");
            if (cartId <= 0) throw new IllegalArgumentException("Cart ID must be a positive number.");
            String result = cartController.removeFromCart(cartId);
            System.out.println(result);
            LOGGER.info("User " + loggedInUser.getUsername() + " removed cart item ID " + cartId);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to remove item: " + e.getMessage());
            LOGGER.warning("Failed to remove cart item for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
            LOGGER.severe("Error removing cart item for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void clearCart() {
        try {
            System.out.print("Are you sure you want to clear your cart? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!"yes".equals(confirm)) {
                System.out.println("Cart clearing cancelled.");
                return;
            }
            String result = cartController.clearCart(loggedInUser.getId());
            System.out.println(result);
            LOGGER.info("User " + loggedInUser.getUsername() + " cleared their cart.");
        } catch (Exception e) {
            System.out.println("Error clearing cart: " + e.getMessage());
            LOGGER.severe("Error clearing cart for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void checkout() {
        try {
            String totalMsg = orderController.checkout(loggedInUser.getId());
            System.out.println(totalMsg);
            if (!totalMsg.contains("empty")) {
                System.out.print("Do you want to proceed with the order (yes/no)? ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (!"yes".equals(confirm) && !"no".equals(confirm)) {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                    return;
                }
                if ("yes".equals(confirm)) {
                    String result = orderController.confirmCheckout(loggedInUser.getId());
                    System.out.println(result);
                    LOGGER.info("User " + loggedInUser.getUsername() + " placed order from cart. Total: " + totalMsg);
                } else {
                    System.out.println("Checkout cancelled by user.");
                    LOGGER.info("User " + loggedInUser.getUsername() + " cancelled checkout.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
            LOGGER.severe("Error during checkout for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void makePayment() {
        try {
            Long orderId = getLongInput("Enter order ID to pay: ");
            if (orderId <= 0) throw new IllegalArgumentException("Order ID must be a positive number.");
            String paymentUrl = paymentController.processPayment(orderId, "BAKONG");
            System.out.println(paymentUrl);
            if (paymentUrl != null && paymentUrl.contains("Scan QR code")) {
                Payment latestPayment = paymentController.getPayment(orderId);
                if (latestPayment == null) throw new Exception("Payment record not found for order ID " + orderId);
                String qrPath = qrCodeController.generateQRCode(latestPayment.getId(), paymentUrl.split(": ")[1]);
                System.out.println(qrPath);
                System.out.println("Simulating Bakong payment success...");
                new PaymentService().handleBakongCallback(String.valueOf(latestPayment.getId()), "SUCCESS");
                System.out.println("Payment completed successfully!");
                LOGGER.info("User " + loggedInUser.getUsername() + " completed payment for order ID " + orderId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());
            LOGGER.warning("Payment failed for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing payment: " + e.getMessage());
            LOGGER.severe("Error processing payment for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void manageOrders() {
        while (true) {
            try {
                List<Order> orders = orderController.getUserOrders(loggedInUser.getId());
                if (orders != null && !orders.isEmpty()) {
                    System.out.println("\n==========================================");
                    System.out.println("            My Order History             ");
                    System.out.println("==========================================");
                    System.out.printf("| %-5s | %-20s | %-10s | %-10s |\n", "ID", "Product", "Total", "Status");
                    System.out.println("------------------------------------------");
                    for (Order o : orders) {
                        System.out.printf("| %-5d | %-20s | %-10.2f | %-10s |\n", o.getId(), o.getProductName(),
                                o.getTotalPrice(), o.getStatus());
                    }
                    System.out.println("==========================================");
                } else {
                    System.out.println("You have no order history yet.");
                    return;
                }

                System.out.println("\nOrder Management Options:");
                System.out.println("1. Cancel Order");
                System.out.println("2. Back to User Menu");
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid option. Please select 1-2.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        cancelOrder();
                        break;
                    case 2:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error managing orders: " + e.getMessage());
                LOGGER.severe("Error managing orders for " + loggedInUser.getUsername() + ": " + e.getMessage());
            }
        }
    }

    private static void cancelOrder() {
        try {
            Long orderId = getLongInput("Enter order ID to cancel: ");
            if (orderId <= 0) throw new IllegalArgumentException("Order ID must be a positive number.");
            System.out.print("Are you sure you want to cancel this order? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!"yes".equals(confirm)) {
                System.out.println("Order cancellation cancelled.");
                return;
            }
            String result = orderController.cancelOrder(orderId, loggedInUser.getId());
            System.out.println(result);
            LOGGER.info("User " + loggedInUser.getUsername() + " cancelled order ID " + orderId);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to cancel order: " + e.getMessage());
            LOGGER.warning("Failed to cancel order for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error cancelling order: " + e.getMessage());
            LOGGER.severe("Error cancelling order for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void manageProfile() {
        while (true) {
            try {
                User user = userController.getUserProfile(loggedInUser.getId());
                if (user != null) {
                    System.out.println("\n==========================================");
                    System.out.println("            Your Profile                 ");
                    System.out.println("==========================================");
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("==========================================");
                } else {
                    System.out.println("Error retrieving profile.");
                    return;
                }

                System.out.println("\nProfile Management Options:");
                System.out.println("1. Update Profile");
                System.out.println("2. Change Password");
                System.out.println("3. Back to User Menu");
                int choice = getIntInput("Choose an option: ");
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid option. Please select 1-3.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        updateProfile();
                        break;
                    case 2:
                        changePassword();
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error managing profile: " + e.getMessage());
                LOGGER.severe("Error managing profile for " + loggedInUser.getUsername() + ": " + e.getMessage());
            }
        }
    }

    private static void updateProfile() {
        try {
            System.out.print("Enter new username (leave blank to keep current): ");
            String newUsername = scanner.nextLine().trim();
            if (newUsername.isEmpty()) newUsername = loggedInUser.getUsername();
            System.out.print("Enter new email (leave blank to keep current): ");
            String newEmail = scanner.nextLine().trim();
            if (newEmail.isEmpty()) newEmail = loggedInUser.getEmail();
            String result = userController.updateUserProfile(loggedInUser.getId(), newUsername, newEmail);
            System.out.println(result);
            if (result.contains("successfully")) {
                loggedInUser.setUsername(newUsername);
                loggedInUser.setEmail(newEmail);
                LOGGER.info("User " + loggedInUser.getUsername() + " updated profile - Username: " + newUsername + ", Email: " + newEmail);
            }
        } catch (Exception e) {
            System.out.println("Error updating profile: " + e.getMessage());
            LOGGER.severe("Error updating profile for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void changePassword() {
        try {
            System.out.print("Enter current password: ");
            String oldPassword = scanner.nextLine().trim();
            if (oldPassword.isEmpty()) throw new IllegalArgumentException("Current password cannot be empty.");
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine().trim();
            if (newPassword.isEmpty()) throw new IllegalArgumentException("New password cannot be empty.");
            System.out.print("Confirm new password: ");
            String confirmPassword = scanner.nextLine().trim();
            if (!newPassword.equals(confirmPassword)) throw new IllegalArgumentException("New passwords do not match.");
            String result = userController.changePassword(loggedInUser.getId(), oldPassword, newPassword);
            System.out.println(result);
            if (result.contains("successfully")) {
                loggedInUser.setPassword(newPassword);
                LOGGER.info("User " + loggedInUser.getUsername() + " changed their password.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to change password: " + e.getMessage());
            LOGGER.warning("Failed to change password for " + loggedInUser.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error changing password: " + e.getMessage());
            LOGGER.severe("Error changing password for " + loggedInUser.getUsername() + ": " + e.getMessage());
        }
    }

    private static void addProduct() {
        System.out.println("\n--- Add New Product ---");
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Product name cannot be empty.");
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();
            double price = getDoubleInput("Enter price: ");
            if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0.");
            int stock = getIntInput("Enter stock: ");
            if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
            Product product = new Product(null, name, description, price, stock);
            String result = adminController.addProduct(product);
            System.out.println(result);
            LOGGER.info("Admin " + loggedInAdmin.getUsername() + " added product: " + name);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add product: " + e.getMessage());
            LOGGER.warning("Failed to add product by " + loggedInAdmin.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
            LOGGER.severe("Error adding product by " + loggedInAdmin.getUsername() + ": " + e.getMessage());
        }
    }

    private static void viewOrders() {
        try {
            List<Order> orders = adminController.viewAllOrders();
            if (orders != null && !orders.isEmpty()) {
                System.out.println("\n==========================================");
                System.out.println("              All Orders                 ");
                System.out.println("==========================================");
                System.out.printf("| %-5s | %-5s | %-20s | %-10s | %-10s |\n", "ID", "User", "Product", "Total", "Status");
                System.out.println("------------------------------------------");
                for (Order o : orders) {
                    System.out.printf("| %-5d | %-5d | %-20s | %-10.2f | %-10s |\n", o.getId(), o.getUserId(),
                            o.getProductName() != null ? o.getProductName() : "Unknown", o.getTotalPrice(), o.getStatus());
                }
                System.out.println("==========================================");
            } else {
                System.out.println("No orders available at this time.");
            }
            LOGGER.info("Admin " + loggedInAdmin.getUsername() + " viewed all orders.");
        } catch (Exception e) {
            System.out.println("Error fetching orders: " + e.getMessage());
            LOGGER.severe("Error fetching orders by " + loggedInAdmin.getUsername() + ": " + e.getMessage());
        }
    }

    private static void updateOrderStatus() {
        try {
            viewOrders();
            Long orderId = getLongInput("Enter order ID to update: ");
            if (orderId <= 0) throw new IllegalArgumentException("Order ID must be a positive number.");
            System.out.print("Enter new status (PENDING/SHIPPED/DELIVERED/CANCELLED): ");
            String status = scanner.nextLine().trim().toUpperCase();
            if (!List.of("PENDING", "SHIPPED", "DELIVERED", "CANCELLED").contains(status)) {
                throw new IllegalArgumentException("Invalid status. Use PENDING, SHIPPED, DELIVERED, or CANCELLED.");
            }
            String result = adminController.updateOrderStatus(orderId, status);
            System.out.println(result);
            LOGGER.info("Admin " + loggedInAdmin.getUsername() + " updated order ID " + orderId + " to status " + status);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to update order status: " + e.getMessage());
            LOGGER.warning("Failed to update order status by " + loggedInAdmin.getUsername() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
            LOGGER.severe("Error updating order status by " + loggedInAdmin.getUsername() + ": " + e.getMessage());
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0; // Default handled by caller validation
        }
    }

    private static long getLongInput(String prompt) {
        System.out.print(prompt);
        try {
            String input = scanner.nextLine().trim();
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            return 0L; // Default handled by caller validation
        }
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        try {
            String input = scanner.nextLine().trim();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0; // Default handled by caller validation
        }
    }
}