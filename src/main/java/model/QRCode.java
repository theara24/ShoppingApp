package model;

public class QRCode {
    private Long id;
    private Long paymentId; // References the Payment (could also link to Order, etc.)
    private String filePath; // Path to the generated QR code image

    public QRCode() {}

    public QRCode(Long id, Long paymentId, String filePath) {
        this.id = id;
        this.paymentId = paymentId;
        this.filePath = filePath;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}