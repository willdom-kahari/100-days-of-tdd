package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class OrderResult {
    public boolean success;
    private String orderId;
    private String message;
    public static OrderResult failed(String itemNotAvailable) {
        OrderResult orderResult = new OrderResult();
        orderResult.success = false;
        orderResult.message = itemNotAvailable;
        return orderResult;
    }

    public static OrderResult success(String orderId) {
        OrderResult orderResult = new OrderResult();
        orderResult.success = true;
        orderResult.orderId = orderId;
        return orderResult;
    }

    public boolean isSuccess() {
        return success;
    }
}
