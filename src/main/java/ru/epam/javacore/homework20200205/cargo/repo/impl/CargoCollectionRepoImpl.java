package ru.epam.javacore.homework20200205.cargo.repo.impl;

import ru.epam.javacore.homework20200205.cargo.domain.Cargo;
import ru.epam.javacore.homework20200205.cargo.domain.CargoField;
import ru.epam.javacore.homework20200205.cargo.repo.CargoComparators;
import ru.epam.javacore.homework20200205.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.homework20200205.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200205.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200205.common.solutions.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.epam.javacore.homework20200205.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200205.common.solutions.search.OrderType.DESC;
import static ru.epam.javacore.homework20200205.storage.Storage.cargoList;

public class CargoCollectionRepoImpl extends CommonCargoRepo {
    @Override
    public void add(Cargo cargo) {
        ListUtils.addToList(cargo, cargoList);
    }

    @Override
    public List<Cargo> getAll() {
        return cargoList;
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> result = new ArrayList<>();
//        for (Cargo item : cargoList) {
//            if (Optional.ofNullable(item)
//                    .flatMap(cargoName -> Optional.ofNullable(cargoName.getName()))
//                    .map(e -> e.equals(name)).orElse(false)) {
//                result.add(item);
//            }
//        }
//        return result;

            result = cargoList.stream()
                    .filter(Objects::nonNull)
                    .filter(e -> e.getName() != null && e.equals(name))
                    .collect(Collectors.toList());
            return result;
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        return ListUtils.getByIdFromList(id, cargoList);
    }

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public boolean update(Cargo cargo) {
        for (int i = 0; i < cargoList.size(); i++) {
            if (cargoList.get(i).getId().equals(cargo.getId())) {
                cargoList.set(i, cargo);
                return true;
            }
        }
        return false;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

    @Override
    public List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo) {
        List<Cargo> sortCargoList = new ArrayList<>(cargoList);
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
//
//        return cargos;
//    }

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
    public boolean deleteById(Long id) {
        return ListUtils.deleteByIdFromList(id, cargoList);
    }
}
