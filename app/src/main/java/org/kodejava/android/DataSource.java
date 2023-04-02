package org.kodejava.android;

import com.zaxxer.hikari.HikariDataSource;
import uk.time2dine.config.AppConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/time2dine_spaces";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    private static HikariDataSource dataSource;

    static{
        try {

            dataSource = new HikariDataSource();
            dataSource.setDriverClassName(Database.getByProviderName(AppConfig.getDatabaseProviderName()).getDriverClass());

            dataSource.setJdbcUrl(AppConfig.getConnectString());
            dataSource.setUsername(AppConfig.getDatabaseUser());
            dataSource.setPassword(AppConfig.getDatabasePassword());

            dataSource.setMinimumIdle(10);
            dataSource.setMaximumPoolSize(250);//The maximum number of connections, idle or busy, that can be present in the pool.
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(3);

        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }


    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
