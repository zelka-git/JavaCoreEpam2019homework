package ru.epam.javacore.homework20200210.application.serviceholder;

import ru.epam.javacore.homework20200210.cargo.repo.dbimpl.CargoDbImpl;
import ru.epam.javacore.homework20200210.cargo.repo.impl.CargoArrayRepoImpl;
import ru.epam.javacore.homework20200210.cargo.repo.impl.CargoCollectionRepoImpl;
import ru.epam.javacore.homework20200210.cargo.service.CargoService;
import ru.epam.javacore.homework20200210.cargo.service.CargoServiceImpl;
import ru.epam.javacore.homework20200210.carrier.repo.dbimpl.CarrierDbImpl;
import ru.epam.javacore.homework20200210.carrier.repo.impl.CarrierArrayRepoImpl;
import ru.epam.javacore.homework20200210.carrier.repo.impl.CarrierCollectionRepoImpl;
import ru.epam.javacore.homework20200210.carrier.service.CarrierService;
import ru.epam.javacore.homework20200210.carrier.service.CarrierServiceImpl;
import ru.epam.javacore.homework20200210.common.business.datasource.HikariCpDataSource;
import ru.epam.javacore.homework20200210.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.homework20200210.storage.initor.relationaldb.DatabaseConfig;
import ru.epam.javacore.homework20200210.transportation.repo.dbimpl.TransportationDbImpl;
import ru.epam.javacore.homework20200210.transportation.repo.impl.TransportationArrayRepoImpl;
import ru.epam.javacore.homework20200210.transportation.repo.impl.TransportationCollectionRepoImpl;
import ru.epam.javacore.homework20200210.transportation.service.TransportationService;
import ru.epam.javacore.homework20200210.transportation.service.TransportationServiceImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ServiceHolder implements Serializable {

    private static final String DATABASE_CONFIG_PATH = "/ru/epam/javacore/homework20200210/db_config/config.properties";
    private static ServiceHolder instance = null;

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private ServiceHolder(StorageType storageType) throws Exception{
        switch (storageType) {
            case ARRAY:
                cargoService = new CargoServiceImpl(new CargoArrayRepoImpl());
                carrierService = new CarrierServiceImpl(new CarrierArrayRepoImpl());
                transportationService = new TransportationServiceImpl(new TransportationArrayRepoImpl());
                break;
            case RELATION_DB:
                prepareDataSourceConfig();
                cleanDb();

                cargoService = new CargoServiceImpl(new CargoDbImpl());
                carrierService = new CarrierServiceImpl(new CarrierDbImpl());
                transportationService = new TransportationServiceImpl(new TransportationDbImpl());
                break;
            default:
                cargoService = new CargoServiceImpl(new CargoCollectionRepoImpl());
                carrierService = new CarrierServiceImpl(new CarrierCollectionRepoImpl());
                transportationService = new TransportationServiceImpl(new TransportationCollectionRepoImpl());

        }
    }

    public static void initServiceHolder(StorageType storageType) throws Exception {
        ServiceHolder.instance = new ServiceHolder(storageType);
    }

    public static ServiceHolder getInstance() {
        return instance;
    }

    public CargoService getCargoService() {
        return cargoService;
    }

    public CarrierService getCarrierService() {
        return carrierService;
    }

    public TransportationService getTransportationService() {
        return transportationService;
    }

    private Object readResolve() {
        return instance;
    }

    private void prepareDataSourceConfig() throws Exception {
        HikariCpDataSource.HikariCpDataSourceBuilder hikariCpDataSourceBuilder
                = new HikariCpDataSource.HikariCpDataSourceBuilder();
        Map<DatabaseConfig, String> dbConfigs = readDbConfigFromResources();

        dbConfigs.forEach((param, value) -> {
            switch (param) {

                case URL: {
                    hikariCpDataSourceBuilder.appendUrl(value);
                    break;
                }
                case USER: {
                    hikariCpDataSourceBuilder.appendUserName(value);
                    break;
                }
                case PASSWORD: {
                    hikariCpDataSourceBuilder.appendPassword(value);
                    break;
                }

                case DRIVER: {
                    hikariCpDataSourceBuilder.appendDriver(value);
                    break;
                }
            }
        });
        HikariCpDataSource.init(hikariCpDataSourceBuilder);
    }

    private Map<DatabaseConfig, String> readDbConfigFromResources() throws Exception {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream(DATABASE_CONFIG_PATH));

        Map<DatabaseConfig, String> result = new HashMap<>();
        Arrays.stream(DatabaseConfig.values()).forEach(dbConfig ->
                result.put(dbConfig, props.getProperty(dbConfig.getPropName())));

        return result;
    }

    private void cleanDb() throws Exception {
        Connection connection = null;
        boolean connectionAutoCommitFlag = false;
        try {
            connection = HikariCpDataSource.getInstance().getConnection();
            connectionAutoCommitFlag = connection.getAutoCommit();
            connection.setAutoCommit(false);
            QueryWrapper.executeUpdate("DELETE FROM TRANSPORTATIONS", connection);
            QueryWrapper.executeUpdate("DELETE FROM CARGOS", connection);
            QueryWrapper.executeUpdate("DELETE FROM CARRIERS", connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(connectionAutoCommitFlag);
                connection.close();
            }
        }

    }
}
