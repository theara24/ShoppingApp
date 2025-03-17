package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

public class QRCodeUtils {

    // Generate QR code image
    public static void generateQRCode(String paymentInfo, String filePath) {
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(paymentInfo, BarcodeFormat.QR_CODE, 300, 300, hints);
            Path path = FileSystems.getDefault().getPath(filePath);

            File qrCodeFile = new File(filePath);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path); // Save the QR code image
            System.out.println("QR Code generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
