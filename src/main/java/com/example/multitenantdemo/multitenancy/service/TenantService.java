package com.example.multitenantdemo.multitenancy.service;

import com.example.multitenantdemo.multitenancy.dto.TenantDetails;

import java.util.List;

public interface TenantService {

    List<TenantDetails> loadAllTenants();
    TenantDetails getTenantByIdentifier(String identifier);

}
