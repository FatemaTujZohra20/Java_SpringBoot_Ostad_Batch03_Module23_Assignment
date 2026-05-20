package com.example.ecommerce.backend.notification.mail.service.impl;

import com.example.ecommerce.backend.notification.mail.dto.MailOrderItemDto;
import com.example.ecommerce.backend.notification.mail.dto.PaymentConfirmationMailDto;
import com.example.ecommerce.backend.notification.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final String fromEmail;
    
    public MailServiceImpl(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}")
            String fromEmail) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
    }
    
    public void sendPaymentConfirmation(PaymentConfirmationMailDto dto) {
        log.info("Sending payment confirmation email to={}", dto.toEmail());
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.toEmail());
        message.setFrom(fromEmail);
        message.setSubject("Payment Confirmed - Order #" + dto.orderNumber());
        message.setText(buildEmailBody(dto));
        
        javaMailSender.send(message);
        log.info("Payment confirmation email sent to={}", dto.toEmail());
    }
    
    private String buildEmailBody(PaymentConfirmationMailDto dto) {
        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(dto.userName()).append(",\n\n");
        body.append("Your payment has been successfully received.\n\n");
        body.append("Order Number : ").append(dto.orderNumber()).append("\n");
        body.append("Order Date   : ").append(dto.orderDate()).append("\n");
        body.append("Payment Date : ").append(dto.paymentDate()).append("\n\n");
        body.append("Items Ordered:\n");
        body.append("--------------------------------------------------\n");
        for (MailOrderItemDto item : dto.items()) {
            body.append("  ").append(item.productName())
                    .append("  x").append(item.quantity())
                    .append("  @ ").append(item.unitPrice())
                    .append("  = ").append(item.totalPrice()).append("\n");
        }
        body.append("--------------------------------------------------\n");
        body.append("Total Amount : ").append(dto.totalAmount()).append("\n\n");
        body.append("Thank you for shopping with us!\n");
        return body.toString();
    }
}
