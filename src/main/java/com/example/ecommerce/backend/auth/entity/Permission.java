package com.example.ecommerce.backend.auth.entity;

import com.example.ecommerce.backend.common.entity.Auditable;
import com.example.ecommerce.backend.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Fine-grained authority used to protect business operations.
 *
 * <p>Permissions are mapped directly to Spring Security authorities and do not
 * receive the {@code ROLE_} prefix.</p>
 *
 * @author Pial Kanti Samadder
 */
@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission extends BaseEntity implements Auditable {
    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String code;

    @Column(length = 500)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;
}
