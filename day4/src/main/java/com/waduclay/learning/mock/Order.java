package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Order {
    private final int quantity;
    private final String productId;
    private final double totalAmount;
    private final String customerEmail;
    private  String orderId;

    public Order(int quantity, String productId, double totalAmount, String customerEmail) {

        this.quantity = quantity;
        this.productId = productId;
        this.totalAmount = totalAmount;
        this.customerEmail = customerEmail;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getOrderId() {
        return orderId;
    }
}
