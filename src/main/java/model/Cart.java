// src/main/java/model/Cart.java
package model;

public class Cart {
    private int userId;
    private int productId;
    private int quantity;
    private String name;
    private double price;

    // Constructor
    public Cart(int userId, int productId, int quantity, String name, double price) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}