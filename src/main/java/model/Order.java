// src/main/java/model/Order.java
package model;

public class Order {
    private int id;
    private int userId;
    private double totalAmount;

    // Constructor
    public Order(int id, int userId, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    // Constructor without id
    public Order(int userId, double totalAmount) {
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}