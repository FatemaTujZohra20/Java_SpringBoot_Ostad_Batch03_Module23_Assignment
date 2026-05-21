package com.example.ecommerce.backend.auth.constants;

import com.example.ecommerce.backend.common.constants.ApiEndpoints;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Public endpoint registry used by Spring Security configuration.
 *
 * @author Pial Kanti Samadder
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PublicUrls {
    /**
     * Endpoints that do not require authentication.
     */
    public static final String[] PUBLIC_ENDPOINTS = {
            ApiEndpoints.Auth.BASE_AUTH + "/register",
            ApiEndpoints.Auth.BASE_AUTH + "/login",
            ApiEndpoints.Auth.BASE_AUTH + "/refresh",
            
            // Stripe redirects the customer's browser to these endpoints after checkout completes.
            // The browser carries no JWT token on that redirect — it is a plain URL navigation.
            // Without permitting these here, Spring Security returns 401 before PaymentController
            // runs and the payment is never recorded in the database.
            ApiEndpoints.Payment.BASE_PAYMENT + "/success",
            ApiEndpoints.Payment.BASE_PAYMENT + "/failed",
            
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}
