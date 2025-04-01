package com.example.multitenantdemo.multitenancy.data;

import com.example.multitenantdemo.multitenancy.context.TenantContext;
import com.example.multitenantdemo.multitenancy.entity.Tenant;
import com.example.multitenantdemo.multitenancy.repository.TenantRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j

@Component
public class DynamicDataSourceConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("masterDataSourceProperties")
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private TenantRepository masterTenantRepository;

    private LoadingCache<String, DataSource> tenantDataSources;
    @Autowired
    private DataSource masterDataSource;

    @PostConstruct
    private void createCache() {
        tenantDataSources = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .removalListener((RemovalListener<String, DataSource>) removal -> {
                    HikariDataSource ds = (HikariDataSource) removal.getValue();
                    ds.close(); // tear down properly
                    log.info("Closed datasource: {}", ds.getPoolName());
                })
                .build(new CacheLoader<String, DataSource>() {
                    public DataSource load(String key) {
                        String key2 = TenantContext.getCurrentTenantIdentifier();
                        Tenant tenant = masterTenantRepository.findByTenantId(key2)
                                .orElseThrow(() -> new RuntimeException("No such tenant: " + key2));
                        return createAndConfigureDataSource(tenant);
                    }
                });
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return masterDataSource;
    }

    @Override
    protected DataSource selectDataSource(Object o) {
        String tenantIdentifier = (String) o;
        System.out.println("dynamic data source provider: " +tenantIdentifier);
        try {
            return tenantDataSources.get(tenantIdentifier);
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to load DataSource for tenant: " + tenantIdentifier);
        }
    }

    private DataSource createAndConfigureDataSource(Tenant tenant) {
        //  String decryptedPassword = encryptionService.decrypt(tenant.getPassword(), secret, salt);

        HikariDataSource ds = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

        ds.setUsername(tenant.getUsername());
        ds.setPassword(tenant.getPassword());
        ds.setJdbcUrl(tenant.getUrl());

        ds.setPoolName(tenant.getTenantId() + "DataSource");
        log.info("Tenant DB: {}, URL: {}, Password: {}", tenant.getUsername(), tenant.getUrl(), tenant.getPassword());

        log.info("Configured datasource: {}", ds.getPoolName());
        return ds;
    }
}
