package com.example.multitenantdemo.multitenancy.data;

import com.example.multitenantdemo.multitenancy.context.TenantContext;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class DataTenantIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {
    public static final String DEFAULT_TENANT = "DEFAULT";

    @Override
    public Object resolveCurrentTenantIdentifier() {
        String tenantIdentifier = TenantContext.getCurrentTenantIdentifier();
        System.out.println("data current tenant resolver "+tenantIdentifier);
        if (!tenantIdentifier.isEmpty()) {
           return tenantIdentifier;
        }
        return DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}
