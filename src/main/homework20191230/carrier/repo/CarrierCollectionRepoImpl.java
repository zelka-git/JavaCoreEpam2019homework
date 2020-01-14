package main.homework20191230.carrier.repo;

import main.homework20191230.carrier.domain.Carrier;
import main.homework20191230.common.solutions.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static main.homework20191230.storage.Storage.carriersList;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        ListUtils.addToList(carrier, carriersList);
    }

    @Override
    public List<Carrier> getAll() {
        return carriersList;
    }

    @Override
    public List<Carrier> getByName(String name) {
        List<Carrier> result = new ArrayList<>();
        for (Carrier item : carriersList) {
            if (item.getName() != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Carrier getById(Long id) {
        return ListUtils.getByIdFromList(id, carriersList);
    }

    @Override
    public boolean update(Carrier carrier) {
        for (int i = 0; i < carriersList.size(); i++) {
            if (carriersList.get(i).getId().equals(carrier.getId())) {
                carriersList.set(i, carrier);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return ListUtils.deleteByIdFromList(id, carriersList);
    }
}
