package utils;

import java.io.File;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtils {

    public static void generateQRCode(String text, String filePath) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            File qrFile = new File(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrFile.toPath());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}