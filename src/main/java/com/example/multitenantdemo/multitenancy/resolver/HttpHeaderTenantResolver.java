package com.example.multitenantdemo.multitenancy.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpHeaderTenantResolver implements TenantResolver<HttpServletRequest> {

    public final String TENANT_HEADER = "X-Tenant";

    @Override
    public String resolveTenant(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(TENANT_HEADER);
    }
}
