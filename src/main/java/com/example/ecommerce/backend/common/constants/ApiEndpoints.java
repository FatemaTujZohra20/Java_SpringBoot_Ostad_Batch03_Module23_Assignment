package com.example.ecommerce.backend.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndpoints {
    private static final String API_VERSION = "/api/v1";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Category {
        public static final String BASE_CATEGORY = API_VERSION + "/categories";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Product {
        public static final String BASE_PRODUCT = API_VERSION + "/products";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Inventory {
        public static final String BASE_INVENTORY = API_VERSION + "/inventory";
    }
}
