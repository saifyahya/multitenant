package com.example.multitenantdemo.multitenancy.service;
import com.example.multitenantdemo.multitenancy.dto.TenantDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesTenantDetailsService implements TenantService {

    private final TenantDetailsProperties tenantDetailsProperties;

    public PropertiesTenantDetailsService(TenantDetailsProperties tenantDetailsProperties) {
        this.tenantDetailsProperties = tenantDetailsProperties;
    }

    @Override
    public List<TenantDetails> loadAllTenants() {
        return tenantDetailsProperties.tenants();
    }

    @Override
    public TenantDetails getTenantByIdentifier(String identifier) {
        return tenantDetailsProperties.tenants()
                .stream()
                .filter(td -> td.identifier().equals(identifier))
                .findFirst()
                .orElse(null);
    }
}
