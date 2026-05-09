package com.example.ecommerce.backend.auth.dto.response;

import java.time.LocalDateTime;

/**
 * Response payload containing safe user account details.
 *
 * @author Pial Kanti Samadder
 */
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String phoneNumber,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long createdBy,
        Long modifiedBy
) {
}
