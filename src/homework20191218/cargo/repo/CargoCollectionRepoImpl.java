package homework20191218.cargo.repo;

import homework20191218.cargo.domain.Cargo;
import homework20191218.storage.IdGenerator;

import java.util.*;

import static homework20191218.storage.Storage.cargoList;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargoList.add(cargo);
    }

    @Override
    public Cargo[] getAll() {
        return cargoList.toArray(new Cargo[cargoList.size()]);
    }

    @Override
    public Cargo[] getByName(String name) {
        List<Cargo> result = new ArrayList<>();
        for (Cargo item : cargoList) {
            if (item.getName() != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return (Cargo[]) result.toArray();
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo item : cargoList) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
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
    public void sort(Comparator comparator) {
        Collections.sort(cargoList, comparator);
    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Cargo> iter = cargoList.iterator();
        while (iter.hasNext()) {
            Cargo element = iter.next();
            if (element.getId() != null && element.getId().equals(id)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
