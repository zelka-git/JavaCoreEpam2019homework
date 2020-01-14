package main.homework20191213.carrier.service;

import main.homework20191213.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(long id);

    Carrier remove(long id);
}
