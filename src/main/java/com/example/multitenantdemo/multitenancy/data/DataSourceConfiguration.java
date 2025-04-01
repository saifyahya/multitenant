package com.example.multitenantdemo.multitenancy.data;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("multitenancy.master.datasource")
    public DataSourceProperties masterDataSourceProperties() {
 return new DataSourceProperties();
    }

    @Bean
    public DataSource masterDataSource() {
        return  masterDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
