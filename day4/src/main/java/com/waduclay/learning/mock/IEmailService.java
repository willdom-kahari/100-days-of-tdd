package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface IEmailService {
    void sendOrderConfirmation(Order order);
    void sendPaymentFailure(String email, String message);
}
