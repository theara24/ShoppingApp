package controller;

import service.PaymentService;
import java.util.Scanner;

public class PaymentController {
    private PaymentService paymentService;
    private Scanner scanner;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
        this.scanner = new Scanner(System.in);
    }

    // Process the payment after order checkout
    public void processPayment(int orderId, double totalAmount) {
        System.out.println("Total amount to pay: $" + totalAmount);
        System.out.print("Do you want to proceed with the payment (yes/no)? ");
        String decision = scanner.nextLine();

        if ("yes".equalsIgnoreCase(decision)) {
            boolean paymentSuccess = paymentService.processPayment(orderId, totalAmount);
            if (paymentSuccess) {
                System.out.println("Payment processed successfully!");
            } else {
                System.out.println("Payment failed. Please try again.");
            }
        } else {
            System.out.println("Payment canceled.");
        }
    }
}
