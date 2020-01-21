package ru.epam.javacore.homework20200120.transportation.repo;

import ru.epam.javacore.homework20200120.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.homework20200120.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

import static ru.epam.javacore.homework20200120.storage.Storage.sizeTransportation;
import static ru.epam.javacore.homework20200120.storage.Storage.transportationArray;

public class TransportationArrayRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        sizeTransportation = ArrayUtils.addToArray(transportation, transportationArray, sizeTransportation, transportation.getClass());
    }

    @Override
    public List<Transportation> getAll() {
        if (sizeTransportation < transportationArray.length) {
            Transportation[] result = new Transportation[sizeTransportation];
            ArrayUtils.copyArray(transportationArray, result, sizeTransportation);
            return Arrays.asList(result);
        }
        return Arrays.asList(transportationArray);
    }

    @Override
    public Transportation getById(Long id) {
        return ArrayUtils.getByIdFromArray(id, transportationArray);
    }

    @Override
    public boolean update(Transportation transportation) {
        for (int i = 0; i < sizeTransportation; i++) {
            if (transportationArray[i].getId().equals(transportation.getId())) {
                transportationArray[i] = transportation;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteById(Long id) {
        return ArrayUtils.deleteByIdFromArray(id, transportationArray, sizeTransportation);
    }
}
