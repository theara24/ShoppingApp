package controller;

import model.QRCode;
import service.QRCodeService;

import java.io.IOException;
import java.sql.SQLException;
import com.google.zxing.WriterException;

public class QRCodeController {
    private QRCodeService qrCodeService;

    public QRCodeController() {
        this.qrCodeService = new QRCodeService();
    }

    public String generateQRCode(Long paymentId) {
        try {
            String filePath = qrCodeService.generateQRCodeForPayment(paymentId);
            return "QR Code generated successfully at: " + filePath;
        } catch (SQLException | WriterException | IOException e) {
            return "Error generating QR code: " + e.getMessage();
        }
    }

    public QRCode getQRCode(Long id) {
        try {
            return qrCodeService.getQRCodeById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching QR code: " + e.getMessage());
            return null;
        }
    }
}