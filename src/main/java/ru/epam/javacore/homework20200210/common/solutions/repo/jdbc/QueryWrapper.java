package ru.epam.javacore.homework20200210.common.solutions.repo.jdbc;

import ru.epam.javacore.homework20200210.common.business.datasource.HikariCpDataSource;
import ru.epam.javacore.homework20200210.common.business.exception.unchecked.SqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryWrapper {

    private static final int BATCH_EXECUTE_THRESHOLD = 10;

    public static <T> List<T> select(String sql, JdbcConsumer<PreparedStatement> psConsumer,
                                     JdbcFunction<ResultSet, T> rsConverter) {
        List<T> result = new ArrayList<>();
        try (Connection connection = HikariCpDataSource
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

    public static <T> Optional<T> selectOne(String sql, JdbcConsumer<PreparedStatement> psConsumer,
                                            JdbcFunction<ResultSet, T> rsConverter) {
        List<T> result = new ArrayList<>();
        try (Connection connection = HikariCpDataSource
                .getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return Optional.of(rsConverter.apply(resultSet));
            }else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> int[] executeUpdate(String sql, List<T> elements,
                                          JdbcBiConsumer<PreparedStatement, T> psConsumer) {
        int[] executeBatch;
        try {
            Connection connection = null;
            PreparedStatement ps = null;

            try {
                connection = HikariCpDataSource
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

    public static int executeUpdate(String sql, JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps);
            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> int executeUpdate(String sql, T el, JdbcBiConsumer<PreparedStatement, T> psConsumer) {
        try (Connection connection = HikariCpDataSource
                .getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            psConsumer.accept(ps, el);
            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdate(String sql, Connection connection) throws SqlException {
        try {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }
}
