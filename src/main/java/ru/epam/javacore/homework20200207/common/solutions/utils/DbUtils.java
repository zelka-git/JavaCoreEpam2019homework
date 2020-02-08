package ru.epam.javacore.homework20200207.common.solutions.utils;

import ru.epam.javacore.homework20200207.common.business.connectionbd.ConnectionBdH2;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcBiConsumer;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcConsumer;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DbUtils {
    private DbUtils() {
    }

    public static <T> List<T> select(String sql, JdbcConsumer<PreparedStatement> psConsumer,
                                     JdbcFunction<ResultSet, T> rsConverter) {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                T element = rsConverter.apply(resultSet);
                result.add(element);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static int executeUpdate(String sql, JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps);
            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> int executeUpdate(String sql, T el, JdbcBiConsumer<PreparedStatement, T> psConsumer) {
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps, el);
            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
