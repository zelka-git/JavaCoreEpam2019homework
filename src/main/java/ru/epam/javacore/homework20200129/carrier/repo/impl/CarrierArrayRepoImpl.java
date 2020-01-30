package ru.epam.javacore.homework20200129.carrier.repo.impl;

import ru.epam.javacore.homework20200129.carrier.domain.Carrier;
import ru.epam.javacore.homework20200129.carrier.repo.CarrierRepo;
import ru.epam.javacore.homework20200129.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.homework20200129.storage.Storage.carriersArray;
import static ru.epam.javacore.homework20200129.storage.Storage.sizeCarrier;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final List<Carrier> EMPTY_CARRIER_LIST = new ArrayList<>();

    @Override
    public void add(Carrier carrier) {
        sizeCarrier = ArrayUtils.addToArray(carrier, carriersArray, sizeCarrier, carrier.getClass());
    }

    @Override
    public List<Carrier> getAll() {
        if (sizeCarrier < carriersArray.length) {
            Carrier[] result = new Carrier[sizeCarrier];
            ArrayUtils.copyArray(carriersArray, result, sizeCarrier);
            return Arrays.asList(result);
        }
        return Arrays.asList(carriersArray);
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Carrier> getByName(String name) {
        Carrier[] arrayTheSameNames = new Carrier[sizeCarrier];
        int index = 0;
        for (Carrier item : carriersArray) {
            if (item != null && item.getName() != null && item.getName().equals(name)) {
                arrayTheSameNames[index++] = item;
            }
        }
        if (index == 0) {
            return EMPTY_CARRIER_LIST;
        } else {
            Carrier[] result = new Carrier[index];
            ArrayUtils.copyArray(arrayTheSameNames, result, index);
            return Arrays.asList(result);
        }
    }

    @Override
    public Optional<Carrier> getById(Long id) {
        return ArrayUtils.getByIdFromArray(id, carriersArray);
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
    public boolean deleteById(Long id) {
        if(ArrayUtils.deleteByIdFromArray(id, carriersArray, sizeCarrier)){
            sizeCarrier--;
            return true;
        }
        return false;
    }
}
