package service;

import utils.QRCodeUtils;
import model.Payment;

import java.util.UUID;

public class BakongPaymentService {

    // Simulate generating a payment link
    public String generatePaymentLink(double amount) {
        String transactionId = UUID.randomUUID().toString();
        String paymentLink = "https://bakong.payment/transaction/" + transactionId + "?amount=" + amount;
        return paymentLink;
    }

    // Simulate making a payment
    public boolean processPayment(Payment payment) {
        // Generate payment link for Bakong
        String paymentLink = generatePaymentLink(payment.getAmount());

        // Generate QR code for the payment link
        String qrCodePath = "qrcodes/payment_" + payment.getOrderId() + ".png";
        QRCodeUtils.generateQRCode(paymentLink, qrCodePath);

        // Simulate the payment processing (in a real system, you would call the Bakong API here)
        System.out.println("Payment processed successfully. Please scan the QR code: " + qrCodePath);

        // Update the payment status
        payment.setStatus("Completed");
        return true;
    }
}
