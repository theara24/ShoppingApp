package model;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int orderId;
    private double amount;
    private String status;
    private LocalDateTime paymentDate;

    // Existing constructors
    public Payment() {}

    public Payment(int orderId, double amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Payment(int orderId, double amount, String status, String paymentDate) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = LocalDateTime.parse(paymentDate);
    }

    // New constructor
    public Payment(int paymentId, int orderId, double amount, String status, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // Getters and setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}