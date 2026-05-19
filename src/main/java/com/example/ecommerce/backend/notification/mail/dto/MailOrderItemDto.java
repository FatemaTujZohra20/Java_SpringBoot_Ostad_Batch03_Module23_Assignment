package com.example.ecommerce.backend.notification.mail.dto;

public record MailOrderItemDto(
        String productName,
        Integer quantity,
        Double unitPrice,
        Double totalPrice
) {
}
