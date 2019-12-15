package homework20191213.carrier.repo;

import homework20191213.carrier.domain.Carrier;

public interface CarrierRepo {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(long id);

    boolean remove(long id);
}
