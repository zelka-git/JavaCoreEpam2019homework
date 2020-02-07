package ru.epam.javacore.homework20200205.cargo.repo.dbImpl;

import ru.epam.javacore.homework20200205.cargo.domain.Cargo;
import ru.epam.javacore.homework20200205.cargo.domain.CargoField;
import ru.epam.javacore.homework20200205.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200205.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.homework20200205.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200205.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200205.common.business.connectionbd.ConnectionBdH2;
import ru.epam.javacore.homework20200205.storage.IdGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.homework20200205.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200205.common.solutions.search.OrderType.DESC;
import static ru.epam.javacore.homework20200205.common.solutions.utils.CargoUtils.getCargoByCargoType;
import static ru.epam.javacore.homework20200205.storage.Storage.cargoList;

public class CargoBdImpl extends CommonCargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> result = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();) {

            String sql = "select * from cargos where name = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            result = parseCargosFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private List<Cargo> parseCargosFromResultSet(ResultSet resultSet) throws SQLException {
        List<Cargo> cargos = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String nameCargo = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            String cargoType = resultSet.getString("cargoType");
            Cargo cargo = getCargoByCargoType(cargoType);
            cargo.setId(id);
            cargo.setName(nameCargo);
            cargo.setWeight(weight);

            cargos.add(cargo);
        }
        return cargos;
    }

    @Override
    public List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo) {
        List<Cargo> sortCargoList = getAll();
        switch (typeSortCargo) {
            case NAME:
                sortCargoList.sort(CargoComparators.COMPARE_BY_NAME);
                break;
            case WEIGHT:
                sortCargoList.sort(CargoComparators.COMPARE_BY_WEIGHT);
                break;
            case NAME_WEIGHT:
                sortCargoList.sort(CargoComparators.COMPARE_BY_NAME.thenComparing(CargoComparators.COMPARE_BY_WEIGHT));
                break;
            default:
                sortCargoList.sort(CargoComparators.COMPARE_BY_WEIGHT.thenComparing(CargoComparators.COMPARE_BY_NAME));
        }
        return sortCargoList;
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        List<Cargo> cargos = getAll();

        if (!cargos.isEmpty()) {
            if (searchCondition.needSorting()) {
                cargos.sort((c1, c2) -> {
                    int result = 0;
                    for (CargoField cargoField : searchCondition.getSortFields()) {
                        if (NAME.equals(cargoField)) {
                            result = c1.getName().compareTo(c2.getName());
                        } else {
                            result = Integer.compare(c1.getWeight(), c2.getWeight());
                        }
                        if (result != 0) break;
                    }
                    if (searchCondition.getOrderType().equals(DESC)) {
                        result *= (-1);
                    }
                    return result;
                });
            }
        }
        return cargos;
    }

    @Override
    public boolean deleteById(Long aLong) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();) {

            String sql = "delete from cargos where id = ?;";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, aLong);
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public void add(Cargo element) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();) {

            String sql = getSqlCommand(element);

            ps = connection.prepareStatement(sql);
            ps.setLong(1, IdGenerator.generateId() + 70);
            ps.setString(2, element.getName());
            ps.setInt(3, element.getWeight());
            ps.setString(4, element.getCargoType().toString());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getSqlCommand(Cargo element) {
        String sql = "";
        switch (element.getCargoType()) {
            case CLOTHES:
                sql = "insert into CARGOS " +
                        "(id,  name, weight, cargoType, size, material) " +
                        "values " +
                        "(?, ?, ?, ?, ?, ?);";
                break;
            case COMPUTERS:
                sql = "insert into CARGOS " +
                        "(id, name, weight, cargoType, description) " +
                        "values " +
                        "(?, ?, ?, ?, ?);";
                break;
            case FOOD:
                sql = "insert into CARGOS " +
                        "(id,  name, weight, cargoType, expiratonDate, storeTemperature) " +
                        "values " +
                        "(?, ?, ?, ?, ?, ?);";
                break;
        }
        return "insert into CARGOS " +
                "(id,  name, weight, cargoType) " +
                "values " +
                "(?, ?, ?, ?);";
    }

    @Override
    public List<Cargo> getAll() {
        List<Cargo> result = new ArrayList<>();
        Statement ps = null;
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();) {

            String sql = "select * from cargos";
            ps = connection.createStatement();

            ResultSet resultSet = ps.executeQuery(sql);

            result = parseCargosFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    public Optional<Cargo> getById(Long aLong) {
        List<Cargo> result = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection connection = ConnectionBdH2
                .getInstance().getConnection();) {

            String sql = "select * from cargos where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, aLong);
            ResultSet resultSet = ps.executeQuery();

            result = parseCargosFromResultSet(resultSet);
            if (result.size() > 0) {
                return Optional.of(result.get(0));
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Optional.empty();

    }

    @Override
    public boolean update(Cargo element) {
        return false;
    }
}
