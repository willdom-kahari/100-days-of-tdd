package com.waduclay.learning.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    IEmailService emailService;

    @Mock
    IPaymentGateway paymentGateway;

    @Mock
    IInventoryService inventoryService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrderSuccessful(){

        // Arrange
        Order order = new Order(2, "product123", 100.0, "customerEmail@example.com");
        PaymentResult paymentResult = PaymentResult.successful();

        // Set up mock expectations
        when(inventoryService.reserveItem(order.getProductId(), order.getQuantity())).thenReturn(true);
        when(paymentGateway.processPayment(order.getTotalAmount(), "4111111111111111")).thenReturn(paymentResult);

        // Act
        OrderResult orderResult = orderService.processOrder(order, "4111111111111111");

        // Assert - Verify INTERACTIONS
        assertTrue(orderResult.isSuccess());

        // Verify the exact interactions we expect
        verify(inventoryService).reserveItem("product123", 2);
        verify(paymentGateway).processPayment(100.0, "4111111111111111");
        verify(emailService).sendOrderConfirmation(order);

        // Verify these methods were NEVER called
        verify(inventoryService, never()).releaseItem(anyString(), anyInt());
        verify(emailService, never()).sendPaymentFailure(anyString(), anyString());
    }

    @Test
    void testPlaceOrder_PaymentFails() {
        // Arrange
        Order order = new Order(2, "product123", 50.0, "customer@example.com");
        PaymentResult failedResult = PaymentResult.failed("Insufficient funds");

        when(inventoryService.reserveItem(order.getProductId(), order.getQuantity())).thenReturn(true);
        when(paymentGateway.processPayment(50.0, "invalid-card"))
                .thenReturn(failedResult);

        // Act
        OrderResult result = orderService.processOrder(order, "invalid-card");

        // Assert
        assertFalse(result.isSuccess());

        // Verify the expected interaction sequence
        verify(inventoryService).reserveItem("product123", 2);
        verify(paymentGateway).processPayment(50.0, "invalid-card");

        // Verify that inventory was released due to payment failure
        verify(inventoryService).releaseItem("product123", 2);

        // Verify that payment failure email was sent
        verify(emailService).sendPaymentFailure("customer@example.com", "Insufficient funds");

        // Verify success email was NOT sent
        verify(emailService, never()).sendOrderConfirmation(any());
    }

    @Test
    void testPlaceOrder_MethodCallOrder() {
        when(inventoryService.reserveItem(anyString(), anyInt())).thenReturn(true);
        when(paymentGateway.processPayment(anyDouble(), anyString())).thenReturn(PaymentResult.successful());
        Order order = new Order(2, "product123", 100.0, "customerEmail@example.com");

        // Act
        orderService.processOrder(order, "card123");

        // Verify the exact order of method calls
        InOrder inOrder = inOrder(inventoryService, paymentGateway, emailService);

        inOrder.verify(inventoryService).reserveItem(anyString(), anyInt());
        inOrder.verify(paymentGateway).processPayment(anyDouble(), anyString());
        inOrder.verify(emailService).sendOrderConfirmation(any());
    }
}
