package com.example.ecommerce.backend.auth.mapper;

import com.example.ecommerce.backend.auth.dto.request.RegisterRequest;
import com.example.ecommerce.backend.auth.dto.response.UserResponse;
import com.example.ecommerce.backend.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for user request, entity, and response models.
 *
 * @author Pial Kanti Samadder
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    /**
     * Converts a registration request to a user entity.
     *
     * @param request registration payload
     * @return user entity containing simple request fields
     */
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterRequest request);

    /**
     * Converts a user entity to its safe API response representation.
     *
     * @param user user entity
     * @return safe user response
     */
    UserResponse toResponse(User user);
}
