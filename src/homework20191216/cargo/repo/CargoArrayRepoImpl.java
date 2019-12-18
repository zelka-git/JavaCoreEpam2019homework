package homework20191216.cargo.repo;

import homework20191216.cargo.domain.Cargo;
import homework20191216.common.utils.ArrayUtils;
import homework20191216.storage.IdGenerator;
import homework20191216.storage.Storage;

import static homework20191216.storage.Storage.cargoArray;
import static homework20191216.storage.Storage.sizeCargo;

public class CargoArrayRepoImpl implements CargoRepo {

    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    @Override
    public void add(Cargo cargo) {
        if (sizeCargo == Storage.cargoArray.length) {
            Cargo[] newArrayCargo = new Cargo[(int) (sizeCargo * 1.5)];
            ArrayUtils.copyArray(Storage.cargoArray, newArrayCargo);
            Storage.cargoArray = newArrayCargo;
        }
        cargo.setId(IdGenerator.generateId());
        Storage.cargoArray[sizeCargo] = cargo;
        sizeCargo++;
    }

    @Override
    public Cargo[] getAll() {
        if (sizeCargo < cargoArray.length) {
            Cargo[] result = new Cargo[sizeCargo];
            ArrayUtils.copyArray(cargoArray, result, sizeCargo);
            return result;
        }
        return cargoArray;
    }

    @Override
    public Cargo[] getByName(String name) {
        Cargo[] arrayTheSameNames = new Cargo[sizeCargo];
        int index = 0;
        for (Cargo item : cargoArray) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                arrayTheSameNames[index++] = item;
            }
        }
        if (index == 0) {
            return EMPTY_CARGO_ARRAY;
        } else {
            Cargo[] result = new Cargo[index];
            ArrayUtils.copyArray(arrayTheSameNames, result, index);
            return result;
        }
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo item : cargoArray) {
            if (item != null && Long.valueOf(id).equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeCargo; i++) {
            if (cargoArray[i] != null && cargoArray[i].getId() != null && cargoArray[i].getId().equals(id)) {
                System.arraycopy(cargoArray, i + 1, cargoArray, i, cargoArray.length - (i + 1));
                cargoArray[cargoArray.length - 1] = null;
                sizeCargo--;
                return true;
            }
        }
        return false;
    }
}
