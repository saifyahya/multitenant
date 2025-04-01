package com.example.multitenantdemo;

import com.example.multitenantdemo.multitenancy.entity.Tenant;
import com.example.multitenantdemo.multitenancy.repository.TenantRepository;
import com.example.multitenantdemo.multitenancy.service.TenantDetailsProperties;
import com.example.multitenantdemo.shoppingapp.service.UsersRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TenantDetailsProperties.class)
@ConfigurationPropertiesScan
public class MultitenantDemoApplication implements CommandLineRunner {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UsersRepository usersRepository;

    public static void main(String[] args) {
        SpringApplication.run(MultitenantDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Tenant tenant = new Tenant();
//        tenant.setDb("FirstTenantDB");
//        tenant.setTenantId("tenant1");
//        tenant.setUrl("jdbc:postgresql://localhost:5432/FirstTenantDB");
//        tenant.setPassword("password");
//        Tenant tenant2 = new Tenant();
//        tenant2.setUrl("jdbc:postgresql://localhost:5432/SecondTenantDB");
//        tenant2.setDb("SecondTenantDB");
//        tenant2.setTenantId("tenant2");
//        tenant2.setPassword("password");
//        tenantRepository.save(tenant);
//        tenantRepository.save(tenant2);

   //     System.out.println(usersRepository.findAll());

    }
}
