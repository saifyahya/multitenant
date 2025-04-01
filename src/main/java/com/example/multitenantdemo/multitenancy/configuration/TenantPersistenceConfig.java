package com.example.multitenantdemo.multitenancy.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = { "com.example.multitenantdemo.shoppingapp.service" },
        entityManagerFactoryRef = "tenantEntityManagerFactory", 
        transactionManagerRef = "tenantTransactionManager"
)
@EnableConfigurationProperties(JpaProperties.class)
public class TenantPersistenceConfig {

    @Qualifier("dynamicDataSourceConnectionProvider")
    @Autowired
    private MultiTenantConnectionProvider multiTenantConnectionProvider;
    @Autowired
    private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

    private final ConfigurableListableBeanFactory beanFactory;
    private final JpaProperties jpaProperties;

    @Autowired
    public TenantPersistenceConfig(
            ConfigurableListableBeanFactory beanFactory,
            JpaProperties jpaProperties
                    ) {
        this.beanFactory = beanFactory;
        this.jpaProperties = jpaProperties;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setPersistenceUnitName("tenant-persistence-unit");
        emfBean.setPackagesToScan("com.example.multitenantdemo.shoppingapp.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfBean.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>(this.jpaProperties.getProperties());
//        properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        properties.put(AvailableSettings.IMPLICIT_NAMING_STRATEGY, "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
//        properties.put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(this.beanFactory));
       // properties.put(AvailableSettings.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this.multiTenantConnectionProvider);
        properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this.currentTenantIdentifierResolver);
        emfBean.setJpaPropertyMap(properties);

        return emfBean;
    }

    @Primary
    @Bean
    public JpaTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager tenantTransactionManager = new JpaTransactionManager();
        tenantTransactionManager.setEntityManagerFactory(emf);
        return tenantTransactionManager;
    }
}
