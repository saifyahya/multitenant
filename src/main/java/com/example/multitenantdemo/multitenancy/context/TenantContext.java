package com.example.multitenantdemo.multitenancy.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class TenantContext {
    private static final Logger log = LoggerFactory.getLogger(TenantContext.class);
    public static final String DEFAULT_TENANT = "DEFAULT";
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    private TenantContext() {
    }

    public static String getCurrentTenantIdentifier() {
        String tenant = currentTenant.get();
        if (tenant ==null || tenant.isEmpty()) {
         //throw new IllegalStateException("Tenant not set");
            return DEFAULT_TENANT;
        }
        return tenant;
    }

    public static void setCurrentTenant(String tenant) {
        log.trace("Setting current tenant to: {}", tenant);
        currentTenant.set(tenant);
    }

    public static void clearCurrentTenant() {
        log.trace("Clearing current tenant");
        currentTenant.remove();
    }
}
