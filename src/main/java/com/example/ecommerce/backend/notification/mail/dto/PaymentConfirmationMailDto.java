package com.example.ecommerce.backend.notification.mail.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PaymentConfirmationMailDto(
        String toEmail,
        String userName,
        UUID orderNumber,
        LocalDateTime orderDate,
        LocalDateTime paymentDate,
        Double totalAmount,
        List<MailOrderItemDto> items
) {
}
