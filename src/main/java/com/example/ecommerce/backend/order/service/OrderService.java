package com.example.ecommerce.backend.order.service;

import com.example.ecommerce.backend.order.dto.request.CreateOrderRequest;
import com.example.ecommerce.backend.order.dto.response.OrderResponse;

/**
 * Service interface for checkout and order lifecycle operations.
 *
 * <p>Order operations coordinate cart contents, product snapshots, inventory
 * reservations, and order persistence. Payment handling is intentionally
 * outside this service.</p>
 *
 * @author Pial Kanti Samadder
 */
public interface OrderService {
    /**
     * Converts a cart into a confirmed order and reserves inventory.
     *
     * @param userId owner user identifier
     * @param request checkout payload containing cart identifier
     * @return confirmed order response
     */
    OrderResponse placeOrder(Long userId, CreateOrderRequest request);

    /**
     * Cancels a confirmed order and releases reserved inventory.
     *
     * @param userId owner user identifier
     * @param orderId order identifier
     * @return cancelled order response
     */
    OrderResponse cancelOrder(Long userId, Long orderId);

    /**
     * Retrieves an order by identifier.
     *
     * @param userId owner user identifier
     * @param orderId order identifier
     * @return matching order response
     */
    OrderResponse getOrder(Long userId, Long orderId);
}
