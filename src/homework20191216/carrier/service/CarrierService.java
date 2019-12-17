package homework20191216.carrier.service;

import homework20191216.carrier.domain.Carrier;
import homework20191216.carrier.repo.CarrierRepo;

public interface CarrierService {
    void add(Carrier carrier, CarrierRepo typeStorage);

    Carrier[] getAll(CarrierRepo typeStorage);

    Carrier[] getByName(String name, CarrierRepo typeStorage);

    Carrier getById(Long id, CarrierRepo typeStorage);

    Carrier remove(Long id, CarrierRepo typeStorage);
}
