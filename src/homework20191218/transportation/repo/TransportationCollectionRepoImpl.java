package homework20191218.transportation.repo;

import homework20191218.storage.IdGenerator;
import homework20191218.transportation.domain.Transportation;

import java.util.Iterator;

import static homework20191218.storage.Storage.transportationList;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationList.add(transportation);
    }

    @Override
    public Transportation[] getAll() {
        return transportationList.toArray(new Transportation[transportationList.size()]);
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
    public boolean update(Transportation transportation) {
        for (int i = 0; i < transportationList.size(); i++) {
            if (transportationList.get(i).getId().equals(transportation.getId())) {
                transportationList.set(i, transportation);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
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
