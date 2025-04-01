package com.example.multitenantdemo.multitenancy.service;

import com.example.multitenantdemo.multitenancy.dto.TenantDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@ConfigurationProperties(prefix = "multitenancy")
public record TenantDetailsProperties(List<TenantDetails> tenants) { }

