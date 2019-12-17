package homework20191216.transportation.repo;

import homework20191216.transportation.domain.Transportation;

import java.util.Iterator;
import java.util.LinkedList;

import static homework20191216.storage.Storage.transportationList;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        if(transportationList == null){
            transportationList = new LinkedList<>();
        }
        transportationList.add(transportation);
    }

    @Override
    public Transportation[] getAll() {
        return (Transportation[]) transportationList.toArray();
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation item : transportationList) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        Iterator<Transportation> iter = transportationList.iterator();
        while (iter.hasNext()) {
            Transportation element = iter.next();
            if (element.getId() != null && element.getId().equals(id)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
