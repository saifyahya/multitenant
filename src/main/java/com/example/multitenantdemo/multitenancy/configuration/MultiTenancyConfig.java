//package com.example.multitenantdemo.multitenancy.configuration;
//
//
//import com.example.multitenantdemo.multitenancy.data.DataTenantIdentifierResolver;
//import org.hibernate.cfg.AvailableSettings;
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//public class MultiTenancyConfig {
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(false); // Disable automatic DDL generation
//        vendorAdapter.setShowSql(true);
//        return vendorAdapter;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
//                                                                       MultiTenantConnectionProvider multiTenantConnectionProvider) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
//        factoryBean.setPackagesToScan("com.example.multitenantdemo"); // Set your package name
//
//        Map<String, Object> jpaProperties = new HashMap<>();
//        //jpaProperties.put(AvailableSettings.MULTI_TENANT, "SCHEMA");
//        jpaProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
//        jpaProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, DataTenantIdentifierResolver.class.getName());
//
//        factoryBean.setJpaPropertyMap(jpaProperties);
//        return factoryBean;
//    }
//
//    @Bean
//    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
//        return new com.example.multitenantdemo.multitenancy.data.MultiTenantConnectionProvider();
//    }
//}
