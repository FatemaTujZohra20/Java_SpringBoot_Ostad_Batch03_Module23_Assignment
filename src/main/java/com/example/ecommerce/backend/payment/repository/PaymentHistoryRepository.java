package com.example.ecommerce.backend.payment.repository;

import com.example.ecommerce.backend.payment.entity.PaymentHistory;
import com.example.ecommerce.backend.payment.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for persisting and retrieving payment history records.
 *
 * <p>Payment history is keyed by Stripe checkout session identifiers for
 * status callback handling and by order/status for locating the latest active
 * payment attempt.</p>
 *
 * @author Pial Kanti Samadder
 */
@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    /**
     * Finds a payment attempt by its Stripe Checkout Session identifier.
     *
     * @param sessionId Stripe Checkout Session identifier
     * @return matching payment history when present
     */
    Optional<PaymentHistory> findBySessionId(String sessionId);

    /**
     * Finds the most recent payment attempt for an order with the given status.
     *
     * @param orderId order identifier
     * @param status payment status
     * @return latest matching payment history when present
     */
    Optional<PaymentHistory> findTopByOrderIdAndStatusOrderByCreatedAtDesc(Long orderId, PaymentStatus status);
}
