package homework20191225.cargo.repo;

import homework20191225.cargo.domain.Cargo;
import homework20191225.cargo.service.TypeSortCargo;
import homework20191225.common.solutions.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static homework20191225.storage.Storage.cargoList;

public class CargoCollectionRepoImpl implements CargoRepo {
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
        for (Cargo item : cargoList) {
            if (item.getName() != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Cargo getById(Long id) {
        return ListUtils.getByIdFromList(id, cargoList);
    }

    @Override
    public Cargo getByIdFetchingTransportations(long id) {
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
    public List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo) {
        List<Cargo> sortCargoList = new ArrayList<>(cargoList);
        switch (typeSortCargo) {
            case NAME:
                sortCargoList.sort(homework20191225.cargo.repo.CargoComparators.COMPARE_BY_NAME);
                break;
            case WEIGHT:
                sortCargoList.sort(homework20191225.cargo.repo.CargoComparators.COMPARE_BY_WEIGHT);
                break;
            case NAME_WEIGHT:
                sortCargoList.sort(homework20191225.cargo.repo.CargoComparators.COMPARE_BY_NAME.thenComparing(homework20191225.cargo.repo.CargoComparators.COMPARE_BY_WEIGHT));
                break;
            default:
                sortCargoList.sort(homework20191225.cargo.repo.CargoComparators.COMPARE_BY_WEIGHT.thenComparing(CargoComparators.COMPARE_BY_NAME));
        }
        return sortCargoList;
    }

    @Override
    public boolean deleteById(Long id) {
        return ListUtils.deleteByIdFromList(id, cargoList);
    }
}
