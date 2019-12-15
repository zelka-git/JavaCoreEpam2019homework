package homework20191213.carrier.repo;

import homework20191213.carrier.domain.Carrier;
import homework20191213.common.utils.ArrayUtils;
import homework20191213.storage.Storage;

public class CarrierRepoImpl extends Storage implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        if (Storage.carriers == null) {
            Storage.carriers = new Carrier[10];
        } else if (sizeCarrier == Storage.carriers.length) {
            Carrier[] newArrayCarrier = new Carrier[(int) (sizeCarrier * 1.5)];
            ArrayUtils.copyArray(Storage.carriers, newArrayCarrier);
            Storage.carriers = newArrayCarrier;
        }
        Storage.carriers[sizeCarrier] = carrier;
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
            return null;
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
    public Carrier remove(long id) {
        for (int i = 0; i < sizeCarrier; i++) {
            if (carriers[i] != null && carriers[i].getId() != null && carriers[i].getId().equals(id)) {
                Carrier removeElement = carriers[i];
                System.arraycopy(carriers, i + 1, carriers, i, carriers.length - (i + 1));
                carriers[carriers.length - 1] = null;
                sizeCarrier--;
                return removeElement;
            }
        }
        return null;
    }
}
