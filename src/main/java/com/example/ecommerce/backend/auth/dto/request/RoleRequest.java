package com.example.ecommerce.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating or updating a security role.
 *
 * @author Pial Kanti Samadder
 */
public record RoleRequest(
        @NotBlank(message = "Role name is required")
        @Size(max = 120, message = "Role name cannot exceed 120 characters")
        String name,

        @NotBlank(message = "Role code is required")
        @Size(max = 80, message = "Role code cannot exceed 80 characters")
        @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "Role code must be uppercase letters, numbers, or underscores")
        String code,

        @Size(max = 500, message = "Role description cannot exceed 500 characters")
        String description
) {
}
