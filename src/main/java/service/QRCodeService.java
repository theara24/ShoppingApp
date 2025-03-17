package service;

import dao.PaymentDAO;
import dao.QRCodeDAO;
import model.Payment;
import model.QRCode;
import utils.QRCodeGenerator;

import java.io.IOException;
import java.sql.SQLException;
import com.google.zxing.WriterException;

public class QRCodeService {
    private QRCodeDAO qrCodeDAO;
    private PaymentDAO paymentDAO;

    public QRCodeService() {
        this.qrCodeDAO = new QRCodeDAO();
        this.paymentDAO = new PaymentDAO();
    }

    public String generateQRCodeForPayment(Long paymentId) throws SQLException, WriterException, IOException {
        Payment payment = paymentDAO.findById(paymentId);
        if (payment == null) {
            throw new SQLException("Payment not found.");
        }

        // Create QR code content (e.g., payment details)
        String qrContent = "Payment ID: " + payment.getId() +
                ", Amount: $" + payment.getAmount() +
                ", Method: " + payment.getPaymentMethod();
        String filePath = "src/main/resources/qrcodes/payment_" + paymentId + ".png";

        // Generate QR code
        QRCodeGenerator.generateQRCode(qrContent, filePath);

        // Save QR code metadata to database
        QRCode qrCode = new QRCode(null, paymentId, filePath);
        qrCodeDAO.save(qrCode);

        return filePath;
    }

    public QRCode getQRCodeById(Long id) throws SQLException {
        return qrCodeDAO.findById(id);
    }
}