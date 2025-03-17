package service;

import java.io.File;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeService {

    public boolean generateQRCode(int orderId, String data) {
        String filePath = "qrcodes/order_" + orderId + ".png";
        int width = 300;
        int height = 300;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            File qrFile = new File(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrFile.toPath());
            return true;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}