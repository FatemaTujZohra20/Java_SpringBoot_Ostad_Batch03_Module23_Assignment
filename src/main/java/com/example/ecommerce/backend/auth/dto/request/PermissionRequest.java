package com.example.ecommerce.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating or updating an operation permission.
 *
 * @author Pial Kanti Samadder
 */
public record PermissionRequest(
        @NotBlank(message = "Permission name is required")
        @Size(max = 120, message = "Permission name cannot exceed 120 characters")
        String name,

        @NotBlank(message = "Permission code is required")
        @Size(max = 100, message = "Permission code cannot exceed 100 characters")
        @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "Permission code must be uppercase letters, numbers, or underscores")
        String code,

        @Size(max = 500, message = "Permission description cannot exceed 500 characters")
        String description
) {
}
