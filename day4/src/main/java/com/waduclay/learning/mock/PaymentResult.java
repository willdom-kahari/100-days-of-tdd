package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class PaymentResult {
    private boolean success;
    private String errorMessage;

    public static PaymentResult successful() {
        PaymentResult paymentResult = new PaymentResult();
        paymentResult.success = true;
        return paymentResult;
    }

    public static PaymentResult failed(String insufficientFunds) {
        PaymentResult paymentResult = new PaymentResult();
        paymentResult.success = false;
        paymentResult.errorMessage = insufficientFunds;
        return paymentResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
