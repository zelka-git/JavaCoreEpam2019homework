package ru.epam.javacore.homework20191216.carrier.repo;

import main.homework20191216.carrier.domain.Carrier;
import main.homework20191216.storage.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static main.homework20191216.storage.Storage.carriersList;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carriersList.add(carrier);
    }

    @Override
    public Carrier[] getAll() {
        return carriersList.toArray(new Carrier[carriersList.size()]);
    }

    @Override
    public Carrier[] getByName(String name) {
        List<Carrier> result = new ArrayList<>();
        for (Carrier item : carriersList) {
            if (item.getName() != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return (Carrier[]) result.toArray();
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier item : carriersList) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        Iterator<Carrier> iter = carriersList.iterator();
        while (iter.hasNext()) {
            Carrier element = iter.next();
            if (element.getId() != null && element.getId().equals(id)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
