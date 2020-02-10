package ru.epam.javacore.homework20200207.common.solutions.utils;

import org.h2.command.ddl.CreateTable;
import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.common.business.connectionbd.ConnectionBdH2;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcBiConsumer;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcConsumer;
import ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces.JdbcFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static <T> int[] executeUpdate(String sql, List<T> elements, JdbcBiConsumer<PreparedStatement, T> psConsumer) {
        int[] executeBatch;
        try {
            Connection connection = null;
            PreparedStatement ps = null;

            try {
                connection = ConnectionBdH2
                        .getInstance().getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(sql);

                for (T item : elements) {
                    psConsumer.accept(ps, item);
                    ps.addBatch();
                }
                executeBatch = ps.executeBatch();
                connection.commit();
                return executeBatch;
            } catch (Exception e) {
                if (connection != null)
                    connection.rollback();
                throw new RuntimeException(e);
            } finally {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
