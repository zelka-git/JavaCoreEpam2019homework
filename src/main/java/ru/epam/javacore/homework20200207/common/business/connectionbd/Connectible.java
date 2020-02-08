package ru.epam.javacore.homework20200207.common.business.connectionbd;

import java.sql.Connection;

public interface Connectible {
    Connection getConnection() throws Exception;
}
