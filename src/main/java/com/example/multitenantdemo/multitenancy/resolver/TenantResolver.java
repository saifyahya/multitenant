package com.example.multitenantdemo.multitenancy.resolver;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface TenantResolver<T> {

    String resolveTenant(T source);

}
