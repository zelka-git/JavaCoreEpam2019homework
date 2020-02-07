package ru.epam.javacore.homework20200205.common.business.connectionbd;

import java.sql.Connection;

public interface Connectible {
    Connection getConnection() throws Exception;
}
