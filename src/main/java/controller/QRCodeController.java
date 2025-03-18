package controller;

import org.json.JSONObject; // Add this import

public class QRCodeController {
    public String generateQRCode(Long paymentId, String paymentUrl) {
        JSONObject json = new JSONObject();
        json.put("paymentId", paymentId);
        json.put("url", paymentUrl);
        // Simulate QR code generation (in reality, use a library like ZXing)
        return "Generated QR code at: qrcodes/payment_" + paymentId + ".png";
    }
}