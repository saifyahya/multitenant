//package com.example.multitenantdemo.multitenancy.data;
//
//import org.hibernate.dialect.Dialect;
//import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
//import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
//import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
//import org.hibernate.engine.jdbc.connections.spi.DatabaseConnectionInfo;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//
//public class MultiTenantConnectionProvider
//        extends AbstractMultiTenantConnectionProvider  {
//
//    public MultiTenantConnectionProvider(){
//        super();
//        connectionProviderMap.put("DEFAULT", new DatasourceConnectionProviderImpl());
//    }
//
//    public static final MultiTenantConnectionProvider INSTANCE =
//            new MultiTenantConnectionProvider();
//
//    private final Map<String, ConnectionProvider> connectionProviderMap =
//            new HashMap<>();
//
//    Map<String, ConnectionProvider> getConnectionProviderMap() {
//        return connectionProviderMap;
//    }
//
//    @Override
//    protected ConnectionProvider getAnyConnectionProvider() {
//        return connectionProviderMap.get(
//                "DEFAULT"
//        );
//    }
//
//    @Override
//    protected ConnectionProvider selectConnectionProvider(Object o) {
//        String tenantIdentifier = (String) o;
//        return connectionProviderMap.get(
//                tenantIdentifier
//        );
//    }
////
////    private void addTenantConnectionProvider(
////            String tenantId,
////            DataSource tenantDataSource,
////            Properties properties) {
////
////        DatasourceConnectionProviderImpl connectionProvider =
////                new DatasourceConnectionProviderImpl();
////        connectionProvider.setDataSource(tenantDataSource);
////        connectionProvider.configure(properties);
////
////        MultiTenantConnectionProvider.INSTANCE
////                .getConnectionProviderMap()
////                .put(
////                        tenantId,
////                        connectionProvider
////                );
////    }
//
//
//    @Override
//    public DatabaseConnectionInfo getDatabaseConnectionInfo(Dialect dialect) {
//        return super.getDatabaseConnectionInfo(dialect);
//    }
//
//    public Connection getConnection(String tenantIdentifier) throws SQLException {
//        Connection connection = selectConnectionProvider(tenantIdentifier).getConnection();
//        connection.createStatement().execute(String.format("SET search_path TO %s;", tenantIdentifier));
//        return connection;
//    }
//}