package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class OrderService {
    private final IPaymentGateway paymentGateway;
    private final IInventoryService inventoryService;
    private final IEmailService emailService;

    public OrderService(IPaymentGateway paymentGateway, IInventoryService inventoryService, IEmailService emailService) {
        this.paymentGateway = paymentGateway;
        this.inventoryService = inventoryService;
        this.emailService = emailService;
    }

    public OrderResult processOrder(Order order, String cardNumber) {
        // 1. Reserve inventory
        if (!inventoryService.reserveItem(order.getProductId(), order.getQuantity())){
            return OrderResult.failed("Item not available");
        }

        try {
            // 2. Process payment
            PaymentResult paymentResult = paymentGateway.processPayment(order.getTotalAmount(), cardNumber);
            if (!paymentResult.isSuccess()){
                // 3. If payment fails, release inventory and notify customer
                inventoryService.releaseItem(order.getProductId(), order.getQuantity());
                emailService.sendPaymentFailure(order.getCustomerEmail(), paymentResult.getErrorMessage());
                return OrderResult.failed("Payment failed: " + paymentResult.getErrorMessage());
            }

            // 4. If successful, send confirmation
            emailService.sendOrderConfirmation(order);
            return OrderResult.success(order.getOrderId());
        } catch (Exception e) {
            // 5. Handle any exceptions
            inventoryService.releaseItem(order.getProductId(), order.getQuantity());
            throw e;
        }
    }
}
