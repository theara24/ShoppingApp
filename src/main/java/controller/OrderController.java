package controller;

import service.OrderService;
import java.util.Scanner;

public class OrderController {
    private OrderService orderService;
    private Scanner scanner;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    // Checkout the cart and place an order
    public void checkout(int userId) {
        double totalAmount = orderService.calculateTotalAmount(userId);
        System.out.println("Total amount for your cart: $" + totalAmount);
        System.out.print("Do you want to proceed with the order (yes/no)? ");
        String decision = scanner.nextLine();

        if ("yes".equalsIgnoreCase(decision)) {
            boolean success = orderService.createOrder(userId, totalAmount);
            if (success) {
                System.out.println("Order placed successfully!");
            } else {
                System.out.println("Failed to place order.");
            }
        } else {
            System.out.println("Order canceled.");
        }
    }
}