package ru.epam.javacore.homework20200120.cargo.repo.impl;

import ru.epam.javacore.homework20200120.cargo.domain.Cargo;
import ru.epam.javacore.homework20200120.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200120.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200120.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200120.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ru.epam.javacore.homework20200120.storage.Storage.cargoArray;
import static ru.epam.javacore.homework20200120.storage.Storage.sizeCargo;

public class CargoArrayRepoImpl extends CommonCargoRepo {

    @Override
    public void add(Cargo cargo) {
        sizeCargo = ArrayUtils.addToArray(cargo, cargoArray, sizeCargo, cargo.getClass());
    }

    @Override
    public List<Cargo> getAll() {
        Cargo[] result = new Cargo[sizeCargo];
        ArrayUtils.copyArray(cargoArray, result, sizeCargo);
        return Arrays.asList(result);
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> result = new ArrayList<>();
        for (Cargo item : cargoArray) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Cargo getById(Long id) {
        return ArrayUtils.getByIdFromArray(id, cargoArray);
    }

    @Override
    public Cargo getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return ArrayUtils.deleteByIdFromArray(id, cargoArray, sizeCargo);
    }

    @Override
    public boolean update(Cargo cargo) {
        for (int i = 0; i < sizeCargo; i++) {
            if (cargoArray[i].getId().equals(cargo.getId())) {
                cargoArray[i] = cargo;
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo) {
        Cargo[] sortCargoArray = new Cargo[sizeCargo];
        ArrayUtils.copyArray(cargoArray, sortCargoArray, sizeCargo);
        switch (typeSortCargo) {
            case NAME:
                Arrays.sort(sortCargoArray, CargoComparators.COMPARE_BY_NAME);
                break;
            case WEIGHT:
                Arrays.sort(sortCargoArray, CargoComparators.COMPARE_BY_WEIGHT);
                break;
            case NAME_WEIGHT:
                Arrays.sort(sortCargoArray, CargoComparators.COMPARE_BY_NAME.thenComparing(CargoComparators.COMPARE_BY_WEIGHT));
                break;
            default:
                Arrays.sort(sortCargoArray, CargoComparators.COMPARE_BY_WEIGHT.thenComparing(CargoComparators.COMPARE_BY_NAME));
        }
        return Arrays.asList(sortCargoArray);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        List<Cargo> cargos = getAll();

        if (!cargos.isEmpty()) {
            if (searchCondition.needSorting()) {
                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
            }
        }

        return cargos;
    }

}
