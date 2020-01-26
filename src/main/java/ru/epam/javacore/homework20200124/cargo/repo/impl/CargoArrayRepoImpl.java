package ru.epam.javacore.homework20200124.cargo.repo.impl;

import org.junit.platform.commons.util.CollectionUtils;
import ru.epam.javacore.homework20200124.cargo.domain.Cargo;
import ru.epam.javacore.homework20200124.cargo.domain.CargoField;
import ru.epam.javacore.homework20200124.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200124.cargo.repo.CargoRepo;
import ru.epam.javacore.homework20200124.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.homework20200124.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200124.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200124.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ru.epam.javacore.homework20200124.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200124.common.solutions.search.OrderType.DESC;
import static ru.epam.javacore.homework20200124.storage.Storage.cargoArray;
import static ru.epam.javacore.homework20200124.storage.Storage.sizeCargo;

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
        if(ArrayUtils.deleteByIdFromArray(id, cargoArray, sizeCargo)){
            sizeCargo--;
            return true;
        }
        return false;
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

//    @Override
//    public List<Cargo> search(CargoSearchCondition searchCondition) {
//        List<Cargo> cargos = getAll();
//
//        if (!cargos.isEmpty()) {
//            if (searchCondition.needSorting()) {
//                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
//                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
//            }
//        }
//        return cargos;
//    }
//}

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
