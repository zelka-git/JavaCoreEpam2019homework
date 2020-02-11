package ru.epam.javacore.homework20200210.cargo.repo.dbimpl;

import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.domain.CargoField;
import ru.epam.javacore.homework20200210.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200210.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.homework20200210.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200210.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200210.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.homework20200210.common.solutions.utils.Mapper;

import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.homework20200210.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200210.common.solutions.search.OrderType.DESC;

public class CargoDbImpl extends CommonCargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Cargo> getByName(String name) {
        String sql = "select * from cargos where name = ?";
        return QueryWrapper.select(sql, ps -> ps.setString(1, name),
                Mapper::mapCargo);
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        String sql = "select * from cargos where id = ?";
        return QueryWrapper.selectOne(sql, ps -> ps.setLong(1, id),
                Mapper::mapCargo);
    }

    @Override
    public List<Cargo> getAll() {
        String sql = "select * from cargos";
        return QueryWrapper.select(sql, ps ->{},
                Mapper::mapCargo);
    }

    @Override
    public void add(Cargo element) {
        String sql;
        sql = "insert into CARGOS " +
                "(id,  name, weight, cargoType, size, material, description, expiratonDate, storeTemperature) " +
                "values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        QueryWrapper.executeUpdate(sql, element, Mapper::setCargo);
    }

    @Override
    public void addList(List<Cargo> cargos) {
        String sql;
        sql = "insert into CARGOS " +
                "(id,  name, weight, cargoType, size, material, description, expiratonDate, storeTemperature) " +
                "values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        QueryWrapper.executeUpdate(sql, cargos, Mapper::setCargo);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from cargos where id = ?;";
        return QueryWrapper.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
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
