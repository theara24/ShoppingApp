package controller;

import service.QRCodeService;
import model.QRCode;
import java.util.Scanner;

public class QRCodeController {
    private QRCodeService qrCodeService;
    private Scanner scanner;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
        this.scanner = new Scanner(System.in);
    }

    // Handle QR code generation for a payment
    public void generateQRCodeForPayment(int orderId) {
        System.out.println("Generating QR Code for Order ID: " + orderId);
        // Simulate some payment info, this would typically include transaction details
        String paymentInfo = "Payment for Order #" + orderId;

        QRCode qrCode = qrCodeService.generatePaymentQRCode(orderId, paymentInfo);
        if (qrCode != null) {
            System.out.println("QR Code generated successfully!");
            System.out.println("QR Code file saved at: " + qrCode.getFileName());
        } else {
            System.out.println("Failed to generate QR Code.");
        }
    }
}
