package homework20191216.transportation.repo;

import homework20191216.common.utils.ArrayUtils;
import homework20191216.storage.IdGenerator;
import homework20191216.storage.Storage;
import homework20191216.transportation.domain.Transportation;

import static homework20191216.storage.Storage.sizeTransportation;
import static homework20191216.storage.Storage.transportationArray;

public class TransportationArrayRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        if (sizeTransportation == Storage.transportationArray.length) {
            Transportation[] newArrayTrans = new Transportation[(int) (sizeTransportation * 1.5)];
            ArrayUtils.copyArray(Storage.transportationArray, newArrayTrans);
            Storage.transportationArray = newArrayTrans;
        }
        transportation.setId(IdGenerator.generateId());
        Storage.transportationArray[sizeTransportation] = transportation;
        sizeTransportation++;
    }

    @Override
    public Transportation[] getAll() {
        if (sizeTransportation < transportationArray.length) {
            Transportation[] result = new Transportation[sizeTransportation];
            ArrayUtils.copyArray(transportationArray, result, sizeTransportation);
            return result;
        }
        return transportationArray;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation item : transportationArray) {
            if (item != null && Long.valueOf(id).equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeTransportation; i++) {
            if (transportationArray[i] != null && Long.valueOf(id).equals(transportationArray[i].getId())) {
                System.arraycopy(transportationArray, i + 1, transportationArray, i, transportationArray.length - (i + 1));
                transportationArray[transportationArray.length - 1] = null;
                sizeTransportation--;
                return true;
            }
        }
        return false;
    }

}
