package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface IPaymentGateway {
    PaymentResult processPayment(double amount, String cardNumber);
    void isAvailable();
}
