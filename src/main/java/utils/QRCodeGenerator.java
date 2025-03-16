package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import model.QRCode;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    // Generate a QR code for the order
    public static QRCode generateQRCode(int orderId, String data) {
        try {
            // Create the QR code data (order info, payment info, etc.)
            String qrData = data + ":" + orderId;

            // Generate QR code
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            MultiFormatWriter qrCodeWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

            // Save the QR code as a file
            Path filePath = FileSystems.getDefault().getPath("src/main/resources/qrcodes/" + "QRCode_" + orderId + ".png");
            File file = new File(filePath.toString());
            com.google.zxing.client.j2se.MatrixToImageWriter.writeToFile(bitMatrix, "PNG", file);

            // Return QR code object
            return new QRCode(orderId, filePath.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
