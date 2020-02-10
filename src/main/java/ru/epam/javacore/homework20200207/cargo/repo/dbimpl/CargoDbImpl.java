package ru.epam.javacore.homework20200207.cargo.repo.dbimpl;

import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.cargo.domain.CargoField;
import ru.epam.javacore.homework20200207.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200207.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.homework20200207.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200207.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200207.common.solutions.utils.DbUtils;
import ru.epam.javacore.homework20200207.common.solutions.utils.Mapper;

import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.homework20200207.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200207.common.solutions.search.OrderType.DESC;

public class CargoDbImpl extends CommonCargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Cargo> getByName(String name) {
        String sql = "select * from cargos where name = ?";
        return DbUtils.select(sql, ps -> ps.setString(1, name),
                Mapper::mapCargo);
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        String sql = "select * from cargos where id = ?";
        List<Cargo> cargos = DbUtils.select(sql, ps -> ps.setLong(1, id),
                Mapper::mapCargo);
        if (cargos.size() > 0) {
            return Optional.of(cargos.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Cargo> getAll() {
        String sql = "select * from cargos";
        return DbUtils.select(sql, ps ->{},
                Mapper::mapCargo);
    }

    @Override
    public void add(Cargo element) {
        String sql;
        sql = "insert into CARGOS " +
                "(id,  name, weight, cargoType, size, material, description, expiratonDate, storeTemperature) " +
                "values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        DbUtils.executeUpdate(sql, element, Mapper::setCargo);
    }

    @Override
    public void addList(List<Cargo> cargos) {
        String sql;
        sql = "insert into CARGOS " +
                "(id,  name, weight, cargoType, size, material, description, expiratonDate, storeTemperature) " +
                "values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        DbUtils.executeUpdate(sql, cargos, Mapper::setCargo);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from cargos where id = ?;";
        return DbUtils.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
    }

    @Override
    public boolean update(Cargo element) {
        return false;
    }

    @Override
    public int countAll() {
        return getAll().size();
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

}
