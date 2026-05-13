package com.example.ecommerce.backend.auth.repository;

import com.example.ecommerce.backend.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for permission persistence and lookup operations.
 *
 * @author Pial Kanti Samadder
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByCode(String code);

    Optional<Permission> findByCode(String code);
}
