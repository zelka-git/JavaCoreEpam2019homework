package homework20191216.carrier.repo;

import homework20191216.carrier.domain.Carrier;
import homework20191216.common.utils.ArrayUtils;
import homework20191216.storage.IdGenerator;

import static homework20191216.storage.Storage.carriersArray;
import static homework20191216.storage.Storage.sizeCarrier;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void add(Carrier carrier) {
        if (sizeCarrier == carriersArray.length) {
            Carrier[] newArrayCarrier = new Carrier[(int) (sizeCarrier * 1.5)];
            ArrayUtils.copyArray(carriersArray, newArrayCarrier);
            carriersArray = newArrayCarrier;
        }
        carrier.setId(IdGenerator.generateId());
        carriersArray[sizeCarrier] = carrier;
        sizeCarrier++;
    }

    @Override
    public Carrier[] getAll() {
        if (sizeCarrier < carriersArray.length) {
            Carrier[] result = new Carrier[sizeCarrier];
            ArrayUtils.copyArray(carriersArray, result, sizeCarrier);
            return result;
        }
        return carriersArray;
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] arrayTheSameNames = new Carrier[sizeCarrier];
        int index = 0;
        for (Carrier item : carriersArray) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                arrayTheSameNames[index++] = item;
            }
        }
        if (index == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            Carrier[] result = new Carrier[index];
            ArrayUtils.copyArray(arrayTheSameNames, result, index);
            return result;
        }
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier item : carriersArray) {
            if (item != null && item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeCarrier; i++) {
            if (carriersArray[i] != null && carriersArray[i].getId() != null && carriersArray[i].getId().equals(id)) {
                System.arraycopy(carriersArray, i + 1, carriersArray, i, carriersArray.length - (i + 1));
                carriersArray[carriersArray.length - 1] = null;
                sizeCarrier--;
                return true;
            }
        }
        return false;
    }
}
