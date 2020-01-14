package main.homework20191213.carrier.repo;

import main.homework20191213.carrier.domain.Carrier;
import main.homework20191213.common.utils.ArrayUtils;

import static main.homework20191213.storage.Storage.carriers;
import static main.homework20191213.storage.Storage.sizeCarrier;

public class CarrierRepoImpl implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        if (carriers == null) {
            carriers = new Carrier[10];
        } else if (sizeCarrier == carriers.length) {
            Carrier[] newArrayCarrier = new Carrier[(int) (sizeCarrier * 1.5)];
            ArrayUtils.copyArray(carriers, newArrayCarrier);
            carriers = newArrayCarrier;
        }
        carriers[sizeCarrier] = carrier;
        sizeCarrier++;
    }

    @Override
    public Carrier[] getAll() {
        if (sizeCarrier < carriers.length) {
            Carrier[] result = new Carrier[sizeCarrier];
            ArrayUtils.copyArray(carriers, result, sizeCarrier);
            return result;
        }
        return carriers;
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] arrayTheSameNames = new Carrier[sizeCarrier];
        int index = 0;
        for (Carrier item : carriers) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                arrayTheSameNames[index++] = item;
            }
        }
        if (index == 0) {
            return new Carrier[0];
        } else {
            Carrier[] result = new Carrier[index];
            ArrayUtils.copyArray(arrayTheSameNames, result, index);
            return result;
        }
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier item : carriers) {
            if (item != null && item.getId() != null && item.getId().equals(id)) {
                return item;
            }

        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeCarrier; i++) {
            if (carriers[i] != null && carriers[i].getId() != null && carriers[i].getId().equals(id)) {
                System.arraycopy(carriers, i + 1, carriers, i, carriers.length - (i + 1));
                carriers[carriers.length - 1] = null;
                sizeCarrier--;
                return true;
            }
        }
        return false;
    }
}
