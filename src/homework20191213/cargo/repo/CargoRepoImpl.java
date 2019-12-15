package homework20191213.cargo.repo;

import homework20191213.cargo.domain.Cargo;
import homework20191213.common.utils.ArrayUtils;
import homework20191213.storage.Storage;

import javax.swing.text.Utilities;

public class CargoRepoImpl extends Storage implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        if (Storage.cargo == null) {
            Storage.cargo = new Cargo[10];
        } else if (sizeCargo == Storage.cargo.length) {
            Cargo[] newArrayCargo = new Cargo[(int) (sizeCargo * 1.5)];
            ArrayUtils.copyArray(Storage.cargo, newArrayCargo);
            Storage.cargo = newArrayCargo;
        }
        Storage.cargo[sizeCargo] = cargo;
        sizeCargo++;
    }

    @Override
    public Cargo[] getAll() {
        if (sizeCargo < cargo.length) {
            Cargo[] result = new Cargo[sizeCargo];
            ArrayUtils.copyArray(cargo, result, sizeCargo);
            return result;
        }
        return cargo;
    }

    @Override
    public Cargo[] getByName(String name) {
        Cargo[] arrayTheSameNames = new Cargo[sizeCargo];
        int index = 0;
        for (Cargo item : cargo) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                arrayTheSameNames[index++] = item;
            }
        }
        if (index == 0) {
            return new Cargo[0];
        } else {
            Cargo[] result = new Cargo[index];
            ArrayUtils.copyArray(arrayTheSameNames, result, index);
            return result;
        }
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo item : cargo) {
            if (item != null && Long.valueOf(id).equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeCargo; i++) {
            if (cargo[i] != null && cargo[i].getId() != null && cargo[i].getId().equals(id)) {
                System.arraycopy(cargo, i + 1, cargo, i, cargo.length - (i + 1));
                cargo[cargo.length - 1] = null;
                sizeCargo--;
                return true;
            }
        }
        return false;
    }
}
