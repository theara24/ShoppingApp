package model;

public class QRCode {
    private int orderId;
    private String fileName;

    public QRCode(int orderId, String fileName) {
        this.orderId = orderId;
        this.fileName = fileName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "QRCode [orderId=" + orderId + ", fileName=" + fileName + "]";
    }
}
