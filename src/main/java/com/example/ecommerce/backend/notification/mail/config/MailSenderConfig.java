package com.example.ecommerce.backend.notification.mail.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

// IT reads notification.mail.* from YAML and maps it into this class automatically.
// The sender address shown to customers is kept separate from the Gmail account
// used to actually send the email — they do not have to be the same.
@ConfigurationProperties(prefix = "notification.mail")
@Getter
@Setter
public class MailSenderConfig {
    
    // The email address that appears in the "From" header.
    // In dev this is the authenticated Gmail account set via the "MAIL_USERNAME" environment variable.
    private String sender;
    
    // The display name shown next to the address in the customer's inbox.
    // Combines with sender to produce: Ecommerce Store <********@gmail.com>
    private String senderName;

}
