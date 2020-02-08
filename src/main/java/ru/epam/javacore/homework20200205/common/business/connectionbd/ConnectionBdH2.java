package ru.epam.javacore.homework20200205.common.business.connectionbd;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionBdH2 implements Connectible {

    final static private String url = "jdbc:h2:C:\\Anzhelika\\projects\\DataBase\\test";
    final static private String name = "root";
    final static private String password = "root";

    private static final ConnectionBdH2 INSTANCE = new ConnectionBdH2();

    private ConnectionBdH2() {

    }

    public static ConnectionBdH2 getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws Exception {
        String driverClass = "org.h2.Driver";
        Class.forName(driverClass);

//        setupDriver();
//        return DriverManager.getConnection("jdbc:apache:commons:dbcp:example");
        DataSource dataSource = setupDataSource();
        return dataSource.getConnection();
    }

    public static DataSource setupDataSource() {
        //
        // First, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(url, name, password);

        //
        // Next we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);

        //
        // Now we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        //
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        PoolingDataSource<PoolableConnection> dataSource =
                new PoolingDataSource<>(connectionPool);

        return dataSource;
    }

}
