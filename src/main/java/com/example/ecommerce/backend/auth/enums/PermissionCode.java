package com.example.ecommerce.backend.auth.enums;

/**
 * Predefined permission codes used for operation-level authorization.
 *
 * <p>Permission codes are exposed as plain authorities and never receive the
 * {@code ROLE_} prefix.</p>
 *
 * @author Pial Kanti Samadder
 */
public enum PermissionCode {
    PRODUCT_READ,
    PRODUCT_CREATE,
    PRODUCT_UPDATE,
    PRODUCT_DELETE,
    CATEGORY_READ,
    CATEGORY_CREATE,
    CATEGORY_UPDATE,
    CATEGORY_DELETE,
    INVENTORY_READ,
    INVENTORY_MANAGE,
    ORDER_READ,
    ORDER_UPDATE,
    ORDER_CANCEL,
    USER_READ,
    USER_MANAGE,
    ROLE_READ,
    ROLE_CREATE,
    ROLE_UPDATE,
    ROLE_DELETE,
    PERMISSION_READ,
    PERMISSION_CREATE,
    PERMISSION_UPDATE,
    PERMISSION_DELETE,
    ROLE_ASSIGN,
    PERMISSION_ASSIGN
}
