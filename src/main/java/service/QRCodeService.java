package service;

import model.QRCode;

import java.sql.Connection;

public class QRCodeService {
    private Connection connection;

    public QRCodeService(Connection connection) {
        this.connection = connection;
    }

    public QRCode generatePaymentQRCode(int orderId, String paymentInfo) {
        // Generate a QR code for the payment
        return new QRCode(orderId, "payment_qr_code.png");
    }

    // Other methods
}