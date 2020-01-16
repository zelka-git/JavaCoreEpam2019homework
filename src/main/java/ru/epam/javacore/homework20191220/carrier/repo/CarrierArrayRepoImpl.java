package ru.epam.javacore.homework20191220.carrier.repo;

import ru.epam.javacore.homework20191220.carrier.domain.Carrier;
import ru.epam.javacore.homework20191220.common.utils.ArrayUtils;
import ru.epam.javacore.homework20191220.storage.IdGenerator;

import static ru.epam.javacore.homework20191220.storage.Storage.carriersArray;
import static ru.epam.javacore.homework20191220.storage.Storage.sizeCarrier;

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
    public boolean update(Carrier carrier) {
        for (int i = 0; i < sizeCarrier; i++) {
            if (carriersArray[i].getId().equals(carrier.getId())) {
                carriersArray[i] = carrier;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        for (int i = 0; i < sizeCarrier; i++) {
            try {
                if (carriersArray[i].getId().equals(id)) {
                    System.arraycopy(carriersArray, i + 1, carriersArray, i, carriersArray.length - (i + 1));
                    carriersArray[carriersArray.length - 1] = null;
                    sizeCarrier--;
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }
}
