package com.example.multitenantdemo.multitenancy.filter;

import com.example.multitenantdemo.multitenancy.context.TenantContext;
import com.example.multitenantdemo.multitenancy.dto.TenantDetails;
import com.example.multitenantdemo.multitenancy.resolver.HttpHeaderTenantResolver;
import com.example.multitenantdemo.multitenancy.service.PropertiesTenantDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantContextFilter extends OncePerRequestFilter {

    private final PropertiesTenantDetailsService propertiesTenantDetailsService;
    private final HttpHeaderTenantResolver httpHeaderTenantResolver;

    public TenantContextFilter(PropertiesTenantDetailsService propertiesTenantDetailsService, HttpHeaderTenantResolver httpHeaderTenantResolver) {
        this.propertiesTenantDetailsService = propertiesTenantDetailsService;
        this.httpHeaderTenantResolver = httpHeaderTenantResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tenantIdentifier = httpHeaderTenantResolver.resolveTenant(request);
        if (tenantIdentifier == null) {
            throw new IllegalStateException("Tenant identifier not found");
        }

        System.out.println("hiii filter tenant " +tenantIdentifier);
        if(!tenantIdentifier.isEmpty() && isValidTenant(tenantIdentifier)){
            TenantContext.setCurrentTenant(tenantIdentifier);
            System.out.println("filter set tenant: " + tenantIdentifier);
        }else{
            TenantContext.setCurrentTenant("DEFAULT");
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            clearTenant();
        }

    }

    private boolean isValidTenant(String tenantIdentifier) {
//        TenantDetails tenantDetails = propertiesTenantDetailsService.getTenantByIdentifier(tenantIdentifier);
//       return tenantDetails != null && tenantDetails.enabled();
        return true;
    }

    private void clearTenant() {
        TenantContext.clearCurrentTenant();
    }
}
