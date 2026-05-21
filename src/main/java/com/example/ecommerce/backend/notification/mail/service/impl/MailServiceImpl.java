package com.example.ecommerce.backend.notification.mail.service.impl;

import com.example.ecommerce.backend.notification.mail.config.MailSenderConfig;
import com.example.ecommerce.backend.notification.mail.dto.MailOrderItemDto;
import com.example.ecommerce.backend.notification.mail.dto.PaymentConfirmationMailDto;
import com.example.ecommerce.backend.notification.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    
    // For the Assignment 23
    // Human-readable timestamp — customers should not see raw ISO-8601 with nanoseconds
    // Customers should see the understandable format always.
    private static final DateTimeFormatter EMAIL_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    
    private final JavaMailSender javaMailSender;
    private final MailSenderConfig mailSenderConfig;
    
    
    @Override
    public void sendPaymentConfirmation(PaymentConfirmationMailDto dto) {
        if (!isDeliverable(dto.toEmail())) {
            log.warn("Payment confirmation skipped — recipient address is missing. orderRef={}", toOrderRef(dto.orderNumber()));
            return;
        }
        
        String orderRef = toOrderRef(dto.orderNumber());
        log.info("Sending payment confirmation to={}, orderRef={}", dto.toEmail(), orderRef);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.toEmail());
        message.setSubject("Payment Received — Your Order " + orderRef + " is Confirmed");
        message.setText(buildEmailBody(dto, orderRef));
        
        // I have learned better professional approach for the email's "From" header section which follows -
        // the "Display Name <example@gmail.com>" format in the customer's inbox.
        // So, now, it produces: Ecommerce Store <*********@gmail.com> and a "Reply-To" section.
        Optional.ofNullable(mailSenderConfig.getSender())
                .filter(s -> !s.isBlank())
                .ifPresent(sender -> {
                    String senderName = mailSenderConfig.getSenderName();
                    String formattedFrom = (senderName != null && !senderName.isBlank())
                            ? senderName + " <" + sender + ">"
                            : sender;
                    message.setFrom(formattedFrom);
                    message.setReplyTo(sender);
                });
        
        javaMailSender.send(message);
        log.info("Payment confirmation email delivered to={}, orderRef={}", dto.toEmail(), orderRef);
    }
    
    private String buildEmailBody(PaymentConfirmationMailDto dto, String orderRef) {
        // If by any mistake, our customers skipped the first name at Registration, here we fall back gracefully
        String greeting = (dto.userName() != null && !dto.userName().isBlank())
                ? dto.userName()
                : "Valued Customer";
        
        // If the items list get larger, 512 initial capacity — avoids internal resize for like a typical order with 3–5 items.
        StringBuilder body = new StringBuilder(512);
        
        body.append("Dear ").append(greeting).append(",\n\n");
        body.append("Thank you for your purchase! We have successfully received your payment.\n\n");
        
        body.append("================================================\n");
        body.append("  ORDER SUMMARY\n");
        body.append("================================================\n");
        body.append("  Order Ref    : ").append(orderRef).append("\n");
        body.append("  Order Date   : ").append(dto.orderDate().format(EMAIL_DATE_FORMAT)).append("\n");
        body.append("  Payment Date : ").append(dto.paymentDate().format(EMAIL_DATE_FORMAT)).append("\n\n");
        
        body.append("  ITEMS ORDERED\n");
        body.append("  ------------------------------------------------\n");
        for (MailOrderItemDto item : dto.items()) {
            body.append("  ").append(item.productName())
                    .append("  x").append(item.quantity())
                    .append("  @ ").append(formatAmount(item.unitPrice()))
                    .append("  =  ").append(formatAmount(item.totalPrice())).append("\n");
        }
        body.append("  ------------------------------------------------\n");
        body.append("  Total Amount : ").append(formatAmount(dto.totalAmount())).append("\n");
        body.append("================================================\n\n");
        
        body.append("Your order is being processed and will be on its way shortly.\n");
        body.append("For any questions, please reach out to our support team.\n\n");
        body.append("Thank you for shopping with us!\n");
        body.append("— The Ecommerce Team\n\n");
        
        body.append("------------------------------------------------\n");
        body.append("This is an automated message. Please do not reply to this email.\n");
        body.append("This mailbox is not monitored and replies will not be read.\n");
        body.append("For support, contact: support@ecommerce.com\n");
        body.append("------------------------------------------------\n");
        
        return body.toString();
    }
    
    // We should always remember that we are making this for our customers...
    // So, we convert a full UUID to a short, customer-friendly order reference -
    // like from   "a83909d3-7602-4410-9a64-32998aa2bd25"   to  "ORD-A83909D3"
    private String toOrderRef(UUID orderNumber) {
        return "ORD-" + orderNumber.toString().substring(0, 8).toUpperCase();
    }
    
    // I learned a professional way to show the "Amount" to our customer:
    // Prices must always show two decimal places with a currency label.
    // Raw Double prints  "130.0"  for whole amounts — unacceptable in a customer-facing email.
    private String formatAmount(Double amount) {
        return String.format("BDT %.2f", amount);
    }
    
    private boolean isDeliverable(String email) {
        return email != null && !email.isBlank();
    }
    
}
