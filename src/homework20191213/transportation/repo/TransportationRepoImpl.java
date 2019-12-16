package homework20191213.transportation.repo;

import homework20191213.common.utils.ArrayUtils;
import homework20191213.storage.Storage;
import homework20191213.transportation.domain.Transportation;

import static homework20191213.storage.Storage.sizeTransportation;
import static homework20191213.storage.Storage.transportation;

public class TransportationRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        if (Storage.transportation == null) {
            Storage.transportation = new Transportation[10];
        } else if (sizeTransportation == Storage.transportation.length) {
            Transportation[] newArrayTrans = new Transportation[(int) (sizeTransportation * 1.5)];
            ArrayUtils.copyArray(Storage.transportation, newArrayTrans);
            Storage.transportation = newArrayTrans;
        }
        Storage.transportation[sizeTransportation] = transportation;
        sizeTransportation++;
    }

    @Override
    public Transportation[] getAll() {
        if (sizeTransportation < transportation.length) {
            Transportation[] result = new Transportation[sizeTransportation];
            ArrayUtils.copyArray(transportation, result, sizeTransportation);
            return result;
        }
        return transportation;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation item : transportation) {
            if (item != null && Long.valueOf(id).equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < sizeTransportation; i++) {
            if (transportation[i] != null && Long.valueOf(id).equals(transportation[i].getId())) {
                System.arraycopy(transportation, i + 1, transportation, i, transportation.length - (i + 1));
                transportation[transportation.length - 1] = null;
                sizeTransportation--;
                return true;
            }
        }
        return false;
    }

}
