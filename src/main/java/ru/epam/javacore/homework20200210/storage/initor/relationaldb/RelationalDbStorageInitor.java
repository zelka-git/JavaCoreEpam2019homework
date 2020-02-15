package ru.epam.javacore.homework20200210.storage.initor.relationaldb;

import ru.epam.javacore.homework20200210.common.business.datasource.HikariCpDataSource;
import ru.epam.javacore.homework20200210.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.homework20200210.storage.initor.StorageInitor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelationalDbStorageInitor implements StorageInitor {
    private static final String DDL_SCRIPT_PATH = "/ru/epam/javacore/homework20200210/db_config/create-schema.sql";
    private static final String DML_SCRIPT_PATH = "/ru/epam/javacore/homework20200210/db_config/init-data.sql";

    @Override
    public void initStorage() throws InitStorageException {
        try {
            createDataBaseStructure();
            fillDataBaseWithData();
        } catch (Exception e) {
            throw new InitStorageException(e.getMessage());
        }
    }

    private void createDataBaseStructure() throws Exception {
        List<String> ddl = Files
                .readAllLines(Paths.get(this.getClass().getResource(DDL_SCRIPT_PATH).toURI()));

        try (Connection con = HikariCpDataSource.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(String.join("", ddl));
        }
    }

    private void fillDataBaseWithData() throws Exception {
        List<String> sqls = prepareSqlsToInitData();
        if (!sqls.isEmpty()) {
            for (String sql : sqls) {
                execSql(sql);
            }
        }
    }

    private List<String> prepareSqlsToInitData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files
                .lines(Paths.get(this.getClass().getResource(DML_SCRIPT_PATH).toURI()))) {
            return stream.map(line -> {
                stringBuilder.append(line);
                if (line.endsWith(";")) {
                    String sql = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    return sql;
                } else if (line.startsWith("-")) {
                    stringBuilder.setLength(0);
                    return null;
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
    }

    private void execSql(String sql) throws Exception {
        try (Connection con = HikariCpDataSource.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(sql);
        }
    }

}
