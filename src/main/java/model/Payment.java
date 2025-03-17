package model;

public class Payment {
    private Long id;
    private Long orderId; // References the Order
    private double amount;
    private String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL"
    private String status; // e.g., "PENDING", "COMPLETED", "FAILED"

    public Payment() {}

    public Payment(Long id, Long orderId, double amount, String paymentMethod, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}