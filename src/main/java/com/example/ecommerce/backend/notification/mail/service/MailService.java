package com.example.ecommerce.backend.notification.mail.service;

import com.example.ecommerce.backend.notification.mail.dto.PaymentConfirmationMailDto;

public interface MailService {
    void sendPaymentConfirmation(PaymentConfirmationMailDto dto);
}
