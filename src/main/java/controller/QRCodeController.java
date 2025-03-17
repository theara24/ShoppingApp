package controller;

import service.QRCodeService;
import java.util.Scanner;

public class QRCodeController {
    private QRCodeService qrCodeService;
    private Scanner scanner;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
        this.scanner = new Scanner(System.in);
    }

    // Method to generate QR code
    public void generateQRCode(int orderId) {
        System.out.print("Enter the data for the QR code: ");
        String data = scanner.nextLine();
        boolean success = qrCodeService.generateQRCode(orderId, data);
        if (success) {
            System.out.println("QR code generated successfully!");
        } else {
            System.out.println("Failed to generate QR code.");
        }
    }
}