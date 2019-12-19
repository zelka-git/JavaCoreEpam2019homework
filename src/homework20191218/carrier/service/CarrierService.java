package homework20191218.carrier.service;

import homework20191218.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(Long id);

    Carrier remove(Long id);

    boolean update(Carrier carrier);
}
