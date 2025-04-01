//package com.example.multitenantdemo.multitenancy.data;
//
//import com.example.multitenantdemo.multitenancy.service.TenantService;
//import org.hibernate.HibernateException;
//import org.hibernate.cfg.AvailableSettings;
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Map;
//
//@Component
//public class ConnectionProvider implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {
//
//  	private final DataSource dataSource;
//    private final TenantService tenantDetailsService;
//
//	ConnectionProvider(DataSource dataSource, TenantService tenantDetailsService) {
//		this.dataSource = dataSource;
//        this.tenantDetailsService = tenantDetailsService;
//    }
//
//	@Override
//	public Connection getAnyConnection() throws SQLException {
//		System.out.println("get any connection");
//		return getConnection("DEFAULT");
//	}
//
//	@Override
//	public void releaseAnyConnection(Connection connection) throws SQLException {
//		System.out.println("release any connection");
//		connection.close();
//	}
//
//	@Override
//	public Connection getConnection(String tenantIdentifier) throws SQLException {
//        var tenantDetails = tenantDetailsService.getTenantByIdentifier(tenantIdentifier);
//        var schema = tenantDetails != null ? tenantDetails.schema() : "DEFAULT";
//		System.out.println(schema);
//		Connection connection = dataSource.getConnection();
//	connection.setSchema(schema);
//		System.out.println("get connection with schema "+connection.getSchema());
//		try {
//			connection.createStatement().execute("SET search_path TO " + schema);
//		} catch (SQLException e) {
//			throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]",
//					e);
//		}
//		return connection;
//	}
//
//	@Override
//	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
//    	connection.setSchema("DEFAULT");
//		connection.close();
//
//  	}
//
//	@Override
//	public boolean supportsAggressiveRelease() {
//		return true;
//	}
//
//	@Override
//	public boolean isUnwrappableAs(Class<?> unwrapType) {
//		return false;
//	}
//
//	@Override
//	public <T> T unwrap(Class<T> unwrapType) {
//		throw new UnsupportedOperationException("Unimplemented method 'unwrap'.");
//	}
//
//	@Override
//	public void customize(Map<String, Object> hibernateProperties) {
//		hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
//	}
//
//}
