package com.example.multitenantdemo.multitenancy.dto;
public record TenantDetails(
        String identifier,
        boolean enabled,
        String schema,
        String issuer
) {}
