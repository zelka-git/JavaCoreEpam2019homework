package ru.epam.javacore.homework20200210.common.business.datasource;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionProvider {
    Connection getConnection() throws SQLException;
}
